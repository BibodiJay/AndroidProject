package com.project.Framework;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Bibodi Jay
 *
 * This class is used to establish connection to emailhost service,
 * setting properties, creating emailMessage and sending email
 * to user.
 */
public class  NotificationEmail
{
    // gmail's smtp port
    final String emailPort = "587";

    //gmail's smtp authentication
    final String smtpAuth = "true";

    // gmail's transport layer security
    final String starttls = "true";

    // gmail host configuration
    final String emailHost = "smtp.gmail.com";

    // declaring global variables to set properties used for sending email
    String fromEmail;
    String fromPassword;
    List<String> toEmailList;
    String emailSubject;
    String emailBody;

    // Java properties for sending email
    Properties emailProperties;

    // session object for establishing session before sending email
    static Session mailSession;

    // setting email message properties
    MimeMessage emailMessage;

    /**
     * This Constructor is used to initialized email properties
     * @param fromEmail, string define's email address of Android Application team
     * @param fromPassword, string define's password of Android Application team
     * @param toEmailList, list of email address to whom email will be sent
     * @param emailSubject, string define's subject of email
     * @param emailBody, string define's text of email
     */
    public NotificationEmail(String fromEmail, String fromPassword, List<String> toEmailList, String emailSubject, String emailBody)
    {
        // setting values to global variable
        this.fromEmail = fromEmail;
        this.fromPassword = fromPassword;
        this.toEmailList = toEmailList;
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;

        // setting email properties to establish connection to send email
        emailProperties = new Properties();
        emailProperties.put("mail.smtp.port", emailPort);
        emailProperties.put("mail.smtp.auth", smtpAuth);
        emailProperties.put("mail.smtp.host", emailHost);
        emailProperties.put("mail.smtp.starttls.enable", starttls);
    }

    /**
     * This method is used to create emailMessage and establish session to send email over TLS
     * @return mimeMessage
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public MimeMessage createEmailMessage() throws MessagingException, UnsupportedEncodingException
    {
        // gets session by passing email properties and user authentication
         mailSession = Session.getInstance(emailProperties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, fromPassword);
            }
        });

        // defines each step initiated after session being generated.
        mailSession.setDebug(true);

        // creating and initializing new MimeMessage by passing session as argument
        emailMessage = new MimeMessage(mailSession);

        // setting user credentials from which email is sent
        emailMessage.setFrom(new InternetAddress(fromEmail, fromEmail));
        for (String toEmail : toEmailList)
        {
            // setting each receipt email address in mimeMessage
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        }

        // setting email subject in mimeMessage
        emailMessage.setSubject(emailSubject);

        // for a html email
        //emailMessage.setContent(emailBody, "text/html");

        // for a text email
        emailMessage.setText(emailBody);

        // return mimeMessage after setting all properties
        return emailMessage;
    }

    /**
     * This method is used to send email
     * @throws MessagingException
     */
    public void sendEmail() throws MessagingException
    {
        // get transport object
        Transport transport = mailSession.getTransport("smtp");

        // connect to host service using transport object
        transport.connect(emailHost,fromEmail, fromPassword);

        // send message
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());

        // close session
        mailSession.getTransport().close();
        Log.i("GMail", "Email sent successfully.");
    }
}