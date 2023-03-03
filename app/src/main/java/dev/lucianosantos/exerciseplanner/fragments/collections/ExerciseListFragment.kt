package dev.lucianosantos.exerciseplanner.fragments.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import dev.lucianosantos.exerciseplanner.R
import dev.lucianosantos.exerciseplanner.core.viewmodels.ExerciseListViewModel
import dev.lucianosantos.exerciseplanner.databinding.FragmentExerciseListBinding
import dev.lucianosantos.exerciseplanner.fragments.collections.adapters.ExerciseListAdapter

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class ExerciseListFragment : Fragment() {

    private var _binding: FragmentExerciseListBinding? = null
    private val binding get() = _binding!!

    private val arguments by navArgs<ExerciseListFragmentArgs>()

    private lateinit var exerciseListViewModel: ExerciseListViewModel

    private lateinit var adapter: ExerciseListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exerciseListViewModel = ViewModelProvider(this)[ExerciseListViewModel::class.java]
        exerciseListViewModel.routineId = arguments.routineId
        lifecycle.addObserver(ExerciseListLifecycleObserver(exerciseListViewModel))
        adapter = ExerciseListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseListBinding.inflate(inflater, container, false)
        val routineId = arguments.routineId
        Toast.makeText(requireContext(), routineId, Toast.LENGTH_SHORT).show()
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
            val routineId = arguments.routineId
            val action = ExerciseListFragmentDirections.actionExerciseListFragmentToExerciseFormFragment(routineId)
            findNavController().navigate(action)
        }

        // Fetch exercises
        exerciseListViewModel.uiState.observe(viewLifecycleOwner) {
            adapter.updateExercises(it.exerciseItemList)
        }
    }

    private fun addDividerDecoration() {
        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.exerciseRecyclerView.addItemDecoration(divider)
    }
}