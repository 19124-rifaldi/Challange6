package com.binar.challange5.view.detail

import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.binar.challange5.model.Result
import com.binar.challange5.network.ApiClient
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.security.auth.callback.Callback

class DetailViewModel:ViewModel() {

    private val _detail = MutableLiveData<String>()
    val detail : LiveData<String> =   _detail


    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading



    fun loadPhoto(frag : Fragment,url:String, container : ImageView){
        _loading.value = true

        val urlb = "https://image.tmdb.org/t/p/original/"

        Glide.with(frag)
            .load("$urlb$url")
            .into(container)


    }
}


