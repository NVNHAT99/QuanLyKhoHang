/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_CompanyOrderDetail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class DAL_CompanyOrderDetail extends DAL {

    // get all product buy of one order with company Oder by ID
    public ArrayList<DTO_CompanyOrderDetail> GetCompanyOrderDetailById(int CompanyOderId) throws SQLException {

        ArrayList<DTO_CompanyOrderDetail> result = new ArrayList<DTO_CompanyOrderDetail>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select * "
                    + " from companyorderdetails where CompanyOrderId=? ";
            preparedStatement = connection.prepareStatement(sqlFind);
            preparedStatement.setInt(1, CompanyOderId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTO_CompanyOrderDetail companyOrder = new DTO_CompanyOrderDetail();
                companyOrder.setCompanyOrderId(resultSet.getInt(1));
                companyOrder.setProductId(resultSet.getInt(2));
                companyOrder.setProductUnit(resultSet.getString(3));
                companyOrder.setQuantity(resultSet.getInt(4));
                companyOrder.setCost(resultSet.getDouble(5));
                companyOrder.setDescription(resultSet.getString(6));
                result.add(companyOrder);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            connection.close();
        }
        return result;
    }

    public void Insert(int CompanyOrderId, int ProductId, double Quantity, double Cost,
            String Description, String ProductUnit, Connection _Connection) throws SQLException {
        Connection Cnn = _Connection;
        String sql = "insert into CompanyOrderDetails(CompanyOrderId,ProductId,ProductUnit,Quantity,Cost,Description) VALUES (?,?,?,?,?,?)";
        preparedStatement = Cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1, CompanyOrderId);
        preparedStatement.setInt(2, ProductId);
        preparedStatement.setString(3, ProductUnit);
        preparedStatement.setDouble(4, Quantity);
        preparedStatement.setDouble(5, Cost);
        preparedStatement.setString(6, Description);
        preparedStatement.executeUpdate();

    }

    public double Getquantity(int CompanyOderId) throws SQLException {
        int result = 0;
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select  Quantity "
                    + " from companyorderdetails where CompanyOrderId = ? ";
            preparedStatement = connection.prepareStatement(sqlFind);
            preparedStatement.setInt(1, CompanyOderId);
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

    public void Update(int CompanyOrderId, int ProductId, double NewQuantity, double Cost,
            String Description, String ProductUnit, Connection _Connection) throws SQLException {
        Connection Cnn = _Connection;
        String sql = "Update CompanyOrderDetails SET ProductUnit = ?,Quantity = ?, Cost = ?, Description = ? Where CompanyOrderId =? and ProductId = ?";
        preparedStatement = Cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, ProductUnit);
        preparedStatement.setDouble(2, NewQuantity);
        preparedStatement.setDouble(3, Cost);
        preparedStatement.setString(4, Description);
        preparedStatement.setInt(5, CompanyOrderId);
        preparedStatement.setInt(6, ProductId);
        preparedStatement.executeUpdate();

    }

    public void Delete(int CompanyOrderId, int ProductId, Connection _Connection) throws SQLException {
        Connection Cnn = _Connection;
        String sql = "DELETE FROM CompanyOrderDetails Where CompanyOrderId =? and ProductId = ?";
        preparedStatement = Cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1, CompanyOrderId);
        preparedStatement.setInt(2, ProductId);
        preparedStatement.executeUpdate();

    }
}
