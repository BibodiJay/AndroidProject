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

    // SEND_EMAIL string defines text which will be used while sending email in progress dialog
    public static final String SEND_EMAIL = "Sending Email...";

    // EMAIL_TEXT_ACCOUNT_CREATED string define's text to be sent to user in account creation email
    public static final String EMAIL_TEXT_ACCOUNT_CREATED = "Hello, \n\nYour account is created successfully for Notification App. For any queries please contact system administrator!";

    // USER_PREFERENCES string defines file name which will be used to store user data
    public static final String USER_PREFERENCES = "UserPreferences" ;

    // PREFERENCES_FIRST_NAME string defines element name in sharedPref file
    public static final String PREFERENCES_FIRST_NAME = "firstName";

    // PREFERENCES_LAST_NAME string defines element name in sharedPref file
    public static final String PREFERENCES_LAST_NAME = "lastName";

    // PREFERENCES_EMAIL_ADDRESS string defines element name in sharedPref file
    public static final String PREFERENCES_EMAIL_ADDRESS = "emailAddress";

    // PREFERENCES_PASSWORD string defines element name in sharedPref file
    public static final String PREFERENCES_PASSWORD = "password";

    // PREFERENCES_IMAGE_STRING string defines element name in sharedPref file
    public static final String PREFERENCES_IMAGE_STRING = "imageString";

    // LOGGED_IN string defines text which is displayed when user logs in successfully
    public static final String LOGGED_IN = "Successfully Logged In";

    // ACCOUNT_CREATED string defines text which will be used as subject in email when user creation email will be sent to user.
    public static final String ACCOUNT_CREATED = "Account Created";

/*    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/notificationapp";

    public static final String DB_USER_NAME = "root";

    public static final String DB_PASSWORD = "admin@12345";

    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";*/

    // to log data
    public static final String TAG = "GalleryUtil";

    // SELECTED_IMAGE_PATH_PROFILE_PIC string defines text which will store path from which image is selected and cropped
    public static final String SELECTED_IMAGE_PATH_PROFILE_PIC = "picturePath";

    // APP_NAME string defines name of this application
    public static final String APP_NAME = "Notification App";
}
