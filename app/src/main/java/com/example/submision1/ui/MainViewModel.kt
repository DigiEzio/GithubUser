package com.example.submision1.ui

import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submision1.data.response.GitResponse
import com.example.submision1.data.response.ItemsItem
import com.example.submision1.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Timer
import java.util.TimerTask

class MainViewModel: ViewModel()   {

    private val _listUser = MutableLiveData<List<ItemsItem?>>()
    val listUser : LiveData<List<ItemsItem?>> = _listUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading


    init {
        findUser(USER_ID)
    }
    fun findUser(query: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getListuser(query)
        client.enqueue(object : Callback<GitResponse> {
            override fun onResponse(call: Call<GitResponse>,response: Response<GitResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listUser.value = response.body()?.items as List<ItemsItem?>
                } else {
                    Log.e(TAG, "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GitResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure : ${t.message.toString()}")
            }
        })
    }

        companion object {

        const val TAG = "MainViewModel"
        private const val USER_ID = "alif"
    }
}
