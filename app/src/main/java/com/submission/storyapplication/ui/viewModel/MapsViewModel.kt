package com.submission.storyapplication.ui.viewModel

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.core.domain.useCase.MapsUseCase
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.flow

class MapsViewModel (val useCase: MapsUseCase): ViewModel(){
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