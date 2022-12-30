package com.example.a3track.api.model

import com.google.gson.annotations.SerializedName

data class CreateTaskRequest(
    @SerializedName("title")
    var title: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("assignedToUserId")
    var assignedToUserID: Int?,
    @SerializedName("priority")
    var priority: Int?,
    @SerializedName("deadline")
    var deadline: Long,
    @SerializedName("departmentId")
    var departmentID: Int,
    @SerializedName("status")
    var status: Int,
)