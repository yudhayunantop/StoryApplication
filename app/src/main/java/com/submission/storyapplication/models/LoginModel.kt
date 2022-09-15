package com.submission.storyapplication.models

data class LoginModel(
    val error: Boolean?,
    val message: String?,
    val loginResult: login?
){
    data class login(
        val userId: String?,
        val name: String?,
        val token: String?
    )
}
