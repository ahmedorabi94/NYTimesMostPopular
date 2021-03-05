package com.example.nytimes.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.nytimes.R
import com.example.nytimes.ui.adapter.ArticleAdapter
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class HomeFragmentTest {

    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @get: Rule
    val espressoIdlingResoureRule = EspressoIdlingResourceRule()


    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {


        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }


    @Test
    fun isHomeFragmentDis() {
        onView(withId(R.id.homeFragmentId)).check(matches(isDisplayed()))

    }

    @Test
    fun test_RecyclerViewClick() {


        onView(withId(R.id.homeFragmentId)).check(matches(isDisplayed()))

        onView(withId(R.id.recycler_view_main)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ArticleAdapter.MyViewHolder>(
                1,
                click()
            )
        )


       onView(withId(R.id.detailsFragmentView)).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.homeFragmentId)).check(matches(isDisplayed()))


    }


}