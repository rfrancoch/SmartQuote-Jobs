package test.java.com.pernix.smartquote.services;

import main.java.com.pernix.smartquote.services.ServiceFactory;
import org.junit.Assert;
import org.junit.Test;

public class ServiceFactoryTest {

    @Test
    public void testGetMailService(){
        ServiceFactory serviceFactory = new ServiceFactory();
        Assert.assertNotNull(serviceFactory.getMailService());
    }

}
