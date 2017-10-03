package com.ikechukwuakalu.daggermvp.data.remote

import com.ikechukwuakalu.daggermvp.data.UsersDataSource
import com.ikechukwuakalu.daggermvp.data.models.Api
import com.ikechukwuakalu.daggermvp.data.models.User
import com.ikechukwuakalu.daggermvp.utils.createService
import io.reactivex.Observable
import javax.inject.Singleton

@Singleton
class RemoteUsersDataSource : UsersDataSource {

    override fun getUsers(city: String): Observable<Api> {
        val service = createService(GithubApi::class.java)
        val location = "location:$city"
        return service.getUsers(location).cache()
    }

    override fun getUser(login: String): Observable<User> {
        val service = createService(GithubApi::class.java)
        return service.fetchUser(login).cache()
    }
}