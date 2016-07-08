package com.project.Framework;

import android.content.Context;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Bibodi Jay
 *
 * This class is used to describe common methods that will be used in application
 * Validating email , validating password using regex and pattern matching
 */
public class NotificationUtils
{
    /**
     * This method is used to validate email address of user
     * @param emailAddress , string entered by user which is email address
     * @param context , applicationContext object used to display message on screen if email does not validate.
     * @return true if user entered email address is valid else return false.
     */
    public static boolean emailAddressValidate(String emailAddress,Context context)
    {
        // check if emailAddress entered by user is not empty or blankspace
        if(!emailAddress.isEmpty() && !emailAddress.equals(""))
        {
            // creates a pattern object which will be case sensitive
            Pattern pattern = Pattern.compile(Constants.EMAIL_PATTERN,Pattern.CASE_INSENSITIVE);

            // checks if email address entered by user matches pattern or not
            Matcher matcher = pattern.matcher(emailAddress);

            // returns true if pattern matches else false.
            return matcher.matches();
        }
        else
        {
            // display on screen that email address is not valid
            Toast.makeText(context.getApplicationContext(),Constants.INVALID_EMAIL_ADDRESS,Toast.LENGTH_LONG).show();

            // returns false as email is not valid
            return false;
        }
    }

    /**
     *This method is used to validate password of the user
     * @param password, string entered by user which is password
     * @param context, applicationContext object used to display message on screen if password does not validate.
     * @return true if user entered password is valid else return false.
     */
    public static boolean passwordValidate(String password, Context context)
    {
        // check if password entered by user is not empty or blankspace
        if(!password.isEmpty() && !password.equals(""))
        {
            // creates a pattern object which will be case sensitive
            Pattern pattern = Pattern.compile(Constants.PASSWORD_PATTERN,Pattern.CASE_INSENSITIVE);

            // checks if password entered by user matches pattern or not
            Matcher matcher = pattern.matcher(password);

            // returns true if pattern matches else false.
            return matcher.matches();
        }
        else
        {
            // display on screen that password is not valid
            Toast.makeText(context.getApplicationContext(),Constants.INVALID_PASSWORD,Toast.LENGTH_LONG).show();

            // returns false as password is not valid
            return false;
        }
    }
}