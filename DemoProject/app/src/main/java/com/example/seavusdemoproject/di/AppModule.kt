package com.example.seavusdemoproject.di

import android.content.Context
import com.example.seavusdemoproject.data.remote.api.ApiService
import com.example.seavusdemoproject.data.remote.repository.PostRepositoryImpl
import com.example.seavusdemoproject.data.remote.repository.TodoRepositoryImpl
import com.example.seavusdemoproject.data.remote.repository.UserRepositoryImpl
import com.example.seavusdemoproject.domain.repository.PostRepository
import com.example.seavusdemoproject.domain.repository.TodoRepository
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
    fun provideUserRepository(api: ApiService): UserRepository {
        return UserRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideTodoRepository(api: ApiService): TodoRepository {
        return TodoRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providePostRepository(api: ApiService): PostRepository {
        return PostRepositoryImpl(api)
    }

}