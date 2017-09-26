package com.ikechukwuakalu.daggermvp.users

import com.ikechukwuakalu.daggermvp.di.scopes.ActivityScoped
import com.ikechukwuakalu.daggermvp.di.scopes.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UsersModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun usersFragment () : UsersFragment

    @ActivityScoped
    @Binds
    abstract fun presenter(presenter: UsersPresenter) : UsersContract.Presenter
}