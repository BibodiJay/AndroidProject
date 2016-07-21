package com.project.androidprojectnotificationapp;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.project.Framework.Constants;

import java.io.File;

/**
 * @author Bibodi Jay
 *
 * This class is performs activity for croping image selected by user
 * Invokes new activity for selecting image from SDCARD on Android device.
 */
public class CropImage extends Activity
{
    // used to set request code when invoked to GalleryActivity and checked if its same request code when data returned from GalleryActivity
    private final int GALLERY_ACTIVITY_CODE=200;

    // used to set request code when image is resized or cropped by user and check request code to set cropped image when data is returned to newUserSignUpActivity
    private final int RESULT_CROP = 400;

    /**
     * This method will invoke gallery activity
     * @param savedInstanceState, contains data to restore activity in previous state
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent gallery_Intent = new Intent(getApplicationContext(), GalleryUtil.class);
        startActivityForResult(gallery_Intent, GALLERY_ACTIVITY_CODE);
    }

    /**
     * This method is used to decide further action taken by user
     * @param requestCode, when request code is GALLERY_ACTIVITY_CODE , image is selected from gallery and when request code is RESULT_CROP, image is cropped and data is sent to newUserSignupActivity
     * @param resultCode, performs action only when result code equals Activity.RESULT_OK whose value will be -1 that means no error occured.
     * @param data,data obtained from previous activity and used/modified data to pass to another activity
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        // when image is selected from gallery activity and returned
        if (requestCode == GALLERY_ACTIVITY_CODE)
        {
            // when process of selecting image occurs with no error resultCode equlas Activity.Result_OK else user manually set Activity_CANCELLED
            if(resultCode == Activity.RESULT_OK)
            {
                // get path of image stored on device.
                String picturePath = data.getStringExtra(Constants.SELECTED_IMAGE_PATH_PROFILE_PIC);
                //perform Crop on the Image Selected from Gallery
                performCrop(picturePath);
            }
        }

        // when image is cropped or selected as it is by user to save.
        if (requestCode == RESULT_CROP)
        {
            // result code is set when cropping an image is done without errors
            if(resultCode == Activity.RESULT_OK)
            {
                // get data from intent
                Bundle extras = data.getExtras();
                Bitmap selectedBitmap = extras.getParcelable("data");

                //creating a new intent to pass data to newUserSignUpActivity
                Intent returnData = new Intent();

                // setting bitmap in intent before returning data.
                returnData.putExtra("bitmapImage",selectedBitmap);
                setResult(RESULT_OK,returnData);

                // finishes CropImage Activity
                finish();
            }
        }
    }

    /**
     * This method is used to perform crop operation and set cropped image and resultCode when user saves the image
     * @param picUri, path of image selected by user.
     */
    private void performCrop(String picUri)
    {
        try
        {
            //Start Crop Activity
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            File f = new File(picUri);
            Uri contentUri = Uri.fromFile(f);

            cropIntent.setDataAndType(contentUri, "image/*");
            // set crop properties
            cropIntent.putExtra("crop", "true");
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            // indicate output X and Y
            cropIntent.putExtra("outputX", 280);
            cropIntent.putExtra("outputY", 280);

            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, RESULT_CROP);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            // display an error message
            String errorMessage = "your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}