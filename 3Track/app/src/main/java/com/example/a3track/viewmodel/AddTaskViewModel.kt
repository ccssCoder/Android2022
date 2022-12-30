package com.example.a3track.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a3track.App
import com.example.a3track.api.ThreeTrackerRepository
import com.example.a3track.api.model.CreateTaskRequest
import com.example.a3track.manager.SharedPreferencesManager

class AddTaskViewModel(val repository: ThreeTrackerRepository): ViewModel() {
    var task: MutableLiveData<CreateTaskRequest> = MutableLiveData()

    suspend fun createTask(){
        val token: String? = App.sharedPreferences.getStringValue(
            SharedPreferencesManager.KEY_TOKEN,
            "Empty token!"
        )
        if (token != null) {
            task.value?.let { repository.createTask(token, it) }
        }
    }
}