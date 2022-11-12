package com.submission.storyapplication.api

import com.submission.storyapplication.domain.models.AllStoriesModel
import com.submission.storyapplication.domain.models.LoginModel
import com.submission.storyapplication.domain.models.ResponseModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiEndPoint {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ) : ResponseModel

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ) : LoginModel

    @Multipart
    @POST("stories")
    suspend fun add_story(
        @Header("Authorization") token:String,
        @Part("description") description: RequestBody,
        @Part photo: MultipartBody.Part
    ) : ResponseModel

    @GET("stories")
    suspend fun get_all_stories(
    @Header("Authorization") token:String,
    @Query("page") page: Int,
    @Query("size") size: Int
    ) : AllStoriesModel

    @GET("stories?location=1")
    suspend fun get_all_stories_location(
        @Header("Authorization") token:String
    ) : AllStoriesModel
}