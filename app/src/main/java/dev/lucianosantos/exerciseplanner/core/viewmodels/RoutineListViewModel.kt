package dev.lucianosantos.exerciseplanner.core.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.lucianosantos.exerciseplanner.core.repository.IRoutinesRepository
import dev.lucianosantos.exerciseplanner.fragments.collections.model.RoutineItem
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutineListViewModel @Inject constructor(
    private val routinesRepository: IRoutinesRepository
) : ViewModel() {

    private val _uiState: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>(UiState(routineItemList = emptyList()))
    }
    val uiState get() : LiveData<UiState> = _uiState

    fun onResume() {
        viewModelScope.launch {
            refreshRoutineList()
        }
    }

    private suspend fun refreshRoutineList() {
        _uiState.postValue(
            UiState(
                routineItemList = routinesRepository.fetchRoutines()
                    .map {
                        RoutineItem(
                            id = it.id,
                            name = it.name,
                            daysOfWeek = it.daysOfWeek
                        )
                    }
            )
        )
    }

    data class UiState(val routineItemList: List<RoutineItem>)
}