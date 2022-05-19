package com.binar.challange5.view.profile

import androidx.lifecycle.*
import com.binar.challange5.di.Resource
import com.binar.challange5.room.UserModel
import com.binar.challange5.room.UserRepo
import com.binar.challange5.utils.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class ProfileViewModel(
    private val userRepo: UserRepo,
    private val dataStore : DataStoreManager
):ViewModel() {
    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private var _userData = MutableLiveData<Resource<UserModel>>()
    val userData: LiveData<Resource<UserModel>> get() = _userData

    fun getId():LiveData<Int>{
        return dataStore.getIdUser().asLiveData()
    }

    fun getUser(id:Int)=viewModelScope.launch(Dispatchers.IO){
        _loading.postValue(true)
        try {
            _loading.postValue(true)
            delay(1000)
            val dataUser = userRepo.getUser(id)
            _userData.postValue(Resource.success(dataUser,0))
        }catch (exception:Exception){
            _userData.postValue(Resource.error(null,exception.message!!))
        }finally {
            _loading.postValue(false)
        }
    }

    fun clearDataUser() {
        viewModelScope.launch {
            dataStore.logoutUser()
        }
    }




}