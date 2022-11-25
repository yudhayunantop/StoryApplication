package com.submission.storyapplication.core.data.database

import androidx.room.*
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel

@Dao
interface StoryDao {
    @Query("SELECT * FROM stories")
    fun getAllStoriesFavorite(): List<AllStoriesModel.stories>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(stories: AllStoriesModel.stories)

    @Delete
    suspend fun deleteFavorite(stories: AllStoriesModel.stories)
}