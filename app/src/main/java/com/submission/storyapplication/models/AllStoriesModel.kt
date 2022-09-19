package com.submission.storyapplication.models

data class AllStoriesModel(
    val error: Boolean?,
    val message: String?,
    val listStory: List<stories>?
){
    data class stories(
        val id: String?,
        val name: String?,
        val description: String?,
        val photoUrl: String?,
        val createdAt: String?,
        val lat: Int?,
        val lon: Int?
    )
}
