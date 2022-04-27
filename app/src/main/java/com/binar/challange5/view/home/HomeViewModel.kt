package com.binar.challange5.view.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.challange5.model.GetMovieResponse
import com.binar.challange5.model.Result
import com.binar.challange5.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel : ViewModel(){
    private val _movie = MutableLiveData<List<Result>>()
    val movie : LiveData<List<Result>> =   _movie

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    init {
        fetchData()
    }
     fun fetchData(){
         _loading.value = true
         ApiClient.instance.getMovie().enqueue(object : Callback<GetMovieResponse> {

             override fun onResponse(
                 call: Call<GetMovieResponse>,
                 response: Response<GetMovieResponse>
             ) {
                 val code = response.code()


                 if (code==200){
                     _loading.value= false
                     _movie.value = response.body()?.results
                 }
                 Log.d("sukses","harusnya bisa")
             }


             override fun onFailure(call: Call<GetMovieResponse>, t: Throwable) {
                 _loading.value = false
                 Log.d("gagal","fail")
             }
         })

    }




}

