package com.paul.nytimesmostpopular.presentation.di

import com.paul.nytimesmostpopular.data.network.repository.ArticlesRepository
import com.paul.nytimesmostpopular.domain.usecases.GetAllArticlesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetAllArticlesUseCase(articlesRepository: ArticlesRepository): GetAllArticlesUseCase{
        return GetAllArticlesUseCase(articlesRepository)
    }
}