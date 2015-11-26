package test.java.com.pernix.smartquote.transformer;

import main.java.com.pernix.smartquote.models.RequisitionInfo;
import main.java.com.pernix.smartquote.transformer.XMLSerializer;
import org.junit.Assert;
import org.junit.Test;
import java.util.Calendar;

public class XMLSerializerTest {

    XMLSerializer xmlSerializer;

    @Test
    public void testSerializeRequisition(){
        RequisitionInfo requisition = new RequisitionInfo(1,"Vasos plasticos", "Tres Rios", 2.0, 30000.0, Calendar.getInstance().getTime(), "Hogar", "example@exaple.com");
        xmlSerializer = new XMLSerializer();
        String result = xmlSerializer.serializeRequisition(requisition);
        Assert.assertNotEquals("", result);
    }
}
