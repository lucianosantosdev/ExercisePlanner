package dev.lucianosantos.exerciseplanner.collections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.lucianosantos.exerciseplanner.repositories.ExercisesRepository

class ExerciseListViewModel(private val repository: ExercisesRepository) : ViewModel() {

    private val uiState: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>(UiState(exerciseItemList = repository.fetchExercises()))
    }

    fun stateOnceAndStream(): LiveData<UiState> {
        return uiState
    }

    fun addExercise(name: String) {
        repository.addExercise(name)
        refreshExerciseList()
    }

    private fun refreshExerciseList() {
        uiState.value?.let {  currentUiState ->
            uiState.value = currentUiState.copy(
                exerciseItemList = repository.fetchExercises()
            )
        }
    }

    data class UiState(val exerciseItemList: List<ExerciseItem>)

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: ExercisesRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ExerciseListViewModel(repository) as T
        }
    }
}