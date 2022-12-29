package com.example.a3track.api.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("ID")
    var id: Int,
    @SerializedName("last_name")
    var lastName: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("location")
    var location: String,
    @SerializedName("phone_number")
    var phoneNumber: String,
    @SerializedName("department_id")
    var departmentId: String,
    @SerializedName("image")
    var profilePictureURL: String?,
)
