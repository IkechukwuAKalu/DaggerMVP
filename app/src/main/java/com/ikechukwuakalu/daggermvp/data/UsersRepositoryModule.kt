package com.ikechukwuakalu.daggermvp.data

import com.ikechukwuakalu.daggermvp.data.remote.RemoteUsersDataSource
import com.ikechukwuakalu.daggermvp.data.qualifiers.Remote
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class UsersRepositoryModule {

    // Dagger requires the method to be static
    @Module
    companion object {
        @Singleton
        @Provides
        @Remote
        @JvmStatic
        fun provideRemoteData() : UsersDataSource = RemoteUsersDataSource()
    }
}