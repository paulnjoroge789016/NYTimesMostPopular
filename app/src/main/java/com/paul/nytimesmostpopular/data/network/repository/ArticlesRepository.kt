package com.paul.nytimesmostpopular.data.network.repository

import com.paul.nytimesmostpopular.data.network.api.NYTimesApi
import com.paul.nytimesmostpopular.data.network.mappers.toDomainArticle
import com.paul.nytimesmostpopular.domain.data.entities.Article
import com.paul.nytimesmostpopular.domain.data.entities.NetworkBoundResource
import com.paul.nytimesmostpopular.domain.repository.IArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ArticlesRepository @Inject constructor(
    private val nyTimesApi: NYTimesApi
) : IArticleRepository {
    override suspend fun getAll(): Flow<NetworkBoundResource<ArrayList<Article>>> = flow {

        emit(NetworkBoundResource.Loading)
        try {
            val articles = ArrayList<Article>()

            val articlesResponse = nyTimesApi.getPopularArticles()
            articlesResponse.articles.forEach { article ->
                articles.add(article.toDomainArticle())
            }
            emit(NetworkBoundResource.Success(articles))
        } catch (exception: IOException) {
            emit(NetworkBoundResource.Failed(exception.message))
        } catch (exception: HttpException) {
            val localizedMessage = exception.localizedMessage
            exception.printStackTrace()
            emit(NetworkBoundResource.Failed(localizedMessage))

        }
    }
}