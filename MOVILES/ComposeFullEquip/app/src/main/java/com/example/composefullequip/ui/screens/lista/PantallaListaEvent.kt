package com.example.composefullequip.ui.screens.lista

sealed class PantallaListaEvent {


    object GetPersonas : PantallaListaEvent()

    object ShufflePersonas : PantallaListaEvent()
}