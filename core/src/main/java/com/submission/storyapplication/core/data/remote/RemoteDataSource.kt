package com.submission.storyapplication.core.data.remote

import com.submission.storyapplication.core.data.remote.response.LoginModel
import com.submission.storyapplication.core.data.remote.response.ResponseModel
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource(private val apiEndPoint: ApiEndPoint) {
    suspend fun login(email:String, password:String): Flow<Resources<LoginModel.login>>{
        return flow {
            emit(Resources.Loading(data = null))
            try {
                val responseLogin=apiEndPoint.login(email, password)
                if (responseLogin.error == false){
                    emit(Resources.Success(data=responseLogin.loginResult as LoginModel.login))
                }else if (responseLogin.error==true){
                    emit(Resources.Error(data=null, message = responseLogin.message.toString()))
                }
            }catch (E:Exception){
                emit(Resources.Error(message = E.message.toString()))
            }
        }
    }
    suspend fun register(name:String, email:String, password: String): Flow<Resources<ResponseModel>>{
        return flow{
            emit(Resources.Loading(data = null))
            try {
                val responseRegister=apiEndPoint.register(name=name, email=email, password=password)
                if (responseRegister.error == false){
                    emit(Resources.Success(data=responseRegister))
                }else if (responseRegister.error==true){
                    emit(Resources.Error(data=null, message = responseRegister.message.toString()))
                }
            }catch (E:Exception){
                emit(Resources.Error(message = E.message.toString()))
            }
        }
    }
}