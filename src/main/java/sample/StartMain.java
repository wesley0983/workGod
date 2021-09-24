package sample;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import sample.controller.view.MainView;
import sample.jfxsupport.AbstractFxmlView;
import sample.jfxsupport.GUIState;

import java.sql.SQLException;

@SpringBootApplication
public class StartMain extends javafx.application.Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartMain.class);
    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        System.out.println("111111111111111111111111111");
        launch(args);
    }

    /**
     * 手動注入tcp與web的控制器方法1
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2ServerTcp() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2ServerWeb() throws SQLException {
        return Server.createWebServer("-web", "-tcpAllowOthers", "-webPort", "8080");
    }

    @Override
    public void init() throws Exception {
        System.out.println("33333333333333333333333");
        SpringApplicationBuilder builder = new SpringApplicationBuilder(StartMain.class);
        builder.application().setWebApplicationType(WebApplicationType.NONE);
        applicationContext = builder.run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("2222222222222222222222222");
        GUIState.setStage(primaryStage);
        GUIState.setHostServices(this.getHostServices());
        showInitialView();
    }

    private void showInitialView() {
        final String stageStyle = applicationContext.getEnvironment().getProperty("javafx.stage.style");
        System.out.println("444444444444"+ stageStyle);
//        if (stageStyle != null) {
//            GUIState.getStage().initStyle(StageStyle.valueOf(stageStyle.toUpperCase()));
//        } else {
//            GUIState.getStage().initStyle(StageStyle.DECORATED);
//        }
        showView(MainView.class);
    }

    public static void showView(final Class<? extends AbstractFxmlView> newView) {
        try {
            final AbstractFxmlView view = applicationContext.getBean(newView);
            System.out.println("555555555555"+view.getView());
            if (GUIState.getScene() == null) {
                GUIState.setScene(new Scene(view.getView()));
            } else {
                GUIState.getScene().setRoot(view.getView());
            }
            GUIState.getStage().setScene(GUIState.getScene());

//            GUIState.getStage().getIcons().addAll(icons);

            GUIState.getStage().addEventHandler(WindowEvent.WINDOW_SHOWN, e -> {
//                if (view.getFxmlLoader().getController() instanceof MainController) {
//                    MainController mainController = (MainController) view.getFxmlLoader().getController();
//                    mainController.onWindowShownEvent();
//                }
                LOGGER.debug("Stage view shown: {} ", view.getClass());
            });
            GUIState.getStage().show();
        } catch (Throwable t) {
            LOGGER.error("Failed to load application: ", t);
            showErrorAlert(t);
        }
    }

    @Override
	public void stop() throws Exception {
		applicationContext.close();
	}

    private static void showErrorAlert(Throwable throwable) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Oops! An unrecoverable error occurred.\n"  + "Please contact your software vendor.\n\n" + "The application will stop now.");
        alert.showAndWait().ifPresent(response -> Platform.exit());
    }
}
