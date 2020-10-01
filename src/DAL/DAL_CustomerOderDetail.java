/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_CustomerOrderDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nhat
 */
public class DAL_CustomerOderDetail extends DAL{
    
    // get all product buy of one order with customer Oder by ID
    public ArrayList<DTO_CustomerOrderDetail> GetCustomerOderDetailById(int CustomerOrderId) throws SQLException {

        ArrayList<DTO_CustomerOrderDetail> result = new ArrayList<DTO_CustomerOrderDetail>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select * "
                    + " from customerorderdetails where CustomerOrderId = ? ";
            preparedStatement = connection.prepareStatement(sqlFind);
            preparedStatement.setInt(1, CustomerOrderId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTO_CustomerOrderDetail customerOderDetail = new DTO_CustomerOrderDetail();
                customerOderDetail.setCustomerOrderId(resultSet.getInt(1));
                customerOderDetail.setProductId(resultSet.getInt(2));
                customerOderDetail.setProductUnit(resultSet.getString(3));
                customerOderDetail.setQuantity(resultSet.getInt(4));
                customerOderDetail.setCost(resultSet.getDouble(5));
                customerOderDetail.setDescription(resultSet.getString(6));
                result.add(customerOderDetail);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            connection.close();
        }
        return result;
    }

    public void Insert(int CustomerOrderId , int ProductId, double Quantity, double Cost,
            String Description, String ProductUnit, Connection _Connection) throws SQLException {
        Connection Cnn = _Connection;
        String sql = "insert into customerorderdetails(CustomerOrderId ,ProductId,ProductUnit,Quantity,Cost,Description) VALUES (?,?,?,?,?,?)";
        preparedStatement = Cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1, CustomerOrderId);
        preparedStatement.setInt(2, ProductId);
        preparedStatement.setString(3, ProductUnit);
        preparedStatement.setDouble(4, Quantity);
        preparedStatement.setDouble(5, Cost);
        preparedStatement.setString(6, Description);
        preparedStatement.executeUpdate();

    }

    public double Getquantity(int CustomerOrderId) throws SQLException {
        int result = 0;
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select  Quantity "
                    + " from customerorderdetails where CustomerOrderId = ? ";
            preparedStatement = connection.prepareStatement(sqlFind);
            preparedStatement.setInt(1, CustomerOrderId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            connection.close();
        }
        return result;
    }

    public void Update(int CustomerOrderId, int ProductId, double NewQuantity, double Cost,
            String Description, String ProductUnit, Connection _Connection) throws SQLException {
        Connection Cnn = _Connection;
        String sql = "Update customerorderdetails SET ProductUnit = ?,Quantity = ?, Cost = ?, Description = ? Where CustomerOrderId =? and ProductId = ?";
        preparedStatement = Cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, ProductUnit);
        preparedStatement.setDouble(2, NewQuantity);
        preparedStatement.setDouble(3, Cost);
        preparedStatement.setString(4, Description);
        preparedStatement.setInt(5, CustomerOrderId);
        preparedStatement.setInt(6, ProductId);
        preparedStatement.executeUpdate();

    }

    public void Delete(int CustomerOrderId, int ProductId, Connection _Connection) throws SQLException {
        Connection Cnn = _Connection;
        String sql = "DELETE FROM customerorderdetails Where CustomerOrderId =? and ProductId = ?";
        preparedStatement = Cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1, CustomerOrderId);
        preparedStatement.setInt(2, ProductId);
        preparedStatement.executeUpdate();

    }
}
