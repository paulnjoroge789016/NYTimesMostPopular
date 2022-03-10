package com.paul.nytimesmostpopular.presentation.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.paul.nytimesmostpopular.domain.data.entities.Article
import com.paul.nytimesmostpopular.domain.data.entities.NetworkBoundResource
import com.paul.nytimesmostpopular.domain.usecases.GetAllArticlesUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ArticlesViewModel @ViewModelInject constructor(
    private val articlesUseCase: GetAllArticlesUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
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

    fun getAllPosts() {

        viewModelScope.launch {
            val articles = ArrayList<Article>()
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
    }


}