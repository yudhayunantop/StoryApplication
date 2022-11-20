package com.submission.storyapplication.core.repository

import com.submission.storyapplication.core.api.ApiEndPoint
import com.submission.storyapplication.core.domain.repoInterface.ILoginRepository

class LoginRepository(val apiEndPoint: ApiEndPoint):
    com.submission.storyapplication.core.domain.repoInterface.ILoginRepository {
    override suspend fun login(email: String, password: String)=apiEndPoint.login(email, password)
}