package org.example.frontend

import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder



@Configuration
class Configuration {

    @Bean
    fun createLoader(context: ApplicationContext?): FXMLLoader {
        val loader: FXMLLoader = FXMLLoader()
        loader.setControllerFactory(context::getBean)
        return loader
    }

    @Bean
    fun createPasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
