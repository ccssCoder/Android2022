package com.example.a3track.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.a3track.R
import com.example.a3track.api.model.UserResponse

class MemberListAdapter(
    private var list: List<UserResponse>,
    private val context: Context
):  RecyclerView.Adapter<MemberListAdapter.DataViewHolder>()  {

    // 1. user defined ViewHolder type
    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var memberImage: ImageView = itemView.findViewById(R.id.memberImage)
        var memberName: TextView = itemView.findViewById(R.id.memberName)

        // Constructor
        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {}
    }

    // 2. Called only a few times = number of items on screen + a few more ones
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MemberListAdapter.DataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.member_item, parent, false)
        return DataViewHolder(itemView)
    }
    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]
        holder.memberName.text = currentItem.firstName + " " + currentItem.lastName

        Glide.with(context)
            .load(currentItem.profilePictureURL)
            .error(R.drawable.ic_baseline_account_circle_24)
            .override(120, 120)
            .transform(RoundedCorners(60))
            .into(holder.memberImage)
    }
    // 4.
    override fun getItemCount() = list.size
}