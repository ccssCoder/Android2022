package com.example.a3track.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a3track.R
import com.example.a3track.adapter.TaskListAdapter
import com.example.a3track.adapter.TaskListAdapter.OnItemClickListener
import com.example.a3track.api.ThreeTrackerRepository
import com.example.a3track.api.model.TaskResponse
import com.example.a3track.viewmodel.SharedViewModel
import com.example.a3track.viewmodel.TasksViewModel
import com.example.a3track.viewmodel.TasksViewModelFactory

class TasksFragment : Fragment(), OnItemClickListener {

    companion object {
        private val TAG: String = "XXX"
    }

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = TasksViewModelFactory(ThreeTrackerRepository())
        tasksViewModel = ViewModelProvider(this, factory)[TasksViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_tasks, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_tasks)
        setupRecyclerView()
        tasksViewModel.tasks.observe(viewLifecycleOwner) {
            Log.d(TAG, "Tasks list = $it")
            adapter.setData(tasksViewModel.tasks.value as ArrayList<TaskResponse>)
            adapter.notifyDataSetChanged()
        }

        return view
    }

    private fun setupRecyclerView() {
        adapter = TaskListAdapter(ArrayList(), this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.setHasFixedSize(true)
    }

    override fun onItemClick(position: Int) {
        val chosenTask = tasksViewModel.tasks.value?.get(position)
        sharedViewModel.setTask(chosenTask)
        findNavController().navigate(R.id.action_tasksFragment_to_taskDetailFragment)
    }
}