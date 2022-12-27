package dev.lucianosantos.exerciseplanner.forms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dev.lucianosantos.exerciseplanner.collections.RoutineListViewModel
import dev.lucianosantos.exerciseplanner.databinding.FragmentRoutineFormBinding
import dev.lucianosantos.exerciseplanner.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A [Fragment] that displays a form to create a new routine.
 */
class RoutineFormFragment : Fragment() {

    private var _binding: FragmentRoutineFormBinding? = null

    private val binding get() = _binding!!

    private val viewModel: RoutineListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoutineFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {
            onSave()
            findNavController().navigate(R.id.action_routineFormFragment_to_exerciseListFragment)
        }
    }

    private fun onSave() {
        val name = binding.routineNameEditText.text.toString()
        val daysOfWeek = binding.daysOfWeekChipGroup.checkedChipIds

        viewModel.addRoutine(name, daysOfWeek)
    }
}