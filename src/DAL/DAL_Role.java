/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_Role;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nhat
 */
public class DAL_Role extends DAL {

    public ArrayList<DTO_Role> GetAllRole() throws SQLException {

        ArrayList<DTO_Role> result = new ArrayList<DTO_Role>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select * "
                    + "from roles where Id!=4";
            preparedStatement = connection.prepareStatement(sqlFind);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTO_Role Role = new DTO_Role();
                Role.setId(resultSet.getInt(1));
                Role.setName(resultSet.getString(2));
                Role.setDescription(resultSet.getString(3));

                result.add(Role);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            connection.close();
        }
        return result;
    }

    public DTO_Role SelectRole(int RoleId) throws SQLException {
        DTO_Role result = new DTO_Role();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select * "
                    + "from roles where Id=? ";
            preparedStatement = connection.prepareStatement(sqlFind);
            preparedStatement.setInt(1, RoleId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result.setId(resultSet.getInt(1));
                result.setName(resultSet.getString(2));
                result.setDescription(resultSet.getString(3));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            connection.close();
        }
        return result;
    }

    public boolean Insert(String RoleName, String description) throws SQLException {
        boolean result = false;
        try {
            connection = dbUltils.Get_connection();
            String sql = "Insert into roles(Name,Description) values(?,?)";
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, RoleName);
            preparedStatement.setString(2, description);
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

    public boolean Update(int RoleId, String RoleName, String description) throws SQLException {

        boolean result = false;
        try {
            connection = dbUltils.Get_connection();
            String sql = "Update  Roles set Name=?,Description=? where Id=?";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
            preparedStatement.setString(1, RoleName);
            preparedStatement.setString(2, description);
            int rs = preparedStatement.executeUpdate();

            if (rs > 0) {
                result = true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            connection.close();
        }
        return result;
    }
    public boolean Delete(int RoleId) throws SQLException {

        boolean result = false;
        try {
            connection = dbUltils.Get_connection();
            String sql = "Delete from Roles Where Id = ? ";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
            preparedStatement.setInt(1,RoleId);
            int rs = preparedStatement.executeUpdate();

            if (rs > 0) {
                result = true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            connection.close();
        }
        return result;
    }
    
}
