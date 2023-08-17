package com.example.hw1.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw1.databinding.ItemTaskBinding
import com.example.hw1.ui.task.TaskModel

class TaskAdapter(val deleteClick: (TaskModel) -> Unit, val onClickOnCheckBox: (TaskModel) -> Unit) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    var list = mutableListOf<TaskModel>()

    fun addData(lists: List<TaskModel>) {
        list.clear()
        list.addAll(lists)
        list.sortByDescending { it.isCompleted }
        notifyDataSetChanged()
    }


    fun addTrueCheckBox(taskModel: TaskModel){
        if (taskModel.isCompleted == true) {
            list.add(0, taskModel) // Добавляем в начало списка
            notifyItemInserted(0)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(taskModel: TaskModel) {
            binding.tvTask.text = taskModel.title
            binding.checkbox.isChecked = taskModel.isCompleted ?: false

            itemView.setOnLongClickListener {
                deleteClick(list[adapterPosition])
                false
            }

            binding.checkbox.setOnCheckedChangeListener(null) // Удалите существующий обработчик, чтобы избежать рекурсивного вызова

            binding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                val updatedTaskModel = taskModel.copy( isCompleted = isChecked)
                onClickOnCheckBox(updatedTaskModel)
            }

        }
    }
}
