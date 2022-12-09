package com.example.a3track.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.example.a3track.api.ThreeTrackerRepository

class TasksViewModelFactory(private val repository: ThreeTrackerRepository) : Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TasksViewModel(repository) as T
    }
}