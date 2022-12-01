package com.example.a3track.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a3track.App
import com.example.a3track.api.ThreeTrackerRepository
import com.example.a3track.api.model.LoginRequestBody
import com.example.a3track.manager.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val repository: ThreeTrackerRepository): ViewModel() {
    var token: MutableLiveData<String> = MutableLiveData()
    var isSuccessful: MutableLiveData<Boolean> = MutableLiveData()

    fun login(username: String, password: String) {
        val requestBody = LoginRequestBody(username, password)
        viewModelScope.launch {
            executeLogin(requestBody)
        }
    }

    private suspend fun executeLogin(requestBody: LoginRequestBody) {
        try {
            val response = withContext(Dispatchers.IO) {
                repository.login(requestBody)
            }

            if (response.isSuccessful) {
                Log.d("TAG", "Login response: ${response.body()}")

                val responseToken = response.body()?.token
                responseToken?.let {
                    token.value = it
                    App.sharedPreferences.putStringValue(SharedPreferencesManager.KEY_TOKEN, it)
                    isSuccessful.value = true
                }
            } else {
                Log.d("TAG", "Login error response: ${response.message()}")
                isSuccessful.value = false
            }
        } catch (e: Exception) {
            Log.d("TAG", "LoginViewModel - login() failed with exception: ${e.message}")
            isSuccessful.value = false
        }
    }
}
