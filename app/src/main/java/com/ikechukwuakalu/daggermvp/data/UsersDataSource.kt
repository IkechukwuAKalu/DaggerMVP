package com.ikechukwuakalu.daggermvp.data

import com.ikechukwuakalu.daggermvp.data.models.User

interface UsersDataSource {

    fun getUsers(city: String, callback: LoadUsersCallback)

    fun getUser(login: String, callback: FetchUserCallback)

    interface LoadUsersCallback{
        fun onSuccess(users: List<User>)

        fun onFailure(t: Throwable)
    }

    interface FetchUserCallback{
        fun onSuccess(user: User?)

        fun onFailure(t: Throwable)
    }
}