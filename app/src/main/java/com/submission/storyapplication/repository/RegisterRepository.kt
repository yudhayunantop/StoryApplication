package com.submission.storyapplication.repository

import com.submission.storyapplication.api.ApiEndPoint
import com.submission.storyapplication.domain.repoInterface.IRegisterRepository

class RegisterRepository(val apiEndPoint: ApiEndPoint): IRegisterRepository {
    override suspend fun register(name: String, email: String, password: String)=apiEndPoint.register(name, email, password)
}