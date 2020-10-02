/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_Role;
import DTO.DTO_Role;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nhat
 */
public class BLL_Role {
    static DAL_Role dAL_Role = new DAL_Role();
    
    public ArrayList<DTO_Role> GetAllRole() throws SQLException {
        return  dAL_Role.GetAllRole();
    }
    
    public DTO_Role SelectRole(int RoleId) throws SQLException {
        
        return dAL_Role.SelectRole(RoleId);
    }
    
    public boolean Insert(String RoleName, String description) throws SQLException {
       
        return dAL_Role.Insert(RoleName, description);
    }
    
    public boolean Update(int RoleId, String RoleName, String description) throws SQLException {
        return dAL_Role.Update(RoleId, RoleName, description);
    }
    public boolean Delete(int RoleId) throws SQLException {
        return dAL_Role.Delete(RoleId);
    }
    
}
