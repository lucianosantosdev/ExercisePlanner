package dev.lucianosantos.exerciseplanner.collections

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.lucianosantos.exerciseplanner.repositories.TestRoutinesRepository
import dev.lucianosantos.exerciseplanner.utils.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RoutineListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testRoutineRepository = TestRoutinesRepository()

    private val viewModel = RoutineListViewModel(repository = testRoutineRepository)

    @Before
    fun setup() {
        testRoutineRepository.routineList.clear()
    }

    @Test
    fun `Verify uiState is initialized with Routines`() {
        // Arrange
        testRoutineRepository.routineList.add(
            RoutineItem(id = "ID", "Test Routine", daysOfWeek = listOf(1,2,3,4,5,6,7))
        )

        // Act
        val uiState = viewModel.stateOnceAndStream().getOrAwaitValue()

        // Assert
        assert(uiState.routineItemList.isNotEmpty())
        assert(uiState.routineItemList[0].id == "ID")
        assert(uiState.routineItemList[0].name == "Test Routine")
        assert(uiState.routineItemList[0].daysOfWeek == listOf(1,2,3,4,5,6,7))
    }
}