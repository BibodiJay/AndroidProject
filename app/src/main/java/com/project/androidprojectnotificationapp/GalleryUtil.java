package com.project.androidprojectnotificationapp;

import java.io.File;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.project.Framework.Constants;

/**
 * @author Bibodi Jay
 *
 * This class is performs activity for selecting image from device and
 * returning data for cropping and saving image as user profile pic
 */
public class GalleryUtil extends Activity
{
    // used to set request code when user selects image from device and checked if its same request code when data returned
    private final static int RESULT_SELECT_IMAGE = 100;

    /**
     * This method will invoke activity to select image from device.
     * @param savedInstanceState, contains data to restore activity in previous state
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        try
        {
            //Pick Image From Gallery
            Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_SELECT_IMAGE);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to decide further action taken by user
     * @param requestCode, when request code is RESULT_SELECT_IMAGE , image is selected from device
     * @param resultCode, performs action only when result code equals Activity.RESULT_OK whose value will be -1 that means no error occurred.
     * @param data,data obtained from previous activity and used/modified data to pass to another activity
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode)
        {
            // case when request code is RESULT_SELECT_IMAGE
            case RESULT_SELECT_IMAGE:

                // when resultCode equals Activity.RESULT_OK and data is not null
                if (resultCode == Activity.RESULT_OK && data != null && data.getData() != null)
                {
                    try
                    {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String picturePath = cursor.getString(columnIndex);
                        cursor.close();

                        //return Image Path to the Main Activity
                        Intent returnFromGalleryIntent = new Intent();
                        // setting image path in intent
                        returnFromGalleryIntent.putExtra(Constants.SELECTED_IMAGE_PATH_PROFILE_PIC,picturePath);
                        setResult(RESULT_OK,returnFromGalleryIntent);

                        // finishes this activity
                        finish();
                    }
                    catch(Exception e)
                    {
                        // when exceptions arise, intent is set with Activity.RESULT_CANCELLED
                        e.printStackTrace();
                        Intent returnFromGalleryIntent = new Intent();
                        setResult(RESULT_CANCELED, returnFromGalleryIntent);

                        // finishes this activity
                        finish();
                    }
                }
                else
                {
                    Log.i(Constants.TAG,"RESULT_CANCELED");

                    // when resultCode is Activity.RESULT_CANCELLED or when data returned from previous activity is null, intent is set with Activity.RESULT_CANCELLED
                    Intent returnFromGalleryIntent = new Intent();
                    setResult(RESULT_CANCELED, returnFromGalleryIntent);

                    // finishes this activity
                    finish();
                }
                break;
        }
    }
}