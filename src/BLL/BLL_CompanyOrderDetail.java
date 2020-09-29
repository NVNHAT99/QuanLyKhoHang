/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_CompanyOrderDetail;
import DTO.DTO_CompanyOrderDetail;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nhat
 */
public class BLL_CompanyOrderDetail {
    
    static DAL_CompanyOrderDetail dAL_CompanyOrderDetail = new DAL_CompanyOrderDetail();
    public ArrayList<DTO_CompanyOrderDetail> GetCompanyOrderDetailById(int CompanyOderId) throws SQLException {
        return dAL_CompanyOrderDetail.GetCompanyOrderDetailById(CompanyOderId);
    }
}
