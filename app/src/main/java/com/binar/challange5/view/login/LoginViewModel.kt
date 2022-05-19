package com.binar.challange5.view.login

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.*
import com.binar.challange5.di.Resource
import com.binar.challange5.room.UserModel
import com.binar.challange5.room.UserRepo
import com.binar.challange5.utils.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginViewModel (
    private val repository: UserRepo,
    private val preferences: DataStoreManager
        ):ViewModel(){

    private val _loginStat = MutableLiveData<Resource<UserModel>>()
    val loginStat: LiveData<Resource<UserModel>> get() = _loginStat

    fun login(email:String,password:String){
        viewModelScope.launch(Dispatchers.IO) {

            _loginStat.postValue(Resource.loading(null))

            try {
                val du = repository.verifyLogin(email,password)
                _loginStat.postValue(Resource.success(du,0))

            }catch (exception : Exception){
                _loginStat.postValue(Resource.error(null,exception.message!!))
            }

        }
    }

    fun checkIfLogin(): LiveData<Int>{
        return preferences.getIdUser().asLiveData()
    }

    fun saveUserDataStore(id: Int) {
        viewModelScope.launch {
            preferences.saveUser(id)
        }
    }




//    if (login.isNullOrEmpty()){
////                Log.d("loginmodel","tidak ditemukan")
////                _registStat.value = 0
////
////            }else{
////                Log.d("loginmodel","ketemu")
////                _regist.value = login
////                _registStat.value = 1
//////                    Log.d("loginmodel2",login.toString())
////            }

//        executor.execute{
//            dataBase = UserDatabase.getInstance(context)
//            val login = dataBase?.userDao()?.getPengguna(email,password)
//            Log.d("login",login.toString())
//
//            runOnUiThread{
//                if (login.isNullOrEmpty()){
//                    Log.d("loginmodel","tidak ditemukan")
//                    _registStat.value = 0
//
//                }else{
//                    Log.d("loginmodel","ketemu")
//                    _regist.value = login
//                    _registStat.value = 1
////                    Log.d("loginmodel2",login.toString())
//                }
//            }
//
//        }


}

//(application: Application) : AndroidViewModel(application)
//private var dataBase : UserDatabase? = null
//private val context = getApplication<Application>().applicationContext
//val messageHandler = Handler(Looper.getMainLooper())
//val executor = Executors.newSingleThreadExecutor()