package com.submission.storyapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class AllStoriesModel(
    val error: Boolean?,
    val message: String?,
    val listStory: List<stories>?
):Serializable
{
    @Entity(tableName = "stories")
    data class stories(
        @PrimaryKey
        val id: String,
        val name: String?,
        val description: String?,
        val photoUrl: String?,
        val createdAt: String?,
        val lat: Float?,
        val lon: Float?
    ):Serializable
}
