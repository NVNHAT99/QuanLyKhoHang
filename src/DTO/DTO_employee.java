package DTO;
import java.sql.Date;

public class DTO_employee {
	
	private int Id;
	private String Username;
	private String Password;
	private String Email;
	private String Name;
	private int RoleId;
	private String PhoneNumber;
	private double Salary;
	private Date date;
	private String Gender;
        private boolean IsDelete;
        
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getRoleId() {
		return RoleId;
	}
	public void setRoleId(int roleId) {
		RoleId = roleId;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public double getSalary() {
		return Salary;
	}
	public void setSalary(double salary) {
		Salary = salary;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}

    /**
     * @return the IsDelete
     */
    public boolean getIsDelete() {
        return IsDelete;
    }

    /**
     * @param IsDelete the IsDelete to set
     */
    public void setIsDelete(boolean IsDelete) {
        this.IsDelete = IsDelete;
    }
	
	
}
