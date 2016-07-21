package com.project.androidprojectnotificationapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.Framework.Constants;
import com.project.Framework.NotificationUtils;
import com.project.Framework.SendMailTask;

import java.util.Arrays;
import java.util.List;

/**
 * @author Bibodi Jay
 *
 * This class performs user registeration for this app.
 * Saves Profile Pic, First Name, Last Name, Email Address and password of a user
 * Also validates Email Address and password
 */
public class NewUserSignUpActivity extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener
{
    //Declaration of Widgets used for NewUserSignUpActivity activity
    EditText firstNameEditText;
    EditText lastNameEditText;
    EditText emailAddressEditText;
    EditText passwordEditText;
    CheckBox showHidePasswordCheckbox;
    ImageView imageView;
    Button saveButton;
    Button cancelButton;
    SharedPreferences sharedPreferences;
    private ProgressDialog statusDialog;

    // used to set request code when invoked to CropImageActivity and checked if its same request code when data returned from CropImageActivity
    static int CROP_IMAGE_REQUEST_CODE = 100;

    /**
     * This method will initialize NewUserSignUpActivity
     * Also will set view for defining UI and retrieving
     * widgets on UI to interact with them programmatically
     * @param savedInstanceState, contains data to restore activity in previous state
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //setting view for NewUserSignUp activity
        setContentView(R.layout.newuser_signup_activity);

        //initializing all widgets used with NewUserSignUp activity
        initializeWidgets();

        // setting title for NewUserSignUp activity
        getSupportActionBar().setTitle(R.string.sign_up);

        // add onClickListener on imageView
        // when user clicks on this imageView, this is the handler.
        imageView.setOnClickListener(this);

        // add onClickListener on button
        // when user clicks on this button, this is the handler.
        saveButton.setOnClickListener(this);

        // add onClickListener on button
        // when user clicks on this button, this is the handler.
        cancelButton.setOnClickListener(this);

        // add onCheckedListener on checkbox
        // when user clicks on this checkbox, this is the handler.
        showHidePasswordCheckbox.setOnCheckedChangeListener(this);
    }

    /**
     * This method is used to initialize and retrieve widgets from UI
     * to interact and perform operation
     */
    private void initializeWidgets()
    {
        // find editText from UI by passing unique id defined for entering first name
        firstNameEditText = (EditText) findViewById(R.id.newuser_firstName_Id);

        // find editText from UI by passing unique id defined for entering last name
        lastNameEditText = (EditText) findViewById(R.id.newuser_lastName_Id);

        // find editText from UI by passing unique id defined for entering email address
        emailAddressEditText = (EditText) findViewById(R.id.newuser_emailAddress_Id);

        // find editText from UI by passing unique id defined for entering password
        passwordEditText = (EditText) findViewById(R.id.newuser_password_id);

        // find checkbox from UI by passing unique id defined for onChecked event and show/hide password entered by user
        showHidePasswordCheckbox = (CheckBox) findViewById(R.id.newuser_checkbox);

        // find imageView from UI by passing unique id defined for onClick event
        imageView = (ImageView) findViewById(R.id.userphoto);

        // set imageView is clickable
        imageView.isClickable();

        // find button from UI by passing unique id defined for onClick event to submit user details
        saveButton = (Button) findViewById(R.id.new_user_save_button);

        // find button from UI by passing unique id defined for onClick event to return to login activity
        cancelButton = (Button) findViewById(R.id.newuser_cancel_button);

        // get sharedPreferences created on user device
        sharedPreferences = getSharedPreferences(Constants.USER_PREFERENCES, Context.MODE_PRIVATE);
    }

    /**
     * This method is called when user changes current state of Checkbox.
     * @param buttonView, contains checkbox button properties
     * @param isChecked, state of checkbox , if false password is hidden and checkbox is not checked
     *                   else true and password is visible and checkbox is checked
     */
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        // checkbox status is changed from uncheck to checked.
        if (!isChecked)
        {
            // show password
            passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            showHidePasswordCheckbox.setText(R.string.newuser_signup_showPassword);
        }
        else
        {
            // hide password
            passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            showHidePasswordCheckbox.setText(R.string.newuser_signup_hidePassword);
        }
    }

    /**
     * This method invokes different methods based on type of view user clicked
     * @param view, Contains view information i.e editText, button, checkbox etc and its properties
     */
    public void onClick(View view)
    {
        // switch case based on type of view id user interacted with
        switch (view.getId())
        {
            // case when user clicks imageView
            case R.id.userphoto:
                // invokes a method to select an profile image for user and crop that image if required
                loadAndCropImage();
                break;
            // case when user clicks save button
            case R.id.new_user_save_button:
                // invokes a method to save all user details
                saveUserDataInSharedPreferences();
                break;
            // case when user clicks cancel button
            case R.id.newuser_cancel_button:
                // invokes a method to return to loginActivity
                loadToPreviousActivity();
                break;
        }
    }

    /**
     * This method is used to invoke new activity for selecting image and cropping the same if required
     */
    public void loadAndCropImage()
    {
        // defining new intent with target activity as CropImage
        Intent loadImage = new Intent(this, CropImage.class);

        // starting cropImage activity for obtaining result from the same
        startActivityForResult(loadImage,CROP_IMAGE_REQUEST_CODE);
    }

    /**
     * This method is used to set selected image in imageView
     * @param requestCode, when request code is CROP_IMAGE_REQUEST_CODE , image will be set in imageView of this activity
     * @param resultCode, performs action only when result code equals Activity.RESULT_OK whose value will be -1 that means no error occurred.
     * @param data,data obtained from previous activity and used/modified data to pass to another activity
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CROP_IMAGE_REQUEST_CODE)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                Bitmap selectedBitmap = data.getParcelableExtra("bitmapImage");
                imageView.setImageBitmap(selectedBitmap);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        }
    }

    /**
     * This method is used to validate email address and password
     * If email address and password are valid, user data will be stored in sharedPreference
     * And notification email will be sent to user about account creation for this application
     */
    public void saveUserDataInSharedPreferences()
    {
        // fetches email address entered by user from emailAddress editText Field
        String emailAddress = emailAddressEditText.getText().toString();

        // fetches password entered by user from password editText filed
        String password = passwordEditText.getText().toString();

        // if password and email address are not empty
        if(!emailAddress.isEmpty() && !password.isEmpty())
        {
            // invokes method in NotificationUtils Class to validate if email address is valid
            if(NotificationUtils.emailAddressValidate(emailAddress,getApplicationContext()))
            {
                // invokes method in NotificationUtils Class to validate if password is valid
                if(NotificationUtils.passwordValidate(password,getApplicationContext()))
                {
                    // Progressdialog to show user that account is getting ready for use
                    statusDialog = new ProgressDialog(this);
                    statusDialog.setMessage("Getting ready...");
                    statusDialog.setIndeterminate(false);
                    statusDialog.setCancelable(false);
                    statusDialog.show();

                    // fetches first name and last name from edit text
                    String firstName = firstNameEditText.getText().toString();
                    String lastName = lastNameEditText.getText().toString();

                    // convert string to list of email address if any
                    List<String> toEmailList = Arrays.asList(emailAddress.split("\\s*,\\s*"));

                    // creating email content that will be displayed to user once email is delivered.
                    String emailBody = Constants.EMAIL_TEXT_ACCOUNT_CREATED
                            +Constants.BEST_REGARDS
                            +Constants.APP_TEAM;
                    try
                    {
                        // send email asynchronously to email address entered by user
                        new SendMailTask(NewUserSignUpActivity.this).execute(Constants.EMAIL_ADDRESS, Constants.PASSWORD, toEmailList, Constants.ACCOUNT_CREATED, emailBody);

                        // creating sharedPreference for storing user data
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        // putting data into shared preference
                        editor.putString(Constants.PREFERENCES_FIRST_NAME, firstName);
                        editor.putString(Constants.PREFERENCES_LAST_NAME, lastName);
                        editor.putString(Constants.PREFERENCES_EMAIL_ADDRESS, emailAddress);
                        editor.putString(Constants.PREFERENCES_PASSWORD,password);
                        editor.putString(Constants.PREFERENCES_IMAGE_STRING,NotificationUtils.encodeImageToString((ImageView)findViewById(R.id.userphoto)));

                        // commit sharedPreference editor so data can stored and file can be generated
                        editor.commit();

                        // Notification on screen to user that account has been created.
                        Toast.makeText(this,Constants.ACCOUNT_CREATED,Toast.LENGTH_LONG).show();

                        // closing Progressdialog
                        statusDialog.dismiss();

                        // starting new activity with target activity as HomeActivity
                        startActivity(new Intent(this,HomeActivity.class));

                        // finishes the current activity
                        finish();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /*
     * This method is used when user clicks cancel button , this activity will be finished
     */
    public void loadToPreviousActivity()
    {
        finish();
    }
}