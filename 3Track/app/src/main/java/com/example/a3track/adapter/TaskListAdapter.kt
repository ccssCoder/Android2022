package com.example.a3track.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a3track.R
import com.example.a3track.api.model.TaskResponse
import com.example.a3track.utils.toTimeDateString

class TaskListAdapter(
    private var list: List<TaskResponse>,
    private val context: Context,
    private val listener: OnItemClickListener
):  RecyclerView.Adapter<TaskListAdapter.DataViewHolder>() {

    // Event handling
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    // 1. user defined ViewHolder type
    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var taskNameTxtView: TextView = itemView.findViewById(R.id.taskNameTxtView)
        var assignedDateTxtView: TextView = itemView.findViewById(R.id.assignedDateTxtView)
        var priorityTxtView: TextView = itemView.findViewById(R.id.priorityTxtView)
        var priorityImgView: ImageView = itemView.findViewById(R.id.priorityImgView)
        var statusTxtView: TextView = itemView.findViewById(R.id.statusTxtView)
        var deadlineTxtView: TextView = itemView.findViewById(R.id.deadlineTxtView)
        var taskDescriptionTxtView: TextView = itemView.findViewById(R.id.taskDescriptionTxtView)
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
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]
        holder.taskNameTxtView.text = currentItem.title
        holder.assignedDateTxtView.text = currentItem.createdTime.toTimeDateString()
        holder.deadlineTxtView.text = currentItem.deadline.toTimeDateString()
        holder.taskDescriptionTxtView.text = currentItem.description

        when (currentItem.priority) {
            0 -> {
                holder.priorityTxtView.text = "High priority"
                holder.priorityImgView.setColorFilter(context.getColor(R.color.priority_red))
            }
            1 -> {
                holder.priorityTxtView.text = "Medium priority"
                holder.priorityImgView.setColorFilter(context.getColor(R.color.priority_yellow))
            }
            2 -> {
                holder.priorityTxtView.text = "Low priority"
                holder.priorityImgView.setColorFilter(context.getColor(R.color.priority_green))
            }
        }

        when (currentItem.status) {
            0 -> {
                holder.statusTxtView.text = "New"
                holder.statusTxtView.setBackgroundColor(context.getColor(R.color.theme))
            }
            1 -> {
                holder.statusTxtView.text = "In Progress"
                holder.statusTxtView.setBackgroundColor(context.getColor(R.color.theme_grey))
            }
            2 -> {
                holder.statusTxtView.text = "Blocked"
                holder.statusTxtView.setBackgroundColor(context.getColor(R.color.priority_red))
            }
        }
    }
    // 4.
    override fun getItemCount() = list.size

    // Update the list
    fun setData(newList: ArrayList<TaskResponse>) {
        list = newList
    }
}
