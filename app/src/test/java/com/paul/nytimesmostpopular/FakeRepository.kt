package com.paul.nytimesmostpopular

import com.paul.nytimesmostpopular.domain.data.entities.Article
import com.paul.nytimesmostpopular.domain.data.entities.NetworkBoundResource
import com.paul.nytimesmostpopular.domain.repository.IArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository : IArticleRepository {
    override suspend fun getAll(): Flow<NetworkBoundResource<ArrayList<Article>>>  = flow {

        val article = Article(
            id = 1,
            title = "TestTitle1",
            abstract = "TestAbstract",
            url = "https://www.nytimes.com/2022/03/09/world/europe/ukraine-family-perebyinis-kyiv.html",
            smallThumbnail = "https://static01.nyt.com/images/2022/03/09/multimedia/09ukraine-family-topart/09ukraine-family-topart-thumbStandard.jpg",
            mediumThumbnail = "https://static01.nyt.com/images/2022/03/09/multimedia/09ukraine-family-topart/09ukraine-family-topart-thumbStandard.jpg",
            largeThumbnail = "https://static01.nyt.com/images/2022/03/09/multimedia/09ukraine-family-topart/09ukraine-family-topart-thumbStandard.jpg",
            publishedDate = "2022-03-09",
            createdBy = "By Andrew E. Kramer"
        )

        val article2 = Article(
            id = 2,
            title = "TestTitle2",
            abstract = "TestAbstract2",
            url = "https://www.nytimes.com/2022/03/09/world/europe/ukraine-family-perebyinis-kyiv.html",
            smallThumbnail = "https://static01.nyt.com/images/2022/03/09/multimedia/09ukraine-family-topart/09ukraine-family-topart-thumbStandard.jpg",
            mediumThumbnail = "https://static01.nyt.com/images/2022/03/09/multimedia/09ukraine-family-topart/09ukraine-family-topart-thumbStandard.jpg",
            largeThumbnail = "https://static01.nyt.com/images/2022/03/09/multimedia/09ukraine-family-topart/09ukraine-family-topart-thumbStandard.jpg",
            publishedDate = "2022-03-09",
            createdBy = "By Andrew E. Kramer"
        )


        val articles = arrayListOf<Article>(article, article2)
        emit(NetworkBoundResource.Success(articles))


    }
}