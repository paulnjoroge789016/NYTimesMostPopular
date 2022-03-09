package com.paul.nytimesmostpopular.domain.repository

import com.paul.nytimesmostpopular.domain.data.entities.Article
import com.paul.nytimesmostpopular.domain.data.entities.NetworkBoundResource
import kotlinx.coroutines.flow.Flow

interface IArticleRepository {

    suspend fun getAll(): Flow<NetworkBoundResource<ArrayList<Article>>>
}