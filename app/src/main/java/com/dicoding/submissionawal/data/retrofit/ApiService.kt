package com.dicoding.submissionawal.data.retrofit

import com.dicoding.submissionawal.data.response.DetailUserResponse
import com.dicoding.submissionawal.data.response.GithubResponse
import com.dicoding.submissionawal.data.response.ItemsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

//  Search : https://api.github.com/search/users?q={username}
    @GET("search/users")
    fun getSearchUsers(@Query("q") q: String): Call<GithubResponse>

//  Detail user : https://api.github.com/users/{username}
    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<ItemsItem>>
    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<ItemsItem>>
}

