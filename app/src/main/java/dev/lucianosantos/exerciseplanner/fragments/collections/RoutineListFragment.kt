package dev.lucianosantos.exerciseplanner.fragments.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.lucianosantos.exerciseplanner.R
import dev.lucianosantos.exerciseplanner.core.viewmodels.RoutineListViewModel
import dev.lucianosantos.exerciseplanner.databinding.FragmentRoutineListBinding
import dev.lucianosantos.exerciseplanner.fragments.collections.adapters.RoutineListAdapter

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class RoutineListFragment : Fragment() {

    private var _binding: FragmentRoutineListBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: RoutineListAdapter

    private lateinit var routineListViewModel: RoutineListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        routineListViewModel = ViewModelProvider(this)[RoutineListViewModel::class.java]
        lifecycle.addObserver(RoutineListLifecycleObserver(routineListViewModel))
        adapter = RoutineListAdapter (
            onItemClickListener = { id ->
                onRoutineItemClicked(id)
            },
            onEditClickListener = { id ->
                onRoutineEditClicked(id)
            }

        )
    }

    private fun onRoutineItemClicked(id: String) {
        val action = RoutineListFragmentDirections.actionRoutineListFragmentToExerciseListFragment(id)
        findNavController().navigate(action)
    }

    private fun onRoutineEditClicked(id: String) {
        val action = RoutineListFragmentDirections.actionRoutineListFragmentToRoutineFormFragment(id)
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

        routineListViewModel.uiState.observe(viewLifecycleOwner) {
            adapter.updateRoutines(it.routineItemList)
        }
    }

    private fun addDividerDecoration() {
        binding.routineRecyclerView.addItemDecoration(ListItemDecoration(requireContext()))
    }
}