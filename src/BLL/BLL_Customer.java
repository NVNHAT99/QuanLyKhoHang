/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import static BLL.Interface_Constant.EMAIL_PATTERN;
import static BLL.Interface_Constant.PhoneNumber_pattern;
import DAL.DAL_Customer;
import DTO.DTO_Customer;
import GUI.GUI_Customer;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class BLL_Customer implements Interface_Constant {

    static DAL_Customer dAL_Customer = new DAL_Customer();

    public ArrayList<DTO_Customer> FindByName(String CustomerName) throws SQLException {
        return dAL_Customer.FindByName(CustomerName);
    }

    public boolean CheckCustomerNameExist(int CustomerId, String CustomerName) throws SQLException {
        return dAL_Customer.CheckCustomerNameExist(CustomerId, CustomerName);
    }

    public ArrayList<DTO_Customer> GetAllCustomer() throws SQLException {
        return dAL_Customer.GetAllCustomer();
    }

    public void Insert(String Name, String PhoneNumber, String Address, String Gender,GUI_Customer gUI_Customer) throws SQLException {
        if ((Name.equals(null) || Name.equals(""))
                || (PhoneNumber.equals(null) || PhoneNumber.equals(""))
                || (Address.equals(null) || Address.equals(""))
                || (Gender.equals(null) || Gender.equals(""))) {
            JOptionPane.showMessageDialog(null, "co du lieu de trong moi kiem tra lai");
        } else {
            try {
                if (dAL_Customer.Insert(Name, PhoneNumber, Address, Gender)) {
                    gUI_Customer.Load();
                    JOptionPane.showMessageDialog(null, "Them khach hang thanh cong");
                } else {
                    JOptionPane.showMessageDialog(null, "Them khach hang that bai");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "co du lieu khong dung dinh dang moi kiem tra lai");
            }

        }

    }

    public void Update(int Id, String Name, String PhoneNumber, String Address, String Gender,GUI_Customer gUI_Customer) throws SQLException {
        if ((Name.equals(null) || Name.equals(""))
                || (PhoneNumber.equals(null) || PhoneNumber.equals(""))
                || (Address.equals(null) || Address.equals(""))
                || (Gender.equals(null) || Gender.equals(""))) {
            JOptionPane.showMessageDialog(null, "co du lieu de trong moi kiem tra lai");
        } else {
            try {
                if (dAL_Customer.Update(Id, Name, PhoneNumber, Address, Gender)) {
                    gUI_Customer.Load();
                    JOptionPane.showMessageDialog(null, "Cap Nhap khach hang thanh cong");
                } else {
                    JOptionPane.showMessageDialog(null, "Cap Nhap hang that bai");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "co du lieu khong dung dinh dang moi kiem tra lai");
            }

        }
    }

    public void Delete(int ID,GUI_Customer gUI_Customer) throws SQLException {
        try {
            if (dAL_Customer.Delete(ID)) {
                JOptionPane.showMessageDialog(null, "Xoa khach hang thanh cong");
                gUI_Customer.Load();
            } else {
                JOptionPane.showMessageDialog(null, "xoa khach hang that bai");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
