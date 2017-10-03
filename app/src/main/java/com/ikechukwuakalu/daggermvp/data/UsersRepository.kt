package com.ikechukwuakalu.daggermvp.data

import com.ikechukwuakalu.daggermvp.data.models.Api
import com.ikechukwuakalu.daggermvp.data.models.User
import com.ikechukwuakalu.daggermvp.data.qualifiers.Remote
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class UsersRepository @Inject constructor(@Remote private val remoteUsersDataSource: UsersDataSource)
    : UsersDataSource {

    override fun getUsers(city: String): Observable<Api> = remoteUsersDataSource.getUsers(city)

    override fun getUser(login: String): Observable<User> = remoteUsersDataSource.getUser(login)
}