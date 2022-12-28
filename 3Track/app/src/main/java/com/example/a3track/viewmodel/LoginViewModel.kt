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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val repository: ThreeTrackerRepository): ViewModel() {
    var token: MutableLiveData<String> = MutableLiveData()
    var isSuccessful: MutableLiveData<Boolean> = MutableLiveData()
    var skipLogin: MutableLiveData<Boolean> = MutableLiveData()
    private var _isLoading = MutableStateFlow(true)
    var isLoading = _isLoading.asStateFlow()

    fun testToken() {
        viewModelScope.launch {
            try {
                testTokenValidness()
                skipLogin.value = true
            } catch (e: Exception){
                skipLogin.value = false
            }
            _isLoading.value = false
        }
    }

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

    private suspend fun testTokenValidness() {
        try {
            val token: String? = App.sharedPreferences.getStringValue(
                SharedPreferencesManager.KEY_TOKEN,
                "Empty token!"
            )
            val response = token?.let {
                repository.getUser(it)
            }

            if (response?.isSuccessful == true) {
                Log.d("TAG", "Get user response: ${response.body()}")
            } else {
                Log.d("TAG","Get tasks error response: ${response?.errorBody()}")
                throw Exception("Not Authorized")
            }
        } catch (e: Exception) {
            throw Exception("Unsuccessful API CALL")
        }
    }
}
