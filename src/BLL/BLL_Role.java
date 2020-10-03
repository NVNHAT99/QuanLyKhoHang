/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_Role;
import DTO.DTO_Permissions;
import DTO.DTO_Role;
import GUI.GUI_Role;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
    
    public void InsertNewRoleWithPermission(DTO_Role NewRole, ArrayList<DTO_Permissions> permissionses) throws SQLException {
        if(NewRole.getName().equals("")){
            JOptionPane.showMessageDialog(null,"Ten Role Khong duoc phep de trong");
        }else{
            if(dAL_Role.InsertNewRoleWithPermission(NewRole, permissionses)){
                JOptionPane.showMessageDialog(null,"Them Role Moi Thanh Cong");
            }
            else{
                JOptionPane.showMessageDialog(null,"Them Role Moi That Bai");
            }
        }
    }
    
    public void UpdateRoleWithPermission(DTO_Role NewRole, ArrayList<DTO_Permissions> permissionses) throws SQLException {
        if(NewRole.getName().equals("")){
            JOptionPane.showMessageDialog(null,"Ten Role Khong duoc phep de trong");
        }else{
            if(dAL_Role.UpdateRoleWithPermission(NewRole, permissionses)){
                JOptionPane.showMessageDialog(null,"Cap Nhap Thanh Cong");
            }
            else{
                JOptionPane.showMessageDialog(null,"Cap Nhap That Bai");
            }
        }
    }
    public void Delete(int RoleId,GUI_Role gui_role) throws SQLException {
        if(dAL_Role.DeleteWithPermissions(RoleId)){
            gui_role.LoadListRole();
            gui_role.Setdefault();
            JOptionPane.showMessageDialog(null,"Xoa Role Thanh Cong");
        }else{
            JOptionPane.showMessageDialog(null, "xoa Role That Bai");
        }
    }
    
}
