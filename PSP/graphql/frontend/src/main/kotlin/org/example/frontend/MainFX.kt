package org.example.frontend

import org.springframework.context.ApplicationListener
import java.io.IOException

class MainFX : ApplicationListener<DiJavaFX.StageReadyEvent> {

    private var fxmlLoader: FXMLLoader? = null

    fun MainFX(fxmlLoader: FXMLLoader?) {
        this.fxmlLoader = fxmlLoader
    }


    override fun onApplicationEvent(event: StageReadyEvent) {
        try {
//            ResourceBundle r = ResourceBundle.getBundle("/i18n/textos");
            val stage: Stage = event.getStage()
            //fxmlLoader.setResources(r);
            val fxmlParent: Parent = fxmlLoader.load(javaClass.getResourceAsStream("/fxml/principal.fxml"))
            val controller: PrincipalController = fxmlLoader.getController()


            stage.setScene(Scene(fxmlParent))
            //stage.setMinHeight(1000);
//            stage.getScene().getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
//            stage.setTitle(r.getString("app.title"));
            stage.show()
        } catch (e: IOException) {
            e.printStackTrace()
            System.exit(0)
        }
    }
}
