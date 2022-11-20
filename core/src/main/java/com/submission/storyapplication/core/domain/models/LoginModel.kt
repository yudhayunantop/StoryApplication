package com.submission.storyapplication.core.domain.models

data class LoginModel(
    val error: Boolean?,
    val message: String?,
    val loginResult: com.submission.storyapplication.core.domain.models.LoginModel.login?
){
    data class login(
        val userId: String?,
        val name: String?,
        val token: String?
    )
}
