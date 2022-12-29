package com.example.a3track.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a3track.App
import com.example.a3track.api.ThreeTrackerRepository
import com.example.a3track.api.model.UserResponse
import com.example.a3track.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: ThreeTrackerRepository) : ViewModel()  {
    companion object {
        private val TAG: String = "XXX"
    }

    var profile: MutableLiveData<UserResponse?> =  MutableLiveData()

    init {
        getMyUser()
    }

    private fun getMyUser() {
        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {
                    repository.getUser(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Get user response: ${response.body()}")

                    val user = response.body()
                    user?.let {
                        profile.value = user
                    }
                } else {
                    Log.d(TAG, "Get user error response: ${response?.errorBody()}")
                }
            }  catch (e: Exception) {
                Log.d(TAG, "ProfileViewModel - getUser() failed with exception: ${e.message}")
            }
        }
    }
}