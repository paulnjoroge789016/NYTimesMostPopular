package com.paul.nytimesmostpopular.presentation.adapters

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.paul.nytimesmostpopular.databinding.FragmentNewsListBinding
import com.paul.nytimesmostpopular.databinding.ListItemBinding

class ArticleViewHolder (binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val binding = DataBindingUtil.bind<ListItemBinding>(binding.root)
}