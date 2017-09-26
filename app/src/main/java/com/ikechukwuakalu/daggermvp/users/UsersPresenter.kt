package com.ikechukwuakalu.daggermvp.users

import com.ikechukwuakalu.daggermvp.data.UsersDataSource
import com.ikechukwuakalu.daggermvp.data.UsersRepository
import com.ikechukwuakalu.daggermvp.data.models.User
import com.ikechukwuakalu.daggermvp.di.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class UsersPresenter @Inject constructor (var usersRepo: UsersRepository) : UsersContract.Presenter{

    private var view : UsersContract.View? = null

    override fun attach(view: UsersContract.View) {
        this@UsersPresenter.view = view
    }

    override fun detach() {
        view = null
    }

    override fun loadUsers(city: String?) {
        val location = city?.capitalize() ?: "Lagos"
        view?.hideUsers()
        view?.showLoading()
        usersRepo.getUsers(location, object : UsersDataSource.LoadUsersCallback{
            override fun onSuccess(users: List<User>) {
                var newUsers = emptyList<User>()
                if (location == "Lagos") {
                    newUsers += listOf(sponsoredUser())
                }
                newUsers += users
                view?.hideLoading()
                view?.showUsers(newUsers)
                view?.setTitle("Github Devs in $location")
            }

            override fun onFailure(t: Throwable) {
                view?.hideLoading()
                view?.showErrorLoadingUsers(t.message)
            }
        })
    }

    override fun openUserDetails(login: String) {
        view?.showUserDetails(login)
    }

    private fun sponsoredUser() : User {
        return User(id = 25153373, login = "IkechukwuAKalu", avatar_url = "https://avatars2.githubusercontent.com/u/25153373?v=4",
                name = "Ikechukwu A. Kalu", url = "https://api.github.com/users/IkechukwuAKalu")
    }
}