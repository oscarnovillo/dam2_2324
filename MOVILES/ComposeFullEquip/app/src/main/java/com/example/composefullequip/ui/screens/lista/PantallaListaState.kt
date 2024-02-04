package com.example.composefullequip.ui.screens.lista

import com.example.composefullequip.domain.modelo.Persona

data class PantallaListaState(val personas: List<Persona> = emptyList(), val isLoading : Boolean = false, val error: String? = null)
