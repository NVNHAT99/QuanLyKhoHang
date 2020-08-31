/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_Category;
import DTO.DTO_Product;
import DTO.DTO_Supplier;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nhat
 */
public class DAL_Category extends DAL{
    
        
    public  ArrayList<DTO_Category> GetAllCatgory() throws SQLException{
        
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
