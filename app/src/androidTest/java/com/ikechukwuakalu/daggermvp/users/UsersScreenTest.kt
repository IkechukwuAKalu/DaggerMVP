package com.ikechukwuakalu.daggermvp.users

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import com.ikechukwuakalu.daggermvp.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UsersScreenTest {

    val userLogin = "IkechukwuAKalu"

    @Rule
    @JvmField
    val activityRule : ActivityTestRule<UsersActivity> = ActivityTestRule<UsersActivity>(UsersActivity::class.java)

    /**
     * For handling RecyclerView items
     */
    private fun withItemText(login: String) : Matcher<View> = object: TypeSafeMatcher<View>() {

        override fun describeTo(description: Description?) {
            description?.appendText("is a descendant of recycler view with login: $login")
        }

        override fun matchesSafely(item: View?): Boolean = allOf(
                isDescendantOfA(isAssignableFrom(RecyclerView::class.java)),
                withText(login)
        ).matches(item)
    }

    @Before
    fun setUp() {
        IdlingRegistry.getInstance()
                .register(activityRule.activity.getIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance()
                .unregister(activityRule.activity.getIdlingResource())
    }

    /**
     * ToDo("Implement Mock data injection for consistent testing results")
     */
    @Test
    fun clickOnUser_OpenUserDetails() {
        onView(withItemText(userLogin)).perform(click())
        onView(withId(R.id.openBrowserButton)).check(matches(isDisplayed()))
    }

    @Test
    fun clickOverflowMenu_CheckChangeCityBoxShown() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.change_city)).perform(click())
        onView(withHint("city")).check(matches(isDisplayed()))
    }
}