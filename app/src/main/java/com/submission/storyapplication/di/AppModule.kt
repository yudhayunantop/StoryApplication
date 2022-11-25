package com.submission.storyapplication.di

import com.submission.storyapplication.MainViewModel
import com.submission.storyapplication.core.domain.interactor.*
import com.submission.storyapplication.core.domain.useCase.*
import com.submission.storyapplication.ui.viewModel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { MapsViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { AddStoriesViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}

val interactorModul = module {
    factory<LoginUseCase> {
        LoginInteractor(
            get()
        )
    }
    factory<RegisterUseCase> {
        RegisterInteractor(
            get()
        )
    }
    factory<MapsUseCase> {
        MapsInteractor(
            get()
        )
    }
    factory<AllStoriesUseCase> {
        AllStoriesInteractor(
            get()
        )
    }
    factory<AddStoriesUseCase> {
        AddStoriesInteractor(
            get()
        )
    }
    factory<FavoriteUseCase> {
        FavoriteInteractor(
            get()
        )
    }
}