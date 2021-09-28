package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sample.entity.po.Company;
import sample.entity.response.Owner;
import sample.entity.response.Report;
import sample.jfxsupport.FXMLController;
import sample.service.CompanyService;
import sample.service.ProductService;

import java.util.Collections;
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

    @FXML
    private void initialize() {
        logger.debug("initialize table star...");
        companyName.setCellValueFactory(new PropertyValueFactory<Report,String>("companyName"));
        productName.setCellValueFactory(new PropertyValueFactory<Report,String>("productName"));
        List<Report> reportList = companyService.init();
        reportData.addAll(reportList);

        reportView.setEditable(true);
        reportView.setItems(reportData);


//        companyData.add(owner);
        menuCompany.setItems(companyData);

    }

    @FXML
    void addCompany(ActionEvent event) {
        String companyText = textCompany.getText();
        if (StringUtils.isNotEmpty(companyText)){
            logger.debug(textCompany.getText());
            Report report = companyService.add(companyText);
            reportData.add(report);
            textCompany.clear();
        }
    }

    @FXML
    void addProduct(ActionEvent event) {
        System.out.println("test2~~");
        String productText = textProduct.getText();

        if (StringUtils.isNotEmpty(productText)){
            productService.add(productText);
        }
    }
}
