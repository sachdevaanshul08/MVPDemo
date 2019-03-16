package com.nytimes;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.nytimes.util.EspressoTestingIdlingResource;
import com.nytimes.viewlayer.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.StringContains.containsString;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    /**
     * Register IdlingResource resource to tell Espresso when your app is in an
     * idle state. This helps Espresso to synchronize test actions.
     */

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoTestingIdlingResource.getIdlingResource());
    }

    /**
     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
     */
    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoTestingIdlingResource.getIdlingResource());
    }

    @Test
    public void checkRecyclerViewVisibility() throws InterruptedException {
        // verify recycler view is being displayed
        onView(withId(R.id.recycler_view_employee_list)).check(matches(isDisplayed()));

        // Get total item of myRecyclerView
        RecyclerView recyclerView = activityTestRule.getActivity().findViewById(R.id.recycler_view_employee_list);
        int itemCount = recyclerView.getAdapter().getItemCount();

        if (itemCount > 0) {

            onView(withId(R.id.recycler_view_employee_list)).perform(RecyclerViewActions.scrollToPosition(itemCount - 1));

            onView(withId(R.id.recycler_view_employee_list)).perform(RecyclerViewActions.scrollToPosition(0));


            onView(withId(R.id.recycler_view_employee_list))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

            // Verify the toast text content
            MainActivity activity = activityTestRule.getActivity();
            onView(withText(containsString("Title : "))).
                    inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                    check(matches(isDisplayed()));
        }
    }


}