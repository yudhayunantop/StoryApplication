package com.submission.storyapplication.koin

import com.submission.storyapplication.preferences.Preferences

class StorageModule {

    val storageModule = module {
        factory {
            Preferences.init(this)
        }

        single {
//        UserRepository(get())
        }
    }
}