package com.binar.challange5

import android.app.Application
import com.binar.challange5.model.movie_repo.MovieSource
import com.binar.challange5.model.movie_repo.Repository

class App:Application() {
    private val movieDataSource by lazy { MovieSource() }
    val repository by lazy { Repository(movieDataSource) }
}