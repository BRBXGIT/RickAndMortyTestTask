package com.example.rickandmortytesttask.di

import com.example.rickandmortytesttask.data.network.details_screen.api.CharacterDetailsScreenApiInstance
import com.example.rickandmortytesttask.domain.character_details_screen.CharacterDetailsScreenRepo
import com.example.rickandmortytesttask.domain.character_details_screen.CharacterDetailsScreenRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterDetailsScreenModule {

    @Provides
    @Singleton
    fun provideCharacterDetailsScreenApiInstance(retrofit: Retrofit): CharacterDetailsScreenApiInstance {
        return retrofit.create(CharacterDetailsScreenApiInstance::class.java)
    }

    @Provides
    @Singleton
    fun provideCharacterDetailsScreenRepo(apiInstance: CharacterDetailsScreenApiInstance): CharacterDetailsScreenRepo {
        return CharacterDetailsScreenRepoImpl(apiInstance)
    }
}