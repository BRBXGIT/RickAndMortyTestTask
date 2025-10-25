package com.example.rickandmortytesttask.di

import com.example.rickandmortytesttask.data.network.home_screen.api.HomeScreenApiInstance
import com.example.rickandmortytesttask.domain.HomeScreenRepo
import com.example.rickandmortytesttask.domain.HomeScreenRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeScreenModule {

    @Provides
    @Singleton
    fun provideHomeScreenApiInstance(retrofit: Retrofit): HomeScreenApiInstance {
        return retrofit.create(HomeScreenApiInstance::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeScreenRepo(apiInstance: HomeScreenApiInstance): HomeScreenRepo = HomeScreenRepoImpl(apiInstance)
}