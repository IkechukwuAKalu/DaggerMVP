package com.ikechukwuakalu.daggermvp.userdetails

import android.os.Bundle
import com.ikechukwuakalu.daggermvp.R
import com.ikechukwuakalu.daggermvp.base.BaseActivity
import javax.inject.Inject

class UsersDetailsActivity: BaseActivity() {

    @Inject
    lateinit var view: UserDetailsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_detail_act)

        addFragment(R.id.detailsRoot, view)
    }

    companion object {
        val LOGIN_KEY = "userLogin"
    }
}