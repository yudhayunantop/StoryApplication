package com.submission.storyapplication.database

import androidx.room.*
import com.submission.storyapplication.domain.models.AllStoriesModel
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {
    @Query("SELECT * FROM stories")
    fun getAllStoriesFavorite(): List<AllStoriesModel.stories>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(stories: AllStoriesModel.stories)

    @Delete
    suspend fun deleteFavorite(stories: AllStoriesModel.stories)
}