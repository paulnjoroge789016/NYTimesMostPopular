package com.paul.nytimesmostpopular.data.network.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.paul.nytimesmostpopular.FakeRepository
import com.paul.nytimesmostpopular.domain.data.entities.Article
import com.paul.nytimesmostpopular.domain.data.entities.NetworkBoundResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArticlesRepositoryTest{
    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var fakeRepository: FakeRepository


    @Before
    fun setUp(){
        fakeRepository = FakeRepository()
    }

    @Test
    fun  `Request to get data from api succeeds`() =  runBlockingTest{

        fakeRepository.getAll().test {

            val articles = awaitItem()

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


            assertThat(articles.javaClass.toString()).isEqualTo("class com.paul.nytimesmostpopular.domain.data.entities.NetworkBoundResource\$Success")
            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun  `request to get data from api should return loading`() =  runBlockingTest{

        fakeRepository.setShouldReturnLoading(true)

        fakeRepository.getAll().test {

            val articles = awaitItem()


            assertThat(articles.javaClass.toString()).isEqualTo("class com.paul.nytimesmostpopular.domain.data.entities.NetworkBoundResource\$Loading")
            cancelAndIgnoreRemainingEvents()
        }

    }


    @Test
    fun  `Request to get data from api should fail`() =  runBlockingTest{

        fakeRepository.setShouldReturnError(true)
        fakeRepository.getAll().test {

            val articles = awaitItem()


            assertThat(articles.javaClass.toString()).isEqualTo("class com.paul.nytimesmostpopular.domain.data.entities.NetworkBoundResource\$Failed")
            cancelAndIgnoreRemainingEvents()
        }

    }




}