package com.submission.storyapplication.login

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.core.domain.useCase.LoginUseCase
import com.submission.storyapplication.core.data.remote.response.LoginModel
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.flow

class LoginViewModel (val useCase: LoginUseCase): ViewModel(){
    suspend fun login(email:String, password:String)= useCase.login(email, password)
}