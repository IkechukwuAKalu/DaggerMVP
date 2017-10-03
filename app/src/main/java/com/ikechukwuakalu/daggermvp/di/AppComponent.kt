package com.ikechukwuakalu.daggermvp.di

import android.app.Application
import com.ikechukwuakalu.daggermvp.BaseApplication
import com.ikechukwuakalu.daggermvp.data.UsersRepository
import com.ikechukwuakalu.daggermvp.data.UsersRepositoryModule
import com.ikechukwuakalu.daggermvp.utils.rx.RxModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(UsersRepositoryModule::class, RxModule::class, ApplicationModule::class,
        ActivityBindingModule::class, AndroidSupportInjectionModule::class))

interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(baseApplication: BaseApplication)

    fun getUsersRepository() : UsersRepository

    override fun inject(instance: DaggerApplication?)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun appBuilder(application: Application) : AppComponent.Builder

        fun build() : AppComponent
    }
}