/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_Category;
import DTO.DTO_Category;
import DTO.DTO_Supplier;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nhat
 */
public class BLL_Category {

    DAL_Category dal_catgory = new DAL_Category();

    public ArrayList<DTO_Category> GetAllCatgory() throws SQLException {
        return dal_catgory.GetAllCatgory();
    }

    public ArrayList<DTO_Category> GetAllCategory_ID_Name() throws SQLException {

        return dal_catgory.GetAllCategory_ID_Name();
    }

    public boolean CheckCategoryNameExist(String CategoryName) throws SQLException {
        return dal_catgory.CheckCategoryNameExist(CategoryName);
    }

    public void Insert(String Name, String Description) throws SQLException {
        if (!CheckCategoryNameExist(Name)) {
            try {
                if (dal_catgory.Insert(Name, Description)) {
                    JOptionPane.showMessageDialog(null, "Them loai san pham thanh cong");
                } else {
                    JOptionPane.showMessageDialog(null, "Them loai san pham that bai");
                }
            } catch (Exception e) {
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ten Loai San Pham Da Ton Tai");
        }
    }

    public void Update(int Id, String Name, String Description) throws SQLException {
        try {
            if (dal_catgory.Update(Id, Name, Description)) {
                JOptionPane.showMessageDialog(null, "Cap Nhap Loai san pham thanh cong");
            } else {
                JOptionPane.showMessageDialog(null, "Cap Nhap Loai san pham that bai");
            }
        } catch (Exception e) {
        }
    }

    public void Delete(int ID) throws SQLException {
        try {
            if (dal_catgory.Delete(ID)) {
                JOptionPane.showMessageDialog(null, "Xoa Loai San Pham thanh cong");
                //need reload table when delete succses dal_catgory.Load();
            } else {
                JOptionPane.showMessageDialog(null, "xoa Loai San Pham that bai ");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
