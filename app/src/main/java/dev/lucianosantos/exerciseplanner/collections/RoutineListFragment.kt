package dev.lucianosantos.exerciseplanner.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dev.lucianosantos.exerciseplanner.R
import dev.lucianosantos.exerciseplanner.data.AppDatabase
import dev.lucianosantos.exerciseplanner.databinding.FragmentRoutineListBinding
import dev.lucianosantos.exerciseplanner.repositories.RoutinesRepository

/**
 * A fragment representing a list of Items.
 */
class RoutineListFragment : Fragment() {

    private var _binding: FragmentRoutineListBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: RoutineListAdapter

    private val viewModel: RoutineListViewModel by activityViewModels {
        RoutineListViewModel.Factory(RoutinesRepository(AppDatabase.getInstance(requireContext()).routineDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = RoutineListAdapter{ id ->
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

        viewModel.stateOnceAndStream().observe(viewLifecycleOwner) {
            adapter.updateRoutines(it.routineItemList)
        }
    }

    private fun addDividerDecoration() {
        binding.routineRecyclerView.addItemDecoration(ListItemDecoration(requireContext()))
    }
}