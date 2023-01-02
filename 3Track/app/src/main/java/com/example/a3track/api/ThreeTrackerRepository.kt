package com.example.a3track.api

import com.example.a3track.api.model.*
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

    suspend fun getUsers(token: String): Response<List<UserResponse>> {
        return RetrofitInstance.USER_API_SERVICE.getUsers(token)
    }

    suspend fun createTask(token: String, createTaskRequest: CreateTaskRequest): Response<String> {
        return RetrofitInstance.USER_API_SERVICE.createTask(token, createTaskRequest)
    }

    suspend fun getDepartments(token: String): Response<List<DepartmentResponse>> {
        return RetrofitInstance.USER_API_SERVICE.getDepartments(token)
    }
}
