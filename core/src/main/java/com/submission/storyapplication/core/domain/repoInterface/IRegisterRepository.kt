package com.submission.storyapplication.core.domain.repoInterface

import com.submission.storyapplication.core.domain.models.ResponseModel

interface IRegisterRepository {
    suspend fun register(name: String, email: String, password: String): com.submission.storyapplication.core.domain.models.ResponseModel
}