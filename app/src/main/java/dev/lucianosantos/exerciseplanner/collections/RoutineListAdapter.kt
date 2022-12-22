package dev.lucianosantos.exerciseplanner.collections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.lucianosantos.exerciseplanner.databinding.RoutineItemBinding
import dev.lucianosantos.exerciseplanner.models.Routine

/**
 * [RecyclerView.Adapter] that can display a [Routine].
 */
class RoutineListAdapter() : RecyclerView.Adapter<RoutineListAdapter.ViewHolder>() {

    private val asyncListDiffer: AsyncListDiffer<Routine> = AsyncListDiffer(this, DiffCallback)

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
        val item = asyncListDiffer.currentList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    fun updateRoutines(routines: List<Routine>) {
        asyncListDiffer.submitList(routines)
    }

    inner class ViewHolder(private val binding: RoutineItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(routine: Routine) {
            binding.routineNameTextView.text = routine.name
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Routine>() {
        override fun areItemsTheSame(oldItem: Routine, newItem: Routine): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Routine, newItem: Routine): Boolean {
            return oldItem.name == newItem.name
        }
    }
}