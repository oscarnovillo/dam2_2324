package org.example.springjavafx.ui.pantallas;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.springjavafx.seguridad.Encriptacion;
import org.springframework.stereotype.Component;

@Component
public class Pantalla1 {

    private final Encriptacion encriptacion;


    public TextArea cifrado;
    public TextField txtNormal;

    public Pantalla1(Encriptacion encriptacion) {
        this.encriptacion = encriptacion;
    }

    public void cifrar(ActionEvent actionEvent) {
        cifrado.setText(encriptacion.encriptar(txtNormal.getText(),"hola"));
    }

    public void descrifrar(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText("Texto desencriptado");
        alert.setContentText(encriptacion.desencriptar(cifrado.getText(),"hola"));
        alert.showAndWait();
    }

    public void crearCertificados(ActionEvent actionEvent) {
    }
}
