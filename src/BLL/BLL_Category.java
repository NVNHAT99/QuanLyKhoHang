/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_Category;
import DTO.DTO_Category;
import DTO.DTO_Supplier;
import GUI.GUI_Category;
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

    public boolean CheckCategoryNameExist(int Id, String CategoryName) throws SQLException {
        return dal_catgory.CheckCategoryNameExist(Id, CategoryName);
    }

    public void Insert(String Name, String Description, GUI_Category jframeGUI_Category) throws SQLException {
        if (!CheckCategoryNameExist(-1, Name)) {
            try {
                if (dal_catgory.Insert(Name, Description)) {
                    jframeGUI_Category.Load();
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

    public void Update(int Id, String Name, String Description, GUI_Category jframeGUI_Category) throws SQLException {
        try {
            if ((Name.equals(null) || Name.equals("")) || Description.equals(null) || Description.equals("")) {
                JOptionPane.showMessageDialog(null, "co du lieu con trong moi kiem tra lai");
            } else {
                if (CheckCategoryNameExist(Id, Name)) {
                    JOptionPane.showMessageDialog(null, "Ten Loai San Pham Da Ton Tai");
                } else {
                    if (dal_catgory.Update(Id, Name, Description)) {
                        jframeGUI_Category.Load();
                        JOptionPane.showMessageDialog(null, "Cap Nhap Loai san pham thanh cong");
                    } else {
                        JOptionPane.showMessageDialog(null, "Cap Nhap Loai san pham that bai");
                    }
                }
            }

        } catch (Exception e) {
        }
    }

    public void Delete(int ID, GUI_Category jframeGUI_Category) throws SQLException {
        try {
            if (dal_catgory.Delete(ID)) {
                jframeGUI_Category.Load();
                JOptionPane.showMessageDialog(null, "Xoa Loai San Pham thanh cong");
            } else {
                JOptionPane.showMessageDialog(null, "xoa Loai San Pham that bai ");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
