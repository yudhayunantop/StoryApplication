package com.submission.storyapplication.core.data.remote.response

import androidx.room.ColumnInfo
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
        @ColumnInfo(name = "id")
        val id: String,
        @ColumnInfo(name = "name")
        val name: String?,
        @ColumnInfo(name="description")
        val description: String?,
        @ColumnInfo(name="photoUrl")
        val photoUrl: String?,
        @ColumnInfo(name="createdAt")
        val createdAt: String?,
        @ColumnInfo(name="lat")
        val lat: Float?,
        @ColumnInfo(name="lon")
        val lon: Float?
    ):Serializable
}
