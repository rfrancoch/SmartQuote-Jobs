package test.java.com.pernix.smartquote.mysql.connector;
import org.junit.Assert;
import org.junit.Test;

import main.java.com.pernix.smartquote.mysql.connector.MySqlConnector;

public class MySqlConnectorTest {

    private MySqlConnector mySqlConnector;

    @Test
    public void testLoadMySQLDriver() throws ClassNotFoundException{
        mySqlConnector = new MySqlConnector();
        mySqlConnector.loadMySqlDriver();
    }

    @Test
    public void testGetConnection(){
        mySqlConnector = new MySqlConnector();
        java.sql.Connection connection = mySqlConnector.getMysqlConnection();
        Assert.assertNotNull(connection);
    }

    @Test
    public void testConnectToDb(){
        mySqlConnector = new MySqlConnector();
        mySqlConnector.connectToDb();
        Assert.assertNotNull(mySqlConnector.getMysqlConnection());
    }
}
