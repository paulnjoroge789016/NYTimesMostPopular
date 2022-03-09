package com.paul.nytimesmostpopular.domain.repository

import com.paul.nytimesmostpopular.domain.data.entities.Article
import kotlinx.coroutines.flow.Flow

interface IArticleRepository {

    suspend fun getAll(): Flow<Result<ArrayList<Article>>>
}