package dev.lucianosantos.exerciseplanner.fragments.forms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import dev.lucianosantos.exerciseplanner.collections.ExerciseFormViewModel
import dev.lucianosantos.exerciseplanner.core.database.AppDatabase
import dev.lucianosantos.exerciseplanner.core.database.entity.Exercise
import dev.lucianosantos.exerciseplanner.core.model.ExerciseDomain
import dev.lucianosantos.exerciseplanner.databinding.FragmentExerciseFormBinding
import dev.lucianosantos.exerciseplanner.core.repository.ExerciseRepository
import java.util.*

/**
 * A [Fragment] that displays a form to create a new routine.
 */
@AndroidEntryPoint
class ExerciseFormFragment : Fragment() {

    private var _binding: FragmentExerciseFormBinding? = null
    private val binding get() = _binding!!

    private val arguments by navArgs<ExerciseFormFragmentArgs>()

    private lateinit var viewModel: ExerciseFormViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseFormBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[ExerciseFormViewModel::class.java]
        viewModel.exerciseId = arguments.routineId
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {
            onSave()
            findNavController().navigateUp()
        }
    }

    private fun onSave() {
        viewModel.saveExercise(
            ExerciseDomain(
                id = UUID.randomUUID().toString(),
                routineId = arguments.routineId,
                name = binding.exerciseNameEditText.text.toString(),
                repetitions = 0,
                weight = 0,
                timeSeconds = 0,
                sessions = 0,
                intensity = 0,
                distance = 0,
            )
        )
    }
}