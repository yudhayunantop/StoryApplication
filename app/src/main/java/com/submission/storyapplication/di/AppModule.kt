package com.submission.storyapplication.di

import androidx.room.Room
import com.submission.storyapplication.MainViewModel
import com.submission.storyapplication.api.ApiEndPoint
import com.submission.storyapplication.domain.interactor.*
import com.submission.storyapplication.domain.useCase.*
import com.submission.storyapplication.helper.constant.baseUrl
import com.submission.storyapplication.database.StoriesDatabase
import com.submission.storyapplication.domain.repoInterface.*
import com.submission.storyapplication.repository.*
import com.submission.storyapplication.viewModel.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule= module{
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//          Tambah waktu agar tidak timeout
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS )
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

val repositoryModule= module{
    single<ILoginRepository>{LoginRepository(get())}
    single<IRegisterRepository>{RegisterRepository(get())}
    single<IMapsRepository>{MapsRepository(get())}
    single<IAllStoriesRepository>{StoriesRepository(get(),get())}
    single<IFavoriteRerpository>{FavoriteRepository(get())}
}

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel{MapsViewModel(get())}
    viewModel{RegisterViewModel(get())}
    viewModel{MainViewModel(get())}
    viewModel{AddStoriesViewModel(get())}
    viewModel{DetailViewModel(get())}
}

val databaseModul = module{
    single{
        Room.databaseBuilder(
            androidContext(),
            StoriesDatabase::class.java, "stories_database.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    factory{
        get<StoriesDatabase>().storyDao()
    }
}

val interactorModul = module{
    factory<LoginUseCase>{LoginInteractor(get())}
    factory<RegisterUseCase>{ RegisterInteractor(get()) }
    factory<MapsUseCase>{ MapsInteractor(get()) }
    factory<AllStoriesUseCase>{ AllStoriesInteractor(get()) }
    factory<AddStoriesUseCase>{ AddStoriesInteractor(get()) }
    factory<FavoriteUseCase>{ FavoriteInteractor(get()) }
}