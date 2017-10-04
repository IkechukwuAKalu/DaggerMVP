package com.ikechukwuakalu.daggermvp.users

import android.os.Bundle
import com.ikechukwuakalu.daggermvp.BaseApplication
import com.ikechukwuakalu.daggermvp.R
import com.ikechukwuakalu.daggermvp.base.BaseActivity
import dagger.Lazy
import javax.inject.Inject

class UsersActivity : BaseActivity() {

    @Inject
    lateinit var usersFragment : Lazy<UsersFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(R.id.mainContainer, usersFragment.get())
        (application as BaseApplication).refWatcher.watch(this)
    }

}
