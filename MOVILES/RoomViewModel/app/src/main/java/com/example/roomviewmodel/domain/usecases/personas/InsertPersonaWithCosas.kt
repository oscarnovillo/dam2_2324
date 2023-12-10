package com.example.roomviewmodel.domain.usecases.personas

import com.example.roomviewmodel.data.PersonaRepository
import com.example.roomviewmodel.data.modelo.toPersonaWithCosas
import com.example.roomviewmodel.domain.modelo.Persona

class InsertPersonaWithCosas(val personasRepository: PersonaRepository) {

    suspend fun invoke(persona: Persona) = personasRepository.insertPersonaEntera(persona.toPersonaWithCosas())
}