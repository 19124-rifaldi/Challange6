package com.binar.challange5.room

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepo(private val userDao: UserDao) {

    companion object {
        @Volatile
        private var instance : UserRepo? = null

        fun getInstance(context: Context): UserRepo? {
            return instance ?: synchronized(UserRepo::class.java){
                if (instance == null) {
                    val database = UserDatabase.getInstance(context)
                    instance = UserRepo(database.userDao())
                }
                return instance
            }
        }
    }
    suspend fun verifyLogin(email : String, password : String): UserModel {
        return userDao.getPengguna(email,password)
    }

    suspend fun getUser(id: Int): UserModel {
        return userDao.getUser(id)
    }

    suspend fun update(user: UserModel) {
        withContext(Dispatchers.IO) {
            userDao.update(user)
        }
    }



}