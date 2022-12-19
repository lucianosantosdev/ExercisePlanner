package dev.lucianosantos.exerciseplanner.collections

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.divider.MaterialDividerItemDecoration
import dev.lucianosantos.exerciseplanner.R
import dev.lucianosantos.exerciseplanner.databinding.FragmentRoutineListBinding
import dev.lucianosantos.exerciseplanner.dummy.MockRoutines

/**
 * A fragment representing a list of Items.
 */
class RoutineListFragment : Fragment() {

    private var _binding: FragmentRoutineListBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: RoutineListAdapter

    private val viewModel: RoutineListViewModel by activityViewModels {
        RoutineListViewModel.Factory(MockRoutines)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = RoutineListAdapter()
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
            findNavController().navigate(R.id.action_routineItemFragment_to_routineFormFragment)
        }

        viewModel.stateOnceAndStream().observe(viewLifecycleOwner) {
            adapter.updateRoutines(it.routineItemList)
        }
    }

    private fun addDividerDecoration() {
        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.routineRecyclerView.addItemDecoration(divider)
    }
}