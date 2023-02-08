package dev.lucianosantos.exerciseplanner.collections

import androidx.lifecycle.*
import dev.lucianosantos.exerciseplanner.core.database.entity.Exercise
import dev.lucianosantos.exerciseplanner.core.repository.IExercisesRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ExerciseFormViewModel(private val repository: IExercisesRepository, private val exerciseId: String) : ViewModel() {

    private val _exercise by lazy {
        viewModelScope.launch(IO) {
            repository.getById(exerciseId)
        }
    }
    val exercise get() = _exercise

    fun saveExercise(exercise: Exercise) {
        viewModelScope.launch(IO){
            repository.addExercise(exercise)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: IExercisesRepository, private val exerciseId: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ExerciseFormViewModel(repository, exerciseId) as T
        }
    }
}