package com.paul.nytimesmostpopular.presentation.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.paul.nytimesmostpopular.domain.data.entities.Article
import com.paul.nytimesmostpopular.domain.data.entities.NetworkBoundResource
import com.paul.nytimesmostpopular.domain.usecases.GetAllArticlesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ArticlesViewModel @ViewModelInject constructor(
    private val articlesUseCase: GetAllArticlesUseCase,
) : ViewModel() {

    private val _articles: MutableLiveData<ArrayList<Article>> = MutableLiveData()
    val articles: LiveData<ArrayList<Article>> get() = _articles

    private val _loadingState: MutableLiveData<Boolean> = MutableLiveData()
    val loadingState: LiveData<Boolean> get() = _loadingState

    private val _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> get() = _errorMessage

    val rawResults: MutableLiveData<NetworkBoundResource<ArrayList<Article>>> = MutableLiveData()

    init {
        _loadingState.value = true
        getAllPosts()
    }

    fun getAllPosts() = viewModelScope.launch {
        articlesUseCase.getAllArticles().collect { results ->

            when (results) {

                is NetworkBoundResource.Success -> {

                    _articles.postValue(results.data)
                    _loadingState.postValue(false)
                    _errorMessage.postValue("")
                }

                is NetworkBoundResource.Failed -> {

                    _loadingState.postValue(false)
                    _errorMessage.postValue(results.message)
                }

                is NetworkBoundResource.Loading -> {

                    _loadingState.postValue(true)
                    _errorMessage.postValue("")
                }
            }
        }

    }


    fun dummyTest() = viewModelScope.launch {
        val data = returnError().asLiveData()


        val value = data.value as NetworkBoundResource.Success

        _errorMessage.postValue(value.data)


    }


    private suspend fun returnError(): Flow<NetworkBoundResource<String>> = flow<NetworkBoundResource<String>> {
        emit(NetworkBoundResource.Success("Error"))
    }


}