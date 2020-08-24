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
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nhat
 */
public class BLL_Supplier {
    
    DAL_Supplier dAL_Supplier = new DAL_Supplier();
    
    public ArrayList<DTO_Supplier> FindBySupplierName(String SupplierName) throws SQLException {
        return dAL_Supplier.FindBySupplierName(SupplierName);
    }
    public ArrayList<DTO_Supplier> GetAllSuppliers() throws SQLException {
        return  dAL_Supplier.GetAllSuppliers();
    }
    
}
