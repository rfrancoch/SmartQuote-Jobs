package main.java.com.pernix.smartquote.services;

import main.java.com.pernix.smartquote.models.RequisitionInfo;
import main.java.com.pernix.smartquote.mysql.connector.MySqlConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import static main.java.com.pernix.smartquote.constants.MySQLConstant.*;

public class MySqlService {

    private static final String SELECT_NEW_REQUISITIONS =   "SELECT r.id, r.description, r.shipping_address, r.quantity, r.base_amount, r.limit_date, c.title, u.email \n" +
                                                            "FROM requisitions r, subscription s, user u, category c \n" +
                                                            "WHERE r.status = 0 \n" +
                                                            "and r.isnotified_open = 0\n" +
                                                            "and u.id = s.id_user\n" +
                                                            "and c.id = r.id_category /* only for the description of the category */\n" +
                                                            "and r.id_category = s.id_category;";

    private static final String UPDATE_REQUISITION = "update requisitions set isnotified_open = b'0' where id = ";
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
    public HashMap<String, ArrayList<RequisitionInfo>> getNewRequisitions(){
        ResultSet selectResult = selectNewRequisitions();
        HashMap<String, ArrayList<RequisitionInfo>>  requisitionsRetrieved = new HashMap<>();
        ArrayList<RequisitionInfo> requisitionInfos;
        try {
            while (selectResult.next()){
                RequisitionInfo requisitionInfo = getRequisitionObjectFromResultSet(selectResult);
                if(requisitionsRetrieved.get(requisitionInfo.getTitle()) == null){
                    requisitionInfos = new ArrayList<>();
                    requisitionInfos.add(requisitionInfo);
                    requisitionsRetrieved.put(requisitionInfo.getTitle(), requisitionInfos);
                }
                requisitionsRetrieved.get(requisitionInfo.getTitle()).add(requisitionInfo);
            }
        } catch (SQLException e) {
            logger.error("SQLException at getNewRequsitions in MySqlService " + e.getMessage());
        }
        return requisitionsRetrieved;
    }

    private RequisitionInfo getRequisitionObjectFromResultSet(ResultSet resultSet) throws SQLException {
        RequisitionInfo requisition = new RequisitionInfo();

        requisition.setRequisition_id(resultSet.getInt(REQUISITION_ID));
        requisition.setDescription(resultSet.getString(DESCRIPTION));
        requisition.setShipping_address(resultSet.getString(SHIPPING_ADDRESS));
        requisition.setQuantity(resultSet.getDouble(QUANTITY));
        requisition.setBase_amount(resultSet.getDouble(BASE_AMOUNT));
        requisition.setLimit_date(resultSet.getDate(LIMIT_DATE));
        requisition.setTitle(resultSet.getString(TITLE));
        requisition.setEmail(resultSet.getString(EMAIL));

        return requisition;
    }

    public void setRequisitionNotified(int id){
        PreparedStatement statement = null;
        try {
            statement = mySqlConnector.getMysqlConnection().prepareStatement(UPDATE_REQUISITION + id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error at setRequisitionNotified in MySqlService " + e.getMessage());
        }
    }
}
