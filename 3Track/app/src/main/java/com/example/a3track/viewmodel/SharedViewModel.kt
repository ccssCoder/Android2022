package com.example.a3track.viewmodel

import androidx.lifecycle.ViewModel
import com.example.a3track.api.model.TaskResponse

class SharedViewModel : ViewModel() {
    private var product: TaskResponse? = null

    fun setTask(prod: TaskResponse?) {
        product = prod
    }

    fun getTask(): TaskResponse? {
        return product
    }
}