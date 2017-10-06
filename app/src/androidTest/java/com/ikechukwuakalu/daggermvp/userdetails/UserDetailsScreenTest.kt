package com.ikechukwuakalu.daggermvp.userdetails

import android.content.Intent
import android.net.Uri
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasAction
import android.support.test.espresso.intent.matcher.IntentMatchers.hasData
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import com.ikechukwuakalu.daggermvp.R
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDetailsScreenTest {

    val userLogin = "IkechukwuAKalu"

    @Rule
    @JvmField
    val intentRule : IntentsTestRule<UsersDetailsActivity> = IntentsTestRule<UsersDetailsActivity>(UsersDetailsActivity::class.java,
            true, false)

    @Before
    fun setUp() {
        val intent = Intent()
        intent.putExtra(UsersDetailsActivity.LOGIN_KEY, userLogin)
        intentRule.launchActivity(intent)

        IdlingRegistry.getInstance()
                .register(intentRule.activity.getIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance()
                .unregister(intentRule.activity.getIdlingResource())
    }

    @Test
    fun showUserDetails() {
        onView(withId(R.id.detailsImage)).check(matches(isDisplayed()))
        onView(withId(R.id.detailsName)).check(matches(isDisplayed()))
        onView(withId(R.id.detailsBio)).check(matches(isDisplayed()))
        onView(withId(R.id.detailsLocation)).check(matches(isDisplayed()))
        onView(withId(R.id.detailsPublicRepos)).check(matches(isDisplayed()))
    }

    @Test
    fun check_OpenBrowserButtonVisibleClickable() {
        onView(withId(R.id.openBrowserButton)).check(matches(isDisplayed()))
        onView(withId(R.id.openBrowserButton))
                .check(matches(isClickable()))
                .perform(click())
        intended(allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData(Uri.parse("https://github.com/$userLogin"))
        ))
    }

}
