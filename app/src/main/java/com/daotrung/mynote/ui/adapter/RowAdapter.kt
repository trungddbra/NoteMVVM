package com.daotrung.mynote.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.daotrung.mynote.database.TaskEntry
import com.daotrung.mynote.databinding.RowTitleBinding

class RowAdapter : ListAdapter<TaskEntry, RowAdapter.MyViewTaskHolder>(diffCallback) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<TaskEntry>() {
            override fun areItemsTheSame(oldItem: TaskEntry, newItem: TaskEntry): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TaskEntry,
                newItem: TaskEntry
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class MyViewTaskHolder(private val binding: RowTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(taskEntry: TaskEntry) {
            binding.taskTitle.text = taskEntry.title
            binding.taskPriority.text = taskEntry.priority.toString()
            binding.taskTimestamp.text = taskEntry.timestamp.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewTaskHolder {
        val binding =
            RowTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewTaskHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewTaskHolder, position: Int) {
        holder.bind(getItem(position))
    }
}