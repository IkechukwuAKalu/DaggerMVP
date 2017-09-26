package com.ikechukwuakalu.daggermvp.users

import com.ikechukwuakalu.daggermvp.base.BasePresenter
import com.ikechukwuakalu.daggermvp.base.BaseView
import com.ikechukwuakalu.daggermvp.data.models.User

interface UsersContract {

    interface View: BaseView<Presenter>{
        fun showLoading()

        fun hideLoading()

        fun hideUsers()

        fun setTitle(title: String)

        fun showUsers(users: List<User>)

        fun showUserDetails(login: String)

        fun showUsersNotAvailable()

        fun showErrorLoadingUsers(message: String?)
    }

    interface Presenter: BasePresenter<View> {
        fun loadUsers(city: String?)

        fun openUserDetails(login: String)
    }
}