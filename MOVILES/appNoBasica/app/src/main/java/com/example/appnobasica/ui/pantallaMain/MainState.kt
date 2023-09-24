package com.example.appnobasica.ui.pantallaMain

import com.example.appnobasica.domain.modelo.Persona

data class MainState(
    val persona: Persona = Persona("null"),
    val error: String? = null
)