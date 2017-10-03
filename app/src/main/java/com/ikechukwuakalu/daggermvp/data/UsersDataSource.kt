package com.ikechukwuakalu.daggermvp.data

import com.ikechukwuakalu.daggermvp.data.models.Api
import com.ikechukwuakalu.daggermvp.data.models.User
import io.reactivex.Observable

interface UsersDataSource {

    fun getUsers(city: String) : Observable<Api>

    fun getUser(login: String) : Observable<User>

}