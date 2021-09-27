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
import sample.entity.response.Report;
import sample.jfxsupport.FXMLController;
import sample.service.CompanyService;

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
    private ComboBox<?> menuCompany;

    @FXML
    private TextField textProduct;

    @FXML
    private Button addBtnProduct;

    @Autowired
    private CompanyService companyService;

    @FXML
    private void initialize() {
        ObservableList<Report> data = FXCollections.observableArrayList(companyService.init());
//        companyName.setCellValueFactory(new PropertyValueFactory<Report,String>("companyName"));
//        productName.setCellValueFactory(new PropertyValueFactory<Report,String>("productName"));

        reportView.setEditable(true);
        reportView.setItems(data);

//        tableView.setItems((ObservableList<Report>) init);
    }

    @FXML
    void addCompany(ActionEvent event) {
        String companyText = textCompany.getText();
        if (StringUtils.isNotEmpty(companyText)){
            logger.debug(textCompany.getText());
            companyService.add(companyText);
        }
    }

    @FXML
    void addProduct(ActionEvent event) {
        System.out.println("test2~~");
    }
}
