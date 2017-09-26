package com.ikechukwuakalu.daggermvp.data

import com.ikechukwuakalu.daggermvp.data.models.User
import com.ikechukwuakalu.daggermvp.data.qualifiers.Remote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class UsersRepository @Inject constructor(@Remote val remoteUsersDataSource: UsersDataSource)
    : UsersDataSource {

    override fun getUsers(city: String, callback: UsersDataSource.LoadUsersCallback) {
        remoteUsersDataSource.getUsers(city, object : UsersDataSource.LoadUsersCallback{
            override fun onSuccess(users: List<User>) {
                callback.onSuccess(users)
            }

            override fun onFailure(t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

    override fun getUser(login: String, callback: UsersDataSource.FetchUserCallback) {
        remoteUsersDataSource.getUser(login, object : UsersDataSource.FetchUserCallback{
            override fun onSuccess(user: User?) {
                callback.onSuccess(user)
            }

            override fun onFailure(t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

}