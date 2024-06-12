package com.dicoding.submissionawal.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.submissionawal.data.response.GithubResponse
import com.dicoding.submissionawal.data.response.ItemsItem
import com.dicoding.submissionawal.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _listItems = MutableLiveData<List<ItemsItem>>()
    val listItems: LiveData<List<ItemsItem>> = _listItems

    private val _listFollower = MutableLiveData<List<ItemsItem>>()
    val listFollower : LiveData<List<ItemsItem>> = _listFollower

    private val _listFollowing = MutableLiveData<List<ItemsItem>>()
    val listFollowing : LiveData<List<ItemsItem>> = _listFollowing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainViewModel"
        private const val LOGIN = "Arief"
    }

    init {
        findGithub()
    }

    fun findGithub(username: String="Arif"){
        _isLoading.value = true
        val client  = ApiConfig.getApiService().getSearchUsers(username)
        client.enqueue(object : Callback<GithubResponse>{
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    _listItems.value = response.body()?.items
                }else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getFollowers(q : String ) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowers(q)
        client.enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(call: Call<List<ItemsItem>>, response: Response<List<ItemsItem>>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listFollower.value = response.body()

                } else {
                    Log.d(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getFollowing(q : String ) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowing(q)
        client.enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(call: Call<List<ItemsItem>>, response: Response<List<ItemsItem>>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listFollowing.value = response.body()

                } else {
                    Log.d(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
    }
}


