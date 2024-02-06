package org.example.frontend

import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ApplicationEvent
import org.springframework.context.ConfigurableApplicationContext


class DiJavaFX : Application(){
    private var applicationContext: ConfigurableApplicationContext? = null

    @Throws(Exception::class)
    fun init() {
        super.init()
        applicationContext = SpringApplicationBuilder(FrontendApplication::class.java).run()
    }

    @Throws(Exception::class)
    fun stop() {
        super.stop()
        applicationContext!!.close()
        Platform.exit()
    }

    @Throws(Exception::class)
    fun start(primaryStage: Stage) {
        primaryStage.setMinWidth(800)
        primaryStage.setMinHeight(600)
        primaryStage.setResizable(true)
        applicationContext!!.publishEvent(StageReadyEvent(primaryStage))
    }


    internal class StageReadyEvent(stage: Stage?) : ApplicationEvent(stage) {
        val stage: Stage
            get() = (getSource() as Stage)
    }
}
