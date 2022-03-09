package com.paul.nytimesmostpopular.presentation.di

import com.paul.nytimesmostpopular.data.network.api.NYTimesApi
import com.paul.nytimesmostpopular.data.network.repository.ArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun providePostRepository(nyTimesApi: NYTimesApi): ArticlesRepository {
        return ArticlesRepository(nyTimesApi)
    }
}