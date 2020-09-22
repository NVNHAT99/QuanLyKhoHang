/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_CompanyOrder;
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
public class DAL_CompanyOrder extends DAL{
    
    public ArrayList<DTO_CompanyOrder> GetAllCompaneyOder() throws SQLException {

        ArrayList<DTO_CompanyOrder> result = new ArrayList<DTO_CompanyOrder>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select * "
                    + "from CompanyOrders where IsDelete!=1";
            preparedStatement = connection.prepareStatement(sqlFind);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTO_CompanyOrder companyOrder = new DTO_CompanyOrder();
                companyOrder.setId(resultSet.getInt(1));
                companyOrder.setSupplierId(resultSet.getInt(2));
                companyOrder.setEmployeeId(resultSet.getInt(3));
                companyOrder.setTimeStamp(resultSet.getDate(4));
                companyOrder.setVAT(resultSet.getFloat(5));
                companyOrder.setCK(resultSet.getFloat(6));
                companyOrder.setTotalMoney(resultSet.getDouble(7));
                companyOrder.setHavePaid(resultSet.getDouble(8));
                companyOrder.setStillOwe(resultSet.getDouble(9));
                companyOrder.setStatus(resultSet.getBoolean(10));
                companyOrder.setDescription(resultSet.getString(11));
                companyOrder.setIsDelete(resultSet.getBoolean(12));
                result.add(companyOrder);
            }
        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }
    
    public int Insert(int SupplierId, int EmployeeId, Date TimeStamp, String Description,float VAT,float CK,double TotalMoney,double StillOwe) throws SQLException {
        int result = -1;
        try {
            connection = dbUltils.Get_connection();
            // default  RoleId for New Employeer
            String sql = "insert into CompanyOrders(SupplierId,EmployeeId,TimeStamp,VAT,CK,TotalMoney,Description,StillOwe) VALUES (?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, SupplierId);
            preparedStatement.setInt(2, EmployeeId);
            preparedStatement.setDate(3, TimeStamp);
            preparedStatement.setString(4, Description);
            preparedStatement.setFloat(5, VAT);
            preparedStatement.setFloat(6, CK);
            preparedStatement.setDouble(7, TotalMoney);
            preparedStatement.setDouble(8, StillOwe);

            int rs = preparedStatement.executeUpdate();

            if (rs > 0) {
                ResultSet key = preparedStatement.getGeneratedKeys();
                if (key.next()) {
                    result = key.getInt(1);

                }
            }

        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }
}
