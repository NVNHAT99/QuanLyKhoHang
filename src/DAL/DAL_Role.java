/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_Permissions;
import DTO.DTO_Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nhat
 */
public class DAL_Role extends DAL {

    static DAL_Permissions dAL_Permissions = new DAL_Permissions();
    static DAL_Employee dAL_Employee = new DAL_Employee();

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

    public int Insert(String RoleName, String description, Connection _Connection) throws SQLException {
        int result = -1;
        Connection cnn = _Connection;
        String sql = "Insert into roles(Name,Description) values(?,?)";
        preparedStatement = cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, RoleName);
        preparedStatement.setString(2, description);
        int rs = preparedStatement.executeUpdate();

        if (rs > 0) {
            ResultSet key = preparedStatement.getGeneratedKeys();
            if (key.next()) {
                result = key.getInt(1);
            }
        }
        return result;
    }

    public boolean InsertNewRoleWithPermission(DTO_Role NewRole, ArrayList<DTO_Permissions> permissionses) throws SQLException {
        try {
            connection = dbUltils.Get_connection();
            connection.setAutoCommit(false);
            int RoleId = Insert(NewRole.getName(), NewRole.getDescription(), connection);
            for (int i = 0; i < 8; i++) {
                dAL_Permissions.Insert(RoleId, i + 1, permissionses.get(i).isAllowInsert(),
                        permissionses.get(i).isAllowSelect(), permissionses.get(i).isAllowUpdate(),
                        permissionses.get(i).isAllowDelete(), connection);
            }
            connection.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            connection.rollback();
            return false;

        } finally {
            connection.close();
        }
        return true;
    }

    public boolean Update(int RoleId, String RoleName, String description, Connection _Connection) throws SQLException {

        boolean result = false;
        Connection cnn = _Connection;
        String sql = "Update  Roles set Name=?,Description=? where Id=?";
        preparedStatement = cnn.prepareStatement(sql);
        preparedStatement.setString(1, RoleName);
        preparedStatement.setString(2, description);
        preparedStatement.setInt(3, RoleId);
        int rs = preparedStatement.executeUpdate();

        if (rs > 0) {
            result = true;
        }
        return result;
    }

    public boolean UpdateRoleWithPermission(DTO_Role NewRole, ArrayList<DTO_Permissions> permissionses) throws SQLException {
        try {
            connection = dbUltils.Get_connection();
            connection.setAutoCommit(false);
            Update(NewRole.getId(), NewRole.getName(), NewRole.getDescription(), connection);
            for (int i = 0; i < 8; i++) {
                dAL_Permissions.Update(NewRole.getId(), i + 1, permissionses.get(i).isAllowInsert(),
                        permissionses.get(i).isAllowSelect(), permissionses.get(i).isAllowUpdate(),
                        permissionses.get(i).isAllowDelete(), connection);
            }
            connection.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            connection.rollback();
            return false;

        } finally {
            connection.close();
        }
        return true;
    }

    public boolean Delete(int RoleId, Connection _Connection) throws SQLException {

        boolean result = false;
        Connection cnn = _Connection;
        String sql = "Delete from Roles Where Id = ? ";
        preparedStatement = cnn.prepareStatement(sql);
        preparedStatement.setInt(1, RoleId);
        int rs = preparedStatement.executeUpdate();

        if (rs > 0) {
            result = true;
        }
        return result;

    }
    public boolean DeleteWithPermissions(int RoleId) throws SQLException {

        try {
            connection = dbUltils.Get_connection();
            connection.setAutoCommit(false);
            dAL_Employee.UpdateByDeleteRole(RoleId,connection);
            dAL_Permissions.Delete(RoleId, connection);
            Delete(RoleId, connection);
            connection.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            connection.rollback();
            return false;

        } finally {
            connection.close();
        }
        return true;

    }
    

}
