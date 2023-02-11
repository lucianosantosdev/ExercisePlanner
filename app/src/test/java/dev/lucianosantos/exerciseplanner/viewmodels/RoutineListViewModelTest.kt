package dev.lucianosantos.exerciseplanner.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import dev.lucianosantos.exerciseplanner.core.database.entity.Routine
import dev.lucianosantos.exerciseplanner.core.viewmodels.RoutineListViewModel
import dev.lucianosantos.exerciseplanner.core.repository.IRoutinesRepository
import dev.lucianosantos.exerciseplanner.utils.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class RoutineListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var routineRepository: IRoutinesRepository

    private lateinit var viewModel: RoutineListViewModel

    @Before
    fun setup() {
        routineRepository = Mockito.mock(IRoutinesRepository::class.java)
        viewModel = RoutineListViewModel(routineRepository)
    }

    @Test
    fun `Verify uiState is initialized with Routines`() {
        // Arrange
        val routine = Routine(id = "ID", "Test Routine", daysOfWeek = listOf(1,2,3,4,5,6,7))
        val fetchRoutineResults = MutableLiveData<List<Routine>>()
        fetchRoutineResults.value = listOf(routine)
        Mockito.`when`(routineRepository.fetchRoutines()).thenReturn(fetchRoutineResults)


        // Act
        val routines = viewModel.routines.getOrAwaitValue()

        // Assert
        assert(routines.isNotEmpty())
        assert(routines[0] == routine)
    }
}