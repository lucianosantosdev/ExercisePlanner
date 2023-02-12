package dev.lucianosantos.exerciseplanner.collections

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.lucianosantos.exerciseplanner.core.database.entity.Exercise
import dev.lucianosantos.exerciseplanner.core.model.ExerciseDomain
import dev.lucianosantos.exerciseplanner.core.repository.IExerciseRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ExerciseFormViewModel @Inject constructor(
    private val repository: IExerciseRepository
) : ViewModel() {

    lateinit var exerciseId: String

    private val _exercise by lazy {
        viewModelScope.launch(IO) {
            repository.getById(exerciseId)
        }
    }
    val exercise get() = _exercise

    fun saveExercise(exercise: ExerciseDomain) {
        viewModelScope.launch(IO){
            repository.addExercise(exercise)
        }
    }
}