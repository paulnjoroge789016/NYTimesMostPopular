package com.paul.nytimesmostpopular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paul.nytimesmostpopular.presentation.adapters.ArticlePagedListAdapter
import com.paul.nytimesmostpopular.presentation.viewmodel.ArticlesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PopularArticlesListFragment : Fragment() {

    private val articlesViewModel: ArticlesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val v  =  inflater.inflate(R.layout.fragment_news_list, container, false)
        val rvArticles = v.findViewById<RecyclerView>(R.id.rv_articles)




        articlesViewModel.articles.observe(viewLifecycleOwner) {

            val adapter = ArticlePagedListAdapter(articlesViewModel, it)
            rvArticles.layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL, false)

            rvArticles.adapter = adapter
        }
        return v
    }


}