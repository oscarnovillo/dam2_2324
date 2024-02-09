package org.example.frontend.data

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.apollographql.apollo3.ApolloClient
import org.springframework.web.context.annotation.ApplicationScope

@Configuration
class ConfigurationApollo {

    @Bean
    fun createApolloClient(): ApolloClient {
        return ApolloClient.Builder()

            .serverUrl("http://localhost:8080/graphql")
            .build()
    }
}
