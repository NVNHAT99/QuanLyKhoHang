package BLL;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.mindrot.BCrypt;

import DAL.DAL_Employee;
import DTO.DTO_employee;

public class BLL_Employee {

    static DAL_Employee dal_Employee = new DAL_Employee();

    public static ArrayList<DTO_employee> Find(String UserName) throws SQLException {
        return dal_Employee.FindByUserName(UserName);
    }

    public static boolean Login(String UserName, String PassWord) throws SQLException {
        DAL_Employee dal_employee = new DAL_Employee();
        ArrayList<DTO_employee> rs = BLL_Employee.Find(UserName);
        if (rs.size() == 0 || rs == null) {

            JOptionPane.showInputDialog("Tai Khoan Khong Ton Tai");
        } else {
            // lay password da dc hast tu db (da lay boi rs )
            String passWordHash = rs.get(0).getPassword();
            if (BCrypt.checkpw(PassWord, passWordHash)) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Mat khau khong chinh xac ");

            }

        }
        return false;
    }

    public static boolean Change_Password(String UserName, String CurrentPass, String NewPassword, String Confirmpass) throws SQLException {
        DAL_Employee dal_employee = new DAL_Employee();
        ArrayList<DTO_employee> rs = BLL_Employee.Find(UserName);
        
        if (rs.size() == 0 || rs == null) {

            JOptionPane.showInputDialog("Tai Khoan Khong Ton Tai");
        } else {
            // lay password da dc hast tu db (da lay boi rs )
            String passWordHash = rs.get(0).getPassword();
            if (BCrypt.checkpw(CurrentPass, passWordHash)) {
                if(NewPassword.equals(Confirmpass)){
                    if(dal_employee.Change_Password(UserName,BCrypt.hashpw(NewPassword, BCrypt.gensalt()))){
                        JOptionPane.showMessageDialog(null, "doi mat khau thanh cong");
                        return true;
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Doi mat khau that bai");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "ConfirmPassword khong chinh xac");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Mat khau hien tai khong chinh xac ");

            }

        }
        return false;
    }

}
