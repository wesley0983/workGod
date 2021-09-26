package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import sample.controller.view.CustomerGridDef;
import sample.controller.view.MainView;
import sample.entity.po.SimpleEntity;
import sample.entity.response.Report;
import sample.jfxsupport.FXMLController;
import sample.jfxsupport.PrototypeController;
import sample.service.CompanyService;
import javafx.fxml.Initializable;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@FXMLController
@Scope("prototype")
public class MainController implements PrototypeController{

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @FXML
    private TextField textCompany;

    @FXML
    private Button addBtnCompany;

    @FXML
    private TableView<Report> tableView;

    @FXML
    private ComboBox<?> menuCompany;

    @FXML
    private TextField textProduct;

    @FXML
    private Button addBtnProduct;

    @Autowired
    private ApplicationContext context;

//    private final ObservableList<Report> data = FXCollections.observableArrayList();


    @Autowired
    private CompanyService companyService;

    @FXML
    private void initialize() {
        MainView mainView = context.getBean(MainView.class);
        MainController mainController = context.getBean(MainController.class);
        mainView.setController(mainController);
        setupGrid();

//        System.out.println(this);
//        List<Report> init = companyService.init();
//        Report report = new Report();
//        report.setCompanyName("a");
//        report.setProductName("b");
//        data.add(report);

        ObservableList<Report> data = FXCollections.observableArrayList(companyService.init());

        tableView.setEditable(true);
        tableView.setItems(data);

//        tableView.setItems((ObservableList<Report>) init);
    }

    private void setupGrid() {
        CustomerGridDef gridDef = context.getBean(CustomerGridDef.class);
        List<String> columnNames = gridDef.getColumnNames();
        List<String> columnDataNames = gridDef.getColumnDataName();
        List<Integer> columnSizes = gridDef.getColumnSizes();

        for (int i = 0; i < gridDef.getColumnNames().size(); i++) {
            TableColumn<Report, String> column = new TableColumn<>(columnNames.get(i));
            column.setCellValueFactory(
                    new PropertyValueFactory<Report, String>(columnDataNames.get(i))
            );
            column.setMinWidth(columnSizes.get(i));
            tableView.getColumns().add(column);
        }

    }

        @FXML
    public void addCompany(ActionEvent event) {
        String companyText = textCompany.getText();
        if (StringUtils.isNotEmpty(companyText)){
            logger.debug(textCompany.getText());
            companyService.add(companyText);
        }
    }

    @FXML
    public void addProduct(ActionEvent event) {
        System.out.println("test2~~");
    }

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        tableView.setEditable(true);
//        tableView.setItems(data);
//    }
}
