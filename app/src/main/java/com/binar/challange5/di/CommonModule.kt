package com.binar.challange5.di

import androidx.room.Room
import com.binar.challange5.network.ApiService
import com.binar.challange5.network.Link
import com.binar.challange5.room.UserDatabase
import com.binar.challange5.utils.DataStoreManager
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit



    val databaseModule = module {
        factory { get<UserDatabase>().userDao() }
        single {
            Room.databaseBuilder(
                androidContext(),
                UserDatabase::class.java,
                "user"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    val networkModule = module {
        single {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            OkHttpClient.Builder()
                .addInterceptor(logging)
                .readTimeout(30,TimeUnit.SECONDS)
                .build()
        }

        single {
            Retrofit.Builder()
                .baseUrl(Link.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
                .create(ApiService::class.java)
        }
    }


val userDataStoreModule = module {
    single {
        DataStoreManager(androidContext())
    }
}