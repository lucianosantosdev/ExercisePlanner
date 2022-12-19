package dev.lucianosantos.exerciseplanner.collections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.lucianosantos.exerciseplanner.repositories.RoutinesRepository

class RoutineListViewModel(private val repository: RoutinesRepository) : ViewModel() {

    private val uiState: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>(UiState(routineItemList = repository.fetchRoutines()))
    }

    fun stateOnceAndStream(): LiveData<UiState> {
        return uiState
    }

    fun addRoutine(name: String, daysOfWeek: List<Int>) {
        repository.addRoutine(name, daysOfWeek)
        refreshRoutineList()
    }

    private fun refreshRoutineList() {
        uiState.value?.let {  currentUiState ->
            uiState.value = currentUiState.copy(
                routineItemList = repository.fetchRoutines()
            )
        }
    }

    data class UiState(val routineItemList: List<RoutineItem>)

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: RoutinesRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RoutineListViewModel(repository) as T
        }
    }
}