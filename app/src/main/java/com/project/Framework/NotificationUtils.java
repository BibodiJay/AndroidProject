package com.project.Framework;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.androidprojectnotificationapp.R;

import java.io.ByteArrayOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLTransactionRollbackException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

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

    /**
     * This method is used to encode image to string
     * @param imageView, imageView which contains image which is to be encoded
     * @return encoded String
     */
    public static String encodeImageToString(ImageView imageView)
    {
        imageView.buildDrawingCache();
        Bitmap bitmap = imageView.getDrawingCache();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] image=stream.toByteArray();
        return Base64.encodeToString(image, 0);
    }

    /**
     * This method is used to decode string into image
     * @param encodedImageString, encoded String which will be converted to image
     * @param ivsavedphoto, imageView for in which image will be set
     * @return imageView
     */
    public static ImageView decodeStringToImage(String encodedImageString,ImageView ivsavedphoto)
    {
        if(encodedImageString!=null && !encodedImageString.isEmpty())
        {
            String base=encodedImageString;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            ivsavedphoto.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
            ivsavedphoto.setScaleType(ImageView.ScaleType.FIT_START);
        }
        return ivsavedphoto;
    }

    /*public static boolean insertValuesInDB(List<PreparedStatement> preparedStatementList)
    {
        int i=0;
        Connection conn = null;
        try
        {
            Class.forName(Constants.JDBC_DRIVER);
            conn = DriverManager.getConnection(Constants.DB_URL,Constants.DB_USER_NAME,Constants.DB_PASSWORD);

            for (PreparedStatement preparedStatement : preparedStatementList)
            {
                int rowInserted = preparedStatement.executeUpdate();

                if(i==1)
                {

                }
                i++;
            }

            conn.close();
        }
        catch (Exception e)
        {
            try
            {
                conn.rollback();
            }
            catch (SQLException sqlException)
            {
                sqlException.printStackTrace();
            }
        }
    }*/

    /*public static void encrypt() throws Exception *//*NoSuchAlgorithmException,NoSuchPaddingException,InvalidKeyException,InvalidAlgorithmParameterException*//*
    {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        SecretKey key = KeyGenerator.getInstance("DES").generateKey();

        // for CBC; must be 8 bytes
        byte[] initVector = new byte[] { 0x10, 0x10, 0x01, 0x04, 0x01, 0x01, 0x01, 0x02 };

        AlgorithmParameterSpec algParamSpec = new IvParameterSpec(initVector);
        Cipher m_encrypter = Cipher.getInstance("DES/CBC/PKCS5Padding");
        Cipher m_decrypter = Cipher.getInstance("DES/CBC/PKCS5Padding");

        m_encrypter.init(Cipher.ENCRYPT_MODE, key, algParamSpec);
        m_decrypter.init(Cipher.DECRYPT_MODE, key, algParamSpec);

        byte[] clearText = "www.java2s.com".getBytes();

        byte[] encryptedText = m_encrypter.doFinal(clearText);

        byte[] decryptedText = m_decrypter.doFinal(encryptedText);

        System.out.println(new String(clearText));
        System.out.println(new String(encryptedText));
        System.out.println(new String(decryptedText));
    }*/
}