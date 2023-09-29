package com.example.appnobasica.data

import com.example.appnobasica.domain.modelo.Persona

object RespositoryDos {

    private val personas = mutableListOf<Persona>()

    init {
        personas.add(Persona("Juanito"))
        personas.add(Persona("Jorgito"))
        personas.add(Persona("Jaimito"))
    }

    private val mapPersonas = mutableMapOf<String, Persona>()

    fun addPersona(persona: Persona) =
        personas.add(persona)


    fun getPersonas(): List<Persona> {
        return personas
    }
}