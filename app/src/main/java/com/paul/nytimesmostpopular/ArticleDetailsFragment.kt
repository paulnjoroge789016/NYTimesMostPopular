package com.paul.nytimesmostpopular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.paul.nytimesmostpopular.domain.data.entities.Article

class ArticleDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val article = savedInstanceState?.get("article") as Article

        Toast.makeText(requireContext(), "$article", Toast.LENGTH_LONG).show()
        return inflater.inflate(R.layout.fragment_article_details, container, false)
    }

}