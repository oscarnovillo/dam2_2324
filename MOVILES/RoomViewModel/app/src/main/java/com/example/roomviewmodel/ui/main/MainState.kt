package com.example.roomviewmodel.ui.main

import com.example.roomviewmodel.domain.modelo.Persona

data class MainState(
    val personas: List<Persona> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
