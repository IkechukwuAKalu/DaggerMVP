package com.ikechukwuakalu.daggermvp.base

import android.support.annotation.VisibleForTesting
import android.support.test.espresso.IdlingResource
import android.support.v4.app.Fragment
import com.ikechukwuakalu.daggermvp.utils.espresso.EspressoIdlingResource
import dagger.android.support.DaggerAppCompatActivity

open class BaseActivity : DaggerAppCompatActivity() {

    fun BaseActivity.addFragment(container: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .add(container, fragment)
                .commit()
    }

    @VisibleForTesting
    fun getIdlingResource() : IdlingResource = EspressoIdlingResource.getIdlingResource()
}