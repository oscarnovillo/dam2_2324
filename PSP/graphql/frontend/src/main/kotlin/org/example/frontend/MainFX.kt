package org.example.frontend

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import org.example.frontend.ui.pantallas.principal.PrincipalController
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import java.io.IOException
import kotlin.system.exitProcess

@Component
class MainFX (
    private val fxmlLoader: FXMLLoader
) : ApplicationListener<DiJavaFX.StageReadyEvent> {






    override fun onApplicationEvent(event: DiJavaFX.StageReadyEvent) {
        try {
//            ResourceBundle r = ResourceBundle.getBundle("/i18n/textos");
            val stage: Stage = event.stage
            //fxmlLoader.setResources(r);
            val fxmlParent: Parent = fxmlLoader.load(javaClass.getResourceAsStream("/fxml/principal.fxml"))
            val controller: PrincipalController = fxmlLoader.getController()


            stage.scene = Scene(fxmlParent)
            //stage.setMinHeight(1000);
//            stage.getScene().getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
//            stage.setTitle(r.getString("app.title"));
            stage.show()
        } catch (e: IOException) {
            e.printStackTrace()
            exitProcess(0)
        }
    }
}
