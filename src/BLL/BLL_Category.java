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

/**
 *
 * @author Nhat
 */
public class BLL_Category {
    
    DAL_Category dal_catgory = new DAL_Category();
    

    public ArrayList<DTO_Category> GetAllCatgory() throws SQLException {
        return  dal_catgory.GetAllCatgory();
    }
    public ArrayList<DTO_Category> GetAllCategory_ID_Name() throws SQLException {
        
        return dal_catgory.GetAllCategory_ID_Name();
    }
    
}
