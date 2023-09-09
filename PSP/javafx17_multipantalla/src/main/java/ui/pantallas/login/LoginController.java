package ui.pantallas.login;

import domain.modelo.Usuario;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import ui.pantallas.common.BasePantallaController;

public class LoginController extends BasePantallaController {

    private LoginViewModel loginViewModel;

    @FXML
    private MFXPasswordField txtPassword;
    @FXML
    private MFXTextField txtUserName;


    @Inject
    public LoginController(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
    }

    public void initialize() {

        loginViewModel.getState().addListener((observableValue, oldState, newState) -> {
            if (newState.getError()!=null)
            {
               this.getPrincipalController().sacarAlertError(newState.getError());
            }
            if (newState.isLoginOK())
            {
                //cambiar de pantalla
                this.getPrincipalController().onLoginHecho(new Usuario(txtUserName.getText(), txtPassword.getText()));
            }


        });
    }

    @FXML
    private void doLogin() {
        Usuario usuario = new Usuario(txtUserName.getText(), txtPassword.getText());

        loginViewModel.doLogin(usuario);


    }




}
