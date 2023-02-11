package com.example.a3track.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a3track.api.model.DepartmentResponse
import com.example.a3track.api.model.TaskResponse
import com.example.a3track.api.model.UserResponse

class SharedViewModel : ViewModel() {
    // Task -> TaskDetail
    private var product: TaskResponse? = null

    fun setTask(prod: TaskResponse?) {
        product = prod
    }

    fun getTask(): TaskResponse? {
        return product
    }

    // Group -> Members
    private var department: DepartmentResponse? = null
    private var members: List<UserResponse>? = null

    fun setDepartment(department: DepartmentResponse?) {
        this.department = department
    }

    fun getDepartment(): DepartmentResponse? {
        return department
    }

    fun setMembers(members: List<UserResponse>) {
        this.members = members
    }

    fun getMembers(): List<UserResponse>? {
        return members
    }
}