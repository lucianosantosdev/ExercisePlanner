package dev.lucianosantos.exerciseplanner.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.lucianosantos.exerciseplanner.data.Routine
import dev.lucianosantos.exerciseplanner.databinding.RoutineItemBinding

/**
 * [RecyclerView.Adapter] that can display a [Routine].
 */
class RoutineListAdapter(private val onClickListener: (String) -> Unit) : RecyclerView.Adapter<RoutineListAdapter.ViewHolder>() {

    private val asyncListDiffer: AsyncListDiffer<Routine> = AsyncListDiffer(this, DiffCallback)

    interface OnClickListener {
        fun onItemClicked(id: Int)
    }

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

            itemView.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onClickListener(routine.id)
                }
            }
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