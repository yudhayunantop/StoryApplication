package com.submission.storyapplication.repository

import com.submission.storyapplication.api.ApiEndPoint

class LoginRepository(val apiEndPoint: ApiEndPoint) {
    suspend fun login(email: String, password: String)=apiEndPoint.login(email, password)
}