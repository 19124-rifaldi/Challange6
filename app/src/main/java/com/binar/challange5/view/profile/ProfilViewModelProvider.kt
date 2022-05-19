package com.binar.challange5.view.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.binar.challange5.di.Injection
import com.binar.challange5.room.UserRepo
import com.binar.challange5.utils.DataStoreManager
import com.binar.challange5.view.login.LoginViewModel

class ProfilViewModelProvider(private val urepo : UserRepo?,
                              private val pref : DataStoreManager
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(urepo!!,pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        @Volatile
        private var instance: ProfilViewModelProvider? = null
        fun getInstance(
            context: Context,
            pref: DataStoreManager
        ): ProfilViewModelProvider =
            instance ?: synchronized(this) {
                instance ?: ProfilViewModelProvider(

                    Injection.provideRepositoryUser(context),
                    pref
                )
            }.also { instance = it }
    }

}
