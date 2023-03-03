package dev.lucianosantos.exerciseplanner.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.lucianosantos.exerciseplanner.core.model.ExerciseDomain
import dev.lucianosantos.exerciseplanner.core.repository.IExerciseRepository
import dev.lucianosantos.exerciseplanner.core.viewmodels.ExerciseListViewModel
import dev.lucianosantos.exerciseplanner.utils.MainDispatcherRule
import dev.lucianosantos.exerciseplanner.utils.getOrAwaitValue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class ExerciseListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var testExercisesRepository : IExerciseRepository

    private lateinit var viewModel: ExerciseListViewModel

    @Before
    fun setup() {
        testExercisesRepository = Mockito.mock(IExerciseRepository::class.java)
        viewModel = ExerciseListViewModel(testExercisesRepository)
        viewModel.routineId = "RoutineId"
    }

    @Test
    fun `Verify uiState is initialized with Exercises`() = runTest {
        // Arrange
        val newExercise = ExerciseDomain(id = "ID", "RoutineId", "Test Exercises", 1,2,3,4,5,6)
        val fetchExerciseResponse = listOf(newExercise)
        Mockito.`when`(testExercisesRepository.fetchExercises("RoutineId")).thenReturn(fetchExerciseResponse)

        // Act
        viewModel.onResume()
        val uiState = viewModel.uiState.getOrAwaitValue()

        // Assert
        assert(uiState.exerciseItemList.isNotEmpty())
        assert(uiState.exerciseItemList[0].id == newExercise.id)
        assert(uiState.exerciseItemList[0].name == newExercise.name)
        assert(uiState.exerciseItemList[0].sessions == newExercise.sessions)
        assert(uiState.exerciseItemList[0].repetitions == newExercise.repetitions)
    }
}