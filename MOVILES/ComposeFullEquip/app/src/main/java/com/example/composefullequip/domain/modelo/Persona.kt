package com.example.composefullequip.domain.modelo

import java.util.UUID

data class Persona(
    val nombre: String,
    val apellido: String,
    val edad: Int,
    val id: UUID = UUID.randomUUID(),
) {
}