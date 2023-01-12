package dev.lucianosantos.exerciseplanner.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.lucianosantos.exerciseplanner.data.Routine
import java.util.*

class TestRoutinesRepository : IRoutinesRepository {

    private val routineList = mutableListOf<Routine>()
    private val routineListLiveData = MutableLiveData<List<Routine>>()

    override suspend fun getById(id: String) = routineList.firstOrNull { routine -> routine.id == id }

    override fun fetchRoutines(): LiveData<List<Routine>> = routineListLiveData

    override suspend fun addRoutine(name: String, daysOfWeek: List<Int>) {
        routineList.add(Routine(
            id = UUID.randomUUID().toString(),
            name = name,
            daysOfWeek = daysOfWeek
        ))
        routineListLiveData.postValue(routineList)
    }

    public fun clear() {
        routineList.clear()
        routineListLiveData.postValue(routineList)
    }

    public fun add(routine: Routine) {
        routineList.add(routine)
        routineListLiveData.postValue(routineList)
    }
}