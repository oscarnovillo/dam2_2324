package ui.pantallas.principal;


import domain.modelo.Cromo;
import domain.modelo.Usuario;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import lombok.extern.log4j.Log4j2;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.Pantallas;
import ui.pantallas.login.LoginController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Log4j2
public class PrincipalController {

    @FXML
    private Menu menuHelp;
    // objeto especial para DI
    Instance<Object> instance;

    @FXML
    private MenuBar menuPrincipal;
    private Stage primaryStage;

    private Usuario actualUser;
    private Cromo cromoSeleccionado;

    public Usuario getActualUser() {
        return actualUser;
    }
    public Cromo getActualCromo(){return cromoSeleccionado;}

    @FXML
    public BorderPane root;


    private final Alert alert;

    private Pane pantallaBienvenida;


    @Inject
    public PrincipalController(Instance<Object> instance) {
       this.instance = instance;
       alert= new Alert(Alert.AlertType.NONE);


    }

    private void cargarPantalla(Pantallas pantalla) {

        switch (pantalla) {
//            case LISTADO:
//                cambioPantalla(cargarPantalla(pantalla.getRuta()));
//                break;
//            case PANTALLA1:
//                if (pantallaBienvenida == null){
//                    pantallaBienvenida = cargarPantalla(pantalla.getRuta());
//                }
//
//                cambioPantalla(pantallaBienvenida);
//                break;
            default:
                cambioPantalla(cargarPantalla(pantalla.getRuta()));
                break;
        }
    }


    public void sacarAlertError(String mensaje)
    {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(mensaje);
        alert.getDialogPane().setId("alert");
        alert.getDialogPane().lookupButton(ButtonType.OK).setId("btn-ok");
        //alert.getDialogPane().lookupButton(ButtonType.CANCEL).setId("btn-cancel");
        alert.showAndWait();
    }


    private Pane cargarPantalla(String ruta) {
        Pane panePantalla = null;
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            BasePantallaController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);
            pantallaController.principalCargado();


        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return panePantalla;
    }





    public void logout() {
        actualUser = null;
        menuPrincipal.setVisible(false);
        cargarPantalla(Pantallas.LOGIN);
    }

    private void cambioPantalla(Pane pantallaNueva) {

        //            FadeTransition fade = new FadeTransition();
//            fade.setNode(root.getCenter());
//            fade.setDuration(Duration.millis(2000));
//            fade.setCycleCount(1);
//            fade.setInterpolator(Interpolator.LINEAR);
//            fade.setFromValue(1);
//            fade.setToValue(0);
//            fade.play();
//            fade.setOnFinished(event -> {
//                root.setCenter(panePantalla);
//            });
        //            TranslateTransition translate = new TranslateTransition();
//            translate.setNode(stackPane.getChildren().get(1));
//            translate.setDuration(Duration.millis(1000));
//            translate.setCycleCount(1);
//            translate.setInterpolator(Interpolator.LINEAR);
//            translate.setFromX(0);
//            translate.setToX(root.getWidth());
//            translate.play();
//            translate.setOnFinished(event -> {
//                stackPane.getChildren().remove(1);
//            });

//        StackPane stackPane = (StackPane) root.getCenter();
//
//        if (stackPane.getChildren().get(0) != pantallaNueva) {
//
//            stackPane.getChildren().add(0, pantallaNueva);
//
//            ScaleTransition scaleTransition = new ScaleTransition();
//            scaleTransition.setNode(stackPane.getChildren().get(1));
//            scaleTransition.setDuration(Duration.millis(500));
//            scaleTransition.setFromX(stackPane.getChildren().get(1).getScaleX());
//            scaleTransition.setFromY(stackPane.getChildren().get(1).getScaleY());
//            scaleTransition.setToX(0);
//            scaleTransition.setToY(0);
//            scaleTransition.setInterpolator(Interpolator.EASE_OUT);
//            scaleTransition.play();
//            scaleTransition.setOnFinished(event -> {
//                Node node = stackPane.getChildren().remove(1);
//                node.setScaleX(1);
//                node.setScaleY(1);
//            });
//        }

        root.setCenter(pantallaNueva);
    }


    public void initialize() {
        menuPrincipal.setVisible(false);
        cargarPantalla(Pantallas.LOGIN);

    }

    private void closeWindowEvent(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().add(ButtonType.CANCEL);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.setTitle("Quit application");
        alert.setContentText("Close without saving?");
        alert.initOwner(primaryStage.getOwner());
        Optional<ButtonType> res = alert.showAndWait();


        res.ifPresent(buttonType -> {
            if (buttonType == ButtonType.CANCEL) {
                event.consume();
            }
        });
    }

    public void help(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ayuda");
        alert.setHeaderText("Ayuda");
        alert.setContentText("Este es un mensaje de ayuda");
        alert.showAndWait();
    }

    public void exit(ActionEvent actionEvent) {
//        primaryStage.close();
//        Platform.exit();
        primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
        primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
    }

    @FXML
    private void cambiarcss(ActionEvent actionEvent) {
        System.out.println(primaryStage.getScene().getRoot().getStylesheets().stream().findFirst().orElseGet(() ->"no encontrado"));


        primaryStage.getScene().getRoot().getStylesheets().clear();



        primaryStage.getScene().getRoot().getStylesheets().add(getClass().getResource("/css/darkmode.css").toExternalForm());

    }



    public double getHeight() {
        return root.getScene().getWindow().getHeight();
    }

    public double getWidth()
    {
//        return 600;
        return root.getScene().getWindow().getWidth();
    }


    @FXML
    private void menuClick(ActionEvent actionEvent) {
        switch (((MenuItem)actionEvent.getSource()).getId())
        {
            case "menuItemPantalla1":
                cargarPantalla(Pantallas.PANTALLA1);
                break;
            case "menuItemListado":
                cargarPantalla(Pantallas.LISTADO);
                break;
            case "menuItemPantallaNueva":
                cargarPantalla(Pantallas.PANTALLANUEVA);
                break;
            case "menuItemLogout":
                logout();
                break;
        }


    }

    //evento de otra pantalla
    public void onLoginHecho(Usuario usuario) {
        actualUser = usuario;
        menuPrincipal.setVisible(true);
        if (actualUser.nombre().equals("admin")) {
            menuHelp.setVisible(false);
        }

        cargarPantalla(Pantallas.PANTALLA1);
    }

    public void onSeleccionCromo(Cromo p) {
        this.cromoSeleccionado = p;
        cargarPantalla(Pantallas.DETALLE);



    }
}
