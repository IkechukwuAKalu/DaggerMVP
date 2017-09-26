package com.ikechukwuakalu.daggermvp.userdetails

import com.ikechukwuakalu.daggermvp.base.BasePresenter
import com.ikechukwuakalu.daggermvp.base.BaseView
import com.ikechukwuakalu.daggermvp.data.models.User

interface UserDetailsContract {

    interface View : BaseView<Presenter> {
        fun showLoading()

        fun hideLoading()

        fun setTitle(title: String)

        fun showUserDetails(user: User?)

        fun showErrorLoadingUser(message: String)
    }

    interface Presenter : BasePresenter<View> {
        fun fetchUserDetails()
    }
}