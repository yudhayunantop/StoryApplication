package com.submission.storyapplication.core.data.local.database

import androidx.room.*
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {
    @Query("SELECT * FROM stories")
    fun getAllStoriesFavorite(): Flow<List<AllStoriesModel.stories>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(stories: AllStoriesModel.stories)

    @Delete
    suspend fun deleteFavorite(stories: AllStoriesModel.stories)
}