package com.submission.storyapplication.core.domain.repoInterface

import com.submission.storyapplication.core.data.remote.network.ApiResponse
import com.submission.storyapplication.core.data.remote.response.LoginModel
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow

interface ILoginRepository {
 suspend fun login(email: String, password: String): Flow<ApiResponse<LoginModel.login>>
}