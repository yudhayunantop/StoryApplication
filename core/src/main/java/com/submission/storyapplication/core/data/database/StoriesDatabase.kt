package com.submission.storyapplication.core.data.database

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
//    companion object {
//        @Volatile
//        private var INSTANCE: StoriesDatabase? = null
//
//        @JvmStatic
//        fun getDatabase(context: Context): StoriesDatabase {
//            return INSTANCE ?: synchronized(this) {
//                INSTANCE ?: Room.databaseBuilder(
//                    context.applicationContext,
//                    StoriesDatabase::class.java, "stories_database"
//                )
//                    .fallbackToDestructiveMigration()
//                    .build()
//                    .also { INSTANCE = it }
//            }
//        }
//    }
}