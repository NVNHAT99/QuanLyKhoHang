package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import DTO.DTO_employee;
import ultils.DBUtils;

public class DAL_Employee extends DAL{

    // check user name when login exist
    public ArrayList<DTO_employee> FindByUserName(String UserName) throws SQLException {
        ArrayList<DTO_employee> result = new ArrayList<DTO_employee>();
        try {
            connection = dbUltils.Get_connection();
            String sqlFind = "Select Username,Password from employees where Username = ?";
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
}
