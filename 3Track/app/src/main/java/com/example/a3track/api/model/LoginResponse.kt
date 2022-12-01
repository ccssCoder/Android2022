package com.example.a3track.api.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("userId")
    var userId: Int,
    @SerializedName("token")
    var token: String,
    @SerializedName("deadline")
    var deadline: Long,
) {
    override fun toString(): String {
        return "LoginResponse(" +
                "userId='$userId'," +
                " token='$token'," +
                " deadline=$deadline)"
    }
}
