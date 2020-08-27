/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_Category;
import DTO.DTO_Supplier;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nhat
 */
public class DAL_Supplier extends DAL {

    public ArrayList<DTO_Supplier> FindBySupplierName(String SupplierName) throws SQLException {
        ArrayList<DTO_Supplier> result = new ArrayList<DTO_Supplier>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select Id,Name from Suppliers where Name = ?";
            preparedStatement = connection.prepareStatement(sqlFind);

            preparedStatement.setString(1, SupplierName);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTO_Supplier supplier = new DTO_Supplier();
                supplier.setId(resultSet.getInt(1));
                supplier.setName(resultSet.getString(2));
                result.add(supplier);
            }
        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }

    public ArrayList<DTO_Supplier> GetAllSuppliers() throws SQLException {

        ArrayList<DTO_Supplier> result = new ArrayList<DTO_Supplier>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select * "
                    + "from Suppliers where IsDelete != 1";
            preparedStatement = connection.prepareStatement(sqlFind);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTO_Supplier supplier = new DTO_Supplier();
                supplier.setId(resultSet.getInt(1));
                supplier.setName(resultSet.getString(3));
                supplier.setPhoneNumber(resultSet.getString(2));
                supplier.setEmail(resultSet.getString(4));
                supplier.setAddress(resultSet.getString(5));
                result.add(supplier);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);

        } finally {
            connection.close();
        }
        return result;
    }
    
    public ArrayList<DTO_Supplier> GetAllSuppliers_ID_Name() throws SQLException {

        ArrayList<DTO_Supplier> result = new ArrayList<DTO_Supplier>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select Id,Name"
                    + "from Suppliers where IsDelete != 1";
            preparedStatement = connection.prepareStatement(sqlFind);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTO_Supplier supplier = new DTO_Supplier();
                supplier.setId(resultSet.getInt(1));
                supplier.setName(resultSet.getString(2));
                result.add(supplier);
            }
        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }
}
