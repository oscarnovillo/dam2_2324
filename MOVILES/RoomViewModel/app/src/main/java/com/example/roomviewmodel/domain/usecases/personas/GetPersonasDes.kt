package com.example.roomviewmodel.domain.usecases.personas

import com.example.roomviewmodel.data.PersonaRepository
import com.example.roomviewmodel.data.modelo.toCosa
import com.example.roomviewmodel.data.modelo.toPersona

class GetPersonasDes(val personasRepository: PersonaRepository) {

    suspend fun invoke(id:Int) =
        personasRepository.getPersonaWithCosas(id).toPersona()


    suspend fun invoke() =
        personasRepository.getPersonaWithCosas().map { it.toPersona() }


    suspend fun invokeCosas() =
        personasRepository.getCosas().map { it.toCosa() }
}