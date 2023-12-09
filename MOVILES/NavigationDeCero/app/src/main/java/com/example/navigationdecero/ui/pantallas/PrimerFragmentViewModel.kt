package com.example.navigationdecero.ui.pantallas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PrimerFragmentViewModel  @Inject constructor() : ViewModel(){



    private val _uiState = MutableLiveData(FragmentState())
    val uiState: LiveData<FragmentState> get() = _uiState

    init{
        _uiState.value = _uiState.value?.copy(cadenas = listOf("uno","dos","tres","cuatro","cinco","seis","siete","ocho","nueve","diez"))
    }

}