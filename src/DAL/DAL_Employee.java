package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import DTO.DTO_employee;
import java.sql.Date;
import ultils.DBUtils;

public class DAL_Employee extends DAL {

    public ArrayList<DTO_employee> GetAllEmloyee() throws SQLException {

        ArrayList<DTO_employee> result = new ArrayList<DTO_employee>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select Id ,Username,Email,Name,RoleId,PhoneNumber,Salary,Birthdate,Gender,IsDelete "
                    + "from employees";
            preparedStatement = connection.prepareStatement(sqlFind);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTO_employee emloyee = new DTO_employee();
                emloyee.setId(resultSet.getInt(1));
                emloyee.setUsername(resultSet.getString(2));
                emloyee.setEmail(resultSet.getString(3));
                emloyee.setName(resultSet.getString(4));
                emloyee.setRoleId(resultSet.getInt(5));
                emloyee.setPhoneNumber(resultSet.getString(6));
                emloyee.setSalary(resultSet.getDouble(7));
                emloyee.setDate(resultSet.getDate(8));
                emloyee.setGender(resultSet.getString(9));
                emloyee.setIsDelete(resultSet.getBoolean(10));
                result.add(emloyee);
            }
        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }

    // check user name when login exist
    public ArrayList<DTO_employee> FindByUserName(String UserName) throws SQLException {
        ArrayList<DTO_employee> result = new ArrayList<DTO_employee>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select Username,Password from employees where Username = ? and IsDelete!=1";
            preparedStatement = connection.prepareStatement(sqlFind);

            preparedStatement.setString(1, UserName);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTO_employee employee = new DTO_employee();
                employee.setUsername(resultSet.getString(1));
                employee.setPassword(resultSet.getString(2));
                result.add(employee);
            }
        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }

    public ArrayList<DTO_employee> FindByEmail(String Email) throws SQLException {
        ArrayList<DTO_employee> result = new ArrayList<DTO_employee>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select Username,Password,Email from employees where Email = ? ";
            preparedStatement = connection.prepareStatement(sqlFind);

            preparedStatement.setString(1, Email);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTO_employee employee = new DTO_employee();
                employee.setUsername(resultSet.getString(1));
                employee.setPassword(resultSet.getString(2));
                employee.setEmail(resultSet.getString(3));
                result.add(employee);
            }
        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }

    public boolean Change_Password(String UserName, String NewPasswordHash) throws SQLException {
        boolean result = false;
        try {
            connection = dbUltils.Get_connection();
            String sql = "UPDATE employees SET Password = ? where Username = ?";
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, NewPasswordHash);
            preparedStatement.setString(2, UserName);

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

    public int Insert(String username, String passwordHash, String name, String email) throws SQLException {
        int result = -1;
        try {
            connection = dbUltils.Get_connection();
            // default  RoleId for New Employeer
            String sql = "INSERT INTO Employees(Username, Password, Email, Name, RoleId) VALUES (?,?,?,?, 3)";
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, passwordHash);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, name);

            int rs = preparedStatement.executeUpdate();

            if (rs > 0) {
                ResultSet key = preparedStatement.getGeneratedKeys();
                if (key.next()) {
                    result = key.getInt(1);

                }
            }

        } catch (Exception e) {
            JOptionPane.showInputDialog(e);

        } finally {
            connection.close();
        }
        return result;
    }

    public boolean Update(int Id, String name, String email, String phonenumber, String Gender, Date birtthdate, double salary, Boolean Isdelete) throws SQLException {
        boolean result = false;
        try {
            connection = dbUltils.Get_connection();
            int rs = -1;
            if (Isdelete == true) {
                String sql = "update Employees set Name=?,PhoneNumber = ?, Gender = ?, Birthdate = ?, Salary = ?,Isdelete = ?, RoleId = ? where Id=? ";
                preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

                preparedStatement.setString(1, name);
                preparedStatement.setString(2, phonenumber);
                preparedStatement.setString(3, Gender);
                preparedStatement.setDate(4, birtthdate);
                preparedStatement.setDouble(5, salary);
                preparedStatement.setBoolean(6, Isdelete);
                // if isdelete == true. set roleID =4/ this roleid for emloyee is deleted
                preparedStatement.setInt(7, 4);
                preparedStatement.setInt(8, Id);
                rs = preparedStatement.executeUpdate();
            } else // if isdelete == false. not set roleIDz/ this roleid for emloyee new employee
            {
                String sql = "update Employees set Name=?,PhoneNumber = ?, Gender = ?, Birthdate = ?, Salary = ?,Isdelete = ? where Id=? ";
                preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

                preparedStatement.setString(1, name);
                preparedStatement.setString(2, phonenumber);
                preparedStatement.setString(3, Gender);
                preparedStatement.setDate(4, birtthdate);
                preparedStatement.setDouble(5, salary);
                preparedStatement.setBoolean(6, Isdelete);
                preparedStatement.setInt(7, Id);
                rs = preparedStatement.executeUpdate();
            }

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
            String sql = "Update categories SET IsDelete = 1 WHERE Id = ?";
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
