package com.submission.storyapplication.viewModel

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.helper.Resources
import com.submission.storyapplication.models.LoginModel
import com.submission.storyapplication.repository.RegisterRepository
import kotlinx.coroutines.flow.flow

class RegisterViewModel (val repository: RegisterRepository): ViewModel(){
    fun register(name:String, email: String, password: String)= flow {
        emit(Resources.Loading(data = null))
        try {
            val responseRegister = repository.register(name, email, password)
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