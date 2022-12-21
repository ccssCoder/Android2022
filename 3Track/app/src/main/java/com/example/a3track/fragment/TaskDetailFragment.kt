package com.example.a3track.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.a3track.R
import com.example.a3track.api.ThreeTrackerRepository
import com.example.a3track.viewmodel.SharedViewModel
import com.example.a3track.viewmodel.TasksViewModel
import com.example.a3track.viewmodel.TasksViewModelFactory

class TaskDetailFragment : Fragment() {

    companion object {
        private val TAG: String = "XXX"
    }

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "${sharedViewModel.getTask()}")
    }
}