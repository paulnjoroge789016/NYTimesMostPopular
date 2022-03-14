package com.paul.nytimesmostpopular.presentation.ui

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.paul.nytimesmostpopular.R
import com.paul.nytimesmostpopular.databinding.FragmentArticleDetailsBinding
import com.paul.nytimesmostpopular.presentation.MainActivity
import com.squareup.picasso.Picasso


@Suppress("DEPRECATION")
class ArticleDetailsFragment : Fragment() {

    private val args: ArticleDetailsFragmentArgs by navArgs<ArticleDetailsFragmentArgs>()
    private lateinit var fragmentArticleDetailsBinding: FragmentArticleDetailsBinding
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val article = args.article
        fragmentArticleDetailsBinding =
            FragmentArticleDetailsBinding.inflate(inflater, container, false)

        val title = fragmentArticleDetailsBinding.articleDetailsContent.titleDes
        val description = fragmentArticleDetailsBinding.articleDetailsContent.descriptionDes
        val createdByTitleDes = fragmentArticleDetailsBinding.articleDetailsContent.createdByDes
        val sourceDes = fragmentArticleDetailsBinding.articleDetailsContent.sourceDes
        val headerImage = fragmentArticleDetailsBinding.collapsingToolbar.image
        val btnReadMore = fragmentArticleDetailsBinding.articleDetailsContent.btnReadMore

        title.text = article.title
        description.text = article.abstract
        createdByTitleDes.text = article.createdBy
        sourceDes.text = article.publishedDate


        btnReadMore.setOnClickListener {

            val readMoreUri = Uri.parse(article.url)
            openCustomTabForReadMore(readMoreUri)
        }

        Picasso.get().load(article.mediumThumbnail).into(headerImage)

        return fragmentArticleDetailsBinding.root
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun openCustomTabForReadMore(uri: Uri) {
        val customTabIntent: CustomTabsIntent = CustomTabsIntent.Builder()
            .setToolbarColor(requireActivity().getColor(R.color.teal_700))
            .build()

        val packageName = "com.android.chrome"

        customTabIntent.intent.setPackage(packageName)
        customTabIntent.launchUrl(requireActivity(), uri)


    }


}

