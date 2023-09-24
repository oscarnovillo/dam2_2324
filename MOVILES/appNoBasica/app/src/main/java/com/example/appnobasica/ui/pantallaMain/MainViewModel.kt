package com.example.appnobasica.ui.pantallaMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appnobasica.R
import com.example.appnobasica.domain.modelo.Persona
import com.example.appnobasica.domain.usecases.personas.AddPersonaUseCase
import com.example.appnobasica.domain.usecases.personas.GetPersonas
import com.example.appnobasica.utils.StringProvider

class MainViewModel(
    private val stringProvider: StringProvider,
    private val addPersonaUseCase: AddPersonaUseCase,
    private val getPersonas: GetPersonas,
) : ViewModel() {

    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState


    fun addPersona(persona: Persona) {

        if (!addPersonaUseCase(persona)) {
            _uiState.value = MainState(
                error = stringProvider.getString(R.string.name),
            )
            _uiState.value = _uiState.value?.copy(error = Constantes.ERROR)

        }
    }

    fun getPersonas(id: Int) {
        val personas = getPersonas()

        if (personas.size < id || id < 0) {
            _uiState.value = _uiState.value?.copy(error = "error")

        } else
            _uiState.value = _uiState.value?.copy(persona = personas[id])


    }

    fun errorMostrado() {
        _uiState.value = _uiState.value?.copy(error = null)
    }

}


/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class MainViewModelFactory(
    private val stringProvider: StringProvider,
    private val addPersona: AddPersonaUseCase,
    private val getPersonas: GetPersonas,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                stringProvider,
                addPersona,
                getPersonas,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}