/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL_Employee;
import BLL.BLL_Product;
import BLL.BLL_Supplier;
import DTO.DTO_CompanyOrderDetail;
import DTO.DTO_Product;
import DTO.DTO_Supplier;
import DTO.DTO_employee;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
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
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
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
    static BLL_Supplier bLL_Supplier = new BLL_Supplier();
    static BLL_Employee bLL_Employee = new BLL_Employee();

    ArrayList<DTO_Product> list_product = bLL_Product.GetAllProductsNotDelete();

    ArrayList<DTO_Supplier> list_Suppliers = bLL_Supplier.GetAllSuppliers();
    ArrayList<DTO_employee> list_employee = bLL_Employee.GetAllEmloyee();

    DefaultComboBoxModel mod_cmb_ProductID = new DefaultComboBoxModel();
    DefaultComboBoxModel mod_cmb_ProductName = new DefaultComboBoxModel();

    public GUI_BuyProduct() throws SQLException {
        initComponents();
        jPanel_groupButtonFuction.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel_showListOrderOrCreateOrder.setBorder(BorderFactory.createLineBorder(Color.black));
        LoadSupplierEmployeeForCombobox();
        LoadData_ForTableOrder();
        //set local date to day create order
        LocalDate DateNow = LocalDate.now();
        txt_DateCreateOrder.setText(DateNow.toString());
        txt_DateCreateOrder.setEditable(false);
        LoadInformationSupplier(0);
        // create a format for displaying percentages (with %-sign)
        NumberFormat percentDisplayFormat = NumberFormat.getPercentInstance();

        // create a format for editing percentages (without %-sign)
        NumberFormat percentEditFormat = NumberFormat.getNumberInstance();

        // create a formatter for editing percentages - input will be transformed to percentages (eg. 50 -> 0.5)
        NumberFormatter percentEditFormatter = new NumberFormatter(percentEditFormat) {
            private static final long serialVersionUID = 1L;

            @Override
            public String valueToString(Object o) throws ParseException {
                Number number = (Number) o;
                if (number != null) {
                    double d = number.doubleValue() * 100.0;
                    number = new Double(d);
                }
                return super.valueToString(number);
            }

            @Override
            public Object stringToValue(String s) throws ParseException {
                Number number = (Number) super.stringToValue(s);
                if (number != null) {
                    double d = number.doubleValue() / 100.0;
                    number = new Double(d);
                }
                return number;
            }
        };

        // set allowed range
        percentEditFormatter.setMinimum(0D);
        percentEditFormatter.setMaximum(100D);
        percentEditFormatter.setAllowsInvalid(false);

        txt_VAT.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(percentDisplayFormat),
        new NumberFormatter(percentDisplayFormat),
        percentEditFormatter));
    }

    public void LoadData_ForTableOrder() throws SQLException {

        //create jtable ;
        // create 2 combox colum. combobox SupplierId and SupplierName
        String[] columeNames = new String[]{"Product Id", "Product Name", "Unit", "Quantity", "cost", "into money", "Note"};

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
        get_model.insertRow(Table_Order_Detail.getRowCount(), new Object[]{"", "", "",
            "", "", "", "", ""});

        // show card in card layout
        CardLayout cardLayout = (CardLayout) jPanel_showListOrderOrCreateOrder.getLayout();
        cardLayout.show(jPanel_showListOrderOrCreateOrder, "card2");

        Table_Order_Detail.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

                try {
                    if (e.getFirstRow() >= 0) {
                        switch (e.getColumn()) {
                            case 0:
                                int productId = Integer.parseInt(Table_Order_Detail.getValueAt(e.getFirstRow(), e.getColumn()).toString());
                                CustomCombo product_nameCombo = (CustomCombo) mod_cmb_ProductName.getElementAt(mod_cmb_ProductID.getIndexOf(productId));
                                if (!Table_Order_Detail.getValueAt(e.getFirstRow(), 1).equals(product_nameCombo)) {
                                    // get index of product in list_produt
                                    int index_product = mod_cmb_ProductID.getIndexOf(productId);
                                    //set value for another column

                                    Table_Order_Detail.setValueAt(product_nameCombo, e.getFirstRow(), 1);
                                    //JOptionPane.showMessageDialog(null, index_product);
                                    Table_Order_Detail.setValueAt(list_product.get(index_product).getUnit(), e.getFirstRow(), 2);
                                }
                            case 1:
                                if (!Table_Order_Detail.getValueAt(e.getFirstRow(), 1).toString().equals("")) {
                                    CustomCombo product_Id_Name = (CustomCombo) Table_Order_Detail.getValueAt(e.getFirstRow(), 1);

                                    if (!Table_Order_Detail.getValueAt(e.getFirstRow(), 0).equals(product_Id_Name.getID())) {
                                        // get index of product in list_produt
                                        int index_product = mod_cmb_ProductName.getIndexOf(product_Id_Name);
                                        //   setvalue for another column with product Id
                                        Table_Order_Detail.setValueAt(product_Id_Name.getID(), e.getFirstRow(), 0);
                                        Table_Order_Detail.setValueAt(list_product.get(index_product).getUnit(), e.getFirstRow(), 2);
                                    }
                                }

                            case 3:
                                try {
                                    String valueQuantity = Table_Order_Detail.getValueAt(e.getFirstRow(), 3).toString();
                                    if (!(valueQuantity.equals(null) || valueQuantity.equals(""))) {
                                        try {
                                            double Quantity = Double.parseDouble(valueQuantity);
                                            String Cost = Table_Order_Detail.getValueAt(e.getFirstRow(), 4).toString();
                                            if (Cost.equals(null) || Cost.equals("")) {
                                                Table_Order_Detail.setValueAt(0, e.getFirstRow(), 4);
                                                Cost = "0";
                                            }
                                            Table_Order_Detail.setValueAt(Quantity * (Double.parseDouble(Cost)), e.getFirstRow(), 5);
                                        } catch (Exception err) {
                                            JOptionPane.showMessageDialog(null, "du lieu so luong san pham khong hop le");
                                        }
                                    }
                                } catch (Exception err) {
                                }
                            case 4:
                                try {
                                    String valueCost = Table_Order_Detail.getValueAt(e.getFirstRow(), 4).toString();
                                    if (!(valueCost.equals(null) || valueCost.equals(""))) {
                                        try {
                                            double Cost = Double.parseDouble(valueCost);
                                            String Quantity = Table_Order_Detail.getValueAt(e.getFirstRow(), 3).toString();
                                            if (Quantity.equals(null) || Quantity.equals("")) {
                                                Table_Order_Detail.setValueAt(0, e.getFirstRow(), 3);
                                                Quantity = "0";
                                            }
                                            NumberFormat f = NumberFormat.getInstance();
                                            f.setGroupingUsed(false);
                                            String a = f.format((Cost * (Double.parseDouble(Quantity))));
                                            Table_Order_Detail.setValueAt(a, e.getFirstRow(), 5);
                                        } catch (Exception err) {
                                            JOptionPane.showMessageDialog(null, "du lieu so luong san pham khong hop le");
                                        }
                                    }
                                } catch (Exception err) {
                                }

                            default:
                                break;
                        }
                    }
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, err);
                }
                if ((e.getLastRow() + 1) == Table_Order_Detail.getRowCount()) {
                    try {
                        if (!(Table_Order_Detail.getValueAt(e.getLastRow(), e.getColumn()).equals(null)
                                || Table_Order_Detail.getValueAt(e.getLastRow(), e.getColumn()).equals(""))) {
                            DefaultTableModel model = (DefaultTableModel) Table_Order_Detail.getModel();
                            model.insertRow(Table_Order_Detail.getRowCount(), new Object[]{"", "",
                                "", "", "", "", "", ""});
                        }
                    } catch (Exception err) {
                    }
                }
            }
        });
    }

    public void LoadSupplierEmployeeForCombobox() {
        DefaultComboBoxModel mod_cmb_SupplierName = new DefaultComboBoxModel();
        DefaultComboBoxModel mod_cmb_SupplierId = new DefaultComboBoxModel();

        DefaultComboBoxModel mod_cmb_Employee = new DefaultComboBoxModel();

        for (int i = 0; i < list_Suppliers.size(); i++) {
            // create object(is a row in table) 
            Object items_cmbSupplierName = new CustomCombo(list_Suppliers.get(i).getId(), list_Suppliers.get(i).getName());
            mod_cmb_SupplierName.addElement(items_cmbSupplierName);

            Object item_cmbSupplierId = list_Suppliers.get(i).getId();
            mod_cmb_SupplierId.addElement(item_cmbSupplierId);

        }

        for (int j = 0; j < list_employee.size(); j++) {

            Object item_Employee = new CustomCombo(list_employee.get(j).getId(), list_employee.get(j).getName());
            mod_cmb_Employee.addElement(item_Employee);
        }

        cmb_SupplierName.setModel(mod_cmb_SupplierName);
        cmb_SupplierId.setModel(mod_cmb_SupplierId);
        cmb_Employee.setModel(mod_cmb_Employee);

        cmb_SupplierName.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int index = cmb_SupplierName.getSelectedIndex();
                if (cmb_SupplierId.getSelectedIndex() != index) {
                    cmb_SupplierId.setSelectedIndex(index);
                    LoadInformationSupplier(index);
                }
            }
        });
//        cmb_SupplierName.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int index = cmb_SupplierName.getSelectedIndex();
//                if (cmb_SupplierId.getSelectedIndex() != index) {
//                    cmb_SupplierId.setSelectedIndex(index);
//                    LoadInformationSupplier(index);
//                }
//            }
//        });
        cmb_SupplierId.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int index_itemslected = cmb_SupplierId.getSelectedIndex();
                if (cmb_SupplierName.getSelectedIndex() != index_itemslected) {
                    cmb_SupplierName.setSelectedIndex(index_itemslected);
                    LoadInformationSupplier(index_itemslected);
                }
            }
        });

    }

    public void LoadInformationSupplier(int Index_SelectSupplierCmbox) {
        txt_Address.setText(list_Suppliers.get(Index_SelectSupplierCmbox).getAddress());
        txt_PhoneNumber.setText(list_Suppliers.get(Index_SelectSupplierCmbox).getPhoneNumber());
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
        jPanel_groupButtonFuction = new javax.swing.JPanel();
        btn_ListCompanyOrder = new javax.swing.JButton();
        btn_CompanyOrder = new javax.swing.JButton();
        jPanel_showListOrderOrCreateOrder = new javax.swing.JPanel();
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
        jPanel_Information2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmb_SupplierId = new javax.swing.JComboBox<>();
        txt_PhoneNumber = new javax.swing.JTextField();
        jPanel_Information3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cmb_Employee = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txt_DateCreateOrder = new javax.swing.JTextField();
        jPanelTableListBuyProduct = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Table_Order_Detail = new javax.swing.JTable();
        btn_Save = new javax.swing.JButton();
        jPanel_CK_VAT = new javax.swing.JPanel();
        jPanel_CK = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_VAT = new javax.swing.JFormattedTextField();
        jPanel_VAT = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txt_CK = new javax.swing.JFormattedTextField();
        jPanel_TotalMoney = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txt_Total = new javax.swing.JTextField();
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

        javax.swing.GroupLayout jPanel_groupButtonFuctionLayout = new javax.swing.GroupLayout(jPanel_groupButtonFuction);
        jPanel_groupButtonFuction.setLayout(jPanel_groupButtonFuctionLayout);
        jPanel_groupButtonFuctionLayout.setHorizontalGroup(
            jPanel_groupButtonFuctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_groupButtonFuctionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_groupButtonFuctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_CompanyOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ListCompanyOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel_groupButtonFuctionLayout.setVerticalGroup(
            jPanel_groupButtonFuctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_groupButtonFuctionLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(btn_CompanyOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_ListCompanyOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_showListOrderOrCreateOrder.setLayout(new java.awt.CardLayout());

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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, a1Layout.createSequentialGroup()
                .addContainerGap(353, Short.MAX_VALUE)
                .addComponent(btn_CreateNew)
                .addGap(18, 18, 18)
                .addComponent(btn_Update)
                .addGap(18, 18, 18)
                .addComponent(btn_Delete)
                .addGap(85, 85, 85))
            .addGroup(a1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        a1Layout.setVerticalGroup(
            a1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(a1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(a1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_CreateNew)
                    .addComponent(btn_Update)
                    .addComponent(btn_Delete))
                .addGap(32, 32, 32))
        );

        jPanel_showListOrderOrCreateOrder.add(a1, "card3");

        jPanel_Group_information_Order.setLayout(new java.awt.GridLayout(1, 0));

        jLabel3.setText("Supplier: ");

        cmb_SupplierName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Address:");

        txt_Address.setEditable(false);

        javax.swing.GroupLayout jPanel_Information1Layout = new javax.swing.GroupLayout(jPanel_Information1);
        jPanel_Information1.setLayout(jPanel_Information1Layout);
        jPanel_Information1Layout.setHorizontalGroup(
            jPanel_Information1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Information1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Information1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel_Information1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmb_SupplierName, 0, 148, Short.MAX_VALUE)
                    .addComponent(txt_Address))
                .addContainerGap())
        );
        jPanel_Information1Layout.setVerticalGroup(
            jPanel_Information1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Information1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Information1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmb_SupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel_Information1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel_Group_information_Order.add(jPanel_Information1);

        jLabel7.setText("Supplier ID:");

        jLabel8.setText("Phone");

        cmb_SupplierId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txt_PhoneNumber.setEditable(false);

        javax.swing.GroupLayout jPanel_Information2Layout = new javax.swing.GroupLayout(jPanel_Information2);
        jPanel_Information2.setLayout(jPanel_Information2Layout);
        jPanel_Information2Layout.setHorizontalGroup(
            jPanel_Information2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Information2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Information2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(29, 29, 29)
                .addGroup(jPanel_Information2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_PhoneNumber)
                    .addComponent(cmb_SupplierId, 0, 118, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(cmb_Employee, 0, 165, Short.MAX_VALUE)
                    .addComponent(txt_DateCreateOrder)))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        btn_Save.setText("Save");
        btn_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SaveActionPerformed(evt);
            }
        });

        jPanel_CK_VAT.setLayout(new java.awt.GridLayout());

        jPanel_CK.setLayout(new java.awt.BorderLayout());

        jLabel6.setText("VAT :   ");
        jPanel_CK.add(jLabel6, java.awt.BorderLayout.LINE_START);

        txt_VAT.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.000"))));
        jPanel_CK.add(txt_VAT, java.awt.BorderLayout.CENTER);

        jPanel_CK_VAT.add(jPanel_CK);

        jPanel_VAT.setLayout(new java.awt.BorderLayout());

        jLabel9.setText("CK :   ");
        jPanel_VAT.add(jLabel9, java.awt.BorderLayout.LINE_START);

        txt_CK.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.000"))));
        jPanel_VAT.add(txt_CK, java.awt.BorderLayout.CENTER);

        jPanel_CK_VAT.add(jPanel_VAT);

        jPanel_TotalMoney.setLayout(new java.awt.BorderLayout());

        jLabel11.setText("Total :   ");
        jPanel_TotalMoney.add(jLabel11, java.awt.BorderLayout.LINE_START);

        txt_Total.setEditable(false);
        jPanel_TotalMoney.add(txt_Total, java.awt.BorderLayout.CENTER);

        jPanel_CK_VAT.add(jPanel_TotalMoney);

        javax.swing.GroupLayout jPanelTableListBuyProductLayout = new javax.swing.GroupLayout(jPanelTableListBuyProduct);
        jPanelTableListBuyProduct.setLayout(jPanelTableListBuyProductLayout);
        jPanelTableListBuyProductLayout.setHorizontalGroup(
            jPanelTableListBuyProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(jPanelTableListBuyProductLayout.createSequentialGroup()
                .addGap(301, 301, 301)
                .addComponent(btn_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel_CK_VAT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelTableListBuyProductLayout.setVerticalGroup(
            jPanelTableListBuyProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableListBuyProductLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jPanel_CK_VAT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );

        javax.swing.GroupLayout jPane_CompanyOrderLayout = new javax.swing.GroupLayout(jPane_CompanyOrder);
        jPane_CompanyOrder.setLayout(jPane_CompanyOrderLayout);
        jPane_CompanyOrderLayout.setHorizontalGroup(
            jPane_CompanyOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Group_information_Order, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelTableListBuyProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPane_CompanyOrderLayout.setVerticalGroup(
            jPane_CompanyOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPane_CompanyOrderLayout.createSequentialGroup()
                .addComponent(jPanel_Group_information_Order, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTableListBuyProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186))
        );

        jPanel_showListOrderOrCreateOrder.add(jPane_CompanyOrder, "card2");

        jLabel1.setText("Buy Product");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_groupButtonFuction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_showListOrderOrCreateOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(351, 351, 351))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel_groupButtonFuction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_showListOrderOrCreateOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_CompanyOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CompanyOrderActionPerformed
        // TODO add your handling code here:

        CardLayout cardLayout = (CardLayout) jPanel_showListOrderOrCreateOrder.getLayout();
        cardLayout.show(jPanel_showListOrderOrCreateOrder, "card2");
    }//GEN-LAST:event_btn_CompanyOrderActionPerformed

    private void btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveActionPerformed
        // TODO add your handling code here:
        ArrayList<DTO_CompanyOrderDetail> listProductBuy = new ArrayList<DTO_CompanyOrderDetail>();
        for (int i = 0; i < Table_Order_Detail.getRowCount(); i++) {
            int ProductId = Integer.parseInt(Table_Order_Detail.getValueAt(i, 0).toString());
            String ProductUnit = String.valueOf(Table_Order_Detail.getValueAt(i, 0));
//            double Quantity = 
//            DTO_CompanyOrderDetail companyOrderDetail = new DTO_CompanyOrderDetail();
//            companyOrderDetail.set(Integer.parseInt(Table_Order_Detail.getValueAt(i,0).toString()));
        }
    }//GEN-LAST:event_btn_SaveActionPerformed

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
    private javax.swing.JButton btn_Save;
    private javax.swing.JButton btn_Update;
    private javax.swing.JComboBox<String> cmb_Employee;
    private javax.swing.JComboBox<String> cmb_SupplierId;
    private javax.swing.JComboBox<String> cmb_SupplierName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanelTableListBuyProduct;
    private javax.swing.JPanel jPanel_CK;
    private javax.swing.JPanel jPanel_CK_VAT;
    private javax.swing.JPanel jPanel_Group_information_Order;
    private javax.swing.JPanel jPanel_Information1;
    private javax.swing.JPanel jPanel_Information2;
    private javax.swing.JPanel jPanel_Information3;
    private javax.swing.JPanel jPanel_TotalMoney;
    private javax.swing.JPanel jPanel_VAT;
    private javax.swing.JPanel jPanel_groupButtonFuction;
    private javax.swing.JPanel jPanel_showListOrderOrCreateOrder;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txt_Address;
    private javax.swing.JFormattedTextField txt_CK;
    private javax.swing.JTextField txt_DateCreateOrder;
    private javax.swing.JTextField txt_PhoneNumber;
    private javax.swing.JTextField txt_Total;
    private javax.swing.JFormattedTextField txt_VAT;
    // End of variables declaration//GEN-END:variables
}
