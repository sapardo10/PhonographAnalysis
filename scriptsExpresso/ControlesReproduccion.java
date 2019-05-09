package com.kabouzeid.gramophone.ui.activities;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import com.kabouzeid.gramophone.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ControlesReproduccion {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.READ_EXTERNAL_STORAGE",
                    "android.permission.WRITE_EXTERNAL_STORAGE");

    @Test
    public void controlesReproduccion() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton = onView(
                allOf(withText("Get started"),
                        childAtPosition(
                                allOf(withId(R.id.mi_button_cta),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction frameLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.recycler_view),
                                childAtPosition(
                                        withId(R.id.container),
                                        0)),
                        10),
                        isDisplayed()));
        frameLayout.perform(click());

        ViewInteraction frameLayout2 = onView(
                allOf(withId(R.id.mini_player_fragment),
                        childAtPosition(
                                allOf(withId(R.id.sliding_panel),
                                        childAtPosition(
                                                withId(R.id.sliding_layout),
                                                1)),
                                1),
                        isDisplayed()));
        frameLayout2.perform(click());

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.player_play_pause_fab),
                        childAtPosition(
                                allOf(withId(R.id.player_media_controller_container),
                                        childAtPosition(
                                                withId(R.id.playback_controls_fragment),
                                                1)),
                                5),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.player_play_pause_fab),
                        childAtPosition(
                                allOf(withId(R.id.player_media_controller_container),
                                        childAtPosition(
                                                withId(R.id.playback_controls_fragment),
                                                1)),
                                5),
                        isDisplayed()));
        floatingActionButton2.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.player_next_button),
                        childAtPosition(
                                allOf(withId(R.id.player_media_controller_container),
                                        childAtPosition(
                                                withId(R.id.playback_controls_fragment),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.player_prev_button),
                        childAtPosition(
                                allOf(withId(R.id.player_media_controller_container),
                                        childAtPosition(
                                                withId(R.id.playback_controls_fragment),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.player_prev_button),
                        childAtPosition(
                                allOf(withId(R.id.player_media_controller_container),
                                        childAtPosition(
                                                withId(R.id.playback_controls_fragment),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withId(R.id.player_prev_button),
                        childAtPosition(
                                allOf(withId(R.id.player_media_controller_container),
                                        childAtPosition(
                                                withId(R.id.playback_controls_fragment),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatImageButton4.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
