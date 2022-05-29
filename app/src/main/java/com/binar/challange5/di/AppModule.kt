package com.binar.challange5.di

import com.binar.challange5.model.movie_repo.MovieSource
import com.binar.challange5.model.movie_repo.Repository
import com.binar.challange5.room.UserRepo
import com.binar.challange5.view.detail.DetailViewModel
import com.binar.challange5.view.home.HomeViewModel
import com.binar.challange5.view.login.LoginViewModel
import com.binar.challange5.view.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module


    val remoteDataSourceModule = module {
        single { MovieSource() }
    }

    val repositoryModuleUser = module {
        single { UserRepo(get()) }
    }

    val repositoryModuleMovie = module {
        single { Repository(get()) }
    }

    val viewModelModule = module {
        viewModel { HomeViewModel(get(), get(), get()) }
        viewModel { LoginViewModel(get(), get()) }
        viewModel { ProfileViewModel(get(), get()) }
    }
