package test.java.com.pernix.smartquote.services;

import main.java.com.pernix.smartquote.services.MailService;
import main.java.com.pernix.smartquote.services.ServiceFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class MailServiceTest {

    private MailService mailService;

    @Test
    public void testMailServiceInstance(){
        ServiceFactory serviceFactory = new ServiceFactory();
        mailService = serviceFactory.getMailService();
        Assert.assertNotNull(mailService);
    }

    @Test
    public void testGenerateAndSendEmail(){
        ServiceFactory serviceFactory = new ServiceFactory();
        mailService = serviceFactory.getMailService();
        ArrayList<String> emails = new ArrayList<>();
        emails.add("smartquotecr.labs@gmail.com");
        boolean isConnected = false;
        isConnected = mailService.generateAndSendEmail(emails,"test", "test");
        Assert.assertTrue(isConnected);
    }
}
