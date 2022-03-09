package com.paul.nytimesmostpopular.domain.usecases

import com.paul.nytimesmostpopular.domain.repository.IArticleRepository
import javax.inject.Inject

class GetAllArticlesUseCase @Inject constructor(
    private val articlesRepository: IArticleRepository
) {
    suspend fun getAllArticles() = articlesRepository.getAll()
}