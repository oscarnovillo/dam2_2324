package org.example.springjavafx.ui.pantallas;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.springjavafx.data.CosasRepository;
import org.example.springjavafx.data.UserRepository;
import org.example.springjavafx.data.modelo.Cosa;
import org.example.springjavafx.data.modelo.User;
import org.example.springjavafx.seguridad.Encriptacion;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class Pantalla1 {

    private final Encriptacion encriptacion;
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final CosasRepository cosaRepository;


    public TextArea cifrado;
    public TextField txtNormal;

    public Pantalla1(Encriptacion encriptacion, PasswordEncoder passwordEncoder, UserRepository userRepository, CosasRepository cosaRepository) {
        this.encriptacion = encriptacion;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.cosaRepository = cosaRepository;
    }

    public void cifrar(ActionEvent actionEvent) {
        cifrado.setText(encriptacion.encriptar(txtNormal.getText(),"hola"));
        User u = new User();
        u.setName(txtNormal.getText());
        u.setPassword(passwordEncoder.encode("hola"));
        u.setCosas(new ArrayList<>());

        Cosa c = new Cosa();
        c.setNombre("cosa1");
        c.setUser(u);
        u.getCosas().add(c);

        userRepository.save(u);
        cosaRepository.save(c);
    }

    public void descrifrar(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText("Texto desencriptado");
        alert.setContentText(encriptacion.desencriptar(cifrado.getText(),"hola"));
        alert.showAndWait();
    }

    public void crearCertificados(ActionEvent actionEvent) {

        List<User> users = userRepository.findAll();
        users.getFirst().setCosas(cosaRepository.findByUserId(users.getFirst().getId()));

        users.getFirst().getCosas().forEach(cosa -> System.out.println(cosa.getNombre()));

        // generar clave publica y privada
        KeyPairGenerator generadorRSA = KeyPairGenerator.getInstance("RSA"); // Hace uso del provider BC
        generadorRSA.initialize(2048,new SecureRandom());
        KeyPair clavesRSA = generadorRSA.generateKeyPair();
        PrivateKey clavePrivada = clavesRSA.getPrivate();
        PublicKey clavePublica = clavesRSA.getPublic();




    }
}
