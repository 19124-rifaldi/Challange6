package com.binar.challange5.view.login

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.challange5.model.Result
import com.binar.challange5.room.UserDatabase
import com.binar.challange5.room.UserModel
import java.util.concurrent.Executors

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private var dataBase : UserDatabase? = null
    private val context = getApplication<Application>().applicationContext
    val messageHandler = Handler(Looper.getMainLooper())
    val executor = Executors.newSingleThreadExecutor()

    private fun runOnUiThread(action: Runnable){
        messageHandler.post(action)
    }


    private val _regist = MutableLiveData<String?>()
    fun regist(): LiveData<String?> {
        return _regist
    }

    private val _registStat = MutableLiveData<Int?>()
    fun registStat(): LiveData<Int?> {
        return _registStat
    }



    fun login(email:String,password:String){

        executor.execute{
            dataBase = UserDatabase.getInstance(context)
            val login = dataBase?.userDao()?.getPengguna(email,password)
            Log.d("login",login.toString())

            runOnUiThread{
                if (login.isNullOrEmpty()){
                    Log.d("loginmodel","tidak ditemukan")
                    _registStat.value = 0

                }else{
                    Log.d("loginmodel","ketemu")
                    _regist.value = login
                    _registStat.value = 1
//                    Log.d("loginmodel2",login.toString())
                }
            }

        }

    }


}