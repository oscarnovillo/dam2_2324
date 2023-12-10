package com.example.roomviewmodel.domain.modelo

import java.time.LocalDate

data class Persona(
    val id: Int,
    val nombre: String,
    val nacimiento: LocalDate,
    val cosas: List<Cosa>?
)
