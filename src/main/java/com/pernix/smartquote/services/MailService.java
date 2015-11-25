package main.java.com.pernix.smartquote.services;
import main.java.com.pernix.smartquote.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailService {

    public static final String MIME_TYPE = "text/html; charset=utf-8";
    private static Session mailSession;
    private final Logger logger = LoggerFactory.getLogger(MySqlService.class);
    private PropertiesUtil propertiesUtil;


    public MailService(Session mailSession, PropertiesUtil properties){
        MailService.mailSession = mailSession;
        this.propertiesUtil = properties;
    }

    public boolean generateAndSendEmail(String [] recipients, String subject, String body){
        MimeMessage mailMessage = new MimeMessage(mailSession);
        boolean result = false;
        try {
            for(String recipient : recipients){
                    mailMessage.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(recipient));
            }
            createEmail(subject, body, mailMessage);
            javax.mail.Transport transport = connectWithEmailServer();
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
            result = transport.isConnected();
            transport.close();

        } catch (MessagingException e) {
            logger.error("MessagingException at generateAndSendEmail in MailService " + e.getMessage());
        }
        return result;
    }

    private void createEmail(String subject, String body, MimeMessage mailMessage) throws MessagingException {
        mailMessage.setSubject(subject);
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(body, MIME_TYPE);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        mailMessage.setContent(multipart);
    }

    private Transport connectWithEmailServer() throws MessagingException {
        Transport transport = mailSession.getTransport(propertiesUtil.getEmailProtocol());
        transport.connect(propertiesUtil.getEmailServer(), propertiesUtil.getEmailUsername(),propertiesUtil.getEmailPassword());
        return transport;
    }

}
