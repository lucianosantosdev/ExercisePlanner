package dev.lucianosantos.exerciseplanner.core.viewmodels

import androidx.lifecycle.*
import dev.lucianosantos.exerciseplanner.core.repository.IExercisesRepository

class ExerciseListViewModel(
    private val exercisesRepository: IExercisesRepository,
    private val routineId: String
) : ViewModel() {

    private val _exercises  by lazy {
        exercisesRepository.fetchExercises(routineId)
    }
    val exercises get() = _exercises

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val exercisesRepository: IExercisesRepository,
        private val routineId: String
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ExerciseListViewModel(exercisesRepository, routineId) as T
        }
    }
}