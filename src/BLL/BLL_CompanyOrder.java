/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_CompanyOrder;
import DAL.DAL_CompanyOrderDetail;
import DTO.DTO_CompanyOrder;
import DTO.DTO_CompanyOrderDetail;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nhat
 */
public class BLL_CompanyOrder {
    static DAL_CompanyOrder dAL_CompanyOrder = new DAL_CompanyOrder();
    static DAL_CompanyOrderDetail dAL_CompanyOrderDetail = new DAL_CompanyOrderDetail();
    public ArrayList<DTO_CompanyOrder> GetAllCompaneyOder() throws SQLException {
        return  dAL_CompanyOrder.GetAllCompaneyOder();
    }
    public DTO_CompanyOrder GetById(int CompanyOrerId) throws SQLException {
        return dAL_CompanyOrder.GetById(CompanyOrerId);
    }
    public void Insert(DTO_CompanyOrder companyOrder,ArrayList<DTO_CompanyOrderDetail> companyOrderDetails) throws SQLException {
        try {
            if(dAL_CompanyOrder.Insert_CompanyOrderWithDetail(companyOrder, companyOrderDetails)){
                JOptionPane.showMessageDialog(null, "Them Hoa Don Thanh Cong");
            }
            else{
                JOptionPane.showMessageDialog(null, "Them Hoa Don That Bai");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    
    public void Update_CompanyOrderWithDetail(DTO_CompanyOrder companyOrder,
            ArrayList<DTO_CompanyOrderDetail> NewCompanyOrderDetails) throws SQLException {
         if(dAL_CompanyOrder.Update_CompanyOrderWithDetail(companyOrder, NewCompanyOrderDetails)){
             JOptionPane.showMessageDialog(null,"Cap Nhap Hoa Don Mua Hang Thanh Cong");
         }else
         {
             JOptionPane.showMessageDialog(null,"Cap Nhap Hoa Don Mua Hang That Bai");
         }
         
    }
    
     public void Update_WithPayMoney(DTO_CompanyOrder companyOrder,int EmployeeId,int SupplierId,
            double  PayMoney,String TimeStamp,String Description) throws SQLException {
         if(dAL_CompanyOrder.Update_WithPayMoney(companyOrder, EmployeeId, SupplierId, PayMoney, TimeStamp, Description)){
             JOptionPane.showMessageDialog(null, "Tra Tien Mua Hang Thanh Cong");
         }else{
             JOptionPane.showMessageDialog(null, "Tra Tien Mua Hang That Bai");
         }
     }
     public void Delete(int CompanyOrderId) throws SQLException {
         
         if(dAL_CompanyOrder.Delete(CompanyOrderId)){
             JOptionPane.showMessageDialog(null, "Xoa Don Mua Hang Thanh Cong");
         }else{
             JOptionPane.showMessageDialog(null, "Xoa Don Mua Hang That Bai");
         }
     }
    
}
