package com.project.Framework;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

/**
 * @author Bibodi Jay
 *
 * This class is used send email asynchronously
 * and showing progressDialog for account being generated
 */
public class SendMailTask extends AsyncTask
{
    // Progress Dialog
    private ProgressDialog statusDialog;

    // activity from which this class instance is created
    private Activity sendMailActivity;

    // constructor used to set activity instance
    public SendMailTask(Activity activity)
    {
        sendMailActivity = activity;
    }

    /**
     * This method is used to set Progress Dialog on specific activity
     * and showing dialog on screen to user using show method of ProgressDialog class.
     */
    protected void onPreExecute()
    {
        statusDialog = new ProgressDialog(sendMailActivity);
        statusDialog.setMessage("Getting ready...");
        statusDialog.setIndeterminate(false);
        statusDialog.setCancelable(false);
        statusDialog.show();
    }

    /**
     * This method is used invoke notification email class methods and update progress dialog simultaneously
     */
    protected Object doInBackground(Object... args)
    {
        try
        {
            Log.i("SendMailTask", "About to instantiate GMail...");
            //publishProgress("Processing input....");
            NotificationEmail androidEmail = new NotificationEmail(args[0].toString(),
                    args[1].toString(), (List) args[2], args[3].toString(),
                    args[4].toString());
            //publishProgress("Preparing mail message....");
            androidEmail.createEmailMessage();
            publishProgress(Constants.SEND_EMAIL);
            androidEmail.sendEmail();
            //publishProgress("Email Sent.");
            Log.i("SendMailTask", "Mail Sent.");
        }
        catch (Exception e)
        {
            publishProgress(e.getMessage());
            Log.e("SendMailTask", e.getMessage(), e);
        }
        return null;
    }

    /**
     * This method updates text of progress dialog
     * @param values
     */
    public void onProgressUpdate(Object... values)
    {
        statusDialog.setMessage(values[0].toString());
    }

    /**
     * This method dismiss the progress dialog, once user account is created
     * @param result
     */
    public void onPostExecute(Object result)
    {
        statusDialog.dismiss();
    }
}
