package com.example.recyclerviewenhanced.framework.main

import com.example.recyclerviewenhanced.domain.Persona

sealed class MainEvent {


    class DeletePersona(val persona: Persona) : MainEvent()
    class SeleccionaPersona(val persona: Persona) : MainEvent()
    class InsertPersona(val persona: Persona) : MainEvent()
    class GetPersonaPorId(val id: Int) : MainEvent()
    class isSelectedPersona(val persona: Persona) : MainEvent()
    class GetPersonaFiltradas(val filtro: String) : MainEvent()
    object GetPersonas : MainEvent()
    object ErrorVisto : MainEvent()
}
