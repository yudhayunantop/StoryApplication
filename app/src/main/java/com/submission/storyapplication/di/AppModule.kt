package com.submission.storyapplication.di

import com.submission.storyapplication.api.ApiEndPoint
import com.submission.storyapplication.helper.constant.baseUrl
import com.submission.storyapplication.repository.LoginRepository
import com.submission.storyapplication.repository.MapsRepository
import com.submission.storyapplication.repository.RegisterRepository
import com.submission.storyapplication.viewModel.LoginViewModel
import com.submission.storyapplication.viewModel.MapsViewModel
import com.submission.storyapplication.viewModel.RegisterViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule= module{
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//                Tambah waktu agar tidak timeout
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
    single{ LoginRepository(get())}
    single{MapsRepository(get())}
    single{RegisterRepository(get())}

}

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel{MapsViewModel(get())}
    viewModel{RegisterViewModel(get())}
}