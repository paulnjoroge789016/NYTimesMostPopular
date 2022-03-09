package com.paul.nytimesmostpopular.domain.data.entities

sealed class NetworkBoundResource <out T>{
    data class Success<out T>(val data: T): NetworkBoundResource<T>()
    data class Failed(val message: String?): NetworkBoundResource<Nothing>()
    object Loading: NetworkBoundResource<Nothing>()
}