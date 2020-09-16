package BLL;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.mindrot.BCrypt;

import DAL.DAL_Employee;
import DTO.DTO_employee;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class BLL_Employee {

    static DAL_Employee dal_Employee = new DAL_Employee();
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static String PhoneNumber_pattern = "^\\d{10}$";

    public static ArrayList<DTO_employee> FindbyUserName(String UserName) throws SQLException {
        return dal_Employee.FindByUserName(UserName);
    }

    public static ArrayList<DTO_employee> FindbyEmail(String Email) throws SQLException {
        return dal_Employee.FindByEmail(Email);
    }

    public ArrayList<DTO_employee> GetAllEmloyee() throws SQLException {
        return dal_Employee.GetAllEmloyee();
    }

    public static boolean Login(String UserName, String PassWord) throws SQLException {
        ArrayList<DTO_employee> rs = BLL_Employee.FindbyUserName(UserName);
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
        ArrayList<DTO_employee> rs = BLL_Employee.FindbyUserName(UserName);

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

    public int Insert(String username, String PassWord, String ConfirmPassWord, String name, String email) throws SQLException {
        int rs = -1;
        if (!((username.equals(null) || username.equals(""))
                || (PassWord.equals(null) || PassWord.equals(""))
                || (name.equals(null) || name.equals(""))
                || (email.equals(null) || email.equals("")))) {

            try {
                if (!email.matches(EMAIL_PATTERN)) {
                    JOptionPane.showMessageDialog(null, "Email khong dung dinh dang");
                } else if (!ConfirmPassWord.equals(PassWord)) {
                    JOptionPane.showMessageDialog(null, "Mat Khau khong khop moi kiem tra lai");
                } else if (FindbyEmail(email).size() > 0) {
                    JOptionPane.showMessageDialog(null, "Email nay da duoc su dung boi nhan vien khac");
                } else if (FindbyUserName(username).size() > 0) {
                    JOptionPane.showMessageDialog(null, "Tai khoan dang nhap nay da ton tai");
                } else {
                    int id_newEmployee = dal_Employee.Insert(username, BCrypt.hashpw(PassWord, BCrypt.gensalt()), name, email);
                    if (id_newEmployee > 0) {
                        JOptionPane.showMessageDialog(null, "Them Nhan Vien Moi Thanh cong");
                        rs = id_newEmployee;
                    } else {
                        JOptionPane.showMessageDialog(null, " Them Nhan Vien Moi That Bai");
                    }
                }
            } catch (Exception e) {
            }

        } else {
            JOptionPane.showMessageDialog(null, " co du lieu con trong moi kiem tra lai");
        }
        return rs;
    }

    public void Update(int ID, String name, String email, String phonenumber, String Gender, String birtthdate, String salary, Boolean Isdelete) throws SQLException {
        if (!((name.equals(null) || name.equals(""))
                || (phonenumber.equals(null) || phonenumber.equals(""))
                || (Gender.equals(null) || Gender.equals(""))
                || (birtthdate.equals(null) || birtthdate.equals(""))
                || (salary.equals(null) || salary.equals(""))
                || (email.equals(null) || email.equals("")))) {

            try {
                if (email.matches(EMAIL_PATTERN)) {
                    if (FindbyEmail(email).size() <= 0) {
                        if (phonenumber.matches(PhoneNumber_pattern)) {
                            try {
                                if (dal_Employee.Update(ID, name, email, phonenumber, Gender, Date.valueOf(birtthdate), Double.parseDouble(salary), Isdelete)) {
                                    JOptionPane.showMessageDialog(null, "Cap Nhap Thong Tin Nhan Vien Thanh Cong");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Cap Nhap Thong Tin Nhan Vien That Bai");
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "co du lieu khong dung dinh dang moi kiem tra lai");
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "So Dien Thoai Khong Dung Dinh Dang");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Email nay da duoc su dung boi nhan vien khac");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Email khong dung dinh dang");
                }

            } catch (Exception e) {
            }

        } else {
            JOptionPane.showMessageDialog(null, " co du lieu con trong moi kiem tra lai");
        }
    }

}
