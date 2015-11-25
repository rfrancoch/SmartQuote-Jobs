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
    public void testSelectNewRequisitions(){
        ResultSet selectResult = mySqlService.selectNewRequisitions();
        Assert.assertNotNull(selectResult);
    }

    public void testGetRequisitionObjectFromResultSet() throws SQLException {

        ResultSet mockedResultSet = Mockito.mock(ResultSet.class);
        Mockito.when(mockedResultSet.getInt("id")).thenReturn(1);
        Mockito.when(mockedResultSet.getString("description")).thenReturn("Monitores");
        Mockito.when(mockedResultSet.getString("shipping_address")).thenReturn("Tres Rios, Cartago");
        Mockito.when(mockedResultSet.getDouble("quantity")).thenReturn(10.0);
        Mockito.when(mockedResultSet.getDouble("base_amount")).thenReturn(2000000.0);
        Mockito.when(mockedResultSet.getDate("limit_date")).thenReturn(Date.valueOf("2015/08/06 15:59:48"));
        Mockito.when(mockedResultSet.getString("title")).thenReturn("Electronicos");
        Mockito.when(mockedResultSet.getString("email")).thenReturn("example@example.com");
    }

    @Test
    public void testGetNewRequisitions(){
        ArrayList<RequisitionInfo> requisitionsRetrieved = mySqlService.getNewRequisitions();
        Assert.assertTrue(requisitionsRetrieved.size() > 0);
    }

}
