package org.example.frontend.data

import com.apollographql.apollo3.ApolloClient
import org.example.visitas.GetPoisQuery
import org.springframework.stereotype.Repository

@Repository
class PoiRepository(
    var apolloClient: ApolloClient

) {

    suspend fun getPOIs() = apolloClient.query(GetPoisQuery())
        .execute().data?.getPois
}
