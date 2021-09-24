package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ComboBox<?> menuCompany;

    @FXML
    private TextField textProduct;

    @FXML
    private Button addBtnProduct;

    @Autowired
    private CompanyService companyService;

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
