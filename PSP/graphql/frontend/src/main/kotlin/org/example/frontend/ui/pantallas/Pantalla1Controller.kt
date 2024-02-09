package org.example.frontend.ui.pantallas

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.example.frontend.data.PoiRepository
import org.example.frontend.data.VisitasRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.net.URL
import java.util.*

@Component
class Pantalla1Controller(
    private val passwordEncoder: PasswordEncoder,
    private val visitasRepository: VisitasRepository,
    private val poiRepository: PoiRepository,
) : Initializable {

    @FXML
    private lateinit var cifrado: TextArea
    @FXML
    private lateinit var txtNormal: TextField

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        GlobalScope.launch {
            poiRepository.getPOIs()?.let {
                it.map { it?.let { cifrado.text += "$it\n"} }
            }
//            visitasRepository.getVisitas()?.let {
//                  it.map { it?.let { txtNormal.text += "$it\n"} }
//            }
        }
    }

}
