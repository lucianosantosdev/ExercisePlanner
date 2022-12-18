package dev.lucianosantos.exerciseplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.divider.MaterialDividerItemDecoration
import dev.lucianosantos.exerciseplanner.databinding.FragmentRoutineListBinding
import dev.lucianosantos.exerciseplanner.dummy.MockRoutineItem

/**
 * A fragment representing a list of Items.
 */
class RoutineListFragment : Fragment() {

    private var _binding: FragmentRoutineListBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: RoutineListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = RoutineListAdapter(MockRoutineItem.routineList)
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
    }

    private fun addDividerDecoration() {
        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.routineRecyclerView.addItemDecoration(divider)
    }
}