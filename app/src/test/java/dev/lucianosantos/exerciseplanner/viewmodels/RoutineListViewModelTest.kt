package dev.lucianosantos.exerciseplanner.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.lucianosantos.exerciseplanner.core.model.RoutineDomain
import dev.lucianosantos.exerciseplanner.core.repository.IRoutinesRepository
import dev.lucianosantos.exerciseplanner.core.viewmodels.RoutineListViewModel
import dev.lucianosantos.exerciseplanner.utils.MainDispatcherRule
import dev.lucianosantos.exerciseplanner.utils.getOrAwaitValue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class RoutineListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var routineRepository: IRoutinesRepository

    private lateinit var viewModel: RoutineListViewModel

    @Before
    fun setup() {
        routineRepository = Mockito.mock(IRoutinesRepository::class.java)
        viewModel = RoutineListViewModel(routineRepository)
    }

    @Test
    fun `Verify uiState is initialized with Routines`() = runTest {
        // Arrange
        val routine = RoutineDomain(id = "ID", "Test Routine", daysOfWeek = listOf(1,2,3,4,5,6,7))
        val fetchRoutineResults = listOf(routine)
        Mockito.`when`(routineRepository.fetchRoutines()).thenReturn(fetchRoutineResults)


        // Act
        viewModel.onResume()
        val routines = viewModel.uiState.getOrAwaitValue()

        // Assert
        assert(routines.routineItemList.isNotEmpty())
        assert(routines.routineItemList[0].id == routine.id)
        assert(routines.routineItemList[0].name == routine.name)
    }
}