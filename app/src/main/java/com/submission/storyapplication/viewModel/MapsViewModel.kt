package com.submission.storyapplication.viewModel

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.core.helper.Resources
import com.submission.storyapplication.core.domain.models.AllStoriesModel
import com.submission.storyapplication.core.domain.models.LoginModel
import com.submission.storyapplication.core.domain.useCase.MapsUseCase
import com.submission.storyapplication.core.repository.MapsRepository
import kotlinx.coroutines.flow.flow

class MapsViewModel (val useCase: com.submission.storyapplication.core.domain.useCase.MapsUseCase): ViewModel(){
    fun maps(token: String)= flow {
        emit(Resources.Loading(data = null))
        try {
            val responseMaps = useCase.get_all_stories(token)
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