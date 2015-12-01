package test.java.com.pernix.smartquote.utils;

import main.java.com.pernix.smartquote.utils.PropertiesUtil;
import org.junit.Assert;
import org.junit.Test;

public class PropertiesUtilTest {

    private PropertiesUtil propertiesUtil;

    @Test
    public void testCreatePropertiesUtil(){
        propertiesUtil = new PropertiesUtil();
        Assert.assertNotNull(propertiesUtil);
    }

    @Test
    public void testGetProperties(){
        propertiesUtil = new PropertiesUtil();
        Assert.assertNotNull(propertiesUtil.getProperties());
    }

    @Test
    public void testGetValues(){
        propertiesUtil = new PropertiesUtil();
        Assert.assertEquals("smtp", propertiesUtil.getEmailProtocol());
        Assert.assertEquals("smtp.gmail.com", propertiesUtil.getEmailServer());
        Assert.assertEquals("smartquotecr.labs@gmail.com", propertiesUtil.getEmailUsername());
        Assert.assertEquals("Pernix123.", propertiesUtil.getEmailPassword());
    }
}
