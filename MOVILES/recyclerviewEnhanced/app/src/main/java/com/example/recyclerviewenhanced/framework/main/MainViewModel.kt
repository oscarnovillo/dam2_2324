package com.example.recyclerviewenhanced.framework.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewenhanced.data.repositories.DogRepository
import com.example.recyclerviewenhanced.domain.Persona
import com.example.recyclerviewenhanced.utils.NetworkResultt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dogRepository: DogRepository) : ViewModel() {


    private val listaPersonas = mutableListOf<Persona>()



    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    private var selectedPersonas = mutableListOf<Persona>()


    private val _uiState = MutableLiveData(MainState())
    val uiState: LiveData<MainState> get() = _uiState


    init {
        listaPersonas.addAll((1..20).map{Persona(it,"nombre$it", LocalDate.now(),null)})
        getPersonas()
        //(1..20).map(transform = {Persona(it,"nombre$it", LocalDate.now(),null)})
//        (1..20).forEach {
//            listaPersonas.add(Persona(it,"nombre$it", LocalDate.now(),null))
//        }
//        listaPersonas.addAll(
//            listOf<Persona>(
//                Persona(1, "nombre", LocalDate.now(), null),
//                Persona(2, "nombre1", LocalDate.now(), null),
//                Persona(3, "nombre2", LocalDate.now(), null),
//                Persona(4, "nombre3", LocalDate.now(), null),
//                Persona(5, "nombre41", LocalDate.now(), null),
//                Persona(6, "nombre52", LocalDate.now(), null),
//                Persona(7, "nombre6", LocalDate.now(), null),
//                Persona(8, "nombre71", LocalDate.now(), null),
//                Persona(9, "nombre82", LocalDate.now(), null),
//                Persona(10, "nombre9", LocalDate.now(), null),
//                Persona(21, "nombre91", LocalDate.now(), null),
//                Persona(31, "nombre92", LocalDate.now(), null),
//                Persona(11, "nombre76", LocalDate.now(), null),
//                Persona(21, "nombre134", LocalDate.now(), null),
//                Persona(31, "nombre24545", LocalDate.now(), null),
//                Persona(131, "1nombre92", LocalDate.now(), null),
//                Persona(111, "1nombre76", LocalDate.now(), null),
//                Persona(121, "1nombre134", LocalDate.now(), null),
//                Persona(131, "1nombre24545", LocalDate.now(), null),
//            )
//        )

    }

    fun handleEvent(event: MainEvent) {
        when (event) {
            MainEvent.GetPersonas -> {
                getPersonas()
            }
            is MainEvent.InsertPersona -> {
                //insertPersonaWithCosas(event.persona!!)
                getPersonas()
            }
            MainEvent.ErrorVisto -> _uiState.value = _uiState.value?.copy(error = null)
            is MainEvent.GetPersonaPorId -> {
            }

            is MainEvent.DeletePersonasSeleccionadas -> {
                _uiState.value?.let {
                    deletePersona(it.personasSeleccionadas)
                    resetSelectMode()
                }
            }
            is MainEvent.SeleccionaPersona -> seleccionaPersona(event.persona)
            is MainEvent.GetPersonaFiltradas -> getPersonas(event.filtro)
            is MainEvent.DeletePersona -> {
                deletePersona(event.persona)
            }

            MainEvent.ResetSelectMode -> resetSelectMode()

            MainEvent.StartSelectMode -> _uiState.value = _uiState.value?.copy(selectMode = true)
        }
    }

    private fun resetSelectMode()
    {
        selectedPersonas.clear()
        _uiState.value = _uiState.value?.copy(selectMode = false)
    }

    private fun getPersonas() {

        viewModelScope.launch {

            var result = dogRepository.getDog()


            when (result) {
                is NetworkResultt.Error -> _error.value = result.message ?: ""
                is NetworkResultt.Loading -> TODO()
                is NetworkResultt.Success -> listaPersonas[0].nombre = result.data?.nombre ?: ""
            }

            result = dogRepository.getDog()

            when (result) {
                is NetworkResultt.Error -> _error.value = result.message ?: ""
                is NetworkResultt.Loading -> TODO()
                is NetworkResultt.Success -> listaPersonas[1].nombre = result.data?.nombre ?: ""
            }


            _uiState.value = _uiState.value?.copy(personas = listaPersonas.toList())
//            _personas.value = getPersonas.invoke()

        }

    }

    private fun getPersonas(filtro: String) {

        viewModelScope.launch {

            _uiState.value =  _uiState.value?.copy (
                personas = listaPersonas.filter { it.nombre.startsWith(filtro) }.toList())
//            _personas.value = getPersonas.invoke()

        }

    }


    private fun deletePersona(personas: List<Persona>) {

        viewModelScope.launch {
//            _sharedFlow.emit("error")
            listaPersonas.removeAll(personas)
            selectedPersonas.removeAll(personas)
            _uiState.value = _uiState.value?.copy(personasSeleccionadas = selectedPersonas.toList())
            getPersonas()
        }

    }

    private fun deletePersona(persona: Persona) {

        deletePersona(listOf(persona))

    }


    private fun seleccionaPersona(persona: Persona) {

        if (isSelected(persona)) {
            selectedPersonas.remove(persona)

        }
        else {
            selectedPersonas.add(persona)
        }
        _uiState.value = _uiState.value?.copy(personasSeleccionadas = selectedPersonas)

    }

    private fun isSelected(persona: Persona): Boolean {
        return selectedPersonas.contains(persona)
    }


}