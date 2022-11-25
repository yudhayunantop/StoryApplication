package com.submission.storyapplication.core.data.repository

import com.submission.storyapplication.core.data.remote.ApiEndPoint
import com.submission.storyapplication.core.data.remote.RemoteDataSource
import com.submission.storyapplication.core.domain.repoInterface.IRegisterRepository

class RegisterRepository(val remoteDataSource: RemoteDataSource):
    IRegisterRepository {
    override suspend fun register(name: String, email: String, password: String)=remoteDataSource.register(name, email, password)
}