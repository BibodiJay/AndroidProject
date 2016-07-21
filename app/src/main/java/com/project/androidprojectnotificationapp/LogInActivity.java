package com.project.androidprojectnotificationapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.Framework.Constants;
import com.project.Framework.NotificationUtils;
import com.project.Framework.SendMailTask;

import java.util.Arrays;
import java.util.List;

/**
 * @author Bibodi Jay
 *
 * This class is performs login activity for the user
 * Also provides ability for user to reset password
 * And Sign up and register new user to application
 */
public class LogInActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,View.OnClickListener
{
    //Declaration of Widgets used for log in activity
    EditText emailAddressEditText;
    EditText passwordEditText;
    Button submitButton;
    TextView forgotPasswordTextView;
    TextView newUserTextView;
    CheckBox showHidePasswordCheckbox;
    SharedPreferences sharedPreferences;
    ProgressDialog statusDialog;

    /**
     * This method will initialize login activity
     * Also will set view for defining UI and retrieving
     * widgets on UI to interact with them programmatically
     * @param savedInstanceState, contains data to restore activity in previous state
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // get sharedPreference created on user device
        sharedPreferences = getSharedPreferences(Constants.USER_PREFERENCES, Context.MODE_PRIVATE);

        // get email address from shared preference
        String emailAddress = sharedPreferences.getString(Constants.PREFERENCES_EMAIL_ADDRESS,null);

        // get password from shared preference
        String password = sharedPreferences.getString(Constants.PREFERENCES_PASSWORD,null);

        // if email address and password is present than go to new activity
        if(emailAddress!=null && !emailAddress.isEmpty() && password!=null && !password.isEmpty())
        {
            // start Home Activity i.e activity after login
            startActivity(new Intent(this,HomeActivity.class));

            // finishes current activity
            finish();
        }

        //setting view for login activity
        setContentView(R.layout.login_activity);

        // setting title for login activity
        getSupportActionBar().setTitle(R.string.logIn);

        //initializing all widgets used with login activity
        initializeWidgets();

        // add onCheckedListener on checkbox
        // when user clicks on this checkbox, this is the handler.
        showHidePasswordCheckbox.setOnCheckedChangeListener(this);

        // add onClickListener on button
        // when user clicks on this button, this is the handler.
        submitButton.setOnClickListener(this);

        // add onClickListener on forgotPassword textview
        // when user clicks on this textview, this is the handler.
        forgotPasswordTextView.setOnClickListener(this);

        // add onClickListener on new sign up  textview
        // when user clicks on this textview, this is the handler.
        newUserTextView.setOnClickListener(this);
    }

    /**
     * This method is used to initialize and retrieve widgets from UI
     * to interact and perform operation
     */
    private void initializeWidgets()
    {
        // find editText from UI by passing unique id defined for entering email address
        emailAddressEditText = (EditText) findViewById(R.id.logIn_Email_Id);

        // find editText from UI by passing unique id defined for entering password
        passwordEditText = (EditText) findViewById(R.id.logIn_Password);

        // find button from UI by passing unique id defined for onClick event to submit email address and password
        submitButton = (Button) findViewById(R.id.logIn_submit);

        // find textView from UI by passing unique id defined for onClick event and start new activity for forgot password
        forgotPasswordTextView = (TextView) findViewById(R.id.logIn_forgotPassword);

        // find textView from UI by passing unique id defined for onClick event and start new activity for sign up new user
        newUserTextView = (TextView) findViewById(R.id.logIn_newUser);

        // find checkbox from UI by passing unique id defined for onChecked event and show/hide password entered by user
        showHidePasswordCheckbox = (CheckBox) findViewById(R.id.logIn_CheckBox);

        // set text color in textView
        forgotPasswordTextView.setTextColor(getResources().getColor(R.color.blue));
        newUserTextView.setTextColor(getResources().getColor(R.color.blue));
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
            showHidePasswordCheckbox.setText(R.string.showPassword);
        }
        else
        {
            // hide password
            passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            showHidePasswordCheckbox.setText(R.string.hidePassword);
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
            // case when user clicks submit button
            case R.id.logIn_submit:
                //invokes method to validate email address, password and notify user for login activity via email
                checkValidEmailAddressAndPassword();
                break;
            // case when user clicks forgot password
            case R.id.logIn_forgotPassword:
                //sets text color to black from blue when user clicks textView
                forgotPasswordTextView.setTextColor(getResources().getColor(R.color.black));
                // invokes method to call forgot password activity
                callForgotPasswordActivity();
                break;
            // case when user clicks new user sign up registration
            case R.id.logIn_newUser:
                // invokes method to call new user signup activity
                callNewUserActivity();
                break;
        }
    }

    /**
     * This method is used to invoke method to send email to user and validate email address and password
     */
    private void checkValidEmailAddressAndPassword()
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
                    statusDialog = new ProgressDialog(this);
                    statusDialog.setMessage("Getting ready...");
                    statusDialog.setIndeterminate(false);
                    statusDialog.setCancelable(false);
                    statusDialog.show();

                    // convert string to list of email address if any
                    List<String> toEmailList = Arrays.asList(emailAddress.split("\\s*,\\s*"));

                    // creating email content that will be displayed to user once email is delivered.
                    String emailBody = Constants.EMAIL_TEXT_LOGIN_ONE+" "+String.valueOf(android.os.Build.MODEL)+ Constants.EMAIL_TEXT_LOGIN_TWO
                            +Constants.BEST_REGARDS
                            +Constants.APP_TEAM;
                    try
                    {
                        // send email asynchronously to email address entered by user
                        new SendMailTask(LogInActivity.this).execute(Constants.EMAIL_ADDRESS, Constants.PASSWORD, toEmailList, Constants.EMAIL_SUBJECT_LOGIN, emailBody);

                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        // TO DO : fetch first name,last name and image from database and save in shared preferences

                        /*editor.putString(Constants.PREFERENCES_FIRST_NAME, firstName);
                        editor.putString(Constants.PREFERENCES_LAST_NAME, lastName);*/
                        editor.putString(Constants.PREFERENCES_EMAIL_ADDRESS, emailAddress);
                        editor.putString(Constants.PREFERENCES_PASSWORD,password);
                        editor.commit();
                        Toast.makeText(this,Constants.LOGGED_IN,Toast.LENGTH_LONG).show();
                        statusDialog.dismiss();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * This method is used to call forgort password activity
     */
    private void callForgotPasswordActivity()
    {
        //start activity for forgot password
        startActivity(new Intent(this,ForgotPasswordActivity.class));
    }

    /**
     * This method is used to call new user signup activity
     */
    private void callNewUserActivity()
    {
        //start activity for sign up
        startActivity(new Intent(this,NewUserSignUpActivity.class));
    }

    /**
     * This method is called on login activity is resumed and all widgets are reset to original configuration
     */
    protected void onResume()
    {
        super.onResume();

        sharedPreferences = getSharedPreferences(Constants.USER_PREFERENCES, Context.MODE_PRIVATE);
        String emailAddress = sharedPreferences.getString(Constants.PREFERENCES_EMAIL_ADDRESS,null);
        String password = sharedPreferences.getString(Constants.PREFERENCES_PASSWORD,null);

        if(emailAddress!=null && !emailAddress.isEmpty() && password!=null && !password.isEmpty())
        {
            //startActivity(new Intent(this,HomeActivity.class));
            finish();
        }

        //empty emailAddress editText field
        emailAddressEditText.setText("");

        //empty password editText field
        passwordEditText.setText("");

        //Reset Checkbox button text
        showHidePasswordCheckbox.setText(R.string.showPassword);

        // Reset Checkbox button to unchecked state
        showHidePasswordCheckbox.setChecked(false);

        // Reset text color view of forgot password textView
        forgotPasswordTextView.setTextColor(getResources().getColor(R.color.blue));

        // Reset text color view of new user signup textView
        newUserTextView.setTextColor(getResources().getColor(R.color.blue));
    }
}