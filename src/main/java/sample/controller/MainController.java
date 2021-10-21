package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import sample.controller.view.CompanyView;
import sample.entity.po.MenuItem;
import sample.entity.po.Product;
import sample.entity.response.Owner;
import sample.entity.response.VendorReport;
import sample.jfxsupport.FXMLController;
import sample.service.CompanyService;
import sample.service.MenuItemService;
import sample.service.ProductService;
import sample.service.impl.MenuItemServiceImpl;

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
    private TextField textCompany;

    @FXML
    private Button addBtnCompany;

    @FXML
    private TableView<VendorReport> vendorReportView;

    @FXML
    private TableColumn<VendorReport, String> companyName;

    @FXML
    private TableColumn<VendorReport, String> productName;

    @FXML
    private ComboBox<Owner> menuCompany;

    @FXML
    private TextField textProduct;

    @FXML
    private Button addBtnProduct;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MenuItemService menuItemService;

    ObservableList<VendorReport> vendorReportData = FXCollections.observableArrayList();
    ObservableList<Owner> companyData = FXCollections.observableArrayList();

    TreeView<MenuItem> treeView;

    /**
     * 初始化
     * */
    @FXML
    private void initialize() {
        logger.debug("initialize table star...");
//        List<MenuItem> menuItemList = menuItemService.findMenuItemList();
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
                System.out.println("tttttttttttttttttttttttttt");
                onSelectItemAction(treeView);
            }
        });



        //初始化-欄位(欄位監聽修改)
        companyName.setCellValueFactory(new PropertyValueFactory<VendorReport,String>("companyName"));
        companyName.setCellFactory(TextFieldTableCell.forTableColumn());
        companyName.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<VendorReport, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<VendorReport, String> t) {
                        String companyOldName = t.getTableView().getItems().get(t.getTablePosition().getRow()).getCompanyName();
                        companyService.editCompany(companyOldName,t.getNewValue());
                    }
                }
        );
        //初始化-欄位(欄位監聽修改)
        productName.setCellValueFactory(new PropertyValueFactory<VendorReport,String>("productName"));
        productName.setCellFactory(TextFieldTableCell.forTableColumn());
        productName.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<VendorReport, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<VendorReport, String> t) {
                        String productOld = t.getTableView().getItems().get(t.getTablePosition().getRow()).getProductName();
                        String companyOldName = t.getTableView().getItems().get(t.getTablePosition().getRow()).getCompanyName();
                        productService.editProduct(companyOldName,productOld,t.getNewValue());
                    }
                }
        );

        //表格
        List<VendorReport> vendorReportList = companyService.init();
        vendorReportData.addAll(vendorReportList);
        vendorReportView.setItems(vendorReportData);
        vendorReportView.setEditable(true);

        //下拉選單
        List<Owner> companyList = companyService.findCompanyAll();
        companyData.addAll(companyList);
        menuCompany.setItems(companyData);
        menuCompany.setEditable(true);

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
        System.out.println("按下----------------------"+item.getValue());
        if (Objects.isNull(item))
            return;
        int tabIndex = findTabIndex(item.getValue().getValue());
        if (tabIndex == -1) {
//            tabPane.getTabs().contains((Object) item.getValue());
            showItemContent(item.getValue());
        } else {
            tabPane.getSelectionModel().select(tabIndex);
        }
    }

    private void showItemContent(MenuItem menuItem) {

        System.out.println("123");

        CompanyView gridView = (CompanyView) context.getBean(CompanyView.class);
        CompanyController controller = (CompanyController) context.getBean(CompanyController.class);
        gridView.setController(controller);

        Tab tab = new Tab(menuItem.getValue() + ADDITIONAL_TAB_TITLE);
        tab.setContent(gridView.getView());
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tabPane.getTabs().size() - 1);


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

    /**
     * 新增廠商
     * */
    @FXML
    void addCompany(ActionEvent event) {
        String companyText = textCompany.getText();
        if (StringUtils.isNotEmpty(companyText)){
            logger.debug(textCompany.getText());
            VendorReport vendorReport = companyService.addCompany(companyText);
            vendorReportData.add(vendorReport);
            companyData.add(new Owner(vendorReport));
            textCompany.clear();
        }
    }

    /**
     * 新增廠商尺寸
     * */
    @FXML
    void addProduct(ActionEvent event) {
        System.out.println("test2~~");
        String productText = textProduct.getText();
        String comboboxValue = String.valueOf(menuCompany.getValue());


        if (StringUtils.isNotBlank(productText) && StringUtils.isNotEmpty(comboboxValue)){
            Product product = productService.addProduct(productText, comboboxValue);

            for (VendorReport vendorReport : vendorReportData){
                if (vendorReport.getCompanyName().equals(comboboxValue)){
                    vendorReport.setProductName(product.getName());
                }
            }
            vendorReportView.refresh();
        }
    }

    @FXML
    void updateTable(ActionEvent event) {
        System.out.println(menuCompany.getValue());
    }
}
