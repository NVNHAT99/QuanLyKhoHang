/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_Category;
import DTO.DTO_Product;
import DTO.DTO_Supplier;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nhat
 */
public class DAL_Category extends DAL {

    public ArrayList<DTO_Category> GetAllCatgory() throws SQLException {

        ArrayList<DTO_Category> result = new ArrayList<DTO_Category>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select id,Name,Description "
                    + "from categories where IsDelete != 1";
            preparedStatement = connection.prepareStatement(sqlFind);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTO_Category category = new DTO_Category();
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                category.setDescription(resultSet.getString(3));
                result.add(category);
            }
        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }

    public boolean CheckCategoryNameExist(String CategoryName) throws SQLException {
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select id,Name,Description "
                    + "from categories where Name = ?";
            preparedStatement = connection.prepareStatement(sqlFind);
            preparedStatement.setString(1, CategoryName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            connection.close();
        }
        return false;
    }

    public boolean Insert(String Name, String Description) throws SQLException {
        boolean result = false;
        try {
            connection = dbUltils.Get_connection();
            String sql = "Insert into categories(Name,Description) values(?,?)";
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, Name);
            preparedStatement.setString(2, Description);

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

    public boolean Update(int ID, String Name, String Description) throws SQLException {
        boolean result = false;
        try {
            connection = dbUltils.Get_connection();
            String sql = "Update categories SET Name = ?,Description = ? WHERE Id = ? ";
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, Name);
            preparedStatement.setString(2, Description);
            preparedStatement.setInt(3, ID);
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

    public boolean Delete(int ID) throws SQLException {
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

    public ArrayList<DTO_Category> GetAllCategory_ID_Name() throws SQLException {

        ArrayList<DTO_Category> result = new ArrayList<DTO_Category>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select id,Name "
                    + " from categories where IsDelete != 1";
            preparedStatement = connection.prepareStatement(sqlFind);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTO_Category category = new DTO_Category();
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                result.add(category);
            }
        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }

}
