/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.Custom_DTO.CustomDTO_Product;
import DTO.DTO_Product;
import DTO.DTO_employee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import ultils.CustomCombo;

/**
 *
 * @author Nhat
 */
public class DAL_Product extends DAL {

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

    public boolean CheckProductNameExist(String ProductName) throws SQLException {
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select Id,Name,Price,SupplierId,CategoryId,Unit,UnitsInStock,ImagePath "
                    + "from products where Name = ?";
            preparedStatement = connection.prepareStatement(sqlFind);
            preparedStatement.setString(1, ProductName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return false;
    }

    public ArrayList<DTO_Product> GetAllProductsNotDelete() throws SQLException {

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

    public ArrayList<CustomDTO_Product> GetAllProduct_ForProductTable() throws SQLException {

        ArrayList<CustomDTO_Product> result = new ArrayList<CustomDTO_Product>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select prt.Id,prt.Name,prt.Price,prt.SupplierId,supp.Name,prt.CategoryId,"
                    + "cate.Name,prt.Unit,prt.UnitsInStock,prt.ImagePath "
                    + "FROM (products prt JOIN suppliers supp on(supp.Id=prt.SupplierId)) "
                    + "JOIN categories cate on(cate.Id = prt.CategoryId)"
                    + " WHERE prt.IsDelete != 1 ";
            preparedStatement = connection.prepareStatement(sqlFind);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CustomDTO_Product product_model = new CustomDTO_Product();
                product_model.setId(resultSet.getInt(1));
                product_model.setName(resultSet.getString(2));
                product_model.setPrice(resultSet.getFloat(3));
                product_model.setSupplier(new CustomCombo(resultSet.getInt(4), resultSet.getString(5)));
                product_model.setCategory(new CustomCombo(resultSet.getInt(6), resultSet.getString(7)));
                product_model.setUnit(resultSet.getString(8));
                product_model.setUnitsInStock(resultSet.getInt(9));
                product_model.setImagePath(resultSet.getString(10));
                result.add(product_model);
            }
        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }

    public boolean Insert(String Name, double Price, int SupplierId, int CategoryId, String Unit, int UnitsInStock, String ImagePath) throws SQLException {
        boolean result = false;
        try {
            connection = dbUltils.Get_connection();
            String sql = "Insert into products(Name,Price,SupplierId,CategoryId,Unit,UnitsInStock,ImagePath) values(?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, Name);
            preparedStatement.setDouble(2, Price);
            preparedStatement.setInt(3, SupplierId);
            preparedStatement.setInt(4, CategoryId);
            preparedStatement.setString(5, Unit);
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
