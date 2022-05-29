package com.binar.challange5.view.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.binar.challange5.model.movie_repo.Repository
import com.binar.challange5.room.UserRepo
import com.binar.challange5.utils.DataStoreManager
import java.lang.IllegalArgumentException

//class HomeFactory(
//    private val repo : Repository,
//    private val userRepository: UserRepo?,
//    private val pref: DataStoreManager)
//    :ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
//            return HomeViewModel(repo,userRepository!!,pref) as T
//        }
//
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//
//    companion object {
//        @Volatile
//        private var instance : HomeFactory? = null
//        fun getInstance(
//            context: Context,
//            repository: Repository,
//            pref: DataStoreManager
//
//        ): HomeFactory =
//            instance ?: synchronized(this) {
//                instance ?: HomeFactory(
//                    repository,
//                    Injection.provideRepositoryUser(context),
//                    pref
//                )
//            }.also { instance = it }
//    }
//}