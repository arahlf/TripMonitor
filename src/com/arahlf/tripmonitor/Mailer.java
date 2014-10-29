package com.arahlf.tripmonitor;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
    
    public Mailer(String email, String password, Properties mailProperties) {
        _email = email;
        _password = password;
        _mailProperties = mailProperties;
    }
    
    public void sendMail(String subject, String text) throws Exception {
        Session session = Session.getDefaultInstance(_mailProperties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(_email, _password);
            }
        });
        
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(_email));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(_email));
        message.setSubject(subject);
        message.setText(text);
        
        Transport.send(message);
    }
    
    private final String _email;
    private final String _password;
    private final Properties _mailProperties;
}
