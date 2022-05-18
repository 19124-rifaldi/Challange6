package com.binar.challange5.model.movie_repo

import com.binar.challange5.model.Result
import com.binar.challange5.network.ApiClient

class MovieSource {
    suspend fun getMovie(): List<Result>?{
        try {
            return ApiClient.instance.getMovie().results
        }catch (cause: Throwable){
            throw ErrorMovie("data cant load", cause)
        }
    }
}
class ErrorMovie(message: String, cause: Throwable?) : Throwable(message, cause)