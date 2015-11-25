package test.java.com.pernix.smartquote.models;

import main.java.com.pernix.smartquote.models.RequisitionInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class RequisitionInfoTest {

    private RequisitionInfo requisitionInfo;

    @Test
    public void testCreateRequisitionInfo(){
        requisitionInfo = new RequisitionInfo("Necesito monitores de computadora 20'", "Tres Rios", 20.0, 100000.0, Calendar.getInstance().getTime(), "Electronico", "test@test.com");
        Assert.assertNotNull(requisitionInfo);
    }

    @Test
    public void testGetsFromRequisitionInfo(){
        Date currentDate = Calendar.getInstance().getTime();
        requisitionInfo = new RequisitionInfo("Necesito monitores de computadora 20'", "Tres Rios", 20.0, 100000.0,currentDate, "Electronico", "test@test.com");
        Assert.assertEquals("Necesito monitores de computadora 20'", requisitionInfo.getDescription());
        Assert.assertEquals("Tres Rios", requisitionInfo.getShipping_address());
        Assert.assertTrue(20.0 == requisitionInfo.getQuantity());
        Assert.assertTrue(100000.0 == requisitionInfo.getBase_amount());
        Assert.assertEquals(currentDate, requisitionInfo.getLimit_date());
        Assert.assertEquals("Electronico", requisitionInfo.getTitle());
        Assert.assertEquals("test@test.com", requisitionInfo.getEmail());
    }
}
