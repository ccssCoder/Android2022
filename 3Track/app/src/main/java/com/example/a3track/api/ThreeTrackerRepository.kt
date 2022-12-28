package com.example.a3track.api

import com.example.a3track.api.model.LoginRequestBody
import com.example.a3track.api.model.LoginResponse
import com.example.a3track.api.model.TaskResponse
import com.example.a3track.api.model.UserResponse
import retrofit2.Response

class ThreeTrackerRepository {
    suspend fun login(loginRequestBody: LoginRequestBody): Response<LoginResponse> {
        return RetrofitInstance.USER_API_SERVICE.login(loginRequestBody)
    }

    suspend fun getTasks(token: String): Response<List<TaskResponse>> {
        return RetrofitInstance.USER_API_SERVICE.getTasks(token)
    }

    suspend fun getUser(token: String): Response<UserResponse> {
        return RetrofitInstance.USER_API_SERVICE.getUser(token)
    }
}
