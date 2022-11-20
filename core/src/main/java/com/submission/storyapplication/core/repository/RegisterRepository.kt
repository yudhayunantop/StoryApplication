package com.submission.storyapplication.core.repository

import com.submission.storyapplication.core.api.ApiEndPoint
import com.submission.storyapplication.core.domain.repoInterface.IRegisterRepository

class RegisterRepository(val apiEndPoint: ApiEndPoint):
    com.submission.storyapplication.core.domain.repoInterface.IRegisterRepository {
    override suspend fun register(name: String, email: String, password: String)=apiEndPoint.register(name, email, password)
}