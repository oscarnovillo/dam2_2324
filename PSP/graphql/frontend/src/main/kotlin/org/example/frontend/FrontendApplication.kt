package org.example.frontend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FrontendApplication

fun main(args: Array<String>) {

    Application.launch(DiJavaFX::class.java, args)

}
