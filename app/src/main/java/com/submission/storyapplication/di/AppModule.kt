package com.submission.storyapplication.di

import com.submission.storyapplication.MainViewModel
import com.submission.storyapplication.viewModel.*
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
    factory<com.submission.storyapplication.core.domain.useCase.LoginUseCase> {
        com.submission.storyapplication.core.domain.interactor.LoginInteractor(
            get()
        )
    }
    factory<com.submission.storyapplication.core.domain.useCase.RegisterUseCase> {
        com.submission.storyapplication.core.domain.interactor.RegisterInteractor(
            get()
        )
    }
    factory<com.submission.storyapplication.core.domain.useCase.MapsUseCase> {
        com.submission.storyapplication.core.domain.interactor.MapsInteractor(
            get()
        )
    }
    factory<com.submission.storyapplication.core.domain.useCase.AllStoriesUseCase> {
        com.submission.storyapplication.core.domain.interactor.AllStoriesInteractor(
            get()
        )
    }
    factory<com.submission.storyapplication.core.domain.useCase.AddStoriesUseCase> {
        com.submission.storyapplication.core.domain.interactor.AddStoriesInteractor(
            get()
        )
    }
    factory<com.submission.storyapplication.core.domain.useCase.FavoriteUseCase> {
        com.submission.storyapplication.core.domain.interactor.FavoriteInteractor(
            get()
        )
    }
}