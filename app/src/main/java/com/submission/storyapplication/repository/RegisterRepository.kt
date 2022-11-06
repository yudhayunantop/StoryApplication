package com.submission.storyapplication.repository

import com.submission.storyapplication.api.ApiEndPoint

class RegisterRepository(val apiEndPoint: ApiEndPoint) {
    suspend fun register(name: String, email: String, password: String)=apiEndPoint.register(name, email, password)
}