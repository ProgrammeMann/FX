package com.springapp.mvc.sender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Senging the mail to user
 */
public class SendMailTLS {

    private String username;
    private String password;
    private Properties props;

    public SendMailTLS(String username, String password) {
        this.username = username;
        this.password = password;
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    public void send(String subject, String text, String fromEmail, String toEmail) {
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
//от кого
            message.setFrom(new InternetAddress(username));
//кому
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
//тема сообщения
            message.setSubject(subject);
//текст
            message.setText(text);

//отправляем сообщение
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}