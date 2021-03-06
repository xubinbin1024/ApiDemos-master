/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.apis.app;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.apis.R;

/**
 * This demonstrates implementing common navigation flows with the action bar.
 * It hows how to use "Up" button in Action Bar, new Document is created in a
 * separate activity, so you have to use "recent" to switch to it, and then
 * the "up" button works as "up", otherwise it works as "Back". Uses the
 * attribute android:taskAffinity=":bar_navigation" to associate the activities.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
@SuppressLint("SetTextI18n")
public class ActionBarNavigation extends Activity {
    /**
     * Called when the activity is starting. First we call through to our super's implementation
     * of onCreate. Then we get a reference to this activity's ActionBar and set the display option
     * DISPLAY_HOME_AS_UP. We set the content view to our layout file R.layout.action_bar_navigation,
     * locate the TextView text (R.id.launchedfrom) for our message, and based on whether the
     * category Intent.CATEGORY_SAMPLE_CODE exists in the intent that launched our activity we
     * either set the text of "text" to "This was launched from ApiDemos" if it does or "This
     * was created from up navigation" if it does not.
     *
     * @param savedInstanceState always null since onSaveInstanceState is not overridden.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Turn on the up affordance.
        final ActionBar bar = getActionBar();
        //noinspection ConstantConditions
        bar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP, ActionBar.DISPLAY_HOME_AS_UP);

        setContentView(R.layout.action_bar_navigation);
        TextView text = (TextView)findViewById(R.id.launchedfrom);
        if (getIntent().hasCategory(Intent.CATEGORY_SAMPLE_CODE)) {
            text.setText("This was launched from ApiDemos");
        } else {
            text.setText("This was created from up navigation");
        }
    }

    /**
     * This is specified by the "New in-task activity" Button by the xml attribute
     * android:onClick="onNewActivity" and is called if the Button is clicked.
     * We create an Intent that will launch the Activity ActionBarNavigationTarget,
     * and then we start this activity.
     *
     * @param button "New in-task activity" Button
     */
    public void onNewActivity(View button) {
        Intent intent = new Intent(this, ActionBarNavigationTarget.class);
        startActivity(intent);
    }

    /**
     * This is specified by the "New document" Button by the xml attribute
     * android:onClick="onNewDocument" and is called if the Button is clicked.
     * We create an Intent that will launch the Activity ActionBarNavigationTarget,
     * add the flag Intent.FLAG_ACTIVITY_NEW_DOCUMENT to the Intent and then we
     * start this activity.
     *
     * @param button "New document" Button
     */
    public void onNewDocument(View button) {
        Intent intent = new Intent(this, ActionBarNavigationTarget.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        startActivity(intent);
    }
}
