package sample;

import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartMain extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("2222222222222222222222222");
        GUIState.setStage(primaryStage);
        GUIState.setHostServices(this.getHostServices());
        showInitialView();
    }
}
