package dev.lucianosantos.exerciseplanner.fragments.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.lucianosantos.exerciseplanner.R
import dev.lucianosantos.exerciseplanner.adapters.RoutineListAdapter
import dev.lucianosantos.exerciseplanner.core.database.AppDatabase
import dev.lucianosantos.exerciseplanner.databinding.FragmentRoutineListBinding
import dev.lucianosantos.exerciseplanner.core.repository.RoutinesRepository
import dev.lucianosantos.exerciseplanner.core.viewmodels.RoutineListViewModel

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class RoutineListFragment : Fragment() {

    private var _binding: FragmentRoutineListBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: RoutineListAdapter

    private lateinit var viewModel: RoutineListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RoutineListViewModel::class.java]
        adapter = RoutineListAdapter { id ->
            onRoutineItemSelected(id)
        }
    }

    private fun onRoutineItemSelected(id: String) {
        val action = RoutineListFragmentDirections.actionRoutineListFragmentToExerciseListFragment(id)
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoutineListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.routineRecyclerView.adapter = adapter
        addDividerDecoration()

        binding.addRoutineFloatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_routineListFragment_to_routineFormFragment)
        }

        viewModel.routines.observe(viewLifecycleOwner) {
            adapter.updateRoutines(it)
        }
    }

    private fun addDividerDecoration() {
        binding.routineRecyclerView.addItemDecoration(ListItemDecoration(requireContext()))
    }
}