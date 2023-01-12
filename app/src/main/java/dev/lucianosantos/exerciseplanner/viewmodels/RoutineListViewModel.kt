package dev.lucianosantos.exerciseplanner.viewmodels

import androidx.lifecycle.*
import dev.lucianosantos.exerciseplanner.data.Exercise
import dev.lucianosantos.exerciseplanner.data.Routine
import dev.lucianosantos.exerciseplanner.repositories.ExercisesRepository
import dev.lucianosantos.exerciseplanner.repositories.RoutinesRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RoutineListViewModel(
    private val routinesRepository: RoutinesRepository
) : ViewModel() {

    private var _routines = routinesRepository.fetchRoutines()
    val routines get() = _routines

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val routinesRepository: RoutinesRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RoutineListViewModel(routinesRepository) as T
        }
    }
}