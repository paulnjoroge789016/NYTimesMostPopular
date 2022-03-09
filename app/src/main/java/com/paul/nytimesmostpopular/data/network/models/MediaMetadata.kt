package com.paul.nytimesmostpopular.data.network.models


import com.google.gson.annotations.SerializedName

data class MediaMetadata(
    @SerializedName("format")
    val format: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)