package dev.lucianosantos.exerciseplanner.fragments.collections.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.lucianosantos.exerciseplanner.core.database.entity.Routine
import dev.lucianosantos.exerciseplanner.databinding.RoutineItemBinding
import dev.lucianosantos.exerciseplanner.fragments.collections.model.RoutineItem

/**
 * [RecyclerView.Adapter] that can display a [Routine].
 */
class RoutineListAdapter(
    private val onItemClickListener: (String) -> Unit,
    private val onEditClickListener: (String) -> Unit
) : RecyclerView.Adapter<RoutineListAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: RoutineItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(routine: RoutineItem) {
            binding.routineNameTextView.text = routine.name

            itemView.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener(routine.id)
                }
            }

            binding.editIconButton.setOnClickListener {
                onEditClickListener(routine.id)
            }
        }
    }

    private val asyncListDiffer: AsyncListDiffer<RoutineItem> = AsyncListDiffer(this, DiffCallback)

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

    fun updateRoutines(routines: List<RoutineItem>) {
        asyncListDiffer.submitList(routines)
    }

    object DiffCallback : DiffUtil.ItemCallback<RoutineItem>() {
        override fun areItemsTheSame(oldItem: RoutineItem, newItem: RoutineItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RoutineItem, newItem: RoutineItem): Boolean {
            return oldItem.name == newItem.name
        }
    }
}