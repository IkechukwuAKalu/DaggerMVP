package com.ikechukwuakalu.daggermvp.userdetails

import com.ikechukwuakalu.daggermvp.data.UsersDataSource
import com.ikechukwuakalu.daggermvp.data.UsersRepository
import com.ikechukwuakalu.daggermvp.data.models.User
import com.ikechukwuakalu.daggermvp.di.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class UserDetailsPresenter @Inject constructor(var usersRepository: UsersRepository, @UserLogin var login: String)
    : UserDetailsContract.Presenter {

    var detailsView: UserDetailsContract.View? = null

    override fun attach(view: UserDetailsContract.View) {
        detailsView = view
    }

    override fun detach() {
        detailsView = null
    }

    override fun fetchUserDetails() {
        detailsView?.showLoading()
        usersRepository.getUser(login, object : UsersDataSource.FetchUserCallback{
            override fun onSuccess(user: User?) {
                detailsView?.hideLoading()
                if (user == null) {
                    detailsView?.showErrorLoadingUser("User not found")
                } else {
                    detailsView?.setTitle(user.login)
                    detailsView?.showUserDetails(user)
                }
            }

            override fun onFailure(t: Throwable) {
                detailsView?.hideLoading()
                detailsView?.showErrorLoadingUser(t.message.toString())
            }
        })
    }
}