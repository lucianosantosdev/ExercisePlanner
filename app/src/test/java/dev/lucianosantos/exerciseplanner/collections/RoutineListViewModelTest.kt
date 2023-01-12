package dev.lucianosantos.exerciseplanner.collections

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.lucianosantos.exerciseplanner.data.Routine
import dev.lucianosantos.exerciseplanner.repositories.TestRoutinesRepository
import dev.lucianosantos.exerciseplanner.utils.getOrAwaitValue
import dev.lucianosantos.exerciseplanner.viewmodels.RoutineListViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RoutineListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testRoutineRepository = TestRoutinesRepository()

    private val viewModel = RoutineListViewModel(testRoutineRepository)

    @Before
    fun setup() {
        testRoutineRepository.clear()
    }

    @Test
    fun `Verify uiState is initialized with Routines`() {
        // Arrange
        testRoutineRepository.add(
            Routine(id = "ID", "Test Routine", daysOfWeek = listOf(1,2,3,4,5,6,7))
        )

        // Act
        val routines = viewModel.routines.getOrAwaitValue()

        // Assert
        assert(routines.isNotEmpty())
        assert(routines[0].id == "ID")
        assert(routines[0].name == "Test Routine")
        assert(routines[0].daysOfWeek == listOf(1,2,3,4,5,6,7))
    }
}