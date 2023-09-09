package ui.pantallas.pantalla1;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.util.Duration;
import ui.pantallas.common.BasePantallaController;

public class Pantalla1Controller extends BasePantallaController {


    @FXML
    private MFXButton btChorra;
    @FXML
    private CheckBox checkState;
    @FXML
    private Label lbBienvenido;

    public void initialize(){

        //animarPantalla();

    }


    private void animarPantalla() {

        TranslateTransition translate = new TranslateTransition();
            translate.setNode(lbBienvenido);
            translate.setDuration(Duration.millis(1000));
            translate.setCycleCount(1);
            translate.setInterpolator(Interpolator.LINEAR);
            translate.setFromX(getPrincipalController().getWidth());
            translate.setToX(lbBienvenido.getLayoutX());
            translate.play();

        TranslateTransition translateButton = new TranslateTransition();
        translateButton.setNode(btChorra);
        translateButton.setDuration(Duration.millis(750));
        translateButton.setCycleCount(1);
        translateButton.setInterpolator(Interpolator.EASE_OUT);
        translateButton.setFromY(getPrincipalController().getHeight());
        translateButton.setToY(btChorra.getLayoutY());
        translateButton.play();

        TranslateTransition translateCheck = new TranslateTransition();
        translateCheck.setNode(checkState);
        translateCheck.setDuration(Duration.millis(500));
        translateCheck.setCycleCount(1);
        translateCheck.setInterpolator(Interpolator.LINEAR);
        translateCheck.setFromY(getPrincipalController().getHeight());
        translateCheck.setToY(checkState.getLayoutY());
        translateCheck.play();

    }

    @Override
    public void principalCargado() {
        lbBienvenido.setText(getPrincipalController().getActualUser().getNombre());
        animarPantalla();
    }

    @FXML
    private void click(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText("Informacion");
        alert.setContentText("Hola mundo");
        alert.showAndWait();
    }
}
