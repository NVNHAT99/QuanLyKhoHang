package BLL;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.mindrot.BCrypt;

import DAL.DAL_Employee;
import DTO.DTO_employee;
import java.sql.Date;

public class BLL_Employee {

    static DAL_Employee dal_Employee = new DAL_Employee();
    private static final String EMAIL_PATTERN = 
    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    public static ArrayList<DTO_employee> Find(String UserName) throws SQLException {
        return dal_Employee.FindByUserName(UserName);
    }
    
    public ArrayList<DTO_employee> GetAllEmloyee() throws SQLException {
        return dal_Employee.GetAllEmloyee();
    }
    public static boolean Login(String UserName, String PassWord) throws SQLException {
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
        ArrayList<DTO_employee> rs = BLL_Employee.Find(UserName);

        if (rs.size() == 0 || rs == null) {

            JOptionPane.showInputDialog("Tai Khoan Khong Ton Tai");
        } else {
            // lay password da dc hast tu db (da lay boi rs )
            String passWordHash = rs.get(0).getPassword();
            if (BCrypt.checkpw(CurrentPass, passWordHash)) {
                if (NewPassword.equals(Confirmpass)) {
                    if (dal_Employee.Change_Password(UserName, BCrypt.hashpw(NewPassword, BCrypt.gensalt()))) {
                        JOptionPane.showMessageDialog(null, "doi mat khau thanh cong");
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Doi mat khau that bai");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ConfirmPassword khong chinh xac");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Mat khau hien tai khong chinh xac ");

            }

        }
        return false;
    }

    public boolean Insert(String username, String PassWord,String ConfirmPassWord, String name, String email) throws SQLException {
        boolean rs = false;
        if ((username.equals(null) || username.equals(""))
                || (PassWord.equals(null) || PassWord.equals(""))
                || (name.equals(null) || name.equals(""))
                || (email.equals(null) || email.equals(""))) {
            
            try {
                if(!email.matches(EMAIL_PATTERN)){
                    JOptionPane.showMessageDialog(null,"Email khong dung dinh dang");
                }else if(ConfirmPassWord.equals(PassWord)){
                    JOptionPane.showMessageDialog(null,"Mat Khau khong khop moi kiem tra lai");
                }else{
                    if(dal_Employee.Insert(username, BCrypt.hashpw(PassWord, BCrypt.gensalt()), name, email)>0){
                        JOptionPane.showMessageDialog(null,"Them Nhan Vien Moi Thanh cong");
                        rs = true;
                    }else{
                        JOptionPane.showMessageDialog(null,"Them Nhan Vien Moi That Bai" + dal_Employee.Insert(username, BCrypt.hashpw(PassWord, BCrypt.gensalt()), name, email));
                    }
                }
            } catch (Exception e) {
            }
            

        }else{
            JOptionPane.showMessageDialog(null, " co du lieu con trong moi kiem tra lai");
        }
        return rs;
    }

    public void Update_WhenAddNewEmloyee(int Id, String phonenumber, String Gender, Date birtthdate, double salary) throws SQLException {

    }

}
