package com.example.a3track.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a3track.R
import com.example.a3track.api.model.TaskResponse

class TaskListAdapter(
    private var list: List<TaskResponse>,
    private val listener: OnItemClickListener
):  RecyclerView.Adapter<TaskListAdapter.DataViewHolder>() {

    // Event handling
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    // 1. user defined ViewHolder type
    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val taskTitleTextView: TextView = itemView.findViewById(R.id.taskTitle)
        // Constructor
        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            // Delegate event handling to ListFragment
            val position: Int = adapterPosition
            if( position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }
    // 2. Called only a few times = number of items on screen + a few more ones
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return DataViewHolder(itemView)
    }
    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]
        holder.taskTitleTextView.text = currentItem.title
    }
    // 4.
    override fun getItemCount() = list.size

    // Update the list
    fun setData(newList: ArrayList<TaskResponse>) {
        list = newList
    }
}
