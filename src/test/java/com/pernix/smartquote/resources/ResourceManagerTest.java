package test.java.com.pernix.smartquote.resources;

import main.java.com.pernix.smartquote.resources.ResourceManager;
import org.junit.Assert;
import org.junit.Test;

public class ResourceManagerTest {

    public static final String EXPECTED_URL = "file:/C:/Users/johnn/IdeaProjects/Smartquote-RequisitionNotifier/out/production/Smartquote-RequisitionNotifier/XMLData";
    private ResourceManager resourceManager;

    @Test
    public void testCreateResourceManager(){
        resourceManager = new ResourceManager();
        Assert.assertNotNull(resourceManager);
    }

    @Test
    public void testGetResourceURL(){
        resourceManager = new ResourceManager();
        String url = resourceManager.getResourceUrl("XMLData").toString();
        Assert.assertEquals(EXPECTED_URL, url);
    }

    @Test
    public void testGetFileAsString(){
        String fileExpected =   "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n" +
                                "<?xml-stylesheet type=\"text/xsl\" href=\"style.xsl\"?>\r\n" +
                                "<requisitionInfo>\r\n" +
                                "    <base_amount>30000.0</base_amount>\r\n" +
                                "    <description>Vasos plasticos</description>\r\n" +
                                "    <email>example@exaple.com</email>\r\n" +
                                "    <limit_date>2015-11-24T15:23:21.263-06:00</limit_date>\r\n" +
                                "    <quantity>2.0</quantity>\r\n" +
                                "    <shipping_address>Tres Rios</shipping_address>\r\n" +
                                "    <title>Hogar</title>\r\n" +
                                "</requisitionInfo>\r\n";

        resourceManager = new ResourceManager();
        String fileAsString = resourceManager.getFileAsString("XMLData");
        Assert.assertEquals(fileExpected, fileAsString);
    }
}
