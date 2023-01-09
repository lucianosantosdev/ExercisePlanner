package dev.lucianosantos.exerciseplanner.fragments.forms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dev.lucianosantos.exerciseplanner.collections.ExerciseDetailsViewModel
import dev.lucianosantos.exerciseplanner.data.Exercise
import dev.lucianosantos.exerciseplanner.databinding.FragmentExerciseFormBinding

/**
 * A [Fragment] that displays a form to create a new routine.
 */
class ExerciseFormFragment : Fragment() {

    private var _binding: FragmentExerciseFormBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ExerciseDetailsViewModel by viewModels()

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
        val name = binding.exerciseNameEditText.text.toString()
//        val exercise = Exercise(
//            name = name,
////            routineId = routineId
//        )
//        viewModel.saveExercise()
    }
}