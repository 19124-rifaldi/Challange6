package com.binar.challange5.network

import com.binar.challange5.model.GetMovieResponse

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
//const val BASE_URL=
//    "https://api.themoviedb.org/3/"

interface ApiService {
    @GET ("movie/popular?api_key=a3694c11f9e2e2c52ca47878bcd70933")
    suspend fun getMovie(): GetMovieResponse


}

object ApiClient{

    private val logging : HttpLoggingInterceptor
    get(){
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
        }
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
    val instance : ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(Link.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        retrofit.create(ApiService::class.java)

    }
}

object Link{
    const val BASE_URL=
        "https://api.themoviedb.org/3/"
}