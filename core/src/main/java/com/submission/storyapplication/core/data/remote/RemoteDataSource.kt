package com.submission.storyapplication.core.data.remote

import com.submission.storyapplication.core.data.remote.response.AllStoriesModel
import com.submission.storyapplication.core.data.remote.response.LoginModel
import com.submission.storyapplication.core.data.remote.response.ResponseModel
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

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
    suspend fun add_story(token: String, description: RequestBody, photo: MultipartBody.Part): Flow<Resources<ResponseModel>>{
        return flow{
            emit(Resources.Loading(data = null))
            try {
                val responseAddStory=apiEndPoint.add_story(token=token, description=description, photo=photo)
                if (responseAddStory.error == false){
                    emit(Resources.Success(data=responseAddStory))
                }else if (responseAddStory.error==true){
                    emit(Resources.Error(data=null, message = responseAddStory.message.toString()))
                }
            }catch (E:Exception){
                emit(Resources.Error(message = E.message.toString()))
            }
        }
    }
    suspend fun get_all_stories(token: String, position: Int, loadSize: Int) =apiEndPoint.get_all_stories(token, position, loadSize)

    suspend fun getAllStories(token: String, position: Int, loadSize: Int) : Flow<Resources<List<AllStoriesModel.stories>>> {
        return flow {
            emit(Resources.Loading(data = null))
            try {
                val responseAllStories =  apiEndPoint.get_all_stories(token = token, page = position, size = loadSize)
                if (responseAllStories.error == false) {
                    emit(Resources.Success(data = responseAllStories.listStory as List<AllStoriesModel.stories>))
                } else {
                    emit(Resources.Error(message = responseAllStories.message.toString()))
                }
            } catch (e: Exception) {
                emit(Resources.Error(message = e.message.toString()))
            }
        }
    }

    suspend fun get_all_stories_location(token: String): Flow<Resources<List<AllStoriesModel.stories>>>{
        return flow{
            emit(Resources.Loading(data = null))
            try {
                val responseAllStoriesLocation=apiEndPoint.get_all_stories_location(token)
                if (responseAllStoriesLocation.error == false){
                    emit(Resources.Success(data=responseAllStoriesLocation.listStory as List<AllStoriesModel.stories>))
                }else if (responseAllStoriesLocation.error==true){
                    emit(Resources.Error(data=null, message = responseAllStoriesLocation.message.toString()))
                }
            }catch (E:Exception){
                emit(Resources.Error(message = E.message.toString()))
            }
        }
    }
}