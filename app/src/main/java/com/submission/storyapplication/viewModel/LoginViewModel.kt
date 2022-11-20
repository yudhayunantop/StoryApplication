package com.submission.storyapplication.viewModel

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.core.helper.Resources
import com.submission.storyapplication.core.domain.models.LoginModel
import com.submission.storyapplication.core.domain.useCase.LoginUseCase
import kotlinx.coroutines.flow.flow

class LoginViewModel (val useCase: com.submission.storyapplication.core.domain.useCase.LoginUseCase): ViewModel(){
    fun login(email: String, password: String)= flow {
        emit(Resources.Loading(data = null))
        try {
            val responseLogin = useCase.login(email, password)
            if (responseLogin.error == false){
                emit(Resources.Success(data=responseLogin.loginResult as com.submission.storyapplication.core.domain.models.LoginModel.login))
            }else if (responseLogin.error==true){
                emit(Resources.Error(data=null, message = responseLogin.message.toString()))
            }
        }catch (exception:Exception){
            emit(Resources.Error(data = null, message = exception.message?: "Error"))
        }
    }
}