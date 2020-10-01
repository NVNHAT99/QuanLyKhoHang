/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_CustomerOderDetail;
import DTO.DTO_CustomerOrderDetail;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nhat
 */
public class BLL_CustomerOrderDetail {
    static DAL_CustomerOderDetail dAL_CustomerOderDetail = new DAL_CustomerOderDetail();
    public ArrayList<DTO_CustomerOrderDetail> GetCustomerOderDetailById(int CustomerOrderId) throws SQLException {
        return dAL_CustomerOderDetail.GetCustomerOderDetailById(CustomerOrderId);
    }
}
