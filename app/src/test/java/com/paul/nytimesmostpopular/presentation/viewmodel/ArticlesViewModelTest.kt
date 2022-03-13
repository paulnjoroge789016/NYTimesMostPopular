package com.paul.nytimesmostpopular.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.paul.nytimesmostpopular.ArticlesTestData
import com.paul.nytimesmostpopular.FakeArticlesRepository
import com.paul.nytimesmostpopular.TestCoroutineRule
import com.paul.nytimesmostpopular.data.network.repository.ArticlesRepository
import com.paul.nytimesmostpopular.domain.data.entities.NetworkBoundResource
import com.paul.nytimesmostpopular.domain.usecases.GetAllArticlesUseCase
import com.paul.nytimesmostpopular.getOrAwaitValueTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class ArticlesViewModelTest{


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @get:Rule
    var testCoroutineDispatcher = TestCoroutineRule()


    private lateinit var articlesViewModel: ArticlesViewModel
    private var getAllArticlesUseCase: GetAllArticlesUseCase = mock(GetAllArticlesUseCase::class.java)


    @Before
    fun setUp(){

    }



    @Test
    fun `get Articles should Return Error`()  = runBlockingTest{

        `when`(getAllArticlesUseCase.getAllArticles()).thenReturn(
            flow {
                emit(NetworkBoundResource.Failed("Error"))
            }
        )


        articlesViewModel = ArticlesViewModel(getAllArticlesUseCase)
        articlesViewModel.getAllPosts()

        val error = articlesViewModel.errorMessage.getOrAwaitValueTest()
        assertThat(error).isEqualTo("Error")


    }


    @Test
    fun `get articles should succeed after articles are returned`()  = runBlockingTest{


        `when`(getAllArticlesUseCase.getAllArticles()).thenReturn(
            flow {
                emit(NetworkBoundResource.Success(ArticlesTestData.articels))
            }
        )


        articlesViewModel = ArticlesViewModel(getAllArticlesUseCase)
        articlesViewModel.getAllPosts()

        val articles = articlesViewModel.articles.getOrAwaitValueTest()

        assertThat(articles).contains(ArticlesTestData.articels[0])


    }

    @Test
    fun `get articles should return loading`()  = runBlockingTest{

        `when`(getAllArticlesUseCase.getAllArticles()).thenReturn(
            flow {
                emit(NetworkBoundResource.Loading)
            }
        )


        articlesViewModel = ArticlesViewModel(getAllArticlesUseCase)
        articlesViewModel.getAllPosts()

        val isLoading = articlesViewModel.loadingState.getOrAwaitValueTest()

        assertThat(isLoading).isTrue()


    }



}