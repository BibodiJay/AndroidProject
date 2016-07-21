package com.project.androidprojectnotificationapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.Framework.Constants;
import com.project.Framework.NotificationUtils;

/**
 * @author Bibodi Jay
 *
 * This class is used to show first activity to user after login
 *
 * TO_DO this design and imlementation is pending
 *
 */
public class HomeActivity extends AppCompatActivity
{
    //Declaration of Widgets used for HomeActivity
    SharedPreferences sharedPreferences;
    ImageView imageview;
    TextView textView;

    /**
     * This method will initialize HomeActivity activity
     * Also will set view for defining UI and retrieving
     * widgets on UI to interact with them programmatically
     * @param savedInstanceState, contains data to restore activity in previous state
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // setting view for this activity
        setContentView(R.layout.home_activity);

        // get sharedPreference created on user device
        sharedPreferences = getSharedPreferences(Constants.USER_PREFERENCES, Context.MODE_PRIVATE);

        // get image encoded in string
        String profilePicString = sharedPreferences.getString(Constants.PREFERENCES_IMAGE_STRING,null);

        // find imageView from UI by passing unique id to display image stored in sharedPref.
        imageview = (ImageView) findViewById(R.id.customTitle_userProfilePic_id);

        // find textView from UI by passing unique id to display title in tool bar
        textView = (TextView) findViewById(R.id.ActionBarTitle_text_id);

       /* ImageView actionBarImage = new ImageView(getApplicationContext());
        actionBarImage = NotificationUtils.decodeStringToImage(profilePic,actionBarImage);*/

        // invoke to method in notification utils which will decode string to image and set that image in imageView of tool bar
        imageview.setImageDrawable(NotificationUtils.decodeStringToImage(profilePicString,imageview).getDrawable());

        // set title of tool bar
        textView.setText(Constants.APP_NAME);
    }
}
