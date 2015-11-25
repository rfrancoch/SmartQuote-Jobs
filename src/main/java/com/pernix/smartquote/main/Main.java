package main.java.com.pernix.smartquote.main;

import main.java.com.pernix.smartquote.models.RequisitionInfo;
import main.java.com.pernix.smartquote.services.MailService;
import main.java.com.pernix.smartquote.services.MySqlService;
import main.java.com.pernix.smartquote.services.ServiceFactory;
import main.java.com.pernix.smartquote.transformer.TransformerEngine;
import main.java.com.pernix.smartquote.transformer.XMLSerializer;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        MySqlService mySqlService = new MySqlService();
        ArrayList<RequisitionInfo> requisitionsRetrieved = mySqlService.getNewRequisitions();
        MailService mailService = ServiceFactory.getMailService();
        TransformerEngine transformerEngine = new TransformerEngine();
        XMLSerializer xmlSerializer = new XMLSerializer();

        for(RequisitionInfo requisition : requisitionsRetrieved){
            String[] email = {requisition.getEmail()};
            String mailBody = transformerEngine.transformXMLtoHTML(xmlSerializer.serializeRequisition(requisition));
            mailService.generateAndSendEmail(email, "Nueva notificación de solicitud", mailBody);
        }

    }
}
