package main.java.com.pernix.smartquote.services;

import main.java.com.pernix.smartquote.models.RequisitionInfo;
import main.java.com.pernix.smartquote.mysql.connector.MySqlConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static main.java.com.pernix.smartquote.constants.MySQLConstant.*;

public class MySqlService {

    public static final String SELECT_NEW_REQUISITIONS =    "SELECT r.description, r.shipping_address, r.quantity, r.base_amount, r.limit_date, c.title, u.email \n" +
                                                            "FROM requisitions r, subscription s, user u, category c \n" +
                                                            "WHERE r.status = 0 \n" +
                                                            "and r.isnotified_open = 0\n" +
                                                            "and u.id = s.id_user\n" +
                                                            "and c.id = r.id_category /* only for the description of the category */\n" +
                                                            "and r.id_category = s.id_category;";
    private MySqlConnector mySqlConnector;
    private final Logger logger = LoggerFactory.getLogger(MySqlService.class);


    public MySqlService(MySqlConnector mySqlConnector) {
        this.mySqlConnector = mySqlConnector;
    }

    public MySqlService(){
        mySqlConnector =  new MySqlConnector();
    }

    public ResultSet selectNewRequisitions () {
        Statement statement = null;
        ResultSet selectResult = null;

        try {
            statement = mySqlConnector.getMysqlConnection().createStatement();
            selectResult = statement.executeQuery(SELECT_NEW_REQUISITIONS);
        } catch (SQLException e) {
            logger.error("SQLException at selectNewRequisitions in MySqlService " + e.getMessage());
        }
        return selectResult;
    }
    public ArrayList<RequisitionInfo> getNewRequisitions(){
        ResultSet selectResult = selectNewRequisitions();
        ArrayList<RequisitionInfo> requisitionsRetrieved = new ArrayList<>();

        try {
            while (selectResult.next()){
                requisitionsRetrieved.add(getRequisitionObjectFromResultSet(selectResult));
            }
        } catch (SQLException e) {
            logger.error("SQLException at getNewRequsitions in MySqlService " + e.getMessage());
        }
        return requisitionsRetrieved;
    }

    private RequisitionInfo getRequisitionObjectFromResultSet(ResultSet resultSet) throws SQLException {
        RequisitionInfo requisition = new RequisitionInfo();

        requisition.setDescription(resultSet.getString(DESCRIPTION));
        requisition.setShipping_address(resultSet.getString(SHIPPING_ADDRESS));
        requisition.setQuantity(resultSet.getDouble(QUANTITY));
        requisition.setBase_amount(resultSet.getDouble(BASE_AMOUNT));
        requisition.setLimit_date(resultSet.getDate(LIMIT_DATE));
        requisition.setTitle(resultSet.getString(TITLE));
        requisition.setEmail(resultSet.getString(EMAIL));

        return requisition;
    }
}
