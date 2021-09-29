package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sample.entity.po.Product;
import sample.entity.response.Owner;
import sample.entity.response.Report;
import sample.jfxsupport.FXMLController;
import sample.service.CompanyService;
import sample.service.ProductService;

import java.util.List;

@FXMLController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @FXML
    private TextField textCompany;

    @FXML
    private Button addBtnCompany;

    @FXML
    private TableView<Report> reportView;

    @FXML
    private TableColumn<Report, String> companyName;

    @FXML
    private TableColumn<Report, String> productName;

    @FXML
    private ComboBox<Owner> menuCompany;

    @FXML
    private TextField textProduct;

    @FXML
    private Button addBtnProduct;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ProductService productService;

    ObservableList<Report> reportData = FXCollections.observableArrayList();
    ObservableList<Owner> companyData = FXCollections.observableArrayList();


    /**
     * 初始化
     * */
    @FXML
    private void initialize() {
        logger.debug("initialize table star...");
        companyName.setCellValueFactory(new PropertyValueFactory<Report,String>("companyName"));
        companyName.setCellFactory(TextFieldTableCell.forTableColumn());
        companyName.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Report, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Report, String> t) {
                        String companyOldName = t.getTableView().getItems().get(t.getTablePosition().getRow()).getCompanyName();
                        companyService.editCompany(companyOldName,t.getNewValue());
                    }
                }
        );

        productName.setCellValueFactory(new PropertyValueFactory<Report,String>("productName"));
        productName.setCellFactory(TextFieldTableCell.forTableColumn());
        productName.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Report, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Report, String> t) {
                        String productOld = t.getTableView().getItems().get(t.getTablePosition().getRow()).getProductName();
                        String companyOldName = t.getTableView().getItems().get(t.getTablePosition().getRow()).getCompanyName();
                        productService.editProduct(companyOldName,productOld,t.getNewValue());
                    }
                }
        );

        //表格
        List<Report> reportList = companyService.init();
        reportData.addAll(reportList);
        reportView.setItems(reportData);
        reportView.setEditable(true);

        //下拉選單
        List<Owner> companyList = companyService.findCompanyAll();
        companyData.addAll(companyList);
        menuCompany.setItems(companyData);
        menuCompany.setEditable(true);

    }

    /**
     * 新增廠商
     * */
    @FXML
    void addCompany(ActionEvent event) {
        String companyText = textCompany.getText();
        if (StringUtils.isNotEmpty(companyText)){
            logger.debug(textCompany.getText());
            Report report = companyService.addCompany(companyText);
            reportData.add(report);
            companyData.add(new Owner(report));
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

            for (Report report:reportData){
                if (report.getCompanyName().equals(comboboxValue)){
                    report.setProductName(product.getName());
                }
            }
            reportView.refresh();
        }
    }

    @FXML
    void updateTable(ActionEvent event) {
        System.out.println(menuCompany.getValue());
    }
}
