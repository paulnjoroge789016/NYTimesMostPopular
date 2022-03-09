package com.paul.nytimesmostpopular.data.network.api

import com.paul.nytimesmostpopular.BuildConfig
import com.paul.nytimesmostpopular.data.network.models.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTimesApi {

    @GET("mostpopular/v2/viewed/7.json")
    suspend fun getPopularArticles(@Query("api-key") apiKey : String = BuildConfig.API_KEY): ArticlesResponse

}