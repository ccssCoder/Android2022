package com.example.a3track.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.a3track.R
import com.example.a3track.api.ThreeTrackerRepository
import com.example.a3track.api.model.CreateTaskRequest
import com.example.a3track.viewmodel.AddTaskViewModel
import com.example.a3track.viewmodel.AddTaskViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.gson.annotations.SerializedName
import kotlin.properties.Delegates

class AddTaskFragment : Fragment() {
    private lateinit var addTaskViewModel: AddTaskViewModel

    private lateinit var departmentNameSpinner: Spinner
    private lateinit var taskNameEditTxt: EditText
    private lateinit var emloyeeNameSpinner: Spinner
    private lateinit var priorityEditTxt: EditText
    private lateinit var descriptionEditTxt: EditText
    private lateinit var createNewTaskBtn: Button

    private lateinit var arrayAdapter1: ArrayAdapter<String>
    private lateinit var arrayAdapter2: ArrayAdapter<String>

    // id for selected department
    private var selectedIdOnSpinner1 by Delegates.notNull<Int>()
    // id for selected member
    private var selectedIdOnSpinner2 by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = AddTaskViewModelFactory(ThreeTrackerRepository())
        addTaskViewModel = ViewModelProvider(this, factory)[AddTaskViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)

        init(view)

        return view
    }

    private fun init(view: View) {
        departmentNameSpinner = view.findViewById(R.id.departmentNameSpinner)
        taskNameEditTxt = view.findViewById(R.id.taskNameEditTxt)
        emloyeeNameSpinner = view.findViewById(R.id.employeeNameSpinner)
        priorityEditTxt = view.findViewById(R.id.priorityEditTxt)
        descriptionEditTxt = view.findViewById(R.id.descriptionEditTxt)
        createNewTaskBtn = requireActivity().findViewById(R.id.createTaskBtn)

        createNewTaskBtn.setOnClickListener {
            evaluateForm(view)
        }

        addTaskViewModel.departments.observe(viewLifecycleOwner) {
            setUpSpinnerForChoosingDepartment()
        }

        addTaskViewModel.members.observe(viewLifecycleOwner) {
            setUpSpinnerForChoosingEmployee()
        }
    }

    private fun evaluateForm(view: View) {
        try {
            val l = CreateTaskRequest(
                taskNameEditTxt.text.toString()!!,
                descriptionEditTxt.text.toString()!!,
                selectedIdOnSpinner2!!,
                priorityEditTxt.text.toString().toInt()!!,
                null,
                selectedIdOnSpinner1!!,
                null
            )
            addTaskViewModel.createTask(l)

            Snackbar.make(
                view,
                "Task Created Successfully",
                800,
            ).show()
        } catch (e: Exception) {
            Snackbar.make(
                view,
                "Fill the form properly!",
                800,
            ).show()
        }
    }

    private fun setUpSpinnerForChoosingDepartment(){
        val list = ArrayList<String>(addTaskViewModel.departments.value?.map { it -> it.name })
        arrayAdapter1 = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            list
        )

        departmentNameSpinner.adapter = arrayAdapter1
        departmentNameSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val depResponse = addTaskViewModel.departments.value?.find { it.name == list[p2] }


                if (depResponse != null) {
                    selectedIdOnSpinner1 = depResponse.departmentID
                }
                Log.d("TAG", "Spinner selected department $depResponse (selected id $selectedIdOnSpinner1)")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun setUpSpinnerForChoosingEmployee(){
        val list = ArrayList<String>(addTaskViewModel.members.value?.map { it -> it.firstName + " " + it.lastName })
        arrayAdapter2 = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            list
        )

        emloyeeNameSpinner.adapter = arrayAdapter2
        emloyeeNameSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val depResponse = addTaskViewModel.members.value?.find { it.firstName + " " + it.lastName == list[p2] }


                if (depResponse != null) {
                    selectedIdOnSpinner2 = depResponse.id
                }
                Log.d("TAG", "Spinner selected member $depResponse (selected id $selectedIdOnSpinner2)")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}