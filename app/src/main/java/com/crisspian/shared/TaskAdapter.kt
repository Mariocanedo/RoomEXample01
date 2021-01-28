package com.crisspian.shared

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.crisspian.shared.databinding.TaskItemBinding
import com.crisspian.shared.model.Task

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskVH>() {

    private var mlistTask = listOf<Task>()

    private val selectedTask = MutableLiveData<Task>()

    fun selectedItem(): LiveData<Task> = selectedTask

    fun update(listTask: List<Task>){
        mlistTask = listTask
        notifyDataSetChanged()
    }

   inner class TaskVH(private val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root),
           View.OnClickListener{
        fun bind(task: Task) {
            binding.tvTitle.text = task.title
            binding.tvDescription.text = task.taskDescription
            binding.tvDate.text = task.date
            binding.cbState.isChecked = task.state
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            selectedTask.value = mlistTask[adapterPosition]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVH {
        return TaskVH(TaskItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TaskVH, position: Int) {
        val task = mlistTask[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int = mlistTask.size

}