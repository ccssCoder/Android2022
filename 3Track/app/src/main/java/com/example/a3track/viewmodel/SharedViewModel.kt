package com.example.a3track.viewmodel

import androidx.lifecycle.ViewModel
import com.example.a3track.api.model.DepartmentResponse
import com.example.a3track.api.model.TaskResponse

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

    fun setDepartment(department: DepartmentResponse?) {
        this.department = department
    }

    fun getDepartment(): DepartmentResponse? {
        return department
    }
}