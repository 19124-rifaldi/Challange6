package com.binar.challange5.view.profile

import androidx.lifecycle.*
import com.binar.challange5.room.UserModel
import com.binar.challange5.room.UserRepo
import com.binar.challange5.utils.DataStoreManager
import com.bumptech.glide.load.engine.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class UpdateProfileViewModel(
    private val userRepo: UserRepo,
    private val dataStore : DataStoreManager
): ViewModel() {

    private var _userData = MutableLiveData<com.binar.challange5.resource.Resource<UserModel>>()
    val userData: LiveData<com.binar.challange5.resource.Resource<UserModel>> get() = _userData

    fun getId():LiveData<Int>{
        return dataStore.getIdUser().asLiveData()
    }

    fun getUser (id:Int)=viewModelScope.launch(Dispatchers.IO) {
        try {
            val dataUser = userRepo.getUser(id)
            _userData.postValue(com.binar.challange5.resource.Resource.success(dataUser,0))
        }catch (exception: Exception){
            _userData.postValue(com.binar.challange5.resource.Resource.error(null,exception.message!!))
        }
    }

    fun update (user : UserModel) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.update(user)
        }

    }


}