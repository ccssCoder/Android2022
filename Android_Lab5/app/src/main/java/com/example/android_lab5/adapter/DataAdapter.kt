package com.example.android_lab5.adapter

import Item
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_lab5.R

// Item event handling
interface OnItemClickListener{
    fun onItemClick(position: Int)
}

class DataAdapter(
    private val list: List<Item>,
    private val listener: OnItemClickListener
):  RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    // 1. user defined ViewHolder type
    inner class DataViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        OnClickListener {
        val questionTxtView: TextView = itemView.findViewById(R.id.itemQuestionView)
        val correctAnswerTxtView: TextView = itemView.findViewById(R.id.itemCorrectAnswerView)

        // Constructor
        init{
            itemView.setOnClickListener( this )
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
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return DataViewHolder(itemView)
    }

    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]
        holder.questionTxtView.text = currentItem.question
        holder.correctAnswerTxtView.text = currentItem.answers[ currentItem.correct ]
    }

    // 4.
    override fun getItemCount() = list.size
}