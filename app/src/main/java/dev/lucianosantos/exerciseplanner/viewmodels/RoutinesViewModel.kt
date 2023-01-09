package dev.lucianosantos.exerciseplanner.viewmodels

import androidx.lifecycle.*
import dev.lucianosantos.exerciseplanner.data.Exercise
import dev.lucianosantos.exerciseplanner.data.Routine
import dev.lucianosantos.exerciseplanner.repositories.ExercisesRepository
import dev.lucianosantos.exerciseplanner.repositories.RoutinesRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RoutinesViewModel(
    private val routinesRepository: RoutinesRepository,
    private val exercisesRepository: ExercisesRepository,
) : ViewModel() {

    private val routines = MutableLiveData<List<Routine>>()
    private val exercises = MutableLiveData<List<Exercise>>()

    init {
        viewModelScope.launch(IO) {
            routines.postValue(routinesRepository.fetchRoutines())
            exercises.postValue(exercisesRepository.fetchExercises())
        }
    }

    fun getRoutines() : LiveData<List<Routine>> = routines

    fun getExercises() : LiveData<List<Exercise>> = exercises

//    fun stateOnceAndStream(): LiveData<UiState> {
//        return uiState
//    }


    fun addRoutine(name: String, daysOfWeek: List<Int>) {
        viewModelScope.launch{
            routinesRepository.addRoutine(name, daysOfWeek)
            refreshRoutineList()
        }
    }

    private fun refreshRoutineList() {
        routines.value?.let {  currentUiState ->
            viewModelScope.launch(IO) {
                routines.value = routinesRepository.fetchRoutines()
            }
        }
    }

//    data class UiState(val routineItemList: List<Routine>)

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val routinesRepository: RoutinesRepository,
        private val exercisesRepository: ExercisesRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RoutinesViewModel(routinesRepository, exercisesRepository) as T
        }
    }
}