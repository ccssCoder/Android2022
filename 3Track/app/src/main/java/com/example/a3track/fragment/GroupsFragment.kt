package com.example.a3track.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.a3track.R
import com.example.a3track.api.ThreeTrackerRepository
import com.example.a3track.viewmodel.AddTaskViewModel
import com.example.a3track.viewmodel.AddTaskViewModelFactory
import com.example.a3track.viewmodel.GroupsViewModel
import com.example.a3track.viewmodel.GroupsViewModelFactory

class GroupsFragment : Fragment() {
    private lateinit var groupsViewModel: GroupsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = GroupsViewModelFactory(ThreeTrackerRepository())
        groupsViewModel = ViewModelProvider(this, factory)[GroupsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_groups, container, false)
    }
}