package dev.lucianosantos.exerciseplanner.fragments.forms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.lucianosantos.exerciseplanner.collections.ExerciseFormViewModel
import dev.lucianosantos.exerciseplanner.data.AppDatabase
import dev.lucianosantos.exerciseplanner.data.Exercise
import dev.lucianosantos.exerciseplanner.databinding.FragmentExerciseFormBinding
import dev.lucianosantos.exerciseplanner.repositories.ExercisesRepository
import java.util.*

/**
 * A [Fragment] that displays a form to create a new routine.
 */
class ExerciseFormFragment : Fragment() {

    private var _binding: FragmentExerciseFormBinding? = null
    private val binding get() = _binding!!

    private val arguments by navArgs<ExerciseFormFragmentArgs>()
    private val viewModel: ExerciseFormViewModel by viewModels {
        ExerciseFormViewModel.Factory(
            ExercisesRepository(AppDatabase.getInstance(requireContext()).exerciseDao()),
            arguments.routineId
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseFormBinding.inflate(inflater, container, false)
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
        viewModel.saveExercise(Exercise(
            id = UUID.randomUUID().toString(),
            routineId = arguments.routineId,
            name = binding.exerciseNameEditText.text.toString(),
            repetitions = 0,
            weight = 0,
            timeSeconds = 0,
            sessions = 0,
            intensity = 0,
            distance = 0,
        ))
    }
}