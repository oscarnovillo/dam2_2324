package com.example.flows.framework.main

import com.example.flows.domain.modelo.Movie

interface MainContract {

    sealed class Event {

        object PedirDatos : Event()
        object MensajeMostrado: Event()

    }

    data class State(
        val movies: List<Movie> = emptyList(),
        val isLoading : Boolean = false,
        val error: String? = null,

    )
}