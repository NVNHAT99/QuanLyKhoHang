/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.DTO_employee;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nhat
 */
public class GUI_Main extends javax.swing.JFrame {

    /**
     * Creates new form GUI_Main
     */
    static DTO_employee _employee;

    public GUI_Main(DTO_employee employee) {
        initComponents();
        _employee = employee;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane_Main = new javax.swing.JTabbedPane();
        Tab_System = new javax.swing.JPanel();
        btn_Infomation = new javax.swing.JButton();
        btn_Role = new javax.swing.JButton();
        btn_ChangePassword = new javax.swing.JButton();
        btn_SystemLog = new javax.swing.JButton();
        btn_Exit = new javax.swing.JButton();
        Tab_Model = new javax.swing.JPanel();
        btn_Product = new javax.swing.JButton();
        btn_Catgories = new javax.swing.JButton();
        btn_Employee = new javax.swing.JButton();
        btn_Supplier = new javax.swing.JButton();
        btn_customer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                formAncestorResized(evt);
            }
        });

        Tab_System.setLayout(new java.awt.GridLayout(2, 3, 10, 10));

        btn_Infomation.setText("Information");
        Tab_System.add(btn_Infomation);

        btn_Role.setText("Role");
        Tab_System.add(btn_Role);

        btn_ChangePassword.setText("Change Pass");
        btn_ChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ChangePasswordActionPerformed(evt);
            }
        });
        Tab_System.add(btn_ChangePassword);

        btn_SystemLog.setText("System Log");
        Tab_System.add(btn_SystemLog);

        btn_Exit.setText("Exit");
        btn_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExitActionPerformed(evt);
            }
        });
        Tab_System.add(btn_Exit);

        tabbedPane_Main.addTab("System", Tab_System);

        Tab_Model.setLayout(new java.awt.GridLayout(2, 3, 10, 10));

        btn_Product.setText("Product");
        btn_Product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ProductActionPerformed(evt);
            }
        });
        Tab_Model.add(btn_Product);

        btn_Catgories.setText("Catgories");
        btn_Catgories.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CatgoriesActionPerformed(evt);
            }
        });
        Tab_Model.add(btn_Catgories);

        btn_Employee.setText("Employee");
        btn_Employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EmployeeActionPerformed(evt);
            }
        });
        Tab_Model.add(btn_Employee);

        btn_Supplier.setText("Supplier");
        Tab_Model.add(btn_Supplier);

        btn_customer.setText("Customer");
        Tab_Model.add(btn_customer);

        tabbedPane_Main.addTab("Model", Tab_Model);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane_Main, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane_Main, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formAncestorResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formAncestorResized
        // TODO add your handling code here:

    }//GEN-LAST:event_formAncestorResized

    private void btn_ChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ChangePasswordActionPerformed
        // TODO add your handling code here:
        GUI_ChangPassword jframChangPassword = new GUI_ChangPassword(_employee);
        jframChangPassword.setResizable(false);
        jframChangPassword.setVisible(true);

    }//GEN-LAST:event_btn_ChangePasswordActionPerformed

    private void btn_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ExitActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btn_ExitActionPerformed

    private void btn_ProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ProductActionPerformed
        try {
            // TODO add your handling code here:
            GUI_Product gUI_Product = new GUI_Product();
            gUI_Product.setLocationRelativeTo(null);
            gUI_Product.setVisible(true);
            gUI_Product.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(GUI_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_ProductActionPerformed

    private void btn_CatgoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CatgoriesActionPerformed
        try {
            // TODO add your handling code here:
            GUI_Category jframeGUI_Category = new GUI_Category();
            jframeGUI_Category.setLocationRelativeTo(null);
            jframeGUI_Category.setVisible(true);
            jframeGUI_Category.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(GUI_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_CatgoriesActionPerformed

    private void btn_EmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EmployeeActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            GUI_Employee jframeGUI_Employee = new GUI_Employee();
            jframeGUI_Employee.setLocationRelativeTo(null);
            jframeGUI_Employee.setVisible(true);
            jframeGUI_Employee.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(GUI_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_EmployeeActionPerformed

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
            java.util.logging.Logger.getLogger(GUI_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Main(_employee).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Tab_Model;
    private javax.swing.JPanel Tab_System;
    private javax.swing.JButton btn_Catgories;
    private javax.swing.JButton btn_ChangePassword;
    private javax.swing.JButton btn_Employee;
    private javax.swing.JButton btn_Exit;
    private javax.swing.JButton btn_Infomation;
    private javax.swing.JButton btn_Product;
    private javax.swing.JButton btn_Role;
    private javax.swing.JButton btn_Supplier;
    private javax.swing.JButton btn_SystemLog;
    private javax.swing.JButton btn_customer;
    private javax.swing.JTabbedPane tabbedPane_Main;
    // End of variables declaration//GEN-END:variables
}
