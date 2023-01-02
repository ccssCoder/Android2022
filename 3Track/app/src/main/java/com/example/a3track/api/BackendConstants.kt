package com.example.a3track.api

object BackendConstants {

    /**
     * Project backend base URL.
     */
    const val BASE_URL = "http://tracker-3track.a2hosted.com/"

    /**
     * Specific URL segments, which will be concatenated with the base URL.
     */
    const val LOGIN_URL = "login"
    const val GET_TASKS_URL = "task/getTasks"
    const val GET_USER_URL = "user"
    const val GET_USERS_URL = "users"
    const val CREATE_TASK_URL = "task/create"
    const val GET_DEPARTMENTS_URL = "department"

    /**
     * Header values.
     */
    const val HEADER_TOKEN = "token"
}
