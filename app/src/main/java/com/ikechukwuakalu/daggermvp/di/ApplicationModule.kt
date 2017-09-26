package com.ikechukwuakalu.daggermvp.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * Expose the application as a context
 */

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun context(application: Application) : Context
}