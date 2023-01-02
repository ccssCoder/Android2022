package com.example.a3track.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import com.example.a3track.R
import com.example.a3track.api.ThreeTrackerRepository
import com.example.a3track.viewmodel.AddTaskViewModel
import com.example.a3track.viewmodel.AddTaskViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlin.properties.Delegates

class AddTaskFragment : Fragment() {
    private lateinit var addTaskViewModel: AddTaskViewModel
    private lateinit var departmentNameSpinner: Spinner
    private lateinit var createNewTaskBtn: Button
    private lateinit var arrayAdapter: ArrayAdapter<String>

    private var selectedIdOnSpinner by Delegates.notNull<Int>()

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
        createNewTaskBtn = view.findViewById(R.id.createNewTaskBtn)

        createNewTaskBtn.setOnClickListener {
            Snackbar.make(
                view,
                "Button Clicked!",
                800,
            ).show()
            // HARD CODED SOLUTION :(
            // addTaskViewModel.createTask()
        }

        addTaskViewModel.departments.observe(viewLifecycleOwner) {
            setUpSpinnerForChoosingDepartment(view)
        }
    }

    private fun setUpSpinnerForChoosingDepartment(view: View){
        departmentNameSpinner = view.findViewById(R.id.departmentNameSpinner)
        val list = ArrayList<String>(addTaskViewModel.departments.value?.map { it -> it.name })
        arrayAdapter = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            list
        )

        departmentNameSpinner.adapter = arrayAdapter
        departmentNameSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val depResponse = addTaskViewModel.departments.value?.find { it.name == list[p2] }


                if (depResponse != null) {
                    selectedIdOnSpinner = depResponse.departmentID
                }
                Log.d("TAG", "Spinner selected value $depResponse (selected id $selectedIdOnSpinner)")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}