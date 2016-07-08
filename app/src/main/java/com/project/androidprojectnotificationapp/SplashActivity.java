package com.project.androidprojectnotificationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * @author Bibodi Jay
 *
 * This class is performs Splash Screen Activity for user and starts logIn activity
 */
public class SplashActivity extends AppCompatActivity
{
    /**
     * This method will initialize SplashActivity
     * @param savedInstanceState, contains data to restore activity in previous state
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // start activity for Login Activity
        startActivity(new Intent(this,LogInActivity.class));

        // finishes SplashActivity
        finish();
    }
}
