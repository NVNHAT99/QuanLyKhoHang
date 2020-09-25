/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL_Product;
import DTO.DTO_Product;
import java.awt.CardLayout;
import java.awt.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;
import ultils.CustomCombo;

/**
 *
 * @author Administrator
 */
public class GUI_BuyProduct extends javax.swing.JFrame {

    /**
     * Creates new form GUI_BuyProduct
     */
    static BLL_Product bLL_Product = new BLL_Product();
    ArrayList<DTO_Product> list_product = bLL_Product.GetAllProductsNotDelete();

    DefaultComboBoxModel mod_cmb_ProductID = new DefaultComboBoxModel();
    DefaultComboBoxModel mod_cmb_ProductName = new DefaultComboBoxModel();

    public GUI_BuyProduct() throws SQLException {
        initComponents();
        LoadData_ForTable();
    }

    public void LoadData_ForTable() throws SQLException {

        //create jtable ;
        // create 2 combox colum. combobox SupplierId and SupplierName
        String[] columeNames = new String[]{"Product Id", "Product Name", "Unit", "Quantity", "into money", "Note"};

        DefaultTableModel model = new DefaultTableModel(null, columeNames);
        for (int i = 0; i < list_product.size(); i++) {
            // create object(is a row in table) 
            Object items_cmbProductName = new CustomCombo(list_product.get(i).getId(), list_product.get(i).getName());
            mod_cmb_ProductName.addElement(items_cmbProductName);

            Object item_cmbProductId = list_product.get(i).getId();
            mod_cmb_ProductID.addElement(item_cmbProductId);

        }
        Table_Order_Detail.setModel(model);
        // create combobox colum
        TableColumnModel columnModel = Table_Order_Detail.getColumnModel();
        columnModel.getColumn(0).setCellEditor(new DefaultCellEditor(new JComboBox(mod_cmb_ProductID)));
        columnModel.getColumn(1).setCellEditor(new DefaultCellEditor(new JComboBox(mod_cmb_ProductName)));
        //add new blank row
        DefaultTableModel get_model = (DefaultTableModel) Table_Order_Detail.getModel();
        get_model.insertRow(Table_Order_Detail.getRowCount(), new Object[]{"", "",
            "", "", "", "", ""});

        // show card in card layout
        CardLayout cardLayout = (CardLayout) jPanel2.getLayout();
        cardLayout.show(jPanel2, "card2");

        Table_Order_Detail.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

                try {
                    if (e.getFirstRow() >= 0) {
                        if (e.getColumn() == 1) {
                            CustomCombo product_Id_Name = (CustomCombo) Table_Order_Detail.getValueAt(e.getFirstRow(), e.getColumn());
                            if (!Table_Order_Detail.getValueAt(e.getFirstRow(), 0).equals(product_Id_Name.getID())) {
                                // set valut for colum product Id
                                Table_Order_Detail.setValueAt(product_Id_Name.getID(), e.getFirstRow(), 0);
                            }
                        } else if (e.getColumn() == 0) {

                            int productId = Integer.parseInt(Table_Order_Detail.getValueAt(e.getFirstRow(), e.getColumn()).toString());
                            CustomCombo product_nameCombo = (CustomCombo) mod_cmb_ProductName.getElementAt(mod_cmb_ProductID.getIndexOf(productId));
                            if(!Table_Order_Detail.getValueAt(e.getFirstRow(), 1).equals(product_nameCombo)){
                                Table_Order_Detail.setValueAt(product_nameCombo, e.getFirstRow(), 1);
                            }

                        }
                    }
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, err);
                }
                if ((e.getLastRow() + 1) == Table_Order_Detail.getRowCount()) {
                    try {
                        if (!(Table_Order_Detail.getValueAt(e.getLastRow(), 0).equals(null)
                                || Table_Order_Detail.getValueAt(e.getLastRow(), 0).equals(""))) {
                            DefaultTableModel model = (DefaultTableModel) Table_Order_Detail.getModel();
                            model.insertRow(Table_Order_Detail.getRowCount(), new Object[]{"", "",
                                "", "", "", "", ""});
                        }
                    } catch (Exception err) {
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_ListCompanyOrder = new javax.swing.JButton();
        btn_CompanyOrder = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        a1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_ListCompanyOrder = new javax.swing.JTable();
        btn_CreateNew = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPane_CompanyOrder = new javax.swing.JPanel();
        jPanel_Group_information_Order = new javax.swing.JPanel();
        jPanel_Information1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cmb_SupplierName = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txt_Address = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_Note = new javax.swing.JTextArea();
        jPanel_Information2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmb_SupplierId = new javax.swing.JComboBox<>();
        txt_PhoneNumber = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_DeadlineToPay = new javax.swing.JFormattedTextField();
        jPanel_Information3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cmb_Employee = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txt_DateCreateOrder = new javax.swing.JFormattedTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Table_Order_Detail = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_ListCompanyOrder.setText("<html> <p>List Company Order<p>  </html>");

        btn_CompanyOrder.setText("<html> <p>Company Order<p></html>");
        btn_CompanyOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CompanyOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_CompanyOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ListCompanyOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(btn_CompanyOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_ListCompanyOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setLayout(new java.awt.CardLayout());

        Table_ListCompanyOrder.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(Table_ListCompanyOrder);

        btn_CreateNew.setText("Create New");

        btn_Update.setText("Update");

        btn_Delete.setText("Delete");

        jLabel2.setText("List Company Order");

        javax.swing.GroupLayout a1Layout = new javax.swing.GroupLayout(a1);
        a1.setLayout(a1Layout);
        a1Layout.setHorizontalGroup(
            a1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, a1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(a1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, a1Layout.createSequentialGroup()
                        .addComponent(btn_CreateNew)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Update)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Delete)
                        .addGap(85, 85, 85))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, a1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(258, 258, 258))))
        );
        a1Layout.setVerticalGroup(
            a1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(a1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(19, 19, 19)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 307, Short.MAX_VALUE)
                .addGroup(a1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_CreateNew)
                    .addComponent(btn_Update)
                    .addComponent(btn_Delete))
                .addGap(32, 32, 32))
        );

        jPanel2.add(a1, "card3");

        jPanel_Group_information_Order.setLayout(new java.awt.GridLayout(1, 0));

        jLabel3.setText("Supplier: ");

        cmb_SupplierName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Address:");

        jLabel6.setText("Note:");

        txt_Note.setColumns(20);
        txt_Note.setRows(5);
        jScrollPane1.setViewportView(txt_Note);

        javax.swing.GroupLayout jPanel_Information1Layout = new javax.swing.GroupLayout(jPanel_Information1);
        jPanel_Information1.setLayout(jPanel_Information1Layout);
        jPanel_Information1Layout.setHorizontalGroup(
            jPanel_Information1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Information1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Information1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Information1Layout.createSequentialGroup()
                        .addGroup(jPanel_Information1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_Information1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_SupplierName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_Address)))
                    .addGroup(jPanel_Information1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_Information1Layout.setVerticalGroup(
            jPanel_Information1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Information1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Information1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmb_SupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel_Information1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Information1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1))
                .addGap(3, 3, 3))
        );

        jPanel_Group_information_Order.add(jPanel_Information1);

        jLabel7.setText("Supplier ID:");

        jLabel8.setText("Phone");

        cmb_SupplierId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Deadline to pay");

        javax.swing.GroupLayout jPanel_Information2Layout = new javax.swing.GroupLayout(jPanel_Information2);
        jPanel_Information2.setLayout(jPanel_Information2Layout);
        jPanel_Information2Layout.setHorizontalGroup(
            jPanel_Information2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Information2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Information2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_Information2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_DeadlineToPay)
                    .addComponent(txt_PhoneNumber)
                    .addComponent(cmb_SupplierId, 0, 119, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanel_Information2Layout.setVerticalGroup(
            jPanel_Information2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Information2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Information2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmb_SupplierId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_Information2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_PhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_Information2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_DeadlineToPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jPanel_Group_information_Order.add(jPanel_Information2);

        jLabel10.setText("Employee");

        cmb_Employee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setText("Date");

        javax.swing.GroupLayout jPanel_Information3Layout = new javax.swing.GroupLayout(jPanel_Information3);
        jPanel_Information3.setLayout(jPanel_Information3Layout);
        jPanel_Information3Layout.setHorizontalGroup(
            jPanel_Information3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Information3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Information3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_Information3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_DateCreateOrder)
                    .addComponent(cmb_Employee, 0, 166, Short.MAX_VALUE)))
        );
        jPanel_Information3Layout.setVerticalGroup(
            jPanel_Information3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Information3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Information3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_DateCreateOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_Information3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmb_Employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(97, Short.MAX_VALUE))
        );

        jPanel_Group_information_Order.add(jPanel_Information3);

        Table_Order_Detail.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(Table_Order_Detail);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 79, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPane_CompanyOrderLayout = new javax.swing.GroupLayout(jPane_CompanyOrder);
        jPane_CompanyOrder.setLayout(jPane_CompanyOrderLayout);
        jPane_CompanyOrderLayout.setHorizontalGroup(
            jPane_CompanyOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Group_information_Order, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPane_CompanyOrderLayout.setVerticalGroup(
            jPane_CompanyOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPane_CompanyOrderLayout.createSequentialGroup()
                .addComponent(jPanel_Group_information_Order, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(213, 213, 213))
        );

        jPanel2.add(jPane_CompanyOrder, "card2");

        jLabel1.setText("Buy Product");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(370, 370, 370))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_CompanyOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CompanyOrderActionPerformed
        // TODO add your handling code here:

        CardLayout cardLayout = (CardLayout) jPanel2.getLayout();
        cardLayout.show(jPanel2, "card2");
    }//GEN-LAST:event_btn_CompanyOrderActionPerformed

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
            java.util.logging.Logger.getLogger(GUI_BuyProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_BuyProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_BuyProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_BuyProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GUI_BuyProduct().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(GUI_BuyProduct.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table_ListCompanyOrder;
    private javax.swing.JTable Table_Order_Detail;
    private javax.swing.JPanel a1;
    private javax.swing.JButton btn_CompanyOrder;
    private javax.swing.JButton btn_CreateNew;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_ListCompanyOrder;
    private javax.swing.JButton btn_Update;
    private javax.swing.JComboBox<String> cmb_Employee;
    private javax.swing.JComboBox<String> cmb_SupplierId;
    private javax.swing.JComboBox<String> cmb_SupplierName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPane_CompanyOrder;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel_Group_information_Order;
    private javax.swing.JPanel jPanel_Information1;
    private javax.swing.JPanel jPanel_Information2;
    private javax.swing.JPanel jPanel_Information3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txt_Address;
    private javax.swing.JFormattedTextField txt_DateCreateOrder;
    private javax.swing.JFormattedTextField txt_DeadlineToPay;
    private javax.swing.JTextArea txt_Note;
    private javax.swing.JTextField txt_PhoneNumber;
    // End of variables declaration//GEN-END:variables
}
