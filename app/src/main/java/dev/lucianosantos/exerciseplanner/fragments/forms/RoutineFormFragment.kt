package dev.lucianosantos.exerciseplanner.fragments.forms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import dev.lucianosantos.exerciseplanner.core.viewmodels.RoutineFormViewModel
import dev.lucianosantos.exerciseplanner.databinding.FragmentRoutineFormBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
            if(it.routineItem == null) {
                return@observe
            }

            binding.routineNameEditText.setText(it.routineItem.name)
            binding.daysOfWeekChipGroup.children.forEachIndexed { index, item ->
                (item as Chip).isChecked = it.routineItem.daysOfWeek.contains(index + 1)
            }
        }
        binding.saveButton.setOnClickListener {
            onSave()
        }
    }

    private fun onSave() {
        val routineName = binding.routineNameEditText.text.toString()

        val daysOfWeekSelected =   mutableListOf<Int>()
        for (id in binding.daysOfWeekChipGroup.checkedChipIds) {
            val chip = binding.daysOfWeekChipGroup.findViewById<Chip>(id)
            val position = binding.daysOfWeekChipGroup.indexOfChild(chip)

            daysOfWeekSelected.add(position + 1)
        }

        lifecycleScope.launch(Dispatchers.IO) {
            routineFormViewModel.saveRoutine(routineName, daysOfWeekSelected)
            withContext(Dispatchers.Main) {
                findNavController().navigateUp()
            }
        }
    }
}