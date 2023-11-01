package com.example.recyclerviewenhanced.framework.main

import com.example.recyclerviewenhanced.domain.Persona

data class MainState(val personas: List<Persona> = emptyList(),
                     val error: String? = null,)