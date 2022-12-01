package com.example.a3track.api

import com.example.a3track.api.model.LoginRequestBody
import com.example.a3track.api.model.LoginResponse
import retrofit2.Response

class ThreeTrackerRepository {
    suspend fun login(loginRequestBody: LoginRequestBody): Response<LoginResponse> {
        return RetrofitInstance.USER_API_SERVICE.login(loginRequestBody)
    }
}
