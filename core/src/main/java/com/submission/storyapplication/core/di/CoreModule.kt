package com.submission.storyapplication.core.di

import androidx.room.Room
import com.submission.storyapplication.core.data.local.LocalDataSource
import com.submission.storyapplication.core.data.local.room.StoriesDatabase
import com.submission.storyapplication.core.data.remote.network.ApiEndPoint
import com.submission.storyapplication.core.data.remote.RemoteDataSource
import com.submission.storyapplication.core.data.repository.*
import com.submission.storyapplication.core.domain.repoInterface.*
import com.submission.storyapplication.core.utils.constant.baseUrl
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val hostname = "story-api.dicoding.dev"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/cMX0U3RLCGDeVwYJ1GaXYfwDbP1ccAzqo9fkZGK5CoQ=")
            .add(hostname, "sha256/jQJTbIh0grw0/1TkHSumWb+Fs0Ggogr621gT3PvPKG0=")
            .add(hostname, "sha256/C5+lpZ7tcVwmwQIMcRtPbsQtWLABXhQzejna0wHFr8M=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//          Tambah waktu agar tidak timeout
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiEndPoint::class.java)
    }
}

val repositoryModule = module {
    single<ILoginRepository> { LoginRepository(get()) }
    single<IRegisterRepository> { RegisterRepository(get()) }
    single<IMapsRepository> { MapsRepository(get()) }
    single<IAllStoriesRepository> { StoriesRepository(get(), get()) }
    single<IFavoriteRepository> { FavoriteRepository(get()) }
    single{RemoteDataSource(get())}
    single{LocalDataSource(get())}
}

//val databaseModul = module {
//    single {
//        Room.databaseBuilder(
//            androidContext(),
//            StoriesDatabase::class.java, "stories_database.db"
//        )
//            .fallbackToDestructiveMigration()
//            .build()
//    }
//    factory {
//        get<StoriesDatabase>().storyDao()
//    }
//}

val databaseModul = module {
    factory { get<StoriesDatabase>().storyDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("submission".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            StoriesDatabase::class.java, "stories_database"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}