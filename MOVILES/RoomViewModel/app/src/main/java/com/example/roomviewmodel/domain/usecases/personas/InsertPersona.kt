package com.example.roomviewmodel.domain.usecases.personas

import com.example.roomviewmodel.data.PersonaRepository
import com.example.roomviewmodel.data.modelo.toPersonaEntity
import com.example.roomviewmodel.domain.modelo.Persona

class InsertPersona(private val personasRepository: PersonaRepository) {

    suspend fun invoke(persona: Persona) = personasRepository.insertPersona(persona.toPersonaEntity())
}