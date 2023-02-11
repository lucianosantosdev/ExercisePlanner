package dev.lucianosantos.exerciseplanner.fragments.forms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.lucianosantos.exerciseplanner.core.database.AppDatabase
import dev.lucianosantos.exerciseplanner.databinding.FragmentRoutineFormBinding
import dev.lucianosantos.exerciseplanner.core.repository.RoutinesRepository
import dev.lucianosantos.exerciseplanner.core.viewmodels.RoutineFormViewModel

/**
 * A [Fragment] that displays a form to create a new routine.
 */
@AndroidEntryPoint
class RoutineFormFragment : Fragment() {

    private var _binding: FragmentRoutineFormBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : RoutineFormViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoutineFormBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[RoutineFormViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {
            onSave()
            findNavController().navigateUp()
//            findNavController().navigate(R.id.action_routineFormFragment_to_exerciseListFragment)
        }
    }

    private fun onSave() {
        val name = binding.routineNameEditText.text.toString()
        val daysOfWeek = binding.daysOfWeekChipGroup.checkedChipIds

        viewModel.saveRoutine(name, daysOfWeek)
    }
}