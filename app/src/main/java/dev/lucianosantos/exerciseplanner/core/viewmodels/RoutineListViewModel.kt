package dev.lucianosantos.exerciseplanner.core.viewmodels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.lucianosantos.exerciseplanner.core.repository.IRoutinesRepository
import javax.inject.Inject

@HiltViewModel
class RoutineListViewModel @Inject constructor(
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