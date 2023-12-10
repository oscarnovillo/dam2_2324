package com.example.roomviewmodel.data

import com.example.roomviewmodel.data.modelo.PersonaEntity
import com.example.roomviewmodel.data.modelo.PersonaWithCosas
import com.example.roomviewmodel.data.modelo.toPersona

class PersonaRepository(private val personaDao: PersonaDao) {

    suspend fun getPersonas() = personaDao.getPersonas().map { it.toPersona() }

    suspend fun getCosas() = personaDao.getCosas()

    suspend fun getPersonasDes() = personaDao.getPersonasDes()

    suspend fun getPersonaWithCosas(id:Int) = personaDao.getPersonaWithCosas(id)

    suspend fun getPersonaWithCosas() = personaDao.getPersonaWithCosas()

    suspend fun insertPersona(persona: PersonaEntity) = personaDao.insert(persona)

    suspend fun insertPersonaEntera(persona: PersonaWithCosas) = personaDao.createTransaction(persona)
}