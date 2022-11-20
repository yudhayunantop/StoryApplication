package com.submission.storyapplication.core.domain.repoInterface

import com.submission.storyapplication.core.domain.models.LoginModel

interface ILoginRepository {
 suspend fun login(email: String, password: String): com.submission.storyapplication.core.domain.models.LoginModel
}