package com.example.sorrisomarcado.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sorrisomarcado.data.Consulta
import com.example.sorrisomarcado.data.ConsultaRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(val consultas: List<Consulta>) : HomeUiState
    object Empty : HomeUiState
}

class HomeViewModel(private val repository: ConsultaRepository) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = repository.getAllConsultas()
        .map { consultas ->
            if (consultas.isEmpty()) HomeUiState.Empty else HomeUiState.Success(consultas)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState.Loading
        )

    companion object {
        fun provideFactory(repository: ConsultaRepository): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                    return HomeViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}
