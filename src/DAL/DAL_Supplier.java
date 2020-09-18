/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_Category;
import DTO.DTO_Supplier;
import java.sql.PreparedStatement;
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
            String sqlFind = "Select Id,Name from Suppliers where Name = ? And IsDelete!=1";
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
    public ArrayList<DTO_Supplier> FindBySupplierEmail(String SupplierEmail) throws SQLException {
        ArrayList<DTO_Supplier> result = new ArrayList<DTO_Supplier>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select Id,Name,Email from Suppliers where Email = ? And IsDelete!=1";
            preparedStatement = connection.prepareStatement(sqlFind);

            preparedStatement.setString(1, SupplierEmail);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTO_Supplier supplier = new DTO_Supplier();
                supplier.setId(resultSet.getInt(1));
                supplier.setName(resultSet.getString(2));
                supplier.setEmail(resultSet.getString(3));
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
            String sqlFind = "Select Suppliers.Id,Suppliers.Name "
                    + " from Suppliers where IsDelete != 1 ";
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
    public boolean Insert(String Name, String Address, String Email, String PhoneNumber) throws SQLException {
        boolean result = false;
        try {
            connection = dbUltils.Get_connection();
            String sql = "Insert into suppliers(Name,Address,Email ,PhoneNumber) values(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, Name);
            preparedStatement.setString(2, Address);
            preparedStatement.setString(3, Email);
            preparedStatement.setString(4, PhoneNumber);

            int rs = preparedStatement.executeUpdate();

            if (rs > 0) {
                result = true;
            }

        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }

    public boolean Update(int ID, String Name, double Price, int SupplierId, int CategoryId, String Unit, int UnitsInStock, String ImagePath) throws SQLException {
        boolean result = false;
        try {
            connection = dbUltils.Get_connection();
            String sql = "Update products SET Name = ?,Price = ?,SupplierId = ?,CategoryId = ?,Unit = ?,UnitsInStock = ?,ImagePath = ? WHERE Id = ? ";
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, Name);
            preparedStatement.setDouble(2, Price);
            preparedStatement.setInt(3, SupplierId);
            preparedStatement.setInt(4, CategoryId);
            preparedStatement.setString(5, Unit);
            preparedStatement.setInt(6, UnitsInStock);
            preparedStatement.setString(7, ImagePath);
            preparedStatement.setInt(8, ID);
            int rs = preparedStatement.executeUpdate();

            if (rs > 0) {
                result = true;
            }

        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }
    
    public boolean Delete(int ID) throws SQLException{
        boolean result = false;
        try {
            connection = dbUltils.Get_connection();
            String sql = "Update products SET IsDelete = 1 WHERE Id = ?";
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            
            preparedStatement.setInt(1, ID);
            int rs = preparedStatement.executeUpdate();

            if (rs > 0) {
                result = true;
            }

        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }
}
