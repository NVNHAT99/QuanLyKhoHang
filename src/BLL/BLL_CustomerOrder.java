/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_CustomerOder;
import DAL.DAL_CustomerOderDetail;
import DTO.DTO_Customer;
import DTO.DTO_CustomerOrderDetail;
import DTO.DTO_CustomerOrder;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nhat
 */
public class BLL_CustomerOrder {
    static DAL_CustomerOder dAL_CustomerOder = new DAL_CustomerOder();
    static DAL_CustomerOderDetail dAL_CustomerOderDetail = new DAL_CustomerOderDetail();
    public ArrayList<DTO_CustomerOrder> GetAllCustomerOder() throws SQLException {
        return  dAL_CustomerOder.GetAllCustomerOder();
    }
    public DTO_CustomerOrder GetById(int CustomerOrerId) throws SQLException {
        return dAL_CustomerOder.GetById(CustomerOrerId);
    }
    public void Insert(DTO_CustomerOrder customerOrder,ArrayList<DTO_CustomerOrderDetail> customerOderDetails) throws SQLException {
        try {
            if(dAL_CustomerOder.Insert_CustomerWithDetail(customerOrder, customerOderDetails)){
                JOptionPane.showMessageDialog(null, "Them Hoa Don Thanh Cong");
            }
            else{
                JOptionPane.showMessageDialog(null, "Them Hoa Don That Bai");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    
    public void Update_CustomerOrderWithDetail(DTO_CustomerOrder customerOrder,
            ArrayList<DTO_CustomerOrderDetail> NewCustomerOderDetails) throws SQLException {
         if(dAL_CustomerOder.Update_CustomerOrderWithDetail(customerOrder, NewCustomerOderDetails)){
             JOptionPane.showMessageDialog(null,"Cap Nhap Hoa Don Mua Hang Thanh Cong");
         }else
         {
             JOptionPane.showMessageDialog(null,"Cap Nhap Hoa Don Mua Hang That Bai");
         }
         
    }
    
     public void Update_WithCollectMoney(DTO_CustomerOrder customerOrder,int EmployeeId,int SupplierId,
            double  PayMoney,String TimeStamp,String Description) throws SQLException {
         if(dAL_CustomerOder.Update_WithCollectMoney(customerOrder, EmployeeId, SupplierId, PayMoney, TimeStamp, Description)){
             JOptionPane.showMessageDialog(null, "Tra Tien Mua Hang Thanh Cong");
         }else{
             JOptionPane.showMessageDialog(null, "Tra Tien Mua Hang That Bai");
         }
     }
     public void Delete(int CustomerOrderId) throws SQLException {
         
         if(dAL_CustomerOder.Delete(CustomerOrderId)){
             JOptionPane.showMessageDialog(null, "Xoa Don Mua Hang Thanh Cong");
         }else{
             JOptionPane.showMessageDialog(null, "Xoa Don Mua Hang That Bai");
         }
     }
}
