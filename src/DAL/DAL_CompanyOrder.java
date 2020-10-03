/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_CompanyOrder;
import DTO.DTO_CompanyOrderDetail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class DAL_CompanyOrder extends DAL {

    static DAL_CompanyOrderDetail dAL_CompanyOrderDetail = new DAL_CompanyOrderDetail();
    static DAL_Product dAL_Product = new DAL_Product();
    static DAL_PayMoney dAL_PayMoney = new DAL_PayMoney();

    public ArrayList<DTO_CompanyOrder> GetAllCompaneyOder() throws SQLException {

        ArrayList<DTO_CompanyOrder> result = new ArrayList<DTO_CompanyOrder>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select * "
                    + "from CompanyOrders where IsDelete!=1";
            preparedStatement = connection.prepareStatement(sqlFind);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTO_CompanyOrder companyOrder = new DTO_CompanyOrder();
                companyOrder.setId(resultSet.getInt(1));
                companyOrder.setSupplierId(resultSet.getInt(2));
                companyOrder.setEmployeeId(resultSet.getInt(3));
                companyOrder.setTimeStamp(resultSet.getDate(4));
                companyOrder.setVAT(resultSet.getFloat(5));
                companyOrder.setCK(resultSet.getFloat(6));
                companyOrder.setTotalMoney(resultSet.getDouble(7));
                companyOrder.setHavePaid(resultSet.getDouble(8));
                companyOrder.setStillOwe(resultSet.getDouble(9));
                companyOrder.setStatus(resultSet.getBoolean(10));
                companyOrder.setDescription(resultSet.getString(11));
                companyOrder.setIsDelete(resultSet.getBoolean(12));
                result.add(companyOrder);
            }
        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }

    public DTO_CompanyOrder GetById(int CompanyOrerId) throws SQLException {

        DTO_CompanyOrder result = new DTO_CompanyOrder();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select * "
                    + "from CompanyOrders where IsDelete!=1 and Id=?";
            preparedStatement = connection.prepareStatement(sqlFind);
            preparedStatement.setInt(1, CompanyOrerId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.setId(resultSet.getInt(1));
                result.setSupplierId(resultSet.getInt(2));
                result.setEmployeeId(resultSet.getInt(3));
                result.setTimeStamp(resultSet.getDate(4));
                result.setVAT(resultSet.getFloat(5));
                result.setCK(resultSet.getFloat(6));
                result.setTotalMoney(resultSet.getDouble(7));
                result.setHavePaid(resultSet.getDouble(8));
                result.setStillOwe(resultSet.getDouble(9));
                result.setStatus(resultSet.getBoolean(10));
                result.setDescription(resultSet.getString(11));
                result.setIsDelete(resultSet.getBoolean(12));
            }
        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }

    public int Insert(int SupplierId, int EmployeeId, Date TimeStamp, String Description, double VAT, double CK,
            double TotalMoney, double StillOwe, Connection _Connection) throws SQLException {
        int result = -1;
        Connection cnn = _Connection;
        // default  RoleId for New Employeer
        String sql = "insert into CompanyOrders(SupplierId,EmployeeId,TimeStamp,VAT,CK,TotalMoney,StillOwe,Description) VALUES (?,?,?,?,?,?,?,?)";
        preparedStatement = cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1, SupplierId);
        preparedStatement.setInt(2, EmployeeId);
        preparedStatement.setDate(3, TimeStamp);
        preparedStatement.setDouble(4, VAT);
        preparedStatement.setDouble(5, CK);
        preparedStatement.setDouble(6, TotalMoney);
        preparedStatement.setDouble(7, StillOwe);
        preparedStatement.setString(8, Description);

        int rs = preparedStatement.executeUpdate();
        if (rs > 0) {
            ResultSet key = preparedStatement.getGeneratedKeys();
            if (key.next()) {
                result = key.getInt(1);

            }
        }
        return result;

    }

    public int CheckExistInList(DTO_CompanyOrderDetail companyOrderDetail, ArrayList<DTO_CompanyOrderDetail> list_companyOrder) {
        // >= 0  is exist
        //-1 false not exist
        for (int i = 0; i < list_companyOrder.size(); i++) {
            if ((companyOrderDetail.getProductId() == list_companyOrder.get(i).getProductId())
                    && (companyOrderDetail.getCompanyOrderId() == list_companyOrder.get(i).getCompanyOrderId())) {
                return i;
            }
        }
        return -1;

    }

    public boolean Insert_CompanyOrderWithDetail(DTO_CompanyOrder companyOrder, ArrayList<DTO_CompanyOrderDetail> companyOrderDetails) throws SQLException {
        Connection Cnn = dbUltils.Get_connection();
        if (Cnn != null) {
            try {
                Cnn.setAutoCommit(false);
                int CompanyOrderId = Insert(companyOrder.getSupplierId(), companyOrder.getEmployeeId(), companyOrder.getTimeStamp(),
                        companyOrder.getDescription(), companyOrder.getVAT(), companyOrder.getCK(),
                        companyOrder.getTotalMoney(), companyOrder.getTotalMoney(), Cnn);
                for (int i = 0; i < companyOrderDetails.size(); i++) {
                    dAL_CompanyOrderDetail.Insert(CompanyOrderId, companyOrderDetails.get(i).getProductId(),
                            companyOrderDetails.get(i).getQuantity(), companyOrderDetails.get(i).getCost(),
                            companyOrderDetails.get(i).getDescription(), companyOrderDetails.get(i).getProductUnit(), Cnn);
                    // update unit in stock
                    if(!dAL_Product.Update_ByCompanyOrder(companyOrderDetails.get(i).getProductId(), companyOrderDetails.get(i).getQuantity(), Cnn)){
                        Cnn.rollback();
                        return false;
                    }
                }
                Cnn.commit();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                Cnn.rollback();
                return false;
            } finally {
                Cnn.close();
            }
        }
        return true;
    }

    public boolean Update(int Id, int SupplierId, int EmployeeId, Date TimeStamp, String Description, double VAT, double CK,
            double TotalMoney, double HavePaid, double StillOwe, Connection _Connection) throws SQLException {
        Connection cnn = _Connection;
        // default  RoleId for New Employeer
        try {
            String sql = "Update CompanyOrders SET SupplierId = ?, EmployeeId = ?,TimeStamp=?,VAT=?,CK=?,"
                    + " TotalMoney=?,StillOwe=?,Description=?,Status=?  where Id=?";
            preparedStatement = cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            boolean Status = false;
            if (StillOwe <= 0) {
                Status = true;
                StillOwe = 0;
            }

            preparedStatement.setInt(1, SupplierId);
            preparedStatement.setInt(2, EmployeeId);
            preparedStatement.setDate(3, TimeStamp);
            preparedStatement.setDouble(4, VAT);
            preparedStatement.setDouble(5, CK);
            preparedStatement.setDouble(6, TotalMoney);
            preparedStatement.setDouble(7, StillOwe);
            preparedStatement.setString(8, Description);

            preparedStatement.setBoolean(9, Status);
            preparedStatement.setInt(10, Id);

            int rs = preparedStatement.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, TimeStamp);
        }
        return false;
    }

    public boolean UpdatePayMoney(int Id, double HavePaid, double StillOwe, Connection _Connection) throws SQLException {
        Connection cnn = _Connection;
        try {
            String sql = "Update CompanyOrders SET  "
                    + " HavePaid=?,StillOwe=?,Status=?  where Id=?";
            preparedStatement = cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            boolean Status = false;
            if (StillOwe <= 0.9 && StillOwe >= 0) {
                Status = true;
                StillOwe = 0;
            }

            preparedStatement.setDouble(1, HavePaid);
            preparedStatement.setDouble(2, StillOwe);
            preparedStatement.setBoolean(3, Status);
            preparedStatement.setInt(4, Id);

            int rs = preparedStatement.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }

    public boolean Update_WithPayMoney(DTO_CompanyOrder companyOrder, int EmployeeId, int SupplierId,
            double PayMoney, String TimeStamp, String Description) throws SQLException {
        // need get all Old CompanyOrder Detail
        // ListProductDeleteFromOder is productId in oder detail
        connection = dbUltils.Get_connection();
        if (connection != null) {

            try {
                connection.setAutoCommit(false);
                dAL_PayMoney.Insert(companyOrder.getId(), EmployeeId, TimeStamp, SupplierId, PayMoney, Description, connection);
                UpdatePayMoney(companyOrder.getId(), companyOrder.getHavePaid(), companyOrder.getStillOwe(), connection);
                connection.commit();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                connection.rollback();
                return false;
            } finally {
                connection.close();
            }
        }

        return true;
    }

    public boolean Update_CompanyOrderWithDetail(DTO_CompanyOrder companyOrder,
            ArrayList<DTO_CompanyOrderDetail> NewCompanyOrderDetails) throws SQLException {
        // need get all Old CompanyOrder Detail
        // ListProductDeleteFromOder is productId in oder detail
        connection = dbUltils.Get_connection();
        if (connection != null) {
            try {
                ArrayList<DTO_CompanyOrderDetail> OldCompanyOrderDetails = dAL_CompanyOrderDetail.GetCompanyOrderDetailById(companyOrder.getId());
                connection.setAutoCommit(false);
                // code update in here
                Update(companyOrder.getId(), companyOrder.getSupplierId(), companyOrder.getSupplierId(),
                        companyOrder.getTimeStamp(), companyOrder.getDescription(),
                        companyOrder.getVAT(), companyOrder.getCK(), companyOrder.getTotalMoney(),
                        companyOrder.getHavePaid(), companyOrder.getTotalMoney() - companyOrder.getHavePaid(), connection);
                // delete CompanyOderDetail in ListProductDeleteFromOder
                for (int i = 0; i < OldCompanyOrderDetails.size(); i++) {
                    int checkExist = CheckExistInList(OldCompanyOrderDetails.get(i), NewCompanyOrderDetails);
                    // if not exist delete
                    if (checkExist < 0) {
                        dAL_CompanyOrderDetail.Delete(companyOrder.getId(),
                                OldCompanyOrderDetails.get(i).getProductId(),
                                connection);
                        // update unit instok
                        double Quanity = 0 - OldCompanyOrderDetails.get(i).getQuantity();
                        dAL_Product.Update_ByCompanyOrder(OldCompanyOrderDetails.get(i).getProductId(), Quanity, connection);
                    }
                }
                // update and insert in here
                for (int j = 0; j < NewCompanyOrderDetails.size(); j++) {
                    int checkExist = CheckExistInList(NewCompanyOrderDetails.get(j), OldCompanyOrderDetails);
                    // if exist check to need update or not
                    if (checkExist >= 0) {
                        // if 2 DTO_CompanyOrderDetail not the same, update
                        if (!NewCompanyOrderDetails.get(j).equals(OldCompanyOrderDetails.get(checkExist))) {

                            double SubQuantity = NewCompanyOrderDetails.get(j).getQuantity()
                                    - OldCompanyOrderDetails.get(j).getQuantity();

                            dAL_CompanyOrderDetail.Update(NewCompanyOrderDetails.get(j).getCompanyOrderId(),
                                    NewCompanyOrderDetails.get(j).getProductId(), NewCompanyOrderDetails.get(j).getQuantity(),
                                    NewCompanyOrderDetails.get(j).getCost(), NewCompanyOrderDetails.get(j).getDescription(),
                                    NewCompanyOrderDetails.get(j).getProductUnit(), connection);
                            // update Unit in stock
                            dAL_Product.Update_ByCompanyOrder(NewCompanyOrderDetails.get(j).getProductId(), SubQuantity, connection);
                        }
                    } else {
                        // insert New
                        dAL_CompanyOrderDetail.Insert(companyOrder.getId(), NewCompanyOrderDetails.get(j).getProductId(),
                                NewCompanyOrderDetails.get(j).getQuantity(), NewCompanyOrderDetails.get(j).getCost(),
                                NewCompanyOrderDetails.get(j).getDescription(), NewCompanyOrderDetails.get(j).getProductUnit(), connection);
                        // update Unit in stock
                        dAL_Product.Update_ByCompanyOrder(NewCompanyOrderDetails.get(j).getProductId(),
                                NewCompanyOrderDetails.get(j).getQuantity(), connection);
                    }
                }
                connection.commit();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                connection.rollback();
                return false;
            } finally {
                connection.close();
            }
        }

        return true;
    }

    public boolean Delete(int CompanyOrderId) throws SQLException {
        connection = dbUltils.Get_connection();
        if (connection != null) {
            try {
                ArrayList<DTO_CompanyOrderDetail> CompanyOrderDetails = dAL_CompanyOrderDetail.GetCompanyOrderDetailById(CompanyOrderId);
                connection.setAutoCommit(false);
                String sql_delete = "Update companyorders SET IsDelete = 1 WHERE Id = ?";
                preparedStatement = connection.prepareStatement(sql_delete, PreparedStatement.RETURN_GENERATED_KEYS);
                for (int i = 0; i < CompanyOrderDetails.size(); i++) {

                    // update unit instok
                    double Quanity = 0 - CompanyOrderDetails.get(i).getQuantity();
                    dAL_Product.Update_ByCompanyOrder(CompanyOrderDetails.get(i).getProductId(), Quanity, connection);
                }
                preparedStatement.setInt(1, CompanyOrderId);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                connection.rollback();
                return false;
            } finally {
                connection.close();
            }

        } else {
            return false;
        }
        return true;
    }
}
