package dev.lucianosantos.exerciseplanner.viewmodels

import androidx.lifecycle.*
import dev.lucianosantos.exerciseplanner.data.Routine
import dev.lucianosantos.exerciseplanner.repositories.RoutinesRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RoutineFormViewModel(
    private val routinesRepository: RoutinesRepository,
    private val routineId: String?
) : ViewModel() {

    private val routine = MutableLiveData<Routine>()

    init {
        fetchRoutine()
    }

    private fun fetchRoutine() {
        if (routineId != null) {
            viewModelScope.launch(IO) {
                routine.postValue(routinesRepository.getById(routineId))
            }
        } else {
            routine.postValue(Routine())
        }
    }

    fun getRoutine() : LiveData<Routine> = routine

    fun saveRoutine(name: String, daysOfWeek: List<Int>) {
        viewModelScope.launch{
            routinesRepository.addRoutine(name, daysOfWeek)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val routinesRepository: RoutinesRepository, private val routineId: String?) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RoutineFormViewModel(routinesRepository, routineId) as T
        }
    }
}