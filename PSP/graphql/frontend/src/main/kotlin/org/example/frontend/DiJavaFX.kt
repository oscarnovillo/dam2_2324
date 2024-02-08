package org.example.frontend

import javafx.application.Application
import javafx.application.Platform
import javafx.stage.Stage
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ApplicationEvent
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Component

@Component
class DiJavaFX (

) : Application(){

    private lateinit var applicationContext: ConfigurableApplicationContext

    override fun init() {
        super.init()
        applicationContext = SpringApplicationBuilder(FrontendApplication::class.java).run()
    }


    override fun stop() {
        super.stop()
        applicationContext.close()
        Platform.exit()
    }


    override fun start(primaryStage: Stage) {
        primaryStage.minWidth = 800.0
        primaryStage.minHeight = 600.0
        primaryStage.isResizable = true
        applicationContext.publishEvent(StageReadyEvent(primaryStage))
    }


    class StageReadyEvent(stage: Stage) : ApplicationEvent(stage) {
        val stage: Stage
            get() = (getSource() as Stage)
    }
}
