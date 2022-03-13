package com.paul.nytimesmostpopular.presentation.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.paul.nytimesmostpopular.BuildConfig
import com.paul.nytimesmostpopular.data.network.api.NYTimesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton




@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }


    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, httpClient: OkHttpClient): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl("http://api.nytimes.com/svc/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))

    }

    @Provides
    @Singleton
    fun provideNYTimesApiService(retrofit: Retrofit.Builder): NYTimesApi {
        return retrofit
            .build()
            .create(NYTimesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return if(BuildConfig.DEBUG){
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        } else {
            OkHttpClient.Builder()
                .build()
        }
    }
}