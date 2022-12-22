package dev.lucianosantos.exerciseplanner.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import dev.lucianosantos.exerciseplanner.R
import dev.lucianosantos.exerciseplanner.databinding.FragmentExerciseListBinding
import dev.lucianosantos.exerciseplanner.dummy.MockExercises
import dev.lucianosantos.exerciseplanner.dummy.MockRoutines

/**
 * A fragment representing a list of Items.
 */
class ExerciseListFragment : Fragment() {

    private var _binding: FragmentExerciseListBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: ExerciseListAdapter

    private val viewModel: ExerciseListViewModel by activityViewModels {
        ExerciseListViewModel.Factory(MockExercises)
    }

    private val routinesViewModel: RoutineListViewModel by activityViewModels {
        RoutineListViewModel.Factory(MockRoutines)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ExerciseListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exerciseRecyclerView.adapter = adapter
        addDividerDecoration()

        binding.saveButton.setOnClickListener {
            findNavController().navigate(R.id.action_exerciseListFragment_pop_to_routineListFragment)
        }

        binding.addExerciseFloatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_exerciseListFragment_to_exerciseFormFragment)
        }

        viewModel.stateOnceAndStream().observe(viewLifecycleOwner) {
            adapter.updateExercises(it.exerciseItemList)
        }
    }

    private fun addDividerDecoration() {
        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.exerciseRecyclerView.addItemDecoration(divider)
    }
}