package com.ikechukwuakalu.daggermvp.data.remote

import com.ikechukwuakalu.daggermvp.createService
import com.ikechukwuakalu.daggermvp.data.UsersDataSource
import com.ikechukwuakalu.daggermvp.data.models.Api
import com.ikechukwuakalu.daggermvp.data.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Singleton

@Singleton
class RemoteUsersDataSource : UsersDataSource {

    override fun getUsers(city: String, callback: UsersDataSource.LoadUsersCallback) {
        val service = createService(GithubService::class.java)
        service.getUsers("location:$city").enqueue(object : Callback<Api> {
            override fun onFailure(call: Call<Api>?, t: Throwable?) {
                callback.onFailure(t ?: Throwable("Unable to fetch Users"))
            }

            override fun onResponse(call: Call<Api>?, response: Response<Api>?) {
                callback.onSuccess(response?.body()?.items?.asList() ?: emptyList())
            }
        })
    }

    override fun getUser(login: String, callback: UsersDataSource.FetchUserCallback) {
        val service = createService(GithubService::class.java)
        service.fetchUser(login).enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                callback.onSuccess(response?.body())
            }

            override fun onFailure(call: Call<User>?, t: Throwable?) {
                callback.onFailure(t ?: Throwable("User not found"))
            }
        })
    }
}