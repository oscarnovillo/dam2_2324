package com.example.primeraapp

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModel : ViewModel(){

    private val _uiState = MutableLiveData<MainState>(MainState("Hola"))
    val uiState: LiveData<MainState> get() = _uiState

    init {
        _uiState.value = _uiState.value?.copy(name = "Hola2") ?: MainState("Hola3")
    }


    fun onClick(texto: String){
        _uiState.value  = _uiState.value?.copy(name = texto)
    }

}


data class MainState(var name:String)
class MainViewModelFactory(

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return MainViewModel(
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
