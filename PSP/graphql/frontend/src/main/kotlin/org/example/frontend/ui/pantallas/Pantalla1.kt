package org.example.frontend.ui.pantallas

import javafx.fxml.FXML
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class Pantalla1(
    private val passwordEncoder: PasswordEncoder,
) {

    @FXML
    private lateinit var cifrado: TextArea
    @FXML
    private lateinit var txtNormal: TextField



}
