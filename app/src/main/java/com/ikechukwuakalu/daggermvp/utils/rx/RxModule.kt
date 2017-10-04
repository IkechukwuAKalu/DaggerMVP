package com.ikechukwuakalu.daggermvp.utils.rx

import com.ikechukwuakalu.daggermvp.utils.rx.schedulers.BaseScheduler
import com.ikechukwuakalu.daggermvp.utils.rx.schedulers.RxScheduler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * This class provides objects for use with RxJava2
 */
@Module
abstract class RxModule {

    @Module
    companion object {
        @Singleton
        @Provides
        @JvmStatic
        fun provideScheduler() : BaseScheduler = RxScheduler()
    }
}