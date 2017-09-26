package com.ikechukwuakalu.daggermvp.di

import com.ikechukwuakalu.daggermvp.di.scopes.ActivityScoped
import com.ikechukwuakalu.daggermvp.userdetails.UserDetailsModule
import com.ikechukwuakalu.daggermvp.userdetails.UsersDetailsActivity
import com.ikechukwuakalu.daggermvp.users.UsersActivity
import com.ikechukwuakalu.daggermvp.users.UsersModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(UsersModule::class))
    abstract fun usersActivity() : UsersActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(UserDetailsModule::class))
    abstract fun detailsActivity() : UsersDetailsActivity
}