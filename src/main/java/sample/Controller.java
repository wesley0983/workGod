package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {


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
