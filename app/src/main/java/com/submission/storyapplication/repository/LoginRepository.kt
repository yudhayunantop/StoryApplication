package com.submission.storyapplication.repository

import com.submission.storyapplication.api.ApiEndPoint
import com.submission.storyapplication.domain.repoInterface.ILoginRepository

class LoginRepository(val apiEndPoint: ApiEndPoint): ILoginRepository {
    override suspend fun login(email: String, password: String)=apiEndPoint.login(email, password)
}