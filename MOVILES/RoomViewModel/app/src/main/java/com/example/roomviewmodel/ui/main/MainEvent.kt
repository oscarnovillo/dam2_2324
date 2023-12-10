package com.example.roomviewmodel.ui.main

import com.example.roomviewmodel.domain.modelo.Persona

sealed class MainEvent {

    class InsertPersona(val persona: Persona) : MainEvent()
    class GetPersonaPorId(val id: Int) : MainEvent()
    object GetPersonas : MainEvent()
    object ErrorVisto : MainEvent()
}