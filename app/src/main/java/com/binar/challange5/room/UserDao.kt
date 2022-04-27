package com.binar.challange5.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: UserModel) : Long

    @Query("SELECT username FROM UserModel WHERE UserModel.email = :email AND UserModel.password = :password")
    fun getPengguna(email : String?, password : String?) : String

}