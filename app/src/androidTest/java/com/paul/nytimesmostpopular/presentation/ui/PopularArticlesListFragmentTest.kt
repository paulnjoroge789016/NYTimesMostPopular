package com.paul.nytimesmostpopular.presentation.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.paul.nytimesmostpopular.R
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PopularArticlesListFragmentTest{


    @Test
    fun testNavigationToArticleDetailsFragment(){

        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )


        val popularArticlesListFragmentScenario = launchFragmentInContainer<PopularArticlesListFragment>()

        onView(withId(R.id.rv_articles)).check(matches(isDisplayed()))

//        popularArticlesListFragmentScenario.onFragment{
//            navController.setGraph(R.navigation.nav_graph)
//        }
    }

}