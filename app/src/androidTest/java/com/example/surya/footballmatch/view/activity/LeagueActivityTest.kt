package com.example.surya.footballmatch.view.activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.widget.AutoCompleteTextView
import com.example.surya.footballmatch.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LeagueActivityTest {

    @Rule
    @JvmField
    var rule = ActivityTestRule(LeagueActivity::class.java)

    @Test
    fun testSearch(){
        Thread.sleep(2000)
        onView(withId(R.id.recycleLeague)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.recycleLeague)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        Thread.sleep(5000)
        onView(withId(R.id.searchView)).perform(click())
        onView(isAssignableFrom(AutoCompleteTextView::class.java))
            .perform(typeText("Arsenal"))
            .perform(pressImeActionButton())
            .perform(pressBack())

        Thread.sleep(6000)

    }


}