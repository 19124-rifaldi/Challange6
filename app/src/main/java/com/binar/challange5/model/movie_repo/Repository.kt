package com.binar.challange5.model.movie_repo

import com.binar.challange5.model.Result
import com.binar.challange5.network.ApiClient

import com.binar.challange5.room.UserDatabase

class Repository(private val movieSource: MovieSource) {


    suspend fun getMovieHome() : List<Result>? {
        return movieSource.getMovie()
    }

}
