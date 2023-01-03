package com.example.a3track.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a3track.App
import com.example.a3track.api.ThreeTrackerRepository
import com.example.a3track.api.model.CreateTaskRequest
import com.example.a3track.api.model.DepartmentResponse
import com.example.a3track.api.model.UserResponse
import com.example.a3track.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class AddTaskViewModel(val repository: ThreeTrackerRepository): ViewModel() {
    var task: MutableLiveData<CreateTaskRequest> = MutableLiveData()
    var departments: MutableLiveData<List<DepartmentResponse>?> = MutableLiveData()
    var members: MutableLiveData<List<UserResponse>?> = MutableLiveData()

    init {
        getDepartments()
        getMembers()
    }

    fun createTask(newTaskRequest: CreateTaskRequest){
        viewModelScope.launch {
//            val newRequest: CreateTaskRequest =
//            CreateTaskRequest(
//                "Write user stories for XY project",
//                "Ask the employees to set up proper user stories",
//                64,
//                1,
//                1625942327,
//                2,
//                1
//            )
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {
                    repository.createTask(token, newTaskRequest)
                }

                if (response?.isSuccessful == true) {
                    Log.d("TAG", "Create request response: ${response.body()}")
                } else {
                    Log.d("TAG", "Get tasks error response: ${response?.errorBody()}")
                }
            } catch (e: Exception) {
                Log.d("TAG", "AddTaskViewModel - createTask failed with exception: ${e.message}")
            }
        }
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

    fun getMembers(){
        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {
                    repository.getUsers(it)
                }
                if (response?.isSuccessful == true) {
                    Log.d("TAG", "Get departments response: ${response.body()}")
                    val membersList = response.body()
                    membersList?.let {
                        members.value = membersList
                    }
                } else {
                    Log.d("TAG", "Get departments error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                Log.d("TAG", "AddTasksViewModel - getDepartments() failed with exception: ${e.message}")
            }
        }
    }
}