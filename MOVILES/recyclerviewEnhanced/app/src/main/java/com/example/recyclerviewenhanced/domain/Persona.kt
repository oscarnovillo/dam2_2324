package com.example.recyclerviewenhanced.domain

import java.time.LocalDate

data class Persona(
    val id: Int,
    var nombre: String,
    val nacimiento: LocalDate,
    val cosas: List<Cosa>?,
    var isSelected : Boolean = false,
)
