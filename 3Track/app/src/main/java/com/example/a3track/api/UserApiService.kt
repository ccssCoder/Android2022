package com.example.a3track.api

import com.example.a3track.api.model.LoginRequestBody
import com.example.a3track.api.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {

    @POST(BackendConstants.LOGIN_URL)
    suspend fun login(@Body loginRequest: LoginRequestBody): Response<LoginResponse>
}
