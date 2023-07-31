package com.example.instantsystemtechnicaltest.di

import com.example.instantsystemtechnicaltest.data.repositories.NewsRepository
import com.example.instantsystemtechnicaltest.data.repositories.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NewsRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl) : NewsRepository
}