package main.java.com.pernix.smartquote.mysql.connector;
import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlConnector {

    public static final String MYSQL_URL = "jdbc:mysql://localhost/smartquote?user=root&password=root";
    public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
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
            mysqlConnection = DriverManager.getConnection(MYSQL_URL);
        } catch (SQLException e) {
            logger.error("SQL Exception at connectToDb in MySQLConnector: " + e.getMessage());
        }
    }

    public void loadMySqlDriver(){
        try {
            Class.forName(MYSQL_DRIVER);
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException at loadMySqlDriver in MySQLConnector: " + e.getMessage());
        }
    }


}