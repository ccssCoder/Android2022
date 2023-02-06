package com.example.a3track.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.a3track.R
import com.example.a3track.api.model.DepartmentResponse

class GroupExpandableListAdapter(
    private val context: Context,
    private var chapterList: List<String>,
    private var topicsList: HashMap<String,List<String>>,
): BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return chapterList.size
    }

    override fun getChildrenCount(p0: Int): Int {
        return this.topicsList[this.chapterList[p0]]!!.size
    }

    override fun getGroup(p0: Int): Any {
        return chapterList[p0]
    }

    override fun getChild(p0: Int, p1: Int): Any {
        return this.topicsList[this.chapterList[p0]]!![p1]
    }

    override fun getGroupId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p1.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    @SuppressLint("InflateParams")
    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        var convertView = p2
        val groupTitle = getGroup(p0) as String

        if(convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.group_item, null)
        }
        val groupTv = convertView!!.findViewById<TextView>(R.id.groupListTitle)
        groupTv.text = groupTitle

        return convertView
    }

    @SuppressLint("InflateParams")
    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        var convertView = p3
        val childTitle = getChild(p0, p1) as String

        if(convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.group_child_item, null)
        }
        val childTv = convertView!!.findViewById<TextView>(R.id.expandedGroupListItem)
        childTv.text = childTitle

        return convertView
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    // Update the list
    fun setData(newList: ArrayList<String>, newMap: HashMap<String,List<String>>) {
        chapterList = newList
        topicsList = newMap
    }
}