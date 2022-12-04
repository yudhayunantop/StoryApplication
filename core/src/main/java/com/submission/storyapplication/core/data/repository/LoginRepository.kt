package com.submission.storyapplication.core.data.repository

import com.submission.storyapplication.core.data.remote.RemoteDataSource
import com.submission.storyapplication.core.domain.repoInterface.ILoginRepository

class LoginRepository(val remoteDataSource: RemoteDataSource):
    ILoginRepository {
    override suspend fun login(email: String, password: String)=remoteDataSource.login(email, password)
}