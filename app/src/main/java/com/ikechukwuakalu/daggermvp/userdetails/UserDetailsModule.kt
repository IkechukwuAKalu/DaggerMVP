package com.ikechukwuakalu.daggermvp.userdetails

import com.ikechukwuakalu.daggermvp.di.scopes.ActivityScoped
import com.ikechukwuakalu.daggermvp.di.scopes.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserDetailsModule {

    @Module
    companion object {
        @Provides
        @ActivityScoped
        @UserLogin
        @JvmStatic
        fun provideLogin(activity: UsersDetailsActivity) : String =
                activity.intent.getStringExtra(UsersDetailsActivity.LOGIN_KEY)
    }

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun detailsFragment(): UserDetailsFragment

    @ActivityScoped
    @Binds
    abstract fun presenter(presenter: UserDetailsPresenter): UserDetailsContract.Presenter
}