/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_Product;
import DTO.Custom_DTO.CustomDTO_Product;
import DTO.DTO_Product;
import GUI.GUI_Product;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nhat
 */
public class BLL_Product {

    static DAL_Product dal_product = new DAL_Product();

    public ArrayList<DTO_Product> FindByProductName(String UserName) throws SQLException {
        return dal_product.FindByProductName(UserName);
    }

    public boolean CheckProductNameExist(int ProductId, String ProductName) throws SQLException {
        return dal_product.CheckProductNameExist(ProductId, ProductName);
    }

    public ArrayList<DTO_Product> GetAllProductsNotDelete() throws SQLException {
        return dal_product.GetAllProductsNotDelete();
    }

    public void Insert(String Name, String Price, int SupplierId, int CategoryId, String Unit, String UnitStock, String ImagePath, GUI_Product gUI_Product) throws SQLException {

        if ((Name.equals(null) || Name.equals(""))
                || (Price.equals(null) || Price.equals(""))
                || (Unit.equals(null) || Unit.equals(""))) {
            JOptionPane.showMessageDialog(null, "co du lieu de trong moi kiem tra lai");
        } else {
            if (!CheckProductNameExist(SupplierId, Name)) {
                try {
                    if (dal_product.Insert(Name, Float.parseFloat(Price), SupplierId, CategoryId, Unit,
                            Integer.parseInt(UnitStock), ImagePath)) {
                        gUI_Product.Load();
                        JOptionPane.showMessageDialog(null, "Them san pham thanh cong");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "co du lieu khong dung dinh dang moi kiem tra lai");
                }

            }else{
                JOptionPane.showMessageDialog(null, "Ten San pham da ton tai");
            }

        }
    }

    public void Update(int ID, String Name, String Price, int SupplierId, int CategoryId, String Unit, String UnitsInStock, String ImagePath, GUI_Product gUI_Product) throws SQLException {
        if ((Name.equals(null) || Name.equals(""))
                || (Price.equals(null) || Price.equals(""))
                || (Unit.equals(null) || Unit.equals(""))) {
            JOptionPane.showMessageDialog(null, "co du lieu de trong moi kiem tra lai");
        } else {
            try {
                if (dal_product.Update(ID, Name, Double.parseDouble(Price), SupplierId, CategoryId, Unit,
                        Integer.parseInt(UnitsInStock), ImagePath)) {
                    JOptionPane.showMessageDialog(null, "Cap Nhap pham thanh cong");
                    gUI_Product.Load();
                } else {
                    JOptionPane.showMessageDialog(null, "cap nhap that bai");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public void Delete(int ID, GUI_Product gUI_Product) throws SQLException {
        try {
            if (dal_product.Delete(ID)) {
                JOptionPane.showMessageDialog(null, "Xoa San pham thanh cong");
                gUI_Product.Load();
            } else {
                JOptionPane.showMessageDialog(null, "xoa that bai that bai");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public ArrayList<CustomDTO_Product> GetAllProduct_ForProductTable() throws SQLException {

        return dal_product.GetAllProduct_ForProductTable();
    }

}
