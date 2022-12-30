package com.example.a3track.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.a3track.R
import com.example.a3track.api.model.TaskResponse
import com.example.a3track.viewmodel.SharedViewModel

class TaskDetailFragment : Fragment() {
    lateinit var taskNameTxtView: TextView
    lateinit var assignedByTxtView: TextView
    lateinit var assignedDateTxtView: TextView
    lateinit var assigneeTxtView: TextView
    lateinit var descriptionTextView: TextView
    lateinit var priorityTxtView: TextView
    lateinit var deadlineTxtView: TextView

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
        val view = inflater.inflate(R.layout.fragment_task_detail, container, false)

        init(view)

        return view
    }

    @SuppressLint("SetTextI18n")
    private fun init(view: View) {
        taskNameTxtView = view.findViewById(R.id.taskNameTxtView)
        assignedByTxtView = view.findViewById(R.id.assignedByTxtView)
        assignedDateTxtView = view.findViewById(R.id.assignedDateTxtView)
        assigneeTxtView = view.findViewById(R.id.assigneeTxtView)
        descriptionTextView = view.findViewById(R.id.descriptionTextView)
        priorityTxtView = view.findViewById(R.id.priorityTxtView)
        deadlineTxtView = view.findViewById(R.id.deadlineTxtView)

        val task: TaskResponse? = sharedViewModel.getTask()

        taskNameTxtView.text = task!!.title
        assignedByTxtView.text = assignedByTxtView.text.toString() + task.createdByUserID.toString()
        assignedDateTxtView.text = assignedDateTxtView.text.toString() + task.createdTime.toString()
        assigneeTxtView.text = assigneeTxtView.text.toString() + task.assignedToUserID.toString()

        priorityTxtView.text = priorityTxtView.text.toString() + task.priority.toString()
        deadlineTxtView.text = deadlineTxtView.text.toString() + task.deadline.toString()
        descriptionTextView.text = task.description
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "${sharedViewModel.getTask()}")
    }
}