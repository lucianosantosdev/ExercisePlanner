package dev.lucianosantos.exerciseplanner.fragments.forms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import dev.lucianosantos.exerciseplanner.databinding.FragmentRoutineFormBinding
import dev.lucianosantos.exerciseplanner.core.viewmodels.RoutineFormViewModel

/**
 * A [Fragment] that displays a form to create a new routine.
 */
@AndroidEntryPoint
class RoutineFormFragment : Fragment() {

    private var _binding: FragmentRoutineFormBinding? = null
    private val binding get() = _binding!!

    private val arguments by navArgs<RoutineFormFragmentArgs>()

    private lateinit var routineFormViewModel : RoutineFormViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoutineFormBinding.inflate(inflater, container, false)
        routineFormViewModel = ViewModelProvider(this)[RoutineFormViewModel::class.java]
        routineFormViewModel.routineId = arguments?.routineId
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        routineFormViewModel.uiState.observe(viewLifecycleOwner) {
            binding.routineNameEditText.setText(it.routineItem?.name)

        }
        binding.saveButton.setOnClickListener {
            onSave()
            findNavController().navigateUp()
//            findNavController().navigate(R.id.action_routineFormFragment_to_exerciseListFragment)
        }
    }

    private fun onSave() {
        val name = binding.routineNameEditText.text.toString()
        val daysOfWeek = binding.daysOfWeekChipGroup.checkedChipIds

        routineFormViewModel.saveRoutine(name, daysOfWeek)
    }
}