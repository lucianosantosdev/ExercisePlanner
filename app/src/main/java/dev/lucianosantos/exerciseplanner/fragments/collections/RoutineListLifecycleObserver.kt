package dev.lucianosantos.exerciseplanner.fragments.collections

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import dev.lucianosantos.exerciseplanner.core.viewmodels.ExerciseListViewModel
import dev.lucianosantos.exerciseplanner.core.viewmodels.RoutineListViewModel

class RoutineListLifecycleObserver(
    private val viewModel: RoutineListViewModel
) : DefaultLifecycleObserver {

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        viewModel.onResume()
    }
}