package com.example.a3track.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a3track.R
import com.example.a3track.adapter.MemberListAdapter
import com.example.a3track.adapter.TaskListAdapter
import com.example.a3track.viewmodel.SharedViewModel

class MembersFragment : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MemberListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_members, container, false)

        recyclerView = view.findViewById(R.id.recycler_view_members)
        setupRecyclerView()

        Log.d("TAG", sharedViewModel.getDepartment().toString())
        Log.d("TAG", sharedViewModel.getMembers().toString())

        setUpToolbarName()

        return view
    }

    private fun setupRecyclerView() {
        val members = sharedViewModel.getMembers()
        members!!
        adapter = MemberListAdapter(members.sortedBy { it.firstName }, requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.setHasFixedSize(true)
    }

    private fun setUpToolbarName() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = sharedViewModel.getDepartment()?.name + " " + "members"
    }
}