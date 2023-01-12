package dev.lucianosantos.exerciseplanner.viewmodels

import androidx.lifecycle.*
import androidx.lifecycle.LiveDataReactiveStreams
import dev.lucianosantos.exerciseplanner.data.Exercise
import dev.lucianosantos.exerciseplanner.data.Routine
import dev.lucianosantos.exerciseplanner.repositories.ExercisesRepository
import dev.lucianosantos.exerciseplanner.repositories.IExercisesRepository
import dev.lucianosantos.exerciseplanner.repositories.RoutinesRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ExerciseListViewModel(
    private val exercisesRepository: IExercisesRepository,
    private val routineId: String
) : ViewModel() {

    private var _exercises = exercisesRepository.fetchExercises(routineId)
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