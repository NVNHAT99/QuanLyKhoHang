/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Nhat
 */
public class DAL_PayMoney extends DAL {

    public boolean Insert(int CompanyOrderId, int EmployeerId, String TimeStamp, int SupplierId,
            double PayMoney, String Description, Connection _Connection) throws SQLException {
        boolean result = false;
        Connection Cnn = _Connection;
        String sql = "insert into companypayfororder(CompanyOrderId,EmployeerId ,TimeStamp,SupplierId,PayMoney,Description) VALUES (?,?,?,?,?,?)";
        preparedStatement = Cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1, CompanyOrderId);
        preparedStatement.setInt(2, EmployeerId);
        preparedStatement.setString(3, TimeStamp);
        preparedStatement.setInt(4, SupplierId);
        preparedStatement.setDouble(5, PayMoney);
        preparedStatement.setString(6, Description);
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) {
            result = true;
        }
        return result;
    }
}
