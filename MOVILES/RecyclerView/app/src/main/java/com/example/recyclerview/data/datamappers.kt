package com.example.recyclerview.data

import com.example.recyclerview.data.model.Ejemplo
import com.example.recyclerview.domain.model.Persona

fun Ejemplo.toPersona() : Persona = Persona(description,extension,id)


fun Persona.toEjemplo() : Ejemplo = Ejemplo(nombre,apellidos,edad)