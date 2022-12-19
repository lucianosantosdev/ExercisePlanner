package dev.lucianosantos.exerciseplanner.collections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.lucianosantos.exerciseplanner.databinding.ExerciseItemBinding

/**
 * [RecyclerView.Adapter] that can display a [ExerciseItem].
 */
class ExerciseListAdapter() : RecyclerView.Adapter<ExerciseListAdapter.ViewHolder>() {

    private val asyncListDiffer: AsyncListDiffer<ExerciseItem> = AsyncListDiffer(this, DiffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ExerciseItemBinding.inflate(
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

    fun updateExercises(Exercises: List<ExerciseItem>) {
        asyncListDiffer.submitList(Exercises)
    }

    inner class ViewHolder(private val binding: ExerciseItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(Exercise: ExerciseItem) {
            binding.exerciseNameTextView.text = Exercise.name
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<ExerciseItem>() {
        override fun areItemsTheSame(oldItem: ExerciseItem, newItem: ExerciseItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ExerciseItem, newItem: ExerciseItem): Boolean {
            return oldItem.name == newItem.name
        }
    }
}