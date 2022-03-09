package com.paul.nytimesmostpopular.data.network.models


import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("num_results")
    val numResults: Int,
    @SerializedName("results")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String
)