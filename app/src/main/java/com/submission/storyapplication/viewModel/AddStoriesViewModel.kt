package com.submission.storyapplication.viewModel

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.domain.useCase.AddStoriesUseCase
import com.submission.storyapplication.helper.Resources
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AddStoriesViewModel(val useCase: AddStoriesUseCase) : ViewModel() {
    fun addStories(token: String, description: RequestBody, photo: MultipartBody.Part) = flow {
        emit(Resources.Loading(data = null))
        try {
            val response = useCase.addStories(token, description, photo)
            if (response.error == false){
                emit(Resources.Success(data=response.message))
            }else{
                emit(Resources.Error(message = response.message.toString()))
            }
        } catch (e: Exception) {
            emit(Resources.Error(message = e.message.toString()))
        }
    }
}