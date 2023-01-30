package com.example.a3track.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.a3track.R
import com.example.a3track.api.ThreeTrackerRepository
import com.example.a3track.api.model.CreateTaskRequest
import com.example.a3track.api.model.DepartmentResponse
import com.example.a3track.api.model.UserResponse
import com.example.a3track.utils.toTimeDateLong
import com.example.a3track.utils.toTimeDateString
import com.example.a3track.viewmodel.AddTaskViewModel
import com.example.a3track.viewmodel.AddTaskViewModelFactory
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import kotlin.properties.Delegates

class AddTaskFragment : Fragment() {
    private lateinit var addTaskViewModel: AddTaskViewModel

    private lateinit var taskNameEditTxt: EditText
    private lateinit var descriptionEditTxt: EditText
    private lateinit var createNewTaskBtn: Button

    private lateinit var deadlineEditTxt: EditText
    private lateinit var materialDatePicker: MaterialDatePicker<Long>

    private lateinit var dropDownAdapter1: ArrayAdapter<DepartmentResponse>
    private lateinit var dropDownDepartmentTxt: AutoCompleteTextView
    private var selectedDepartmentId by Delegates.notNull<Int>()

    private lateinit var dropDownAdapter2: ArrayAdapter<UserResponse>
    private lateinit var dropDownEmployeeNameTxt: AutoCompleteTextView
    private var selectedAssigneeId by Delegates.notNull<Int>()

    private lateinit var dropDownPriorityTxt: AutoCompleteTextView
    private lateinit var dropDownAdapter3: ArrayAdapter<String>
    private var selectedPriority by Delegates.notNull<Int>()

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
        taskNameEditTxt = view.findViewById(R.id.taskNameEditTxt)
        dropDownPriorityTxt = view.findViewById(R.id.dropDownPriorityTxt)
        descriptionEditTxt = view.findViewById(R.id.descriptionEditTxt)
        createNewTaskBtn = requireActivity().findViewById(R.id.createTaskBtn)
        deadlineEditTxt = view.findViewById(R.id.deadlineEditTxt)
        dropDownDepartmentTxt = view.findViewById(R.id.dropDownDepartmentTxt)
        dropDownEmployeeNameTxt = view.findViewById(R.id.dropDownEmployeeNameTxt)
        setUpDatePicker()

        addTaskViewModel.departments.observe(viewLifecycleOwner) {
            setUpDropDownMenuDepartment()
        }

        addTaskViewModel.members.observe(viewLifecycleOwner) {
            setUpDropDownMenuAssignee()
        }

        setUpDropDownMenuPriority()

        createNewTaskBtn.setOnClickListener {
            evaluateForm(view)
        }
    }

    private fun setUpDatePicker() {
        materialDatePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .build()

        deadlineEditTxt.setOnClickListener {
            materialDatePicker.show(childFragmentManager, "MATERIAL_DATE_PICKER")
        }

        materialDatePicker.addOnPositiveButtonClickListener {
            deadlineEditTxt.setText(it.toTimeDateString())
        }
    }

    private fun evaluateForm(view: View) {
        try {
            val l = CreateTaskRequest(
                taskNameEditTxt.text.toString()!!,
                descriptionEditTxt.text.toString()!!,
                selectedAssigneeId!!,
                selectedPriority,
                deadlineEditTxt.text.toString().toTimeDateLong()!!,
                selectedDepartmentId!!,
                0
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

    private fun setUpDropDownMenuDepartment() {
        dropDownAdapter1 = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_item,
            ArrayList<DepartmentResponse>(addTaskViewModel.departments.value)
        )
        dropDownDepartmentTxt.setAdapter(dropDownAdapter1)

        dropDownDepartmentTxt.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val item: DepartmentResponse = parent.getItemAtPosition(position) as DepartmentResponse
            selectedDepartmentId = item.departmentID
        }
    }

    private fun setUpDropDownMenuAssignee() {
        dropDownAdapter2 = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_item,
            ArrayList<UserResponse>(addTaskViewModel.members.value).sortedBy { it.lastName }
        )
        dropDownEmployeeNameTxt.setAdapter(dropDownAdapter2)

        dropDownEmployeeNameTxt.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val item: UserResponse = parent.getItemAtPosition(position) as UserResponse
            selectedAssigneeId = item.id
        }
    }

    private fun setUpDropDownMenuPriority() {
        dropDownAdapter3 = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_item,
            arrayOf("High", "Medium", "Low")
        )
        dropDownPriorityTxt.setAdapter(dropDownAdapter3)

        dropDownPriorityTxt.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            when (parent.getItemAtPosition(position) as String) {
                "High" -> { selectedPriority = 0 }
                "Medium" -> { selectedPriority = 1 }
                "Low" -> { selectedPriority = 2 }
            }
        }
    }
}