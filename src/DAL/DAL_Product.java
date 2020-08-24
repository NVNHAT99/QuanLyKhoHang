/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_Product;
import DTO.DTO_employee;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nhat
 */
public class DAL_Product extends DAL{
    
    public ArrayList<DTO_Product> FindByProductName(String productName) throws SQLException {
        ArrayList<DTO_Product> result = new ArrayList<DTO_Product>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select Id ,Name from products where Name = ?";
            preparedStatement = connection.prepareStatement(sqlFind);

            preparedStatement.setString(1, productName);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTO_Product product = new DTO_Product();
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                result.add(product);
            }
        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }
    
    public  ArrayList<DTO_Product> GetAllProductsNotDelete() throws SQLException{
        
        ArrayList<DTO_Product> result = new ArrayList<DTO_Product>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select Id,Name,Price,SupplierId,CategoryId,Unit,UnitsInStock,ImagePath "
                    + "from products where IsDelete != 1";
            preparedStatement = connection.prepareStatement(sqlFind);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTO_Product product = new DTO_Product();
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getFloat(3));
                product.setSupplierId(resultSet.getInt(4));
                product.setCategoryId(resultSet.getInt(5));
                product.setUnit(resultSet.getString(6));
                product.setUnitsInStock(resultSet.getInt(7));
                product.setImagePath(resultSet.getString(8));
                result.add(product);
            }
        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }
    
    public boolean Insert(String Name, float Price,int SupplierId,int CategoryId,int Unit,int UnitsInStock,String ImagePath) throws SQLException {
        boolean result = false;
        try {
            connection = dbUltils.Get_connection();
            String sql = "Insert into products(Id,Name,Price,SupplierId,CategoryId,Unit,UnitsInStock,ImagePath) values(?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, Name);
            preparedStatement.setFloat(2, Price);
            preparedStatement.setInt(3, SupplierId);
            preparedStatement.setInt(4, CategoryId);
            preparedStatement.setInt(5, Unit);
            preparedStatement.setInt(6, UnitsInStock);
            preparedStatement.setString(7, ImagePath);

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
