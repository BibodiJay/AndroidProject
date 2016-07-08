package com.project.Framework;

/**
 * @author Bibodi Jay
 *
 * This class defines string constants
 * It will be used across application.
 */
public class Constants
{
    // EMAIL_ADDRESS string define's email address of Android Application team
    public static final String EMAIL_ADDRESS = "jay23193@gmail.com";

    // PASSWORD string define's password of Android Application team
    public static final String PASSWORD = "$Yellowrose23193";

    // EMAIL_SUBJECT_LOGIN string define's subject of email to be sent when user logs into this application
    public static final String EMAIL_SUBJECT_LOGIN = "Alert Login";

    // EMAIL_TEXT_LOGIN_ONE and EMAIL_TEXT_LOGIN_TWO string define's text to be sent to logged in user in email
    public static final String EMAIL_TEXT_LOGIN_ONE = "Hello, \n\nYour account was logged in for Notification App from";
    public static final String EMAIL_TEXT_LOGIN_TWO = " .If it was not you please contact support team! Please ignore this email if you made this activity.";

    // EMAIL_SUBJECT_FORGOT_PASSWORD string define's subject of email to be sent when user forget and/or reset Password for this application
    public static final String EMAIL_SUBJECT_FORGOT_PASSWORD = "Reset Password";

    // EMAIL_TEXT_FORGOT_PASSWORD_ONE and EMAIL_TEXT_FORGOT_PASSWORD_TWO string define's text to be sent to user in forgot Password email
    public static final String EMAIL_TEXT_FORGOT_PASSWORD_ONE = "Hello, \n\nPlease click link below to reset your password";
    public static final String EMAIL_TEXT_FORGOT_PASSWORD_TWO = "Please ignore this email if you din't made a request to reset password.";

    // BEST_REGARDS string define's text for best regards in any email sent from Android Application team to user
    public static final String BEST_REGARDS = "\n\nBest Regards,";

    // APP_TEAM string define's text for team name in any email sent from Android Application team to user
    public static final String APP_TEAM = "\nNotification App Team";

    // string define's pattern of email
    static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    // string define's pattern of password
    static String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    // INVALID_EMAIL_ADDRESS string define's text which is used when email address entered is not valid
    public static final String INVALID_EMAIL_ADDRESS = "Invalid Email Address";

    // INVALID_PASSWORD string define's text which is used when password entered is not valid
    public static final String INVALID_PASSWORD = "Invalid Password"+
            "\n Password must have..." +
            "\n Minimum 6 characters" +
            "\n Maximum 20 characters" +
            "\n Must contain one digit from 0-9" +
            "\n Must contain one lower case character" +
            "\n Must contain one upper case character" +
            "\n Must contain one special character from @, #, $, %";

    public static final String SEND_EMAIL = "Sending Email...";
}
