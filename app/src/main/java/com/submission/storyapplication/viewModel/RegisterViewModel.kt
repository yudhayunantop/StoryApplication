package com.submission.storyapplication.viewModel

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.core.helper.Resources
import com.submission.storyapplication.core.domain.models.LoginModel
import com.submission.storyapplication.core.domain.useCase.RegisterUseCase
import com.submission.storyapplication.core.repository.RegisterRepository
import kotlinx.coroutines.flow.flow

class RegisterViewModel (val useCase: com.submission.storyapplication.core.domain.useCase.RegisterUseCase): ViewModel(){
    fun register(name:String, email: String, password: String)= flow {
        emit(Resources.Loading(data = null))
        try {
            val responseRegister = useCase.register(name, email, password)
            if (responseRegister.error == false){
                emit(Resources.Success(data=responseRegister.message.toString()))
            }else if (responseRegister.error==true){
                emit(Resources.Error(data=null, message = responseRegister.message.toString()))
            }
        }catch (exception:Exception){
            emit(Resources.Error(data = null, message = exception.message?: "Error"))
        }
    }
}