package main.java.com.pernix.smartquote.services;

import main.java.com.pernix.smartquote.utils.PropertiesUtil;
import javax.mail.Session;

public class ServiceFactory {

    private static MailService mailService = null;
    private static PropertiesUtil propertiesUtil;

    public static MailService getMailService(){
        if(propertiesUtil == null)
            propertiesUtil = new PropertiesUtil();
        mailService = (mailService != null) ? mailService : (new MailService(getMailServiceSession(), propertiesUtil));
        return mailService;
    }

    private static Session getMailServiceSession(){
        return Session.getDefaultInstance(propertiesUtil.getProperties(), null);
    }
}
