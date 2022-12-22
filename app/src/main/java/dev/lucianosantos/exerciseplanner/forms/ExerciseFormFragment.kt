package dev.lucianosantos.exerciseplanner.forms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dev.lucianosantos.exerciseplanner.collections.ExerciseListViewModel
import dev.lucianosantos.exerciseplanner.dummy.MockExercises
import dev.lucianosantos.exerciseplanner.R
import dev.lucianosantos.exerciseplanner.databinding.FragmentExerciseFormBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A [Fragment] that displays a form to create a new routine.
 */
class ExerciseFormFragment : Fragment() {

    private var _binding: FragmentExerciseFormBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ExerciseListViewModel by activityViewModels {
        ExerciseListViewModel.Factory(MockExercises)
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
        val name = binding.exerciseNameEditText.text.toString()

        viewModel.addExercise(name)
    }
}