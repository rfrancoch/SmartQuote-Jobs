package main.java.com.pernix.smartquote.services;
import main.java.com.pernix.smartquote.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class MailService {

    static Session mailSession;
    private final Logger logger = LoggerFactory.getLogger(MySqlService.class);
    private PropertiesUtil propertiesUtil;


    public MailService(Session mailSession, PropertiesUtil properties){
        MailService.mailSession = mailSession;
        this.propertiesUtil = properties;
    }

    public void generateAndSendEmail(String [] recipients, String subject, String body){
        MimeMessage mailMessage = new MimeMessage(mailSession);
        try {
            for(String recipient : recipients){
                    mailMessage.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(recipient));
            }

            mailMessage.setSubject(subject);
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(body, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            mailMessage.setContent(multipart);

            javax.mail.Transport transport = mailSession.getTransport(propertiesUtil.getEmailProtocol());
            transport.connect(propertiesUtil.getEmailServer(), propertiesUtil.getEmailUsername(),propertiesUtil.getEmailPassword());
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
            transport.close();

        } catch (MessagingException e) {
            logger.error("MessagingException at generateAndSendEmail in MailService " + e.getMessage());
        }
    }

}
