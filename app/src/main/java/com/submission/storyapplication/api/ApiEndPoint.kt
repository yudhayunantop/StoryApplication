package com.submission.storyapplication.api

import com.submission.storyapplication.models.AllStoriesModel
import com.submission.storyapplication.models.ResponseModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiEndPoint {
    @FormUrlEncoded
    @POST("register/")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ) : Call<ResponseModel>

    @FormUrlEncoded
    @POST("login/")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ) : Call<ResponseModel>

//    MINUS AUTH TOKEN
    @Multipart
    @POST("stories/")
    fun add_story(
        @Part("description") description: RequestBody,
        @Part photo: MultipartBody.Part,
        @Part("lat") lat: RequestBody,
        @Part("lon") lon: RequestBody
    ) : Call<ResponseModel>

//    MINUS AUTH TOKEN
    @GET("keluhan/{id}")
    fun get_all_stories(
        @Path("page") page: Int,
    ) : Call<AllStoriesModel>
}