package com.example.rickandmortytesttask.di

import android.content.Context
import androidx.room.Room
import com.example.rickandmortytesttask.data.local.CharacterDb
import com.example.rickandmortytesttask.data.network.home_screen.api.HomeScreenApiInstance
import com.example.rickandmortytesttask.domain.home_screen.HomeScreenRepo
import com.example.rickandmortytesttask.domain.home_screen.HomeScreenRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideCharacterDb(@ApplicationContext context: Context): CharacterDb {
        return Room.databaseBuilder(
            context = context,
            klass = CharacterDb::class.java,
            name = "CharacterDb"
        ).build()
    }

    @Provides
    @Singleton
    fun provideHomeScreenRepo(apiInstance: HomeScreenApiInstance, characterDb: CharacterDb):
            HomeScreenRepo = HomeScreenRepoImpl(apiInstance, characterDb)
}