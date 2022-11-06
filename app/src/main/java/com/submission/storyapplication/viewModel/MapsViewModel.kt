package com.submission.storyapplication.viewModel

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.helper.Resources
import com.submission.storyapplication.domain.models.AllStoriesModel
import com.submission.storyapplication.domain.models.LoginModel
import com.submission.storyapplication.repository.MapsRepository
import kotlinx.coroutines.flow.flow

class MapsViewModel (val repository: MapsRepository): ViewModel(){
    fun maps(token: String)= flow {
        emit(Resources.Loading(data = null))
        try {
            val responseMaps = repository.getAllStoriesLocation(token)
            if (responseMaps.error == false){
                emit(Resources.Success(data=responseMaps.listStory))
            }else if (responseMaps.error==true){
                emit(Resources.Error(data=null, message = responseMaps.message.toString()))
            }
        }catch (exception:Exception){
            emit(Resources.Error(data = null, message = exception.message?: "Error"))
        }
    }
}