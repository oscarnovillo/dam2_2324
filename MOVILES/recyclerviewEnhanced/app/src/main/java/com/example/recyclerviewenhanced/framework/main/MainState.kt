package com.example.recyclerviewenhanced.framework.main

import com.example.recyclerviewenhanced.domain.Persona

data class MainState(val personas: List<Persona> = emptyList(),
                     val personasSeleccionadas: List<Persona> = emptyList(),
                     val selectMode: Boolean = false,
                     val error: String? = null,)