package com.example.composefullequip.ui.screens.lista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composefullequip.data.ListaRepository
import com.example.composefullequip.ui.screens.lista.PantallaListaEvent.GetPersonas
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantallaListaViewModel  @Inject constructor(
    private val repository: ListaRepository
): ViewModel() {
    fun handleEvent(PantallaListaEvent: PantallaListaEvent) {
        when(PantallaListaEvent){
            is GetPersonas -> {
                val personas = repository.getPersonas()
                _state.value = state.value.copy(
                    personas = personas
                )
            }

            else -> {
                val personas = repository.shufflePersonas()
                _state.value = state.value.copy(
                    personas = personas
                )

            }
        }

    }


    private val _state = MutableStateFlow(PantallaListaState())
    val state : StateFlow<PantallaListaState> = _state.asStateFlow()


    init {
        startTimer()
    }
    fun startTimer() {

       viewModelScope.launch {
             for (i in 0 .. 2) {
                delay(2000)
                handleEvent(PantallaListaEvent.ShufflePersonas)
            }
           _state.value = _state.value.copy(
               error = "saca un error"
           )
        }
    }



}