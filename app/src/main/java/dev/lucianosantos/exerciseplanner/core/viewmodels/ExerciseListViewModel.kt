package dev.lucianosantos.exerciseplanner.core.viewmodels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.lucianosantos.exerciseplanner.core.repository.IExerciseRepository
import javax.inject.Inject

@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    private val exercisesRepository: IExerciseRepository,

) : ViewModel() {

    lateinit var routineId: String

    private val _exercises  by lazy {
        exercisesRepository.fetchExercises(routineId)
    }
    val exercises get() = _exercises
}