package org.example.frontend.ui.pantallas.principal

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import javafx.util.Callback
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import java.io.IOException
import java.net.URL
import java.util.*

@Component
class PrincipalController(

    var context: ApplicationContext

) : Initializable {


    @FXML
    lateinit var root: BorderPane





    private fun cargarPantalla(ruta: String): Pane? {
        var panePantalla: Pane? = null
        try {
            val fxmlLoader = FXMLLoader()
            panePantalla = fxmlLoader.load(javaClass.getResourceAsStream(ruta))
            fxmlLoader.setControllerFactory { context.getBean(it) }
            root.center = panePantalla
        } catch (e: IOException) {
            println(e.message)
        }
        return panePantalla
    }

    override fun initialize(p0: URL?, p1: ResourceBundle?) {

        cargarPantalla("/fxml/pantalla1.fxml")
    }

}
