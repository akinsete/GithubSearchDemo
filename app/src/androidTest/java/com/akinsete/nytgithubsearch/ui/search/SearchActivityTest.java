package com.akinsete.nytgithubsearch.ui.search;


import com.akinsete.nytgithubsearch.R;
import com.akinsete.nytgithubsearch.TestComponentRule;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.akinsete.nytgithubsearch.customassertions.AssertionUtils.clickChildViewWithId;
import static com.akinsete.nytgithubsearch.customassertions.AssertionUtils.waitFor;
import static com.akinsete.nytgithubsearch.customassertions.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.lessThanOrEqualTo;


/**
 * Created by sundayakinsete on 2019-04-12.
 */

@RunWith(AndroidJUnit4.class)
public class SearchActivityTest {

    private final TestComponentRule mComponent =
            new TestComponentRule(InstrumentationRegistry.getTargetContext());

    private final IntentsTestRule<SearchActivity> mMain =
            new IntentsTestRule<>(SearchActivity.class, false, true);

    @Rule
    public TestRule chain = RuleChain.outerRule(mComponent).around(mMain);

    @Test
    public void checkIfRequiredViewsDisplayedAndEnabled() {
        //mMain.launchActivity(SearchActivity.getSearchActivityIntent(mComponent.getContext()));
        onView(withId(R.id.et_search)).check(matches(isDisplayed()));
        onView(withId(R.id.et_search)).check(matches(isEnabled()));
        onView(withId(R.id.btn_search)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_search)).check(matches(isEnabled()));
    }

    @Test
    public void ensureButtonCompletelyDisplayedWithCorrectText(){
        onView(withId(R.id.btn_search)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.btn_search)).check(matches(isClickable()));
        onView(withId(R.id.btn_search)).check(matches(withText(R.string.search)));
    }

    @Test
    public void ensureEditTextHasCorrectHint() {
        onView(withId(R.id.et_search)).check(matches(withHint(R.string.enter_organisation_name)));
    }


    @Test
    public void ensureProgressBarWasDisplayedAfterSearchClicked() {
        startSearchingByApple("apple");
        onView(withId(R.id.pb_loading)).check(matches(isDisplayed()));
    }

    @Test
    public void displaySnackBarErrorOnEmptySearch() {
        onView(withId(R.id.btn_search)).perform(click());
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.empty_org_error)));
    }

    @Test
    public void ensureRecyclerViewIsPopulatedWithResults() {
        startSearchingByApple("github");
        onView(withId(R.id.search_recycler_view)).check(withItemCount(lessThanOrEqualTo(3)));
    }


    @Test
    public void ensureRecyclerViewItemCanBeClicked(){
        startSearchingByApple("nytimes");
        onView(withId(R.id.search_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.recycler_parent_layout)));
    }


    // Start searching with the giving organisation name
    private void startSearchingByApple(String orgName) {
        onView(withId(R.id.et_search)).perform(ViewActions.clearText())
                .perform(ViewActions.typeText(orgName),closeSoftKeyboard());
        onView(withId(R.id.btn_search)).perform(click());
        onView(isRoot()).perform(waitFor(5000));
    }



    @After
    public void tearDown() {
        mMain.getActivity().finishAndRemoveTask();
    }





}