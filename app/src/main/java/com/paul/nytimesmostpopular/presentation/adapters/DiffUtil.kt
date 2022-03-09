package com.paul.nytimesmostpopular.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.paul.nytimesmostpopular.domain.data.entities.Article

object PostDIffUtil: DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return (oldItem == newItem)
    }

}