package com.example.a3track.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.a3track.App
import com.example.a3track.api.ThreeTrackerRepository
import com.example.a3track.api.model.DepartmentResponse
import com.example.a3track.api.model.UserResponse
import com.example.a3track.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class GroupsViewModel(private val repository: ThreeTrackerRepository) : ViewModel()  {
    var departments: MutableLiveData<List<DepartmentResponse>?> = MutableLiveData()

    init {
        getDepartments()
    }

    fun getDepartments(){
        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {
                    repository.getDepartments(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d("TAG", "Get departments response: ${response.body()}")

                    val departmentList = response.body()
                    departmentList?.let {
                        departments.value = departmentList
                    }
                } else {
                    Log.d("TAG", "Get departments error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                Log.d("TAG", "AddTasksViewModel - getDepartments() failed with exception: ${e.message}")
            }
        }
    }

//    fun getMembers(){
//        viewModelScope.launch {
//            try {
//                val token: String? = App.sharedPreferences.getStringValue(
//                    SharedPreferencesManager.KEY_TOKEN,
//                    "Empty token!"
//                )
//                val response = token?.let {
//                    repository.getUsers(it)
//                }
//                if (response?.isSuccessful == true) {
//                    Log.d("TAG", "Get departments response: ${response.body()}")
//                    val membersList = response.body()
//                    membersList?.let {
//                        members.value = membersList
//                    }
//                } else {
//                    Log.d("TAG", "Get departments error response: ${response?.errorBody()}")
//                }
//
//            } catch (e: Exception) {
//                Log.d("TAG", "AddTasksViewModel - getDepartments() failed with exception: ${e.message}")
//            }
//        }
//    }
}