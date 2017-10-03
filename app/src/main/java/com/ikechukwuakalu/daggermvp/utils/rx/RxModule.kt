package com.ikechukwuakalu.daggermvp.utils.rx

import com.ikechukwuakalu.daggermvp.utils.rx.schedulers.RxScheduler
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * This class provides objects for use with RxJava2
 */
@Module
abstract class RxModule {

    @Module
    companion object {
        /**
         * This should not be static {@Singleton} else, returning to an activity will not
         * display any data
         */
        @Provides
        @JvmStatic
        fun provideCompositeDisposable() : CompositeDisposable = CompositeDisposable()

        @Singleton
        @Provides
        @JvmStatic
        fun provideScheduler() : RxScheduler = RxScheduler()
    }
}