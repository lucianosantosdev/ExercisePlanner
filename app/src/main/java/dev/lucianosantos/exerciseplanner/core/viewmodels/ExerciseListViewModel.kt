package dev.lucianosantos.exerciseplanner.core.viewmodels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.lucianosantos.exerciseplanner.core.database.entity.Exercise
import dev.lucianosantos.exerciseplanner.core.repository.IExerciseRepository
import dev.lucianosantos.exerciseplanner.fragments.collections.model.ExerciseItem
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    private val exercisesRepository: IExerciseRepository,

) : ViewModel() {

    lateinit var routineId: String

    private val _uiState: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>(UiState(exerciseItemList = emptyList()))
    }
    val uiState get() : LiveData<UiState> = _uiState

    fun onResume() {
        viewModelScope.launch {
            refreshExerciseList()
        }
    }

    private suspend fun refreshExerciseList() {
        _uiState.postValue(
            UiState(
                exerciseItemList = exercisesRepository.fetchExercises(routineId)
                    .map {
                        ExerciseItem(
                            id = it.id,
                            name = it.name,
                            sessions = it.sessions?: 0,
                            repetitions = it.repetitions?: 0,
                        )
                    }
            )
        )
    }

    data class UiState(val exerciseItemList: List<ExerciseItem>)
}