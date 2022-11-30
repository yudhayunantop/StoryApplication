package com.submission.storyapplication.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel

@Database(
    entities = [AllStoriesModel.stories::class],
    version = 2,
    exportSchema = false
)
abstract class StoriesDatabase : RoomDatabase() {
    abstract fun storyDao (): StoryDao
}