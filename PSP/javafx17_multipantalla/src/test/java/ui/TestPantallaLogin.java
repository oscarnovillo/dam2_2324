package ui;

import domain.usecases.LoginUseCase;
import domain.usecases.LoginUseCaseImpl;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import ui.pantallas.login.LoginController;
import ui.pantallas.login.LoginViewModel;
import ui.pantallas.principal.PrincipalController;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;



@ExtendWith(ApplicationExtension.class)
@ExtendWith(MockitoExtension.class)
public class TestPantallaLogin extends ApplicationTest {


    private PrincipalController principalController; // = mock(PrincipalController.class);;


    private LoginUseCase loginUseCase; // = mock(LoginUseCase.class);


    private LoginViewModel loginViewModel;
    LoginController controller;

    @BeforeEach
    void setUp() {
        //principalController = mock(PrincipalController.class);
    }

    @Start
    public void start(Stage stage) throws IOException {

        principalController = mock(PrincipalController.class);
        loginUseCase = mock(LoginUseCaseImpl.class);
        loginViewModel = new LoginViewModel(loginUseCase);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(param -> new LoginController(loginViewModel));
        InputStream s = getClass().getResourceAsStream("/fxml/login.fxml");
        Parent fxmlParent = fxmlLoader.load(s);
        controller = fxmlLoader.getController();
        controller.setPrincipalController(principalController);



        stage.setScene(new Scene(fxmlParent));
        stage.getScene().getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        stage.show();

    }


    @Test
    void should_contain_button_with_text(FxRobot robot) {
        //given
        robot.clickOn("#txtUserName");
        robot.write("admin");
        when(loginUseCase.doLogin(argThat(usuario -> usuario.getNombre().equals("admin")))).thenReturn(true);
        //when(loginUseCase.doLogin(any(Usuario.class))).thenReturn(true);

        //when
        robot.clickOn("#btLogin");

        //FxAssert.verifyThat("#btLogin").textIs("Login");

        //then

        verify(principalController).onLoginHecho(argThat(usuario -> usuario.getNombre().equals("admin")));
    }

    @Test
    void should_alert_error(FxRobot robot) {
        //given
        robot.clickOn("#txtUserName");
        robot.write("otro");
        when(loginUseCase.doLogin(argThat(usuario -> !usuario.getNombre().equals("admin")))).thenReturn(false);
        //when(loginUseCase.doLogin(any(Usuario.class))).thenReturn(true);
        TextField text = lookup("#txtUserName").queryAs(TextField.class);
        text.setText("otrogggg");


        //when
        robot.clickOn(".button");

        //then
        assertAll(
                () -> verify(principalController).sacarAlertError("usuario o pass no valido"));
//        Node dialogPane = robot.lookup(".dialog-pane").query();
//        assertNotNull(robot.from(dialogPane).lookup((Text t) -> t.getText().startsWith("usuario o pass no valido")).query());
        robot.sleep(1000);

    }


}
