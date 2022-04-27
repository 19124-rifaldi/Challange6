package com.binar.challange5.view.register


import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.challange5.room.UserDatabase
import com.binar.challange5.room.UserModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.util.concurrent.Executors


class RegistViewModel (application: Application) : AndroidViewModel(application) {
    private var dataBase : UserDatabase? = null
    private val context = getApplication<Application>().applicationContext
    val messageHandler = Handler(Looper.getMainLooper())
    val executor = Executors.newSingleThreadExecutor()

    private fun runOnUiThread(action: Runnable){
        messageHandler.post(action)
    }


    private val _regist = MutableLiveData<Int>()
    fun regist(): LiveData<Int>{
        return _regist
    }

    fun registTry(user : UserModel){
        dataBase = UserDatabase.getInstance(context)
        GlobalScope.async {
            val regist =dataBase?.userDao()?.insertUser(user)
            runOnUiThread {
                if (regist != 0.toLong()){
                    Toast.makeText(context, "Pendaftaran telah berhasil", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(context, "Pendaftaran gagal", Toast.LENGTH_LONG).show()

                }
            }
        }




    }
}