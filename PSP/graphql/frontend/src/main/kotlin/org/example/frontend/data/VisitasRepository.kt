package org.example.frontend.data

import com.apollographql.apollo3.ApolloClient
import org.example.visitas.VisitasQuery
import org.springframework.stereotype.Repository

@Repository
class VisitasRepository(
    var apolloClient: ApolloClient
) {

    suspend fun getVisitas() = apolloClient.query(VisitasQuery()).execute().data?.getVisitas

}
