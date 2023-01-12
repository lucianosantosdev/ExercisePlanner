package dev.lucianosantos.exerciseplanner.collections

import androidx.lifecycle.*
import dev.lucianosantos.exerciseplanner.data.Exercise
import dev.lucianosantos.exerciseplanner.repositories.ExercisesRepository
import dev.lucianosantos.exerciseplanner.repositories.IExercisesRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ExerciseFormViewModel(private val repository: IExercisesRepository, private val exerciseId: String) : ViewModel() {

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