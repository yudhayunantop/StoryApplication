package com.submission.storyapplication.core.database

import androidx.room.*
import com.submission.storyapplication.core.domain.models.AllStoriesModel
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {
    @Query("SELECT * FROM stories")
    fun getAllStoriesFavorite(): List<com.submission.storyapplication.core.domain.models.AllStoriesModel.stories>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(stories: com.submission.storyapplication.core.domain.models.AllStoriesModel.stories)

    @Delete
    suspend fun deleteFavorite(stories: com.submission.storyapplication.core.domain.models.AllStoriesModel.stories)
}