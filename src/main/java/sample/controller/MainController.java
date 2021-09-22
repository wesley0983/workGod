package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.jfxsupport.FXMLController;

@FXMLController
public class MainController {


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

    @FXML
    void addCompany(ActionEvent event) {
        System.out.println("test~~");
    }

    @FXML
    void addProduct(ActionEvent event) {
        System.out.println("test~~");
    }
}
