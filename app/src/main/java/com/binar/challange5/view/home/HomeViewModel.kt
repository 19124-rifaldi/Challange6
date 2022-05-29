package com.binar.challange5.view.home

import android.util.Log
import androidx.lifecycle.*
import com.binar.challange5.model.Result
import com.binar.challange5.model.movie_repo.Repository
import com.binar.challange5.room.UserModel
import com.binar.challange5.room.UserRepo
import com.binar.challange5.utils.DataStoreManager

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.binar.challange5.resource.Resource


class HomeViewModel(
    private val repo: Repository,
    private val userRepo: UserRepo,
    private val pref : DataStoreManager
    ) :
    ViewModel(){


    private val _movie = MutableLiveData<List<Result>>()
    val movie : LiveData<List<Result>> =   _movie

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    private val _user = MutableLiveData<Resource<UserModel>>()
    val user : LiveData<Resource<UserModel>> get() = _user

    init {
        fetchData()
    }
     fun fetchData() {
         _loading.value = true
         viewModelScope.launch {
             delay(1000)
             _loading.value = true
             try {
                 _movie.value = repo.getMovieHome()
                 Log.d("sukses", "harusnya bisa")
             } catch (t: Throwable) {

             } finally {
                 _loading.value = false
             }
         }
     }

         fun User(id : Int){
             viewModelScope.launch(Dispatchers.IO) {
                 _user.postValue(Resource.loading(null))
                 try {
                     val data = userRepo.getUser(id)
                     _user.postValue(Resource.success(data, 0))
                 } catch (exception: Exception) {
                     _user.postValue(Resource.error(null, exception.message!!))
                 }
             }
         }

         fun getIdUser(): LiveData<Int>{
             return pref.getIdUser().asLiveData()
         }

        fun clearDataUser() {
            viewModelScope.launch {
                pref.logoutUser()
            }
        }







//         ApiClient.instance.getMovie().enqueue(object : Callback<GetMovieResponse> {
//
//             override fun onResponse(
//                 call: Call<GetMovieResponse>,
//                 response: Response<GetMovieResponse>
//             ) {
//                 val code = response.code()
//
//
//                 if (code==200){
//                     _loading.value= false
//                     _movie.value = response.body()?.results
//                 }
//                 Log.d("sukses","harusnya bisa")
//             }
//
//
//             override fun onFailure(call: Call<GetMovieResponse>, t: Throwable) {
//                 _loading.value = false
//                 Log.d("gagal","fail")
//             }
//         })








}

