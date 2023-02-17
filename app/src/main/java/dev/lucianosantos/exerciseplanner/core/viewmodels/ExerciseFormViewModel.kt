package dev.lucianosantos.exerciseplanner.collections

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.lucianosantos.exerciseplanner.core.database.entity.Exercise
import dev.lucianosantos.exerciseplanner.core.model.ExerciseDomain
import dev.lucianosantos.exerciseplanner.core.repository.IExerciseRepository
import dev.lucianosantos.exerciseplanner.fragments.collections.model.ExerciseItem
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ExerciseFormViewModel @Inject constructor(
    private val repository: IExerciseRepository
) : ViewModel() {

    lateinit var exerciseId: String

    private val _uiState: MutableLiveData<UiState>  by lazy {
        MutableLiveData<UiState>(UiState(exerciseItem = null))
    }
    val uiState get() : LiveData<UiState> = _uiState

    fun saveExercise(exercise: ExerciseDomain) {
        viewModelScope.launch(IO){
            repository.addExercise(exercise)
        }
    }

    data class UiState(val exerciseItem: ExerciseItem?)
}