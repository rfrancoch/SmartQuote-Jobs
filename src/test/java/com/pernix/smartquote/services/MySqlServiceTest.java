package test.java.com.pernix.smartquote.services;

import main.java.com.pernix.smartquote.models.RequisitionInfo;
import main.java.com.pernix.smartquote.mysql.connector.MySqlConnector;
import main.java.com.pernix.smartquote.services.MySqlService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySqlServiceTest {

    private MySqlConnector mySqlConnector;
    private MySqlService mySqlService;

    @Before
    public void initializeMySqlService(){
        mySqlConnector = new MySqlConnector();
        mySqlService = new MySqlService(mySqlConnector);
    }

    @Test
    public void testSelectNewRequisitions() {
        ResultSet selectResult = mySqlService.selectNewRequisitions();
        Assert.assertNotNull(selectResult);
    }

    @Test
    public void testGetNewRequisitions(){
        ArrayList<RequisitionInfo> requisitionsRetrieved = mySqlService.getNewRequisitions();
        Assert.assertTrue(requisitionsRetrieved.size() > 0);
    }

}
