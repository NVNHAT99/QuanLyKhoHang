/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_Product;
import DTO.DTO_Product;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nhat
 */
public class BLL_Product {
    static DAL_Product dal_product = new DAL_Product();
    public  ArrayList<DTO_Product> FindByProductName(String UserName) throws SQLException{
        return dal_product.FindByProductName(UserName);
    }
    public  ArrayList<DTO_Product> GetAllProductsNotDelete() throws SQLException{
        return  dal_product.GetAllProductsNotDelete();
    }
     public  boolean Insert(String Name, float Price,int SupplierId,int CategoryId,int Unit,int UnitsInStock,String ImagePath) throws SQLException{
         
         return dal_product.Insert(Name, Price, SupplierId, CategoryId, Unit, UnitsInStock, ImagePath);
     }
}
