package com.example.submision1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submision1.data.response.DetailUserResponse
import com.example.submision1.data.response.GitResponse
import com.example.submision1.data.response.ItemsItem
import com.example.submision1.data.retrofit.ApiConfig
import com.example.submision1.ui.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel: ViewModel() {

    private val _listDetailUser = MutableLiveData<DetailUserResponse?>()
    val listDetailUser : LiveData<DetailUserResponse?> = _listDetailUser

    private val _isLoading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _isLoading


    fun getDetailUser(user: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(user)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listDetailUser.value = (response.body())

                } else {
                    Log.e(TAG, "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure : ${t.message.toString()}")
            }
        })
    }


    companion object {
        private const val TAG = "ListViewModel"
    }
}