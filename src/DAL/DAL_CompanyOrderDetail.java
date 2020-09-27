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
                    + "from companyorderdetails where IsDelete!=1 and ";
            preparedStatement = connection.prepareStatement(sqlFind);

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
            JOptionPane.showMessageDialog(null,e);

        } finally {
            connection.close();
        }
        return result;
    }

    public void Insert(int CompanyOrderId, int ProductId, double Quantity, double Cost,
            String Description, String ProductUnit, Connection _Connection) throws SQLException {
        connection = _Connection;
        String sql = "insert into CompanyOrderDetails(CompanyOrderId,ProductId,ProductUnit,Quantity,Cost,Description) VALUES (?,?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1, CompanyOrderId);
        preparedStatement.setInt(2, ProductId);
        preparedStatement.setString(3, ProductUnit);
        preparedStatement.setDouble(4, Quantity);
        preparedStatement.setDouble(5, Cost);
        preparedStatement.setString(6, Description);
        preparedStatement.executeUpdate();
        
    }
}
