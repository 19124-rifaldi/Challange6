package com.binar.challange5.room

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: UserModel) : Long

    @Query("SELECT * FROM UserModel WHERE email = :email AND password LIKE :password")
    suspend fun getPengguna(email : String, password : String) : UserModel

    @Query("SELECT * FROM UserModel WHERE id = :id")
    suspend fun getUser(id: Int): UserModel

    @Update
    suspend fun update(user: UserModel)

}