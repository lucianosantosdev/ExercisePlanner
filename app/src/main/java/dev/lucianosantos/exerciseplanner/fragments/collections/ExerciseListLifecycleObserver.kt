package dev.lucianosantos.exerciseplanner.fragments.collections

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import dev.lucianosantos.exerciseplanner.core.viewmodels.ExerciseListViewModel

class ExerciseListLifecycleObserver(
    private val viewModel: ExerciseListViewModel
) : DefaultLifecycleObserver {

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        viewModel.onResume()
    }
}