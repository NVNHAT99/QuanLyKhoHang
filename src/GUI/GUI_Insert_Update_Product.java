/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL_Category;
import BLL.BLL_Product;
import BLL.BLL_Supplier;
import DTO.Custom_DTO.CustomDTO_Product;
import DTO.DTO_Category;
import DTO.DTO_Product;
import DTO.DTO_Supplier;
import com.mysql.jdbc.log.NullLogger;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import sun.java2d.loops.CustomComponent;
import ultils.CustomCombo;

/**
 *
 * @author Administrator
 */
public class GUI_Insert_Update_Product extends javax.swing.JFrame {

    static BLL_Product bll_product = new BLL_Product();
    static BLL_Supplier bLL_Supplier = new BLL_Supplier();
    static BLL_Category bLL_Category = new BLL_Category();

    static CustomDTO_Product Current_Product = null;
    static GUI_Product GuiProduct = null;

    /**
     * Creates new form GUI_Product_Insert_Update
     */
    public GUI_Insert_Update_Product(GUI_Product gUI_Product) throws SQLException {
        initComponents();
        Load();
        GuiProduct = gUI_Product;
    }

    public GUI_Insert_Update_Product(CustomDTO_Product product, GUI_Product gUI_Product) throws SQLException {
        initComponents();
        Load();
        Current_Product = product;
        GuiProduct = gUI_Product;
        txt_Name.setText(product.getName());
        txt_price.setText(String.valueOf(product.getPrice()));
        txt_unit.setText(String.valueOf(product.getUnit()));
        txt_UnitStock.setText(String.valueOf(product.getUnitsInStock()));
        txt_ImagePath.setText(product.getImagePath());
        ImageIcon img_productIcon = new ImageIcon(new ImageIcon(product.getImagePath()).getImage().getScaledInstance(Img_Product.getWidth(), Img_Product.getHeight(), Image.SCALE_DEFAULT));
        Img_Product.setIcon(img_productIcon);
        cmb_Supplier.getModel().setSelectedItem(product.getSupplier());
        cmb_Category.getModel().setSelectedItem(product.getCategory());
        label_Header.setText("Update Product");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_Name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Img_Product = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        label_Header = new javax.swing.JLabel();
        btn_Save = new javax.swing.JButton();
        btn_Close = new javax.swing.JButton();
        cmb_Supplier = new javax.swing.JComboBox<>();
        cmb_Category = new javax.swing.JComboBox<>();
        btn_ChoseImage = new javax.swing.JButton();
        txt_ImagePath = new javax.swing.JTextField();
        txt_price = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_UnitStock = new javax.swing.JFormattedTextField();
        txt_unit = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Name :");

        jLabel4.setText("Price:");

        jLabel5.setText("Supplier :");

        jLabel6.setText("Category :");

        jLabel7.setText("Unit :");

        label_Header.setText("Insert Product");

        btn_Save.setText("Save");
        btn_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SaveActionPerformed(evt);
            }
        });

        btn_Close.setText("Close");
        btn_Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CloseActionPerformed(evt);
            }
        });

        cmb_Supplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmb_Category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_ChoseImage.setText("Chose Image");
        btn_ChoseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ChoseImageActionPerformed(evt);
            }
        });

        txt_ImagePath.setEditable(false);

        jLabel8.setText("UnitStock :");

        txt_UnitStock.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_Header, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(197, 197, 197))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(txt_UnitStock))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(btn_Save, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Name)
                            .addComponent(cmb_Category, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb_Supplier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_price)
                            .addComponent(txt_unit))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Img_Product, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_ChoseImage, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ImagePath, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Close, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_Header, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(txt_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ChoseImage)
                    .addComponent(txt_ImagePath))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(cmb_Supplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(cmb_Category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_unit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txt_UnitStock, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Img_Product, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Close, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(58, 58, 58))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CloseActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btn_CloseActionPerformed

    private void btn_ChoseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ChoseImageActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter imageFitler = new FileNameExtensionFilter("Image", "jpg", "png");
        fileChooser.setFileFilter(imageFitler);
        // user only can select one image on one times
        fileChooser.setMultiSelectionEnabled(false);

        int NumberImage = fileChooser.showDialog(this, "Chose file image for Product");
        if (NumberImage == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            txt_ImagePath.setText(f.getAbsolutePath());
            Img_Product.setIcon(new ImageIcon(new ImageIcon(f.getAbsolutePath()).getImage().getScaledInstance(Img_Product.getWidth(), Img_Product.getHeight(), Image.SCALE_DEFAULT)));
        }
    }//GEN-LAST:event_btn_ChoseImageActionPerformed

    private void btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveActionPerformed
        // TODO add your handling code here:

        if (Current_Product == null) {
            // insert
            int Supplier_ID = ((CustomCombo) cmb_Supplier.getSelectedItem()).getID();
            int category_ID = ((CustomCombo) cmb_Category.getSelectedItem()).getID();
            try {
                bll_product.Insert(txt_Name.getText(), txt_price.getText(), Supplier_ID, category_ID, txt_unit.getText(), txt_UnitStock.getText(), txt_ImagePath.getText(), GuiProduct);
                Current_Product=null;
            } catch (SQLException ex) {
                Logger.getLogger(GUI_Insert_Update_Product.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //update
            int Supplier_ID = ((CustomCombo) cmb_Supplier.getSelectedItem()).getID();
            int category_ID = ((CustomCombo) cmb_Category.getSelectedItem()).getID();
            try {
                bll_product.Update(Current_Product.getId(), txt_Name.getText(), txt_price.getText(), Supplier_ID, category_ID, txt_unit.getText(), txt_UnitStock.getText(), txt_ImagePath.getText(),
                                GuiProduct);
            } catch (SQLException ex) {
                Logger.getLogger(GUI_Insert_Update_Product.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_SaveActionPerformed

    public void Load() throws SQLException {

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ArrayList<DTO_Supplier> Listsuppliers = bLL_Supplier.GetAllSuppliers_ID_Name();
        ArrayList<DTO_Category> ListCategorys = bLL_Category.GetAllCategory_ID_Name();
        DefaultComboBoxModel mod_Supplier = new DefaultComboBoxModel();
        for (int i = 0; i < Listsuppliers.size(); i++) {
            // create object(is a row in table) 
            Object items_Supplier = new CustomCombo(Listsuppliers.get(i).getId(), Listsuppliers.get(i).getName());
            mod_Supplier.addElement(items_Supplier);
        }
        DefaultComboBoxModel mod_Category = new DefaultComboBoxModel();
        for (int j = 0; j < ListCategorys.size(); j++) {
            // create object(is a row in table) 
            Object items_Category = new CustomCombo(ListCategorys.get(j).getId(), ListCategorys.get(j).getName());
            mod_Category.addElement(items_Category);
        }

        cmb_Supplier.setModel(mod_Supplier);
        cmb_Category.setModel(mod_Category);
        // this code to set selected items and get selected item
//        cmb_Supplier.getModel().setSelectedItem(new CustomCombo(Listsuppliers.get(2).getId(), Listsuppliers.get(2).getName()));
//        CustomCombo supplier = (CustomCombo) cmb_Supplier.getSelectedItem();
//        JOptionPane.showMessageDialog(null, supplier.getID());

        txt_UnitStock.setText("1");
    }

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
            java.util.logging.Logger.getLogger(GUI_Insert_Update_Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Insert_Update_Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Insert_Update_Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Insert_Update_Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GUI_Insert_Update_Product(GuiProduct).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(GUI_Insert_Update_Product.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Img_Product;
    private javax.swing.JButton btn_ChoseImage;
    private javax.swing.JButton btn_Close;
    private javax.swing.JButton btn_Save;
    private javax.swing.JComboBox<String> cmb_Category;
    private javax.swing.JComboBox<String> cmb_Supplier;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel label_Header;
    private javax.swing.JTextField txt_ImagePath;
    private javax.swing.JTextField txt_Name;
    private javax.swing.JFormattedTextField txt_UnitStock;
    private javax.swing.JTextField txt_price;
    private javax.swing.JTextField txt_unit;
    // End of variables declaration//GEN-END:variables
}
