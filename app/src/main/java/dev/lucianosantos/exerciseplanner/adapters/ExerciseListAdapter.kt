package dev.lucianosantos.exerciseplanner.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.lucianosantos.exerciseplanner.core.database.entity.Exercise
import dev.lucianosantos.exerciseplanner.databinding.ExerciseItemBinding

/**
 * [RecyclerView.Adapter] that can display a [Exercise].
 */
class ExerciseListAdapter() : RecyclerView.Adapter<ExerciseListAdapter.ViewHolder>() {

    private val asyncListDiffer: AsyncListDiffer<Exercise> = AsyncListDiffer(this, DiffCallback)

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

    fun updateExercises(Exercises: List<Exercise>) {
        asyncListDiffer.submitList(Exercises)
    }

    inner class ViewHolder(private val binding: ExerciseItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(Exercise: Exercise) {
            binding.nameTextView.text = Exercise.name
            binding.firstDetailTextView.text = Exercise.sessions?.toString()
            binding.secondDetailTextView.text = Exercise.repetitions?.toString()
            binding.thirdDetailTextView.text = Exercise.repetitions?.toString()

        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Exercise>() {
        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.name == newItem.name
        }
    }
}