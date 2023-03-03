package dev.lucianosantos.exerciseplanner.core.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.lucianosantos.exerciseplanner.core.repository.IRoutinesRepository
import dev.lucianosantos.exerciseplanner.fragments.collections.model.RoutineItem
import dev.lucianosantos.exerciseplanner.fragments.collections.model.toRoutineItem
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutineFormViewModel @Inject constructor(
    private val routinesRepository: IRoutinesRepository,
) : ViewModel() {
    var routineId: String? = null
        set(value) {
            field = value
            getRoutineById()
        }

    private val _uiState: MutableLiveData<UiState>  by lazy {
        MutableLiveData<UiState>(UiState(routineItem = null))
    }
    val uiState get() : LiveData<UiState> = _uiState

    private fun getRoutineById() {
        if (routineId == null) return

        viewModelScope.launch {
            _uiState.postValue(
                UiState(
                    routineItem = routinesRepository.getById(routineId!!)?.toRoutineItem()
                )
            )
        }
    }

    data class UiState(val routineItem: RoutineItem?)

    suspend fun saveRoutine(name: String, daysOfWeek: List<Int>) : String =
    if (routineId == null) {
        routinesRepository.addRoutine(name, daysOfWeek)
    } else {
        routinesRepository.updateRoutine(routineId!!, name, daysOfWeek)
        routineId!!
    }
}