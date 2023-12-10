package com.example.roomviewmodel.domain.usecases.personas

import com.example.roomviewmodel.data.PersonaRepository
import com.example.roomviewmodel.data.modelo.toPersona

class GetPersonas(val personasRepository: PersonaRepository) {

     suspend fun invoke() = personasRepository.getPersonas()
}