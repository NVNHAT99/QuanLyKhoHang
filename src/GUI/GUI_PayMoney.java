/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL_CompanyOrder;
import BLL.BLL_Employee;
import BLL.BLL_Supplier;
import DTO.DTO_CompanyOrder;
import DTO.DTO_Supplier;
import DTO.DTO_employee;
import static GUI.GUI_BuyProduct.bLL_Supplier;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import ultils.CustomCombo;

/**
 *
 * @author Nhat
 */
public class GUI_PayMoney extends javax.swing.JFrame {

    /**
     * Creates new form GUI_PayOrCollectMoney
     */
    static int SupplierId = -1;
    static int CompanyOrderId = -1;
    static int EmployeeId = -1;
    static String DateCreatOrder = "";
    static BLL_CompanyOrder bLL_CompanyOrder = new BLL_CompanyOrder();
    static BLL_Supplier bLL_Supplier = new BLL_Supplier();
    static BLL_Employee bLL_Employee = new BLL_Employee();

    ArrayList<DTO_Supplier> list_Suppliers = bLL_Supplier.GetAllSuppliers();
    ArrayList<DTO_employee> list_employee = bLL_Employee.GetAllEmloyee();
    ArrayList<DTO_CompanyOrder> list_CompanyOrder = bLL_CompanyOrder.GetAllCompaneyOder();

    public GUI_PayMoney(int SupplierId, int CompanyOrderId, int EmployeeId, String DateCreatOrder) throws SQLException {
        initComponents();
        this.SupplierId = SupplierId;
        this.CompanyOrderId = CompanyOrderId;
        this.EmployeeId = EmployeeId;
        this.DateCreatOrder = DateCreatOrder;
        LoadDataForPayMoney();

    }

    public void LoadDataForPayMoney() throws SQLException {

        DefaultComboBoxModel mod_cmb_Supplier = new DefaultComboBoxModel();
        DefaultComboBoxModel mod_cmb_Employee = new DefaultComboBoxModel();
        DefaultComboBoxModel mod_cmb_CompanyOrder = new DefaultComboBoxModel();

        DTO_CompanyOrder dTO_CompanyOrder = null;
        NumberFormat f = new DecimalFormat("#0.00");
        f.setGroupingUsed(false);
        try {
            dTO_CompanyOrder = bLL_CompanyOrder.GetById(CompanyOrderId);
        } catch (SQLException ex) {
            //Logger.getLogger(GUI_PayMoney.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            txt_DateCreateOrder.setText(dTO_CompanyOrder.getTimeStamp().toString());

            txt_Pay.setText("0");

            txt_TotalMoney.setText(f.format(dTO_CompanyOrder.getTotalMoney()));
            txt_stillOwe.setText(f.format(dTO_CompanyOrder.getStillOwe()));
            LocalDate DateNow = LocalDate.now();
            txt_DateCreateBillPayMoney.setText(DateNow.toString());
            txt_DateCreateBillPayMoney.setEditable(false);
        } catch (Exception e) {
        }
        for (int i = 0; i < list_Suppliers.size(); i++) {
            // create object(is a row in table) 
            Object items_cmbSupplierName = new CustomCombo(list_Suppliers.get(i).getId(), list_Suppliers.get(i).getName());
            mod_cmb_Supplier.addElement(items_cmbSupplierName);
            if (list_Suppliers.get(i).getId() == SupplierId) {
                mod_cmb_Supplier.setSelectedItem(items_cmbSupplierName);
            }
        }

        for (int j = 0; j < list_employee.size(); j++) {

            Object item_Employee = new CustomCombo(list_employee.get(j).getId(), list_employee.get(j).getName());
            mod_cmb_Employee.addElement(item_Employee);
            if (list_employee.get(j).getId() == EmployeeId) {
                mod_cmb_Employee.setSelectedItem(item_Employee);
            }
        }
        for (int j = 0; j < list_CompanyOrder.size(); j++) {
            mod_cmb_CompanyOrder.addElement(list_CompanyOrder.get(j).getId());
            if (list_employee.get(j).getId() == CompanyOrderId) {
                mod_cmb_CompanyOrder.setSelectedItem(CompanyOrderId);
            }
        }

        cmb_Supplier.setModel(mod_cmb_Supplier);
        cmb_Employee.setModel(mod_cmb_Employee);
        cmb_CompanyOrder.setModel(mod_cmb_CompanyOrder);
        cmb_CompanyOrder.setEditable(false);
        cmb_Employee.setEditable(false);
        cmb_CompanyOrder.setEditable(false);

//        cmb_Supplier.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                int index = cmb_Supplier.getSelectedIndex();
//                if (cmb_Supplier.getSelectedIndex() != index) {
//                    cmb_Supplier.setSelectedIndex(index);
//                    //LoadInformationSupplier(index);
//                }
//            }
//        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel_Infomation = new javax.swing.JPanel();
        jPanel_GroupInfo1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmb_Supplier = new javax.swing.JComboBox<>();
        cmb_CompanyOrder = new javax.swing.JComboBox<>();
        jPanel_GroupInfo2 = new javax.swing.JPanel();
        txt_DateCreateOrder = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_DateCreateBillPayMoney = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmb_Employee = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_Note = new javax.swing.JTextArea();
        txt_TotalMoney = new javax.swing.JFormattedTextField();
        txt_stillOwe = new javax.swing.JFormattedTextField();
        txt_Pay = new javax.swing.JFormattedTextField();
        btn_Save = new javax.swing.JButton();
        btn_Cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Pay Money");

        jLabel2.setText("Supplier:");

        jLabel3.setText("Company Order");

        cmb_Supplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmb_CompanyOrder.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel_GroupInfo1Layout = new javax.swing.GroupLayout(jPanel_GroupInfo1);
        jPanel_GroupInfo1.setLayout(jPanel_GroupInfo1Layout);
        jPanel_GroupInfo1Layout.setHorizontalGroup(
            jPanel_GroupInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_GroupInfo1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_GroupInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel_GroupInfo1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(cmb_Supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_GroupInfo1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmb_CompanyOrder, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_GroupInfo1Layout.setVerticalGroup(
            jPanel_GroupInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_GroupInfo1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_GroupInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmb_Supplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_GroupInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmb_CompanyOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        txt_DateCreateOrder.setEditable(false);

        jLabel4.setText("Date Create Order");

        jLabel5.setText("Date Pay:");

        txt_DateCreateBillPayMoney.setEditable(false);

        javax.swing.GroupLayout jPanel_GroupInfo2Layout = new javax.swing.GroupLayout(jPanel_GroupInfo2);
        jPanel_GroupInfo2.setLayout(jPanel_GroupInfo2Layout);
        jPanel_GroupInfo2Layout.setHorizontalGroup(
            jPanel_GroupInfo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_GroupInfo2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_GroupInfo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_GroupInfo2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_DateCreateOrder, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_GroupInfo2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_DateCreateBillPayMoney)))
                .addContainerGap())
        );
        jPanel_GroupInfo2Layout.setVerticalGroup(
            jPanel_GroupInfo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_GroupInfo2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_GroupInfo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_DateCreateOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel_GroupInfo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_DateCreateBillPayMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_InfomationLayout = new javax.swing.GroupLayout(jPanel_Infomation);
        jPanel_Infomation.setLayout(jPanel_InfomationLayout);
        jPanel_InfomationLayout.setHorizontalGroup(
            jPanel_InfomationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_InfomationLayout.createSequentialGroup()
                .addComponent(jPanel_GroupInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_GroupInfo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel_InfomationLayout.setVerticalGroup(
            jPanel_InfomationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_GroupInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel_GroupInfo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel6.setText("Total Money:");

        jLabel7.setText("Still Owe");

        jLabel8.setText("pay :");

        jLabel9.setText("Employee : ");

        cmb_Employee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setText("Note : ");

        txt_Note.setColumns(20);
        txt_Note.setRows(5);
        jScrollPane1.setViewportView(txt_Note);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        txt_TotalMoney.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        txt_stillOwe.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(cmb_Employee, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_TotalMoney)
                            .addComponent(txt_stillOwe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                            .addComponent(txt_Pay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_TotalMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txt_stillOwe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txt_Pay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cmb_Employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_Save.setText("Save");
        btn_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SaveActionPerformed(evt);
            }
        });

        btn_Cancel.setText("Cancel");
        btn_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Infomation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Save)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Cancel)
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel_Infomation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Save)
                    .addComponent(btn_Cancel))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveActionPerformed
        // TODO add your handling code here:
        DTO_CompanyOrder dTO_CompanyOrder = null;
        NumberFormat f = new DecimalFormat("#0.00");
        f.setGroupingUsed(false);
        try {
            dTO_CompanyOrder = bLL_CompanyOrder.GetById(CompanyOrderId);
            double NewHavePaid = dTO_CompanyOrder.getHavePaid() + Double.parseDouble(txt_Pay.getText());
            double NewStillOwe = dTO_CompanyOrder.getTotalMoney()-NewHavePaid;
            dTO_CompanyOrder.setHavePaid(NewHavePaid);
            dTO_CompanyOrder.setStillOwe(NewStillOwe);
            bLL_CompanyOrder.Update_WithPayMoney(dTO_CompanyOrder, EmployeeId, SupplierId,Double.parseDouble(txt_Pay.getText()),
                    txt_DateCreateBillPayMoney.getText(),txt_Note.getText());
        } catch (SQLException ex) {
            //Logger.getLogger(GUI_PayMoney.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }//GEN-LAST:event_btn_SaveActionPerformed

    private void btn_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btn_CancelActionPerformed

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
            java.util.logging.Logger.getLogger(GUI_PayMoney.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_PayMoney.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_PayMoney.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_PayMoney.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //SupplierId,CompanyOrderId,EmployeeId,DateCreatOrder
                    new GUI_PayMoney(SupplierId, CompanyOrderId, EmployeeId, DateCreatOrder).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(GUI_PayMoney.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Cancel;
    private javax.swing.JButton btn_Save;
    private javax.swing.JComboBox<String> cmb_CompanyOrder;
    private javax.swing.JComboBox<String> cmb_Employee;
    private javax.swing.JComboBox<String> cmb_Supplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_GroupInfo1;
    private javax.swing.JPanel jPanel_GroupInfo2;
    private javax.swing.JPanel jPanel_Infomation;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_DateCreateBillPayMoney;
    private javax.swing.JTextField txt_DateCreateOrder;
    private javax.swing.JTextArea txt_Note;
    private javax.swing.JFormattedTextField txt_Pay;
    private javax.swing.JFormattedTextField txt_TotalMoney;
    private javax.swing.JFormattedTextField txt_stillOwe;
    // End of variables declaration//GEN-END:variables
}
