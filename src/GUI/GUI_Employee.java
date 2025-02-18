/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL_Employee;
import DTO.DTO_Permissions;
import DTO.DTO_employee;
import static GUI.GUI_Customer.permissionses;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class GUI_Employee extends javax.swing.JFrame {

    /**
     * Creates new form GUI_Employee
     */
    static BLL_Employee bLL_Employee = new BLL_Employee();
    static ArrayList<DTO_Permissions> permissionses = null;

    public GUI_Employee(ArrayList<DTO_Permissions> permissions) throws SQLException {
        initComponents();
        permissionses = permissions;
        load();
        LoadPermissions();
    }
    public void LoadPermissions() {
        if (!permissionses.get(1).isAllowInsert()) {
            btn_Signup.setEnabled(false);
        }
        if (!permissionses.get(1).isAllowDelete()) {
            btn_delete.setEnabled(false);
        }
        if (!permissionses.get(1).isAllowUpdate()) {
            btn_Update.setEnabled(false);
        }
    }

    public void load() throws SQLException {
        ArrayList<DTO_employee> list_employee = bLL_Employee.GetAllEmloyee();
        String[] columeNames = new String[]{"ID", "Username", "Email ", "Name", "PhoneNumber", "Salary", "Birthdate", "Gender", "IsDelete", "RoleId"};
        DefaultTableModel model = new DefaultTableModel(null, columeNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // or a condition at your choice with row and column
            }
        };
        for (int i = 0; i < list_employee.size(); i++) {
            // create object(is a row in table) 
            Object[] employee = new Object[]{
                list_employee.get(i).getId(), list_employee.get(i).getUsername(), list_employee.get(i).getEmail(),
                list_employee.get(i).getName(), list_employee.get(i).getPhoneNumber(), list_employee.get(i).getSalary(),
                list_employee.get(i).getDate(), list_employee.get(i).getGender(), list_employee.get(i).getIsDelete(),
                list_employee.get(i).getRoleId()
            };
            model.addRow(employee);
        }

        Table_Employee.setModel(model);
        Table_Employee.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int RowSelected = Table_Employee.getSelectedRow();
                if (RowSelected > -1) {
                    try {
                        String Salary = "";
                        String Birthdate = "", Gender = "", PhoneNumber = "";
                        txt_ID.setText(Table_Employee.getValueAt(RowSelected, 0).toString());
                        txt_UserName.setText(Table_Employee.getValueAt(RowSelected, 1).toString());
                        txt_Email.setText(Table_Employee.getValueAt(RowSelected, 2).toString());
                        txt_Name.setText(Table_Employee.getValueAt(RowSelected, 3).toString());

                        try {
                            PhoneNumber = Table_Employee.getValueAt(RowSelected, 4).toString();
                        } catch (Exception ef) {
                        }
                        try {
                            Salary = Table_Employee.getValueAt(RowSelected, 5).toString();
                        } catch (Exception ef) {
                        }
                        try {
                            Birthdate = Table_Employee.getValueAt(RowSelected, 6).toString();
                        } catch (Exception ef) {
                        }
                        try {
                            Gender = Table_Employee.getValueAt(RowSelected, 7).toString();
                        } catch (Exception ef) {
                        }

                        txt_PhoneNumber.setText(PhoneNumber);
                        txt_Salary.setText(Salary);
                        txt_Birthdate.setText(Birthdate);
                        txt_Gender.setText(Gender);
                        Isdelete.setSelected(Boolean.parseBoolean(Table_Employee.getValueAt(RowSelected, 8).toString()));

                    } catch (Exception f) {
                    }
                }
            }
        });        // set selected row 
        Table_Employee.setRowSelectionInterval(0, 0);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_UserName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_Email = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_Employee = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txt_PhoneNumber = new javax.swing.JTextField();
        btn_Signup = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btn_Update = new javax.swing.JButton();
        txt_Gender = new javax.swing.JTextField();
        btn_delete = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btn_close = new javax.swing.JButton();
        txt_Birthdate = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_ID = new javax.swing.JTextField();
        txt_Salary = new javax.swing.JTextField();
        txt_Name = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Isdelete = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel8.setText("Salary");

        jLabel3.setText("UserName");

        jLabel1.setText("Employee");

        Table_Employee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(Table_Employee);

        jLabel4.setText("Email");

        btn_Signup.setText("Signup");
        btn_Signup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SignupActionPerformed(evt);
            }
        });

        jLabel5.setText("PhoneNumber:");

        btn_Update.setText("Update");
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });

        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        jLabel6.setText("Gender:");

        btn_close.setText("CLose");
        btn_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_closeActionPerformed(evt);
            }
        });

        jLabel2.setText("ID :");

        jLabel7.setText("Birthdate");

        jLabel9.setText("Name :");

        Isdelete.setText("Isdelete");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_PhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Gender, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_Signup)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Update)
                        .addGap(18, 18, 18)
                        .addComponent(btn_delete)
                        .addGap(18, 18, 18)
                        .addComponent(btn_close))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(328, 328, 328))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Isdelete)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_Birthdate, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_Salary, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(248, 248, 248)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_PhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_Gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(43, 43, 43))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_Birthdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_Salary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(Isdelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Signup)
                            .addComponent(btn_Update)
                            .addComponent(btn_delete)
                            .addComponent(btn_close))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_closeActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btn_closeActionPerformed

    private void btn_SignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SignupActionPerformed
        // TODO add your handling code here:
        GUI_SignUp jframeGUI_SignUp = new GUI_SignUp(this);
        jframeGUI_SignUp.pack();
        jframeGUI_SignUp.setLocationRelativeTo(null);
        jframeGUI_SignUp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jframeGUI_SignUp.setVisible(true);
    }//GEN-LAST:event_btn_SignupActionPerformed

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        // TODO add your handling code here:
        try {
            int Rowslected = Table_Employee.getSelectedRow();
            DTO_employee employee = new DTO_employee();
            employee.setId(Integer.parseInt(Table_Employee.getValueAt(Rowslected, 0).toString()));
            employee.setUsername(Table_Employee.getValueAt(Rowslected, 1).toString());
            employee.setEmail(Table_Employee.getValueAt(Rowslected, 2).toString());
            try {
                employee.setName(Table_Employee.getValueAt(Rowslected, 3).toString());
            } catch (Exception e) {
                employee.setName("");
            }
            try {
                employee.setPhoneNumber(Table_Employee.getValueAt(Rowslected, 4).toString());
            } catch (Exception e) {
                employee.setPhoneNumber("");
            }

            try {
                employee.setSalary(Double.parseDouble(Table_Employee.getValueAt(Rowslected, 5).toString()));
            } catch (Exception e) {
                employee.setSalary(0);
            }
            if (String.valueOf(Table_Employee.getValueAt(Rowslected, 6)).equals("null")) {
                employee.setDate(null);
            } else {
                employee.setDate(Date.valueOf(String.valueOf(Table_Employee.getValueAt(Rowslected, 6))));
            }
            try {
                employee.setGender(String.valueOf(Table_Employee.getValueAt(Rowslected, 7)));
            } catch (Exception e) {
                employee.setGender("");
            }
            employee.setIsDelete(Boolean.parseBoolean(Table_Employee.getValueAt(Rowslected, 8).toString()));
            employee.setRoleId(Integer.parseInt(Table_Employee.getValueAt(Rowslected, 9).toString()));

            GUI_UpdateEmployee jframguiGUI_UpdateEmployee = new GUI_UpdateEmployee(this, employee, false);
            jframguiGUI_UpdateEmployee.pack();
            jframguiGUI_UpdateEmployee.setLocationRelativeTo(null);
            jframguiGUI_UpdateEmployee.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jframguiGUI_UpdateEmployee.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        try {
            int Rowslected = Table_Employee.getSelectedRow();
            int Id_emloyee_delete = Integer.parseInt(Table_Employee.getValueAt(Rowslected, 0).toString());
            bLL_Employee.Delete(Id_emloyee_delete, this);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GUI_Employee(permissionses).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(GUI_Employee.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Isdelete;
    private javax.swing.JTable Table_Employee;
    private javax.swing.JButton btn_Signup;
    private javax.swing.JButton btn_Update;
    private javax.swing.JButton btn_close;
    private javax.swing.JButton btn_delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_Birthdate;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_Gender;
    private javax.swing.JTextField txt_ID;
    private javax.swing.JTextField txt_Name;
    private javax.swing.JTextField txt_PhoneNumber;
    private javax.swing.JTextField txt_Salary;
    private javax.swing.JTextField txt_UserName;
    // End of variables declaration//GEN-END:variables
}
