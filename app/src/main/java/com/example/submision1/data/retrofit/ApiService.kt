package com.example.submision1.data.retrofit

import com.example.submision1.data.response.DetailUserResponse
import com.example.submision1.data.response.GitResponse
import com.example.submision1.data.response.ItemsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/search/users")
    fun getListuser(
        @Query("q") id: String,
    ): Call<GitResponse>

    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<ItemsItem>>


}
//    @Headers("Authorization: token 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")