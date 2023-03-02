package dev.lucianosantos.exerciseplanner.core.viewmodels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.lucianosantos.exerciseplanner.core.database.entity.Routine
import dev.lucianosantos.exerciseplanner.core.model.ExerciseDomain
import dev.lucianosantos.exerciseplanner.core.repository.IRoutinesRepository
import dev.lucianosantos.exerciseplanner.core.repository.RoutinesRepository
import dev.lucianosantos.exerciseplanner.fragments.collections.model.ExerciseItem
import dev.lucianosantos.exerciseplanner.fragments.collections.model.RoutineItem
import dev.lucianosantos.exerciseplanner.fragments.collections.model.toRoutineItem
import kotlinx.coroutines.Dispatchers.IO
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

    fun getRoutineById() {
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

    fun saveRoutine(name: String, daysOfWeek: List<Int>) {
        viewModelScope.launch{
            if (routineId == null) {
                routinesRepository.addRoutine(name, daysOfWeek)
            } else {
                // routinesRepository.updateRoutine(routineId, name, daysOfWeek)
            }
        }
    }
}