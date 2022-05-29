package com.binar.challange5

import android.app.Application
import com.binar.challange5.model.movie_repo.MovieSource
import com.binar.challange5.model.movie_repo.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.binar.challange5.di.*

class App:Application() {
//    private val movieDataSource by lazy { MovieSource() }
//    val repository by lazy { Repository(movieDataSource) }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    userDataStoreModule,
                    repositoryModuleMovie,
                    repositoryModuleUser,
                    viewModelModule,
                    remoteDataSourceModule

                )
            )
        }
    }
}