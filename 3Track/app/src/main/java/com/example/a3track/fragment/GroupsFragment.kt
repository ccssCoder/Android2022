package com.example.a3track.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.a3track.R
import com.example.a3track.adapter.GroupExpandableListAdapter
import com.example.a3track.api.ThreeTrackerRepository
import com.example.a3track.api.model.DepartmentResponse
import com.example.a3track.viewmodel.GroupsViewModel
import com.example.a3track.viewmodel.GroupsViewModelFactory
import com.example.a3track.viewmodel.SharedViewModel

class GroupsFragment : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var groupsViewModel: GroupsViewModel
    private lateinit var listViewAdapter: GroupExpandableListAdapter
    private lateinit var eListView: ExpandableListView

    // Necessary structural data-holders for ExpandableListView
    lateinit var departmentNameList: List<String>
    lateinit var departmentResponseHashMap: HashMap<String, List<String>>

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
        val view =  inflater.inflate(R.layout.fragment_groups, container, false)

        setupExpandableListView(view)

        groupsViewModel.departments.observe(viewLifecycleOwner){
            //need to build a data-holder structure
            buildHashMap()
            listViewAdapter.setData(departmentNameList as ArrayList<String>, departmentResponseHashMap)
            listViewAdapter.notifyDataSetChanged()
        }

        return view
    }

    private fun setupExpandableListView(view: View) {
        eListView = view.findViewById(R.id.eListView)
        listViewAdapter = GroupExpandableListAdapter(requireContext(), ArrayList(), HashMap())
        eListView.setAdapter(listViewAdapter)
        eListView.divider = null

        eListView.setOnChildClickListener { _, _, i, _, _ ->
            try{
                sharedViewModel.setDepartment(groupsViewModel.departments.value?.get(i))
                // navigate to members page
                true
            } catch (e: Exception) {
                false
            }
        }
    }

    private fun buildHashMap() {
        departmentNameList = ArrayList()
        departmentResponseHashMap = HashMap()

        groupsViewModel.departments.value?.forEachIndexed { index, element ->
            (departmentNameList as ArrayList<String>).add( element.name )
            var tmp: MutableList<String> = ArrayList()
            tmp.add("View Members")
            tmp.add("Leave group")

            departmentResponseHashMap[departmentNameList[index]] = tmp
        }
    }
}