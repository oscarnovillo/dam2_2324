package com.example.flows.framework.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flows.data.MovieRepository
import com.example.flows.framework.main.MainContract.State
import com.example.flows.framework.utils.Utils
import com.example.flows.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext val appContext: Context,

    private val movieRepository: MovieRepository
) : ViewModel() {


    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState

    private val _uiError = Channel<String>()
    val uiError = _uiError.receiveAsFlow()


    fun handleEvent(event: MainContract.Event) {
        when (event) {
            MainContract.Event.PedirDatos -> {
                pedirDatos()
            }

            MainContract.Event.MensajeMostrado -> {
                _uiState.update { it.copy(error = null) }
            }
        }


    }

    private fun pedirDatos() {
        viewModelScope.launch {
            if (Utils.hasInternetConnection(context = appContext)) {
                movieRepository.fetchTrendingMovies()
                    .catch(action = { cause -> _uiError.send(cause.message ?: "") })
                    .collect { result ->
                        when (result) {
                            is NetworkResult.Error -> {
                                _uiState.update {
                                    it.copy(
                                        error = result.message,
                                        isLoading = false
                                    )
                                }
                                //_uiError.send(result.message ?: "Error")
                            }

                            is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                            is NetworkResult.Success -> _uiState.update {
                                it.copy(
                                    movies = result.data ?: emptyList(), isLoading = false
                                )
                            }

                        }
                    }
            } else {
                _uiState.update {
                    it.copy(
                        error = "no hay internet cargando de cache.",
                        isLoading = false
                    )
                }
                when (val result = movieRepository.fetchTrendingMoviesCached()) {
                    is NetworkResult.Error -> TODO()
                    is NetworkResult.Loading -> TODO()
                    is NetworkResult.Success -> _uiState.update {
                        it.copy(
                            movies = result.data ?: emptyList(), isLoading = false
                        )
                    }
                }
            }
            //                  if (!Utils.hasInternetConnection(appContext))
            //                      _uiError.send("no hay internet"+ BuildConfig.API_KEY)
            //                     // _uiState.value = UiState.Failure("no hay internet")
            //                  else
            //                      _uiError.send("hay internet")
            //                      //_uiState.value = UiState.Failure("hay internet")
        }
    }


}