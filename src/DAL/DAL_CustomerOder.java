/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_CustomerOrderDetail;
import DTO.DTO_CustomerOrder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nhat
 */
public class DAL_CustomerOder extends DAL {

    static DAL_CustomerOderDetail dAL_CustomerOderDetail = new DAL_CustomerOderDetail();
    static DAL_Product dAL_Product = new DAL_Product();
    static DAL_CollectMoney dAL_CollectMoney = new DAL_CollectMoney();

    public ArrayList<DTO_CustomerOrder> GetAllCustomerOder() throws SQLException {

        ArrayList<DTO_CustomerOrder> result = new ArrayList<DTO_CustomerOrder>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select * "
                    + "from customerorders where IsDelete!=1";
            preparedStatement = connection.prepareStatement(sqlFind);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTO_CustomerOrder customerOrder = new DTO_CustomerOrder();
                customerOrder.setId(resultSet.getInt(1));
                customerOrder.setCustomerId(resultSet.getInt(2));
                customerOrder.setEmployeeId(resultSet.getInt(3));
                customerOrder.setTimeStamp(resultSet.getDate(4));
                customerOrder.setVAT(resultSet.getFloat(5));
                customerOrder.setCK(resultSet.getFloat(6));
                customerOrder.setTotalMoney(resultSet.getDouble(7));
                customerOrder.setHavePaid(resultSet.getDouble(8));
                customerOrder.setStillOwe(resultSet.getDouble(9));
                customerOrder.setStatus(resultSet.getBoolean(10));
                customerOrder.setDescription(resultSet.getString(11));
                customerOrder.setIsDelete(resultSet.getBoolean(12));
                result.add(customerOrder);
            }
        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }

    public DTO_CustomerOrder GetById(int CustomerOrderId) throws SQLException {

        DTO_CustomerOrder result = new DTO_CustomerOrder();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select * "
                    + "from customerorders where IsDelete!=1 and Id=?";
            preparedStatement = connection.prepareStatement(sqlFind);
            preparedStatement.setInt(1, CustomerOrderId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.setId(resultSet.getInt(1));
                result.setCustomerId(resultSet.getInt(2));
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

    public int Insert(int CustomerId, int EmployeeId, Date TimeStamp, String Description, double VAT, double CK,
            double TotalMoney, double StillOwe, Connection _Connection) throws SQLException {
        int result = -1;
        Connection cnn = _Connection;
        // default  RoleId for New Employeer
        String sql = "insert into customerorders(CustomerId ,EmployeeId,TimeStamp,VAT,CK,TotalMoney,StillOwe,Description) VALUES (?,?,?,?,?,?,?,?)";
        preparedStatement = cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1, CustomerId);
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

    public int CheckExistInList(DTO_CustomerOrderDetail customerOrderDetail, ArrayList<DTO_CustomerOrderDetail> list_CustomerOderDetails) {
        // >= 0  is exist
        //-1 false not exist
        for (int i = 0; i < list_CustomerOderDetails.size(); i++) {
            if ((customerOrderDetail.getProductId() == list_CustomerOderDetails.get(i).getProductId())
                    && (customerOrderDetail.getCustomerOrderId() == list_CustomerOderDetails.get(i).getCustomerOrderId())) {
                return i;
            }
        }
        return -1;

    }

    public boolean Insert_CustomerWithDetail(DTO_CustomerOrder CustomerOrder, ArrayList<DTO_CustomerOrderDetail> customerOderDetails) throws SQLException {
        Connection Cnn = dbUltils.Get_connection();
        if (Cnn != null) {
            try {
                Cnn.setAutoCommit(false);
                int CustomerOrderId = Insert(CustomerOrder.getCustomerId(), CustomerOrder.getEmployeeId(), CustomerOrder.getTimeStamp(),
                        CustomerOrder.getDescription(), CustomerOrder.getVAT(), CustomerOrder.getCK(),
                        CustomerOrder.getTotalMoney(), CustomerOrder.getTotalMoney(), Cnn);
                for (int i = 0; i < customerOderDetails.size(); i++) {
                    dAL_CustomerOderDetail.Insert(CustomerOrderId, customerOderDetails.get(i).getProductId(),
                            customerOderDetails.get(i).getQuantity(), customerOderDetails.get(i).getCost(),
                            customerOderDetails.get(i).getDescription(), customerOderDetails.get(i).getProductUnit(), Cnn);
                    // update unit in stock
                    if (!dAL_Product.Update_ByCustomerOrder(customerOderDetails.get(i).getProductId(), customerOderDetails.get(i).getQuantity(), Cnn)) {
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

    public boolean Update(int Id, int CustomerId, int EmployeeId, Date TimeStamp, String Description, double VAT, double CK,
            double TotalMoney, double HavePaid, double StillOwe, Connection _Connection) throws SQLException {
        Connection cnn = _Connection;
        // default  RoleId for New Employeer
        try {
            String sql = "Update customerorders SET CustomerId  = ?, EmployeeId = ?,TimeStamp=?,VAT=?,CK=?,"
                    + " TotalMoney=?,StillOwe=?,Description=?,Status=?  where Id=?";
            preparedStatement = cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            boolean Status = false;
            if (StillOwe <= 0) {
                Status = true;
                StillOwe = 0;
            }

            preparedStatement.setInt(1, CustomerId);
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

    public boolean UpdateCollect(int Id, double HavePaid, double StillOwe, Connection _Connection) throws SQLException {
        Connection cnn = _Connection;
        try {
            String sql = "Update customerorders SET  "
                    + " HavePaid=?,StillOwe=?,Status=?  where Id=?";
            preparedStatement = cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            boolean Status = false;
            if (StillOwe <= 0.9 && StillOwe >= 0) {
                Status = true;
                StillOwe = 0;
            }
            else if(StillOwe < 0){
                JOptionPane.showMessageDialog(null, "So tien tra lon hon so tien dang no");
                return false;
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

    public boolean Update_WithCollectMoney(DTO_CustomerOrder customerOrder, int EmployeeId, int SupplierId,
            double PayMoney, String TimeStamp, String Description) throws SQLException {
        // need get all Old CompanyOrder Detail
        // ListProductDeleteFromOder is productId in oder detail
        connection = dbUltils.Get_connection();
        if (connection != null) {

            try {
                connection.setAutoCommit(false);
                if (!dAL_CollectMoney.Insert(customerOrder.getId(), EmployeeId, TimeStamp, SupplierId, PayMoney, Description, connection)) {
                    connection.rollback();
                    return false;
                }
                if(!UpdateCollect(customerOrder.getId(), customerOrder.getHavePaid(), customerOrder.getStillOwe(), connection)){
                    connection.rollback();
                    return false;
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

    public boolean Update_CustomerOrderWithDetail(DTO_CustomerOrder CustomerOder,
            ArrayList<DTO_CustomerOrderDetail> NewCustomerOderDetails) throws SQLException {
        // need get all Old customerorder Detail
        // ListProductDeleteFromOder is productId in oder detail
        connection = dbUltils.Get_connection();
        if (connection != null) {
            try {
                ArrayList<DTO_CustomerOrderDetail> OldCustomerOderDetails = dAL_CustomerOderDetail.GetCustomerOderDetailById(CustomerOder.getId());
                connection.setAutoCommit(false);
                // code update in here
                Update(CustomerOder.getId(), CustomerOder.getCustomerId(), CustomerOder.getEmployeeId(),
                        CustomerOder.getTimeStamp(), CustomerOder.getDescription(),
                        CustomerOder.getVAT(), CustomerOder.getCK(), CustomerOder.getTotalMoney(),
                        CustomerOder.getHavePaid(), CustomerOder.getTotalMoney() - CustomerOder.getHavePaid(), connection);
                // delete CustomerOderDetail in ListProductDeleteFromOder
                for (int i = 0; i < OldCustomerOderDetails.size(); i++) {
                    int checkExist = CheckExistInList(OldCustomerOderDetails.get(i), NewCustomerOderDetails);
                    // if not exist delete
                    if (checkExist < 0) {
                        dAL_CustomerOderDetail.Delete(CustomerOder.getId(),
                                OldCustomerOderDetails.get(i).getProductId(),
                                connection);
                        // update unit instok
                        double Quanity = 0 - OldCustomerOderDetails.get(i).getQuantity();
                        if (!dAL_Product.Update_ByCustomerOrder(OldCustomerOderDetails.get(i).getProductId(), Quanity, connection)) {
                            connection.rollback();
                            return false;
                        }
                    }
                }
                // update and insert in here
                for (int j = 0; j < NewCustomerOderDetails.size(); j++) {
                    int checkExist = CheckExistInList(NewCustomerOderDetails.get(j), OldCustomerOderDetails);
                    // if exist check to need update or not
                    if (checkExist >= 0) {
                        // if 2 DTO_CustomerOrderDetail not the same, update
                        if (!NewCustomerOderDetails.get(j).equals(OldCustomerOderDetails.get(checkExist))) {

                            double SubQuantity = NewCustomerOderDetails.get(j).getQuantity()
                                    - OldCustomerOderDetails.get(j).getQuantity();

                            dAL_CustomerOderDetail.Update(NewCustomerOderDetails.get(j).getCustomerOrderId(),
                                    NewCustomerOderDetails.get(j).getProductId(), NewCustomerOderDetails.get(j).getQuantity(),
                                    NewCustomerOderDetails.get(j).getCost(), NewCustomerOderDetails.get(j).getDescription(),
                                    NewCustomerOderDetails.get(j).getProductUnit(), connection);
                            // update Unit in stock
                            if (!dAL_Product.Update_ByCustomerOrder(NewCustomerOderDetails.get(j).getProductId(), SubQuantity, connection)) {
                                connection.rollback();
                                return false;
                            }

                        }
                    } else {
                        // insert New
                        dAL_CustomerOderDetail.Insert(CustomerOder.getId(), NewCustomerOderDetails.get(j).getProductId(),
                                NewCustomerOderDetails.get(j).getQuantity(), NewCustomerOderDetails.get(j).getCost(),
                                NewCustomerOderDetails.get(j).getDescription(), NewCustomerOderDetails.get(j).getProductUnit(), connection);
                        // update Unit in stock
                        if (!dAL_Product.Update_ByCustomerOrder(NewCustomerOderDetails.get(j).getProductId(),
                                NewCustomerOderDetails.get(j).getQuantity(), connection)) {
                            connection.rollback();
                            return false;
                        }
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

    public boolean Delete(int CustomerOrderId) throws SQLException {
        connection = dbUltils.Get_connection();
        if (connection != null) {
            try {
                ArrayList<DTO_CustomerOrderDetail> customerOderDetails = dAL_CustomerOderDetail.GetCustomerOderDetailById(CustomerOrderId);
                connection.setAutoCommit(false);
                String sql_delete = "Update customerorders SET IsDelete = 1 WHERE Id = ?";
                preparedStatement = connection.prepareStatement(sql_delete, PreparedStatement.RETURN_GENERATED_KEYS);
                for (int i = 0; i < customerOderDetails.size(); i++) {

                    // update unit instok
                    double Quanity = 0 - customerOderDetails.get(i).getQuantity();
                    dAL_Product.Update_ByCustomerOrder(customerOderDetails.get(i).getProductId(), Quanity, connection);
                }
                preparedStatement.setInt(1, CustomerOrderId);
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
