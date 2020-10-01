/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Nhat
 */
public class DAL_CollectMoney extends DAL{
    public boolean Insert(int CustomerOrderId, int EmployeerId, String TimeStamp, int CustomerId,
            double PayMoney, String Description, Connection _Connection) throws SQLException {
        boolean result = false;
        Connection Cnn = _Connection;
        String sql = "insert into companypayfororder(CustomerOrderId,EmployeerId ,TimeStamp,CustomerId,PayMoney,Description) VALUES (?,?,?,?,?,?)";
        preparedStatement = Cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1, CustomerOrderId);
        preparedStatement.setInt(2, EmployeerId);
        preparedStatement.setString(3, TimeStamp);
        preparedStatement.setInt(4, CustomerId);
        preparedStatement.setDouble(5, PayMoney);
        preparedStatement.setString(6, Description);
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) {
            result = true;
        }
        return result;
    }
}
