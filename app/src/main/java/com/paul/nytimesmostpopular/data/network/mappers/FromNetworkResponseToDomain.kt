package com.paul.nytimesmostpopular.data.network.mappers

import com.paul.nytimesmostpopular.data.network.models.Article

fun Article.toDomainArticle() = com.paul.nytimesmostpopular.domain.data.entities.Article(
    id = this.id,
    title = this.title,
    abstract = this.abstract,
    url = this.url,
    smallThumbnail = this.media[0].mediaMetadata[0].url,
    mediumThumbnail = this.media[0].mediaMetadata[1].url,
    largeThumbnail = this.media[0].mediaMetadata[2].url,
    publishedDate = this.publishedDate

)