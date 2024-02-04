package com.example.composefullequip.data

import com.example.composefullequip.domain.modelo.Persona
import javax.inject.Inject

class ListaRepository @Inject constructor(){



    companion object{
        val data = mutableListOf<Persona>(
            Persona("nombre 1","apellido 1", 20),
            Persona("nombre 2","apellido 2", 21),
            Persona("nombre 3","apellido 3", 22),
            Persona("nombre 4","apellido 4", 23),
            Persona("nombre 5","apellido 5", 24),
            Persona("nombre 6","apellido 6", 25),
            Persona("nombre 7","apellido 7", 26),
            Persona("nombre 8","apellido 8", 27),
            Persona("nombre 9","apellido 9", 28),
            Persona("nombre 10","apellido 10", 29),
            Persona("nombre 11","apellido 11", 30),
        )

    }


    fun getPersonas() : List<Persona> = data.toList()

    fun shufflePersonas() : List<Persona>  {
        val persona =  data.removeAt(5)
        data.add(persona)

        return data.shuffled().toList()
    }


}
