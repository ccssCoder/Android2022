package com.example.a3track.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a3track.api.ThreeTrackerRepository

class AddTaskViewModelFactory(private val repository: ThreeTrackerRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddTaskViewModel(repository) as T
    }
}