package dev.lucianosantos.exerciseplanner

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import dev.lucianosantos.exerciseplanner.items.RoutineItem
import dev.lucianosantos.exerciseplanner.databinding.RoutineItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class RoutineListAdapter(
    private val values: List<RoutineItem>
) : RecyclerView.Adapter<RoutineListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            RoutineItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: RoutineItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(routine: RoutineItem) {
            binding.routineNameTextView.text = routine.name
        }
    }

}