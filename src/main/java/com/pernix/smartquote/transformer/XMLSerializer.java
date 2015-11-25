package main.java.com.pernix.smartquote.transformer;

import main.java.com.pernix.smartquote.models.RequisitionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;


public class XMLSerializer {

    private final Logger logger = LoggerFactory.getLogger(TransformerEngine.class);

    public String serializeRequisition(RequisitionInfo requisition){
        StringWriter stringWriter = new StringWriter();
        Marshaller jaxbMarshaller = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RequisitionInfo.class);
            jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(requisition, stringWriter);

        } catch (JAXBException e) {
            logger.error("JAXBException at serializeRequisition in XMLSerializer " + e.getMessage());
        }
        return stringWriter.toString();
    }
}
