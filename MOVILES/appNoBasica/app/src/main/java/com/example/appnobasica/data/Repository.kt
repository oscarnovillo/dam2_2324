package com.example.appnobasica.data

import com.example.appnobasica.domain.modelo.Persona

class Repository {

    fun addPersona(persona: Persona) = personas.add(persona)


    fun getPersonas(): List<Persona> {
        return personas
    }

    init {
        personas.add(Persona("Juanito"))
        personas.add(Persona("Jorgito"))
        personas.add(Persona("Jaimito"))
    }

    companion object {

        private val personas = mutableListOf<Persona>()
        fun getInstance(): Repository = Repository()

    }

}