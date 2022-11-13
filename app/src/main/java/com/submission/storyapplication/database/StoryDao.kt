package com.submission.storyapplication.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.submission.storyapplication.domain.models.AllStoriesModel
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {
    @Query("SELECT * FROM stories")
    fun getAllStoriesFavorite(): Flow<List<AllStoriesModel.stories>>

    @Insert
    suspend fun insertFavorite(stories: AllStoriesModel.stories)

    @Delete
    suspend fun  deleteFavorite(stories: AllStoriesModel.stories)
}