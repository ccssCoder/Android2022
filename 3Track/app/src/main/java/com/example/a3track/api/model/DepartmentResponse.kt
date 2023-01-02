package com.example.a3track.api.model

import com.google.gson.annotations.SerializedName

data class DepartmentResponse(
    @SerializedName("ID")
    var departmentID: Int,
    @SerializedName("name")
    var name: String,
)