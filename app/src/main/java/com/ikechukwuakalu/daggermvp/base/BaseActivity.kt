package com.ikechukwuakalu.daggermvp.base

import android.support.v4.app.Fragment
import dagger.android.support.DaggerAppCompatActivity

open class BaseActivity : DaggerAppCompatActivity() {

    fun BaseActivity.addFragment(container: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .add(container, fragment)
                .commit()
    }
}