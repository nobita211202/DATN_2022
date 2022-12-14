package org.datn.utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {
    public static void sendCode(String email,String text,String content) throws MessagingException {
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage mailMessage;
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        mailMessage = new MimeMessage(getMailSession);
        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        mailMessage.setContent(text,"text/html;charset = UTF-8");
        mailMessage.setSubject(content);
        Transport transport = getMailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", "lavantho0508@gmail.com", "zjipajndhlovrtvk");
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }
}
