/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_Permissions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nhat
 */
public class DAL_Permissions extends DAL {

    public ArrayList<DTO_Permissions> GetPermissionsByRoleId(int RoleId) throws SQLException {

        ArrayList<DTO_Permissions> result = new ArrayList<DTO_Permissions>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select ModelId,AllowInsert,AllowSelect,AllowUpdate,AllowDelete "
                    + "from permissions  Where RoleId = ?  ";
            preparedStatement = connection.prepareStatement(sqlFind);
            preparedStatement.setInt(1, RoleId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTO_Permissions permissions = new DTO_Permissions();
                permissions.setRoleId(RoleId);
                permissions.setModelId(resultSet.getInt(1));
                permissions.setAllowInsert(resultSet.getBoolean(2));
                permissions.setAllowSelect(resultSet.getBoolean(3));
                permissions.setAllowUpdate(resultSet.getBoolean(4));
                permissions.setAllowDelete(resultSet.getBoolean(5));
                result.add(permissions);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            connection.close();
        }
        return result;
    }

    public boolean Insert(int RoleId, int ModelId, Boolean AllowInsert, Boolean AllowSelect, Boolean AllowUpdate,
            Boolean AllowDelete, Connection _Connection) throws SQLException {
        boolean result = false;
        Connection cnn = _Connection;
        String sql = "Insert into Permissions(RoleId,ModelId,AllowInsert,AllowSelect,AllowUpdate,AllowDelete) "
                + " values(?,?,?,?,?,?) ";
        preparedStatement = cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1, RoleId);
        preparedStatement.setInt(2, ModelId);
        preparedStatement.setBoolean(3, AllowInsert);
        preparedStatement.setBoolean(4, AllowSelect);
        preparedStatement.setBoolean(5, AllowUpdate);
        preparedStatement.setBoolean(6, AllowDelete);
        int rs = preparedStatement.executeUpdate();

        if (rs > 0) {
            result = true;
        }
        return result;
    }

    public boolean Update(int RoleId, int ModelId, Boolean AllowInsert, Boolean AllowSelect, Boolean AllowUpdate,
            Boolean AllowDelete, Connection _Connection) throws SQLException {

        boolean result = false;
        Connection cnn = _Connection;
        String sql = "Update Permissions Set AllowInsert=?,AllowSelect=?,AllowUpdate=?,AllowDelete=? where RoleId=? and ModelId=?";

        preparedStatement = cnn.prepareStatement(sql);

        preparedStatement.setBoolean(1, AllowInsert);
        preparedStatement.setBoolean(2, AllowSelect);
        preparedStatement.setBoolean(3, AllowUpdate);
        preparedStatement.setBoolean(4, AllowDelete);
        preparedStatement.setInt(5, RoleId);
        preparedStatement.setInt(6, ModelId);
        int rs = preparedStatement.executeUpdate();

        if (rs > 0) {
            result = true;
        }
        return result;
    }

    public boolean Delete(int RoleId,Connection _Connection) throws SQLException {
        boolean result = false;
        Connection cnn = _Connection;
        String sql = "Delete from Permissions Where RoleId = ? ";

        preparedStatement = cnn.prepareStatement(sql);

        preparedStatement.setInt(1, RoleId);
        int rs = preparedStatement.executeUpdate();

        if (rs > 0) {
            result = true;
        }
        return result;

        
    }

}
