package com.paul.nytimesmostpopular.presentation.ui.articleslist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paul.nytimesmostpopular.R
import com.paul.nytimesmostpopular.domain.data.entities.Article
import com.paul.nytimesmostpopular.presentation.adapters.ArticlePagedListAdapter
import com.paul.nytimesmostpopular.presentation.adapters.ArticleViewOnClickListener
import com.paul.nytimesmostpopular.presentation.ui.viewmodel.ArticlesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PopularArticlesListFragment : Fragment(), ArticleViewOnClickListener {

    lateinit var articles: ArrayList<Article>
    private val articlesViewModel: ArticlesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        setHasOptionsMenu(true)

        val v = inflater.inflate(R.layout.fragment_news_list, container, false)
        val rvArticles = v.findViewById<RecyclerView>(R.id.rv_articles)
        val progressBar = v.findViewById<ProgressBar>(R.id.progress_bar)
        val layoutError = v.findViewById<LinearLayout>(R.id.layout_popup_error)
        val tvError = v.findViewById<TextView>(R.id.tv_error_message)
        val btnRetry = v.findViewById<LinearLayout>(R.id.btn_retry)




        btnRetry.setOnClickListener {
            articlesViewModel.getAllPosts()
        }



        articlesViewModel.articles.observe(viewLifecycleOwner) {

            setUpRecyclerView(it, rvArticles)
        }

        articlesViewModel.loadingState.observe(viewLifecycleOwner) {
            if (it) {
                progressBar.visibility = View.VISIBLE
                layoutError.visibility = View.GONE
                rvArticles.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                layoutError.visibility = View.GONE

            }
        }

        articlesViewModel.errorMessage.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                layoutError.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                tvError.text = it
            } else {
                layoutError.visibility = View.GONE
                progressBar.visibility = View.GONE
            }
        }

        return v
    }

    private fun setUpRecyclerView(
        it: ArrayList<Article>,
        rvArticles: RecyclerView
    ) {
        articles = ArrayList()
        articles.addAll(it)
        val adapter = ArticlePagedListAdapter(it, this@PopularArticlesListFragment)
        rvArticles.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )

        rvArticles.adapter = adapter

        rvArticles.visibility = View.VISIBLE
    }

    override fun onItemClick(position: Int) {
        val article = articles[position]
        var bundle: Bundle? = null
        bundle = Bundle()
        bundle.putParcelable("article", article)

        val action =
            PopularArticlesListFragmentDirections.actionNewsListFragmentToArticleDetailsFragment(
                article
            )

        findNavController().navigate(action)


    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
//        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }


}