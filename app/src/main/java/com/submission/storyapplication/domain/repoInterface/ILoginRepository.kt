package com.submission.storyapplication.domain.repoInterface

import com.submission.storyapplication.domain.models.LoginModel

interface ILoginRepository {
 suspend fun login(email: String, password: String): LoginModel
}