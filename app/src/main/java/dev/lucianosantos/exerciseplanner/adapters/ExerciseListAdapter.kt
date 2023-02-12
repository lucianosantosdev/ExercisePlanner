package dev.lucianosantos.exerciseplanner.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.lucianosantos.exerciseplanner.core.database.entity.Exercise
import dev.lucianosantos.exerciseplanner.databinding.ExerciseItemBinding
import dev.lucianosantos.exerciseplanner.fragments.collections.model.ExerciseItem

/**
 * [RecyclerView.Adapter] that can display a [Exercise].
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

    fun updateExercises(exercises: List<ExerciseItem>) {
        asyncListDiffer.submitList(exercises)
    }

    inner class ViewHolder(private val binding: ExerciseItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(exercise: ExerciseItem) {
            binding.nameTextView.text = exercise.name
            binding.firstDetailTextView.text = exercise.sessions?.toString()
            binding.secondDetailTextView.text = exercise.repetitions?.toString()
            binding.thirdDetailTextView.text = exercise.repetitions?.toString()
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