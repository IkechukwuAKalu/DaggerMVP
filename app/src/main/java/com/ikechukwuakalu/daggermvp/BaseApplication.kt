package com.ikechukwuakalu.daggermvp

import com.ikechukwuakalu.daggermvp.data.UsersRepository
import com.ikechukwuakalu.daggermvp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class BaseApplication: DaggerApplication(){

    @Inject
    lateinit var usersRepository: UsersRepository

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder()
                .appBuilder(this)
                .build()
        appComponent.inject(this)
        return appComponent
    }

}