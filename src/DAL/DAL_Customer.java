/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_Customer;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class DAL_Customer extends DAL{
    public ArrayList<DTO_Customer> FindByName(String CustomerName) throws SQLException {
        ArrayList<DTO_Customer> result = new ArrayList<DTO_Customer>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select Id,Name from customers where Name = ? And IsDelete!=1";
            preparedStatement = connection.prepareStatement(sqlFind);

            preparedStatement.setString(1, CustomerName);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTO_Customer Customer = new DTO_Customer();
                Customer.setId(resultSet.getInt(1));
                Customer.setName(resultSet.getString(2));
                result.add(Customer);
            }
        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }

    public boolean CheckCustomerNameExist(int CustomerId, String CustomerName) throws SQLException {
        boolean result = false;// not exist
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select Id,Name from customers where Name = ? And Id != ? And IsDelete !=1 ";
            preparedStatement = connection.prepareStatement(sqlFind);

            preparedStatement.setString(1, CustomerName);
            preparedStatement.setInt(2, CustomerId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (Exception e) {
            //JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }

    public ArrayList<DTO_Customer> GetAllCustomer() throws SQLException {

        ArrayList<DTO_Customer> result = new ArrayList<DTO_Customer>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select Id,Name,PhoneNumber,Address,Gender "
                    + "from customers where IsDelete != 1";
            preparedStatement = connection.prepareStatement(sqlFind);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTO_Customer Customer = new DTO_Customer();
                Customer.setId(resultSet.getInt(1));
                Customer.setName(resultSet.getString(2));
                Customer.setPhoneNumber(resultSet.getString(3));
                 Customer.setAddress(resultSet.getString(4));
                Customer.setGender(resultSet.getString(5));
               
                result.add(Customer);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            connection.close();
        }
        return result;
    }

    
    public boolean Insert(String Name, String PhoneNumber, String Address, String Gender) throws SQLException {
        boolean result = false;
        try {
            connection = dbUltils.Get_connection();
            String sql = "Insert into customers(Name,PhoneNumber,Address ,Gender) values(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, Name);
            preparedStatement.setString(2, PhoneNumber);
            preparedStatement.setString(3, Address);
            preparedStatement.setString(4, Gender);

            int rs = preparedStatement.executeUpdate();

            if (rs > 0) {
                result = true;
            }

        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }

    public boolean Update(int Id, String Name, String PhoneNumber, String Address, String Gender) throws SQLException {
        boolean result = false;
        try {
            connection = dbUltils.Get_connection();
            String sql = "Update customers SET Name = ?,PhoneNumber = ?,Address = ?,Gender = ? WHERE Id = ? ";
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, Name);
            preparedStatement.setString(2, PhoneNumber);
            preparedStatement.setString(3, Address);
            preparedStatement.setString(4, Gender);
            preparedStatement.setInt(5, Id);

            int rs = preparedStatement.executeUpdate();

            if (rs > 0) {
                result = true;
            }

        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }

    public boolean Delete(int ID) throws SQLException {
        boolean result = false;
        try {
            connection = dbUltils.Get_connection();
            String sql = "Update customers SET IsDelete = 1 WHERE Id = ?";
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, ID);
            int rs = preparedStatement.executeUpdate();

            if (rs > 0) {
                result = true;
            }

        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }
}
