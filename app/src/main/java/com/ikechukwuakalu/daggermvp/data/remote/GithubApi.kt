package com.ikechukwuakalu.daggermvp.data.remote

import com.ikechukwuakalu.daggermvp.data.models.Api
import com.ikechukwuakalu.daggermvp.data.models.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("search/users?")
    fun getUsers(@Query("q") city: String) : Observable<Api>

    @GET("users/{login}")
    fun fetchUser(@Path("login") login: String) : Observable<User>
}