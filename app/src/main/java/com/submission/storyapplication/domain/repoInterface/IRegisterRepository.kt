package com.submission.storyapplication.domain.repoInterface

import com.submission.storyapplication.domain.models.ResponseModel

interface IRegisterRepository {
    suspend fun register(name: String, email: String, password: String): ResponseModel
}