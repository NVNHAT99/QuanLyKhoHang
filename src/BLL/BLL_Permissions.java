/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_Permissions;
import DTO.DTO_Permissions;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nhat
 */
public class BLL_Permissions {
    
    static DAL_Permissions dAL_Permissions = new DAL_Permissions();
    
    public ArrayList<DTO_Permissions> GetPermissionsByRoleId(int RoleId) throws SQLException {
        return dAL_Permissions.GetPermissionsByRoleId(RoleId);
    }
    
}
