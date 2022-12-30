package com.example.a3track.api

import com.example.a3track.api.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApiService {

    @POST(BackendConstants.LOGIN_URL)
    suspend fun login(@Body loginRequest: LoginRequestBody): Response<LoginResponse>

    @GET(BackendConstants.GET_TASKS_URL)
    suspend fun getTasks(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<List<TaskResponse>>

    @GET(BackendConstants.GET_USER_URL)
    suspend fun getUser(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<UserResponse>

    @POST(BackendConstants.CREATE_TASK_URL)
    suspend fun createTask(@Header(BackendConstants.HEADER_TOKEN) token: String, @Body loginRequest: CreateTaskRequest): Response<String>
}
