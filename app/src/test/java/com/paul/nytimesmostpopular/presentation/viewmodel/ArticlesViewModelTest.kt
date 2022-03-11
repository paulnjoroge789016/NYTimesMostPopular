package com.paul.nytimesmostpopular.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.paul.nytimesmostpopular.FakeArticlesRepository
import com.paul.nytimesmostpopular.MainCoroutineRule
import com.paul.nytimesmostpopular.TestCoroutineRule
import com.paul.nytimesmostpopular.domain.usecases.GetAllArticlesUseCase
import com.paul.nytimesmostpopular.getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class ArticlesViewModelTest{


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @get:Rule
    var testCoroutineDispatcher = TestCoroutineRule()


    private lateinit var articlesViewModel: ArticlesViewModel
    lateinit var getAllArticlesUseCase: GetAllArticlesUseCase
    lateinit var fakeRepository: FakeArticlesRepository
    @Before
    fun setUp(){

        fakeRepository =  FakeArticlesRepository()
        getAllArticlesUseCase = GetAllArticlesUseCase(fakeRepository)
        articlesViewModel = ArticlesViewModel(getAllArticlesUseCase)
    }



    @Test
    fun getArticlesReturnsError() =   testCoroutineDispatcher.runBlockingTest{

        fakeRepository.setShouldReturnError(true)
        articlesViewModel.getAllPosts()

        val errorMessage = articlesViewModel.errorMessage.getOrAwaitValueTest()

        assertThat(errorMessage).matches("Error")
    }


    @Test
    fun `dummy test in viewmodel`() = testCoroutineDispatcher.runBlockingTest{

        articlesViewModel.dummyTest()
        val error  =  articlesViewModel.errorMessage.getOrAwaitValueTest()

        assertThat(error).isEqualTo("Error")
    }
}