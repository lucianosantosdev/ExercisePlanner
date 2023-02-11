package dev.lucianosantos.exerciseplanner.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import dev.lucianosantos.exerciseplanner.core.database.entity.Exercise
import dev.lucianosantos.exerciseplanner.core.viewmodels.ExerciseListViewModel
import dev.lucianosantos.exerciseplanner.core.repository.IExerciseRepository
import dev.lucianosantos.exerciseplanner.utils.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class ExerciseListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var testExercisesRepository : IExerciseRepository

    private lateinit var viewModel: ExerciseListViewModel

    @Before
    fun setup() {
        testExercisesRepository = Mockito.mock(IExerciseRepository::class.java)
        viewModel = ExerciseListViewModel(testExercisesRepository, "RoutineId")
    }

    @Test
    fun `Verify uiState is initialized with Exercises`() {
        // Arrange
        val newExercise = Exercise(id = "ID", "RoutineId", "Test Exercises", 1,2,3,4,5,6)
        val fetchExerciseResponse = MutableLiveData<List<Exercise>>()
        fetchExerciseResponse.value = listOf(newExercise)
        Mockito.`when`(testExercisesRepository.fetchExercises("RoutineId")).thenReturn(fetchExerciseResponse)

        // Act
        val exercises = viewModel.exercises.getOrAwaitValue()

        // Assert
        assert(exercises.isNotEmpty())
        assert(exercises[0] == newExercise)
    }
}