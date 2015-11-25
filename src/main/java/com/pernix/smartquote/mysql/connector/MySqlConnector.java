package main.java.com.pernix.smartquote.mysql.connector;
import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlConnector {

    private Connection mysqlConnection;
    private final Logger logger = LoggerFactory.getLogger(MySqlConnector.class);

    public MySqlConnector(Connection mysqlConnection) {
        this.mysqlConnection = mysqlConnection;
    }

    public MySqlConnector(){
        mysqlConnection = null;
        connectToDb();
    }

    public Connection getMysqlConnection() {
        return mysqlConnection;
    }

    public void setMysqlConnection(Connection mysqlConnection) {
        this.mysqlConnection = mysqlConnection;
    }

    public void connectToDb() {
        loadMySqlDriver();
        try {
            mysqlConnection = DriverManager.getConnection("jdbc:mysql://localhost/smartquote?user=root&password=root");
        } catch (SQLException e) {
            logger.error("SQL Exception at connectToDb in MySQLConnector: " + e.getMessage());
        }
    }

    public void loadMySqlDriver(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException at loadMySqlDriver in MySQLConnector: " + e.getMessage());
        }
    }


}