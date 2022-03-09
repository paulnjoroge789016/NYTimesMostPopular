package com.paul.nytimesmostpopular.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paul.nytimesmostpopular.R
import com.paul.nytimesmostpopular.databinding.ListItemBinding
import com.paul.nytimesmostpopular.domain.data.entities.Article
import com.paul.nytimesmostpopular.presentation.viewmodel.ArticlesViewModel
import com.paul.nytimesmostpopular.util.CircleTransform
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso


class ArticlePagedListAdapter  (private val articles: ArrayList<Article>,
                                private val articleViewOnClickListener : ArticleViewOnClickListener):
    RecyclerView.Adapter<ArticleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding =
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)

        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.binding!!.title.text = article.title
        holder.binding.createdBy.text = article.createdBy
        holder.binding.dateRow.date.text = article.publishedDate

        holder.binding.iconRight.setOnClickListener {
            articleViewOnClickListener.onItemClick(position)
        }
        Picasso.get()
            .load(article.smallThumbnail)
            .transform(CircleTransform())
            .placeholder(R.drawable.ic_launcher_foreground)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.binding.image, object : Callback {
                override fun onSuccess() {
                    Log.d("Picasso", "onSuccess: success ")
                }

                override fun onError(e: Exception?) {
                    Log.d("Picasso", "onError: ${e?.message}")
                    Log.d("Picasso", "onError: ${e?.printStackTrace()}")
                }

            })



    }

    override fun getItemCount(): Int {
        return articles.size
    }

}