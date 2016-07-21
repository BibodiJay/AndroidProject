package com.project.androidprojectnotificationapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.Framework.Constants;
import com.project.Framework.NotificationUtils;
import com.project.Framework.SendMailTask;
import java.util.Arrays;
import java.util.List;

/**
 * @author Bibodi Jay
 *
 * This class is performs forgot password activity for the user
 * Able to fetch email address and send email to reset password
 */
public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener
{
    //Declaration of Widgets used for forgotPassword activity
    EditText emailAddressEditText;
    Button submitButton;

    /**
     * This method will initialize forgotPassword activity
     * Also will set view for defining UI and retrieving
     * widgets on UI to interact with them programmatically
     * @param savedInstanceState, contains data to restore activity in previous state
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //setting view for forgotPassword activity
        setContentView(R.layout.forgotpassword_activity);

        // setting title for forgotPassword activity
        getSupportActionBar().setTitle(R.string.forgotPassword_Title);

        //initializing all widgets used with forgotPassword activity
        initializeWidgets();

        // add onClickListener on button
        // when user clicks on this button, this is the handler.
        submitButton.setOnClickListener(this);
    }

    /**
     * This method is used to initialize and retrieve widgets from UI
     * to interact and perform operation
     */
    private void initializeWidgets()
    {
        // find editText from UI by passing unique id defined for entering email address
        emailAddressEditText = (EditText) findViewById(R.id.forgotPassword_Email_Id);

        // find button from UI by passing unique id defined for onClick event to submit email address
        submitButton = (Button) findViewById(R.id.forgotPassword_submit);
    }

    /**
     * This method is used invoke NotificationUtils method to validate email address and send email to reset password
     * @param view, Contains view information i.e editText, button, checkbox etc and its properties
     */
    public void onClick(View view)
    {
        // invokes method in NotificationUtils Class to validate if email address is valid
        if(NotificationUtils.emailAddressValidate(emailAddressEditText.getText().toString(),getApplicationContext()))
        {
            // convert string to list of email address if any
            List<String> toEmailList = Arrays.asList(emailAddressEditText.getText().toString().split("\\s*,\\s*"));

            // creating email content that will be displayed to user once email is delivered.
            String emailBody = Constants.EMAIL_TEXT_FORGOT_PASSWORD_ONE+"\n\n"+Constants.EMAIL_TEXT_FORGOT_PASSWORD_TWO
                    +Constants.BEST_REGARDS
                    +Constants.APP_TEAM;

            try
            {
                // send email asynchronously to email address entered by user
                new SendMailTask(ForgotPasswordActivity.this).execute(Constants.EMAIL_ADDRESS, Constants.PASSWORD, toEmailList, Constants.EMAIL_SUBJECT_FORGOT_PASSWORD, emailBody);
                Toast.makeText(this,Constants.FORGOT_PASSWORD_EMAIL_SENT,Toast.LENGTH_LONG).show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}