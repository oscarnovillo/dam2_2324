package ui.pantallas.principal;

import domain.modelo.Usuario;
import domain.usecases.LoginUseCaseImpl;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import ui.pantallas.login.LoginController;
import ui.pantallas.login.LoginViewModel;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(ApplicationExtension.class)
class PrincipalControllerTest {

    //class under test
    PrincipalController principalController ;


    @Start
    public void start(Stage stage) throws IOException {


        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(param -> container.select(PrincipalController.class).get());
        InputStream s = getClass().getResourceAsStream("/fxml/principal.fxml");
        Parent fxmlParent = fxmlLoader.load(s);
        principalController = fxmlLoader.getController();


        stage.setScene(new Scene(fxmlParent));
        stage.getScene().getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        stage.show();

    }


    @Test
    @DisplayName("comprobar pantalla incial al cargar")
    void pantallaPrincipal() {
        //given

        //when

        //then
        FxAssert.verifyThat("#pantallaLogin", Node::isVisible);
        FxAssert.verifyThat("#menuPrincipal", node -> !node.isVisible());


    }


    @Test
    void menuItemListadoClick(FxRobot robot) {
        //given
        Usuario usuario = new Usuario("admin", "admin");
        robot.interact(() -> {
            principalController.onLoginHecho(usuario);
        });

        robot.sleep(1000);

        //when
        robot.clickOn("#menuFile");
        robot.clickOn("#menuItemListado");

        //then
        FxAssert.verifyThat("#pantallaListado", Node::isVisible);
    }

    @Test
    @DisplayName("login ok")
    void onLogin(FxRobot robot) {
        //given
        Usuario usuario = new Usuario("admin", "admin");

        //when
        robot.interact(() -> {
            principalController.onLoginHecho(usuario);
        });
        robot.sleep(1000);
        //then
        assertThat(principalController.getActualUser()).isEqualTo(usuario);

        FxAssert.verifyThat("#lbBienvenido", Node::isVisible);
        FxAssert.verifyThat("#menuPrincipal", Node::isVisible);

        assertThat(robot.lookup("#menuHelp").tryQuery()).isNotPresent();


    }





    @Test
    void sacarAlertError(FxRobot robot) throws InterruptedException {
        //given

        //when
        robot.interact(() -> {
         principalController.sacarAlertError("mensaje");
        });
        robot.sleep(1000);
        //then
        DialogPane a  = robot.lookup("#alert").queryAs(DialogPane.class);

        assertThat(a.getContentText()).isEqualTo("mensaje");
        assertThat(a.getHeaderText()).isEqualTo("Error");
        robot.clickOn("#btn-ok");
        robot.sleep(1000);

    }
}
