package com.example.seavusdemoproject.di

import android.content.Context
import com.example.seavusdemoproject.data.remote.api.ApiService
import com.example.seavusdemoproject.data.remote.repository.UsersRepositoryImpl
import com.example.seavusdemoproject.domain.repository.UserRepository
import com.example.seavusdemoproject.presentation.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Provides
    @Singleton
    fun provideUsersRepository(api: ApiService): UserRepository {
        return UsersRepositoryImpl(api)
    }

}