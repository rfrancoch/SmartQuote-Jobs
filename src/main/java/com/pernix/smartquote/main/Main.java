package main.java.com.pernix.smartquote.main;

import main.java.com.pernix.smartquote.models.RequisitionInfo;
import main.java.com.pernix.smartquote.services.MailService;
import main.java.com.pernix.smartquote.services.MySqlService;
import main.java.com.pernix.smartquote.services.ServiceFactory;
import main.java.com.pernix.smartquote.transformer.TransformerEngine;
import main.java.com.pernix.smartquote.transformer.XMLSerializer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static final String EMAIL_SUBJECT = new String("Nueva notificaci\u00F3n de solicitud");

    public static void main(String[] args) {
        MySqlService mySqlService = new MySqlService();
        HashMap<String, ArrayList<RequisitionInfo>> requisitionsRetrieved = mySqlService.getNewRequisitions();
        MailService mailService = ServiceFactory.getMailService();
        TransformerEngine transformerEngine = new TransformerEngine();
        XMLSerializer xmlSerializer = new XMLSerializer();

        for (Map.Entry<String, ArrayList<RequisitionInfo>> requisitionInfoListByRequisitionId : requisitionsRetrieved.entrySet()){
            ArrayList<RequisitionInfo> requisitions = requisitionInfoListByRequisitionId.getValue();
            String mailBody = transformerEngine.transformXMLtoHTML(xmlSerializer.serializeRequisition(requisitions.get(0)));
            mailService.generateAndSendEmail(getEmailAddressesIntoArray(requisitions), EMAIL_SUBJECT, mailBody);
            mySqlService.setRequisitionNotified(Integer.parseInt(requisitionInfoListByRequisitionId.getKey()));
        }
    }

    private static  ArrayList<String> getEmailAddressesIntoArray(ArrayList<RequisitionInfo> requisitions) {
        ArrayList<String> emails = new ArrayList<>();
        for (RequisitionInfo requisitionInfo : requisitions){
            emails.add(requisitionInfo.getEmail());
        }
        return emails;
    }
}
