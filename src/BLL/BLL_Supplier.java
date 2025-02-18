/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_Category;
import DAL.DAL_Supplier;
import DTO.DTO_Category;
import DTO.DTO_Supplier;
import GUI.GUI_Supplier;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nhat
 */
public class BLL_Supplier implements Interface_Constant {

    DAL_Supplier dAL_Supplier = new DAL_Supplier();

    public ArrayList<DTO_Supplier> FindBySupplierName(String SupplierName) throws SQLException {
        return dAL_Supplier.FindBySupplierName(SupplierName);
    }

    public boolean CheckSupplierNameExist(int SupplierId, String SupplierName) throws SQLException {
        return dAL_Supplier.CheckSupplierNameExist(SupplierId, SupplierName);
    }

    public boolean CheckSupplierEmailExist(int SupplierId, String SupplierEmail) throws SQLException {
        return dAL_Supplier.CheckSupplierEmaiExist(SupplierId, SupplierEmail);
    }

    public ArrayList<DTO_Supplier> FindBySupplierEmail(String SupplierEmail) throws SQLException {
        return dAL_Supplier.FindBySupplierName(SupplierEmail);
    }

    public ArrayList<DTO_Supplier> GetAllSuppliers() throws SQLException {
        return dAL_Supplier.GetAllSuppliers();
    }

    public ArrayList<DTO_Supplier> GetAllSuppliers_ID_Name() throws SQLException {
        return dAL_Supplier.GetAllSuppliers_ID_Name();
    }

    public void Insert(String Name, String Address, String Email, String PhoneNumber, GUI_Supplier jframeGUI_Supplier) throws SQLException {

        if ((Name.equals(null) || Name.equals(""))
                || (Address.equals(null) || Address.equals(""))
                || (Email.equals(null) || Email.equals(""))
                || (PhoneNumber.equals(null) || PhoneNumber.equals(""))) {
            JOptionPane.showMessageDialog(null, "Co du lieu con trong moi kiem tra lai");
        } else {
            if (FindBySupplierName(Name).size() <= 0) {
                if (Email.matches(EMAIL_PATTERN)) {
                    if (FindBySupplierEmail(Email).size() <= 0) {
                        if (PhoneNumber.matches(PhoneNumber_pattern)) {
                            try {
                                if (dAL_Supplier.Insert(Name, Address, Email, PhoneNumber)) {
                                    jframeGUI_Supplier.Load();
                                    JOptionPane.showMessageDialog(null, "Them nha cung cap thanh cong");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Them nha cung cap that bai");
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Co du lieu khong dung dinh dang moi kiem tra lai");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "So dien thoai khong dung dinh dang");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Email da duoc su dung");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Email Khong dung dinh dang");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Ten nha cung cap da ton tai");
            }
        }

    }

    public void Update(int Id, String Name, String Address, String Email, String PhoneNumber, GUI_Supplier jframeGUI_Supplier) throws SQLException {

        if ((Name.equals(null) || Name.equals(""))
                || (Address.equals(null) || Address.equals(""))
                || (Email.equals(null) || Email.equals(""))
                || (PhoneNumber.equals(null) || PhoneNumber.equals(""))) {
            JOptionPane.showMessageDialog(null, "Co du lieu con trong moi kiem tra lai");
        } else {
            if (!CheckSupplierNameExist(Id, Name)) {
                if (!CheckSupplierEmailExist(Id, Email)) {
                    if (Email.matches(EMAIL_PATTERN)) {
                        if (PhoneNumber.matches(PhoneNumber_pattern)) {
                            try {
                                if (dAL_Supplier.Update(Id, Name, Address, Email, PhoneNumber)) {
                                    jframeGUI_Supplier.Load();
                                    JOptionPane.showMessageDialog(null, "Cap Nhap nha cung cap thanh cong");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Cap Nhap nha cung cap that bai");
                                }
                            } catch (HeadlessException | SQLException | ParseException e) {
                                JOptionPane.showMessageDialog(null, "Co du lieu khong dung dinh dang moi kiem tra lai");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "So dien thoai khong dung dinh dang");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Email Khong Dung Dinh Dang");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Email da duoc su dung");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ten nha cung cap da ton tai");
            }

        }
    }
    public void Delete(int ID,GUI_Supplier gUI_Suppliert) throws SQLException{
        try {
            if (dAL_Supplier.Delete(ID)) {
                JOptionPane.showMessageDialog(null, "Xoa nha cung cap thanh cong");
                gUI_Suppliert.Load();
            } else {
                JOptionPane.showMessageDialog(null, "xoa nha cung cap that bai");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
