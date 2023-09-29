package com.example.appnobasica.ui.pantallaMain

import com.example.appnobasica.domain.modelo.Persona

data class MainState(
    val persona: Persona = Persona(nombre=""),
    val error: String? = null
)