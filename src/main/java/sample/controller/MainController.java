package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import sample.controller.view.CompanyView;
import sample.entity.po.MenuItem;
import sample.jfxsupport.FXMLController;
import sample.service.MenuItemService;

import java.util.List;
import java.util.Objects;

@FXMLController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private static String ADDITIONAL_TAB_TITLE = "     ";

    @FXML
    private SplitPane mainSplitPanel;

    @FXML
    private TabPane tabPane;

    @FXML
    private Button addBtnCompany;

    @FXML
    private Button addBtnProduct;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private MenuItemService menuItemService;



    TreeView<MenuItem> treeView;

    /**
     * 初始化
     * */
    @FXML
    private void initialize() {
        logger.debug("initialize table star...");

        MenuItem menuRoot = menuItemService.findMenuRoot();
        TreeItem<MenuItem> rootNode = new TreeItem<>();
        rootNode.setValue(menuRoot);
        rootNode.setExpanded(menuRoot.getExpanded());
        treeView = new TreeView<>();
        treeView.setRoot(rootNode);
        buildTreeItems(rootNode);
        mainSplitPanel.getItems().add(0, treeView);
        treeView.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                onSelectItemAction(treeView);
            }
        });

    }

    private void buildTreeItems(TreeItem<MenuItem> parentNode) {
        int parentId = -1;
        if (parentNode.getValue() != null) {
            parentId = parentNode.getValue().getId();
        }
        List<MenuItem> menuItems = menuItemService.getMenuItemByParent(parentId);
        for (MenuItem item : menuItems) {
            TreeItem<MenuItem> itemNode = new TreeItem<>();
            itemNode.setValue(item);
            itemNode.setExpanded(item.getExpanded());
//            if (!item.getImage().isEmpty()) {
//                itemNode.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/" + item.getImage()))));
//            }
            parentNode.getChildren().add(itemNode);
            buildTreeItems(itemNode);
        }
    }

    private void onSelectItemAction(TreeView<MenuItem> treeView) {
        TreeItem<MenuItem> item = treeView.getSelectionModel().getSelectedItem();

        if (Objects.isNull(item))
            return;
        int tabIndex = findTabIndex(item.getValue().getBarName());
        System.out.println("按下"+tabIndex+"---");
        if (tabIndex == -1) {
            showItemContent(item.getValue());
        } else {
            tabPane.getSelectionModel().select(tabIndex);
        }
    }

    private void showItemContent(MenuItem menuItem) {
        CompanyView gridView = context.getBean(CompanyView.class);
        CompanyController controller = context.getBean(CompanyController.class);
        gridView.setController(controller);

        Tab tab = new Tab(menuItem.getBarName() + ADDITIONAL_TAB_TITLE);
        tab.setContent(gridView.getView());
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tabPane.getTabs().size() - 1);

        controller.initCompany();
    }

    private int findTabIndex(String title) {
        for (int i = 0; i < tabPane.getTabs().size(); i++) {
            Tab tab = tabPane.getTabs().get(i);
            if (tab.getText().equals(title + ADDITIONAL_TAB_TITLE)) {
                return i;
            }
        }
        return -1;
    }





}
