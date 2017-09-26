package com.ikechukwuakalu.daggermvp.data.remote

import com.ikechukwuakalu.daggermvp.data.models.Api
import com.ikechukwuakalu.daggermvp.data.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService{
    @GET("search/users?")
    fun getUsers(@Query("q") city: String) : Call<Api>

    @GET("users/{login}")
    fun fetchUser(@Path("login") login: String) : Call<User>
}