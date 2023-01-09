package dev.lucianosantos.exerciseplanner.collections

import androidx.lifecycle.*
import dev.lucianosantos.exerciseplanner.data.Exercise
import dev.lucianosantos.exerciseplanner.data.ExerciseDao
import dev.lucianosantos.exerciseplanner.repositories.ExercisesRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ExerciseDetailsViewModel(private val repository: ExercisesRepository, private val exerciseId: String) : ViewModel() {

    private val exercise = MutableLiveData<Exercise>()

    init {
        fetchExercise()
    }

    private fun fetchExercise() {
        viewModelScope.launch(IO) {
            exercise.postValue(repository.getById(exerciseId))
        }
    }

    fun getExercise(): LiveData<Exercise> = exercise

    fun saveExercise(exercise: Exercise) {
        // TODO: update exercise
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: ExercisesRepository, private val exerciseId: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ExerciseDetailsViewModel(repository, exerciseId) as T
        }
    }
}