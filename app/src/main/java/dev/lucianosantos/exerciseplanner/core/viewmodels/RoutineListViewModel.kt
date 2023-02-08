package dev.lucianosantos.exerciseplanner.core.viewmodels

import androidx.lifecycle.*
import dev.lucianosantos.exerciseplanner.core.repository.IRoutinesRepository

class RoutineListViewModel(
    private val routinesRepository: IRoutinesRepository
) : ViewModel() {

    private val _routines by lazy {
        routinesRepository.fetchRoutines()
    }
    val routines get() = _routines

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val routinesRepository: IRoutinesRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RoutineListViewModel(routinesRepository) as T
        }
    }
}