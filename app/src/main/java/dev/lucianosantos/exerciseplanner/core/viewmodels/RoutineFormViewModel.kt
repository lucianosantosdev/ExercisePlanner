package dev.lucianosantos.exerciseplanner.core.viewmodels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.lucianosantos.exerciseplanner.core.database.entity.Routine
import dev.lucianosantos.exerciseplanner.core.repository.IRoutinesRepository
import dev.lucianosantos.exerciseplanner.core.repository.RoutinesRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutineFormViewModel @Inject constructor(
    private val routinesRepository: IRoutinesRepository,
) : ViewModel() {

    var routineId: String? = null

    private val _routine by lazy {
        if (routineId != null) {
            viewModelScope.launch(IO) {
                routinesRepository.getById(routineId!!)
            }
        } else {
            Routine()
        }
    }
    val routine get() = _routine

    fun saveRoutine(name: String, daysOfWeek: List<Int>) {
        viewModelScope.launch{
            routinesRepository.addRoutine(name, daysOfWeek)
        }
    }
}