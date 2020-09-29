/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL_CompanyOrder;
import BLL.BLL_CompanyOrderDetail;
import BLL.BLL_Employee;
import BLL.BLL_Product;
import BLL.BLL_Supplier;
import DTO.DTO_CompanyOrder;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
    static BLL_CompanyOrder bLL_CompanyOrder = new BLL_CompanyOrder();
    static BLL_CompanyOrderDetail bLL_CompanyOrderDetail = new BLL_CompanyOrderDetail();
    static boolean Update = false;
    static int CompanyOrderId_Update = -1;// for update
    static int IndexRowInListCompanyOrder = -1;

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
        if (!Update) {
            //add new blank row
            DefaultTableModel get_model = (DefaultTableModel) Table_Order_Detail.getModel();
            get_model.insertRow(Table_Order_Detail.getRowCount(), new Object[]{"", "", "",
                "", "", "", "", ""});
        }
        AddEventForVAT_CK();
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

        txt_CK.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(percentDisplayFormat),
                new NumberFormatter(percentDisplayFormat),
                percentEditFormatter));
        txt_VAT.setValue(0D);
        txt_CK.setValue(0D);
        txt_Total.setText("0");
        LoadCreateOrder();

    }

    public boolean CheckProductOrderExist(int ProductId, int Column, int RowChange) {
        for (int i = 0; i < Table_Order_Detail.getRowCount(); i++) {
            if (RowChange == i) {
                continue;
            }
            int Id = -1;
            try {
                if (Column == 0) {
                    Id = Integer.parseInt(Table_Order_Detail.getValueAt(i, 0).toString());
                } else if (Column == 1) {
                    Id = ((CustomCombo) (Table_Order_Detail.getValueAt(i, 1))).getID();
                }
            } catch (Exception e) {
            }
            if (Id == ProductId) {
                return true;
            }
        }
        return false;

    }

    public double SumTo_TotalMoey() {
        double Sum = 0;
        for (int i = 0; i < Table_Order_Detail.getRowCount(); i++) {
            double EachIntoMoney = 0;
            try {
                EachIntoMoney = Double.parseDouble(Table_Order_Detail.getValueAt(i, 5).toString());
            } catch (Exception e) {
            }
            Sum += EachIntoMoney;
        }
        return Sum;
    }

    public void LoadCreateOrder() {
        //set local date to day create order
        LocalDate DateNow = LocalDate.now();
        txt_DateCreateOrder.setText(DateNow.toString());
        txt_DateCreateOrder.setEditable(false);
        LoadInformationSupplier(0);
    }

    public void LoadData_ForTableOrder() throws SQLException {

        //create jtable ;
        // create 2 combox colum. combobox SupplierId and SupplierName
        String[] columeNames = new String[]{"Product Id", "Product Name", "Unit", "Quantity", "cost", "into money", "Note"};

        DefaultTableModel model = new DefaultTableModel(null, columeNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 5) {
                    return false;
                } else {
                    return true;
                }
            }
        };
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
                                if (!Table_Order_Detail.getValueAt(e.getFirstRow(), 1).equals(product_nameCombo.getID())) {

                                    // get index of product in list_produt
                                    int index_product = mod_cmb_ProductID.getIndexOf(productId);
                                    //set value for another column

                                    if (CheckProductOrderExist(productId, e.getColumn(), e.getFirstRow())) {
                                        JOptionPane.showMessageDialog(null, "san pham nay da co trong gio hang, ban co the chinh lai so luon muon mua");
                                        try {
                                            int rowselectted = e.getFirstRow();
                                            if (rowselectted != -1) {
                                                int modelIndex = Table_Order_Detail.convertRowIndexToModel(rowselectted); // converts the row index in the view to the appropriate index in the model
                                                DefaultTableModel model = (DefaultTableModel) Table_Order_Detail.getModel();
                                                model.removeRow(modelIndex);
                                                model.insertRow(Table_Order_Detail.getRowCount(), new Object[]{"", "",
                                                    "", "", "", "", "", ""});

                                            }
                                        } catch (Exception err) {

                                        }
                                    } else {
                                        Table_Order_Detail.setValueAt(product_nameCombo, e.getFirstRow(), 1);
                                        //JOptionPane.showMessageDialog(null, index_product);
                                        Table_Order_Detail.setValueAt(list_product.get(index_product).getUnit(), e.getFirstRow(), 2);

                                        Table_Order_Detail.setValueAt(0, e.getFirstRow(), 5);
                                    }

                                }

                                break;
                            case 1:
                                CustomCombo product_Id_Name = (CustomCombo) Table_Order_Detail.getValueAt(e.getFirstRow(), 1);
                                if (!Table_Order_Detail.getValueAt(e.getFirstRow(), 1).toString().equals("")) {
                                    if (!Table_Order_Detail.getValueAt(e.getFirstRow(), 0).equals(product_Id_Name.getID())) {
                                        // get index of product in list_produt
                                        int index_product = mod_cmb_ProductName.getIndexOf(product_Id_Name);
                                        // check if product in this row change exist in list we delete row, else add that id to list
                                        if (CheckProductOrderExist(product_Id_Name.getID(), e.getColumn(), e.getFirstRow())) {
                                            JOptionPane.showMessageDialog(null, "san pham nay da co trong gio hang, ban co the chinh lai so luon muon mua");
                                            try {
                                                int rowselectted = e.getFirstRow();
                                                if (rowselectted != -1) {
                                                    int modelIndex = Table_Order_Detail.convertRowIndexToModel(rowselectted); // converts the row index in the view to the appropriate index in the model
                                                    DefaultTableModel model = (DefaultTableModel) Table_Order_Detail.getModel();
                                                    model.removeRow(modelIndex);
                                                    model.insertRow(Table_Order_Detail.getRowCount(), new Object[]{"", "",
                                                        "", "", "", "", "", ""});
                                                }
                                            } catch (Exception err) {

                                            }

                                        } else {
                                            //   setvalue for another column with product Id
                                            Table_Order_Detail.setValueAt(product_Id_Name.getID(), e.getFirstRow(), 0);
                                            Table_Order_Detail.setValueAt(list_product.get(index_product).getUnit(), e.getFirstRow(), 2);

                                            Table_Order_Detail.setValueAt(0, e.getFirstRow(), 5);
                                        }

                                    }
                                }

                                break;

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
                                            NumberFormat f = NumberFormat.getInstance();
                                            f.setGroupingUsed(false);
                                            String intoMoney = f.format((Quantity * (Double.parseDouble(Cost))));
                                            Table_Order_Detail.setValueAt(intoMoney, e.getFirstRow(), 5);
                                        } catch (Exception err) {
                                            JOptionPane.showMessageDialog(null, "du lieu so luong san pham khong hop le");
                                        }
                                    }
                                } catch (Exception err) {
                                }
                                break;
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
                                            String total = f.format((Cost * (Double.parseDouble(Quantity))));
                                            Table_Order_Detail.setValueAt(total, e.getFirstRow(), 5);
                                        } catch (Exception err) {
                                            JOptionPane.showMessageDialog(null, "du lieu so luong san pham khong hop le");
                                        }
                                    }
                                } catch (Exception err) {
                                }
                                break;
                            case 6:
                                String IntoMoney = "";
                                try {
                                    IntoMoney = Table_Order_Detail.getValueAt(e.getFirstRow(), 5).toString();
                                } catch (Exception err) {
                                }
                                if (IntoMoney.equals("") || IntoMoney.equals(null)) {
                                    Table_Order_Detail.setValueAt(0, e.getFirstRow(), 5);
                                }
                            default:
                                break;
                        }
                    }
                } catch (Exception err) {
                    //JOptionPane.showMessageDialog(null, err);
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

                try {
                    double VAT = Double.parseDouble(txt_VAT.getValue().toString());
                    double CK = Double.parseDouble(txt_CK.getValue().toString());
                    double NewTotalMoney = SumTo_TotalMoey() + SumTo_TotalMoey()*VAT - SumTo_TotalMoey()*CK;
                    NumberFormat f = NumberFormat.getInstance();
                    f.setGroupingUsed(false);
                    String StringewTotalMoney = f.format(NewTotalMoney);
                    txt_Total.setText(StringewTotalMoney);
                } catch (Exception err) {
                    //JOptionPane.showMessageDialog(null, err);
                }

            }
        });

    }

    public void AddEventForVAT_CK() {
        txt_CK.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                String StringCurrentTotalMoney = "";
                try {
                    StringCurrentTotalMoney = txt_Total.getText();

                } catch (Exception err) {

                }
                try {
                    double CurrenttotalMoney = SumTo_TotalMoey();
                    double VAT = Double.parseDouble(txt_VAT.getValue().toString());
                    double CK = Double.parseDouble(txt_CK.getValue().toString());
                    double NewTotalMoney = CurrenttotalMoney + (VAT * CurrenttotalMoney) - (CK * CurrenttotalMoney);
                    NumberFormat f = NumberFormat.getInstance();
                    f.setGroupingUsed(false);
                    String StringewTotalMoney = f.format(NewTotalMoney);
                    txt_Total.setText(StringewTotalMoney);

                } catch (Exception err) {
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }
        });
        txt_VAT.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                String StringCurrentTotalMoney = "";
                try {
                    StringCurrentTotalMoney = txt_Total.getText();

                } catch (Exception err) {

                }
                try {
                    double CurrenttotalMoney = SumTo_TotalMoey();
                    double VAT = Double.parseDouble(txt_VAT.getValue().toString());
                    double CK = Double.parseDouble(txt_CK.getValue().toString());
                    double NewTotalMoney = CurrenttotalMoney + (VAT * CurrenttotalMoney) - (CK * CurrenttotalMoney);
                    NumberFormat f = NumberFormat.getInstance();
                    f.setGroupingUsed(false);
                    String StringewTotalMoney = f.format(NewTotalMoney);
                    txt_Total.setText(StringewTotalMoney);

                } catch (Exception err) {
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
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

    public void LoadForListCompanyOrder() throws SQLException, SQLException {

        ArrayList<DTO_CompanyOrder> List_CompanyOrder = bLL_CompanyOrder.GetAllCompaneyOder();
        String[] columeNames = new String[]{"ID", "Supplier Id ", "Employee Id", "TimeStamp ", "VAT", "CK", "TotalMoney", "HavePaid", "Still Owe", "Status", "Description"};
        DefaultTableModel model = new DefaultTableModel(null, columeNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // or a condition at your choice with row and column
            }
        };
        for (int i = 0; i < List_CompanyOrder.size(); i++) {
            // create object(is a row in table) 
            Object[] product = new Object[]{
                List_CompanyOrder.get(i).getId(), List_CompanyOrder.get(i).getSupplierId(), List_CompanyOrder.get(i).getEmployeeId(),
                List_CompanyOrder.get(i).getTimeStamp(),
                List_CompanyOrder.get(i).getVAT(), List_CompanyOrder.get(i).getCK(), List_CompanyOrder.get(i).getTotalMoney(),
                List_CompanyOrder.get(i).getHavePaid(), List_CompanyOrder.get(i).getStillOwe(),
                List_CompanyOrder.get(i).isStatus(), List_CompanyOrder.get(i).getDescription()};
            model.addRow(product);
        }
        Table_ListCompanyOrder.setModel(model);
    }

    public void ClearCompanyInput() {
        cmb_SupplierId.setSelectedIndex(0);
        cmb_Employee.setSelectedIndex(0);
        DefaultTableModel model = (DefaultTableModel) Table_Order_Detail.getModel();
        model.setRowCount(0);
        model.insertRow(Table_Order_Detail.getRowCount(), new Object[]{"", "",
                                "", "", "", "", "", ""});
        txt_VAT.setValue(0D);
        txt_CK.setValue(0D);
        txt_Total.setText("0");
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
        JPane_ListCompanyOrder = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_ListCompanyOrder = new javax.swing.JTable();
        btn_CreateNew = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btn_PayMoney = new javax.swing.JButton();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_Note = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
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
        btn_DeleteRow = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_ListCompanyOrder.setText("<html> <p>List Company Order<p>  </html>");
        btn_ListCompanyOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ListCompanyOrderActionPerformed(evt);
            }
        });

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
        btn_CreateNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CreateNewActionPerformed(evt);
            }
        });

        btn_Update.setText("Update");
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });

        btn_Delete.setText("Delete");

        jLabel2.setText("List Company Order");

        btn_PayMoney.setText("Pay Money");
        btn_PayMoney.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PayMoneyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPane_ListCompanyOrderLayout = new javax.swing.GroupLayout(JPane_ListCompanyOrder);
        JPane_ListCompanyOrder.setLayout(JPane_ListCompanyOrderLayout);
        JPane_ListCompanyOrderLayout.setHorizontalGroup(
            JPane_ListCompanyOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPane_ListCompanyOrderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_CreateNew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_PayMoney)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(170, 170, 170))
            .addGroup(JPane_ListCompanyOrderLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        JPane_ListCompanyOrderLayout.setVerticalGroup(
            JPane_ListCompanyOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPane_ListCompanyOrderLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(JPane_ListCompanyOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_CreateNew)
                    .addComponent(btn_Update)
                    .addComponent(btn_Delete)
                    .addComponent(btn_PayMoney))
                .addGap(32, 32, 32))
        );

        jPanel_showListOrderOrCreateOrder.add(JPane_ListCompanyOrder, "card3");

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
                .addGap(18, 18, 18)
                .addGroup(jPanel_Information1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
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

        txt_Note.setColumns(20);
        txt_Note.setRows(5);
        jScrollPane1.setViewportView(txt_Note);

        jLabel13.setText("Note");

        javax.swing.GroupLayout jPanel_Information3Layout = new javax.swing.GroupLayout(jPanel_Information3);
        jPanel_Information3.setLayout(jPanel_Information3Layout);
        jPanel_Information3Layout.setHorizontalGroup(
            jPanel_Information3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Information3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Information3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Information3Layout.createSequentialGroup()
                        .addGroup(jPanel_Information3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_Information3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_Employee, 0, 165, Short.MAX_VALUE)
                            .addComponent(txt_DateCreateOrder)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Information3Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel_Information3Layout.setVerticalGroup(
            jPanel_Information3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Information3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Information3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_DateCreateOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Information3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmb_Employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_Information3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

        jPanel_CK_VAT.setLayout(new java.awt.GridLayout(1, 0));

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

        btn_DeleteRow.setText("Delete Row");
        btn_DeleteRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteRowActionPerformed(evt);
            }
        });

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelTableListBuyProductLayout = new javax.swing.GroupLayout(jPanelTableListBuyProduct);
        jPanelTableListBuyProduct.setLayout(jPanelTableListBuyProductLayout);
        jPanelTableListBuyProductLayout.setHorizontalGroup(
            jPanelTableListBuyProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addComponent(jPanel_CK_VAT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTableListBuyProductLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_DeleteRow)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(155, 155, 155))
        );
        jPanelTableListBuyProductLayout.setVerticalGroup(
            jPanelTableListBuyProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableListBuyProductLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel_CK_VAT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanelTableListBuyProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Save)
                    .addComponent(btn_DeleteRow)
                    .addComponent(jButton1))
                .addGap(53, 53, 53))
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
                .addComponent(jPanel_Group_information_Order, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelTableListBuyProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134))
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel_showListOrderOrCreateOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jPanel_groupButtonFuction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_CompanyOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CompanyOrderActionPerformed
        // TODO add your handling code here:
        if (!Update) {
            DefaultTableModel model = (DefaultTableModel) Table_Order_Detail.getModel();
            model.setRowCount(0);
            model.insertRow(Table_Order_Detail.getRowCount(), new Object[]{"", "",
                "", "", "", "", "", ""});
        }
        CardLayout cardLayout = (CardLayout) jPanel_showListOrderOrCreateOrder.getLayout();
        cardLayout.show(jPanel_showListOrderOrCreateOrder, "card2");
    }//GEN-LAST:event_btn_CompanyOrderActionPerformed

    private void btn_ListCompanyOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ListCompanyOrderActionPerformed
        // TODO add your handling code here:
        Table_ListCompanyOrder.removeAll();
        try {
            LoadForListCompanyOrder();
        } catch (SQLException ex) {
            Logger.getLogger(GUI_BuyProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        CardLayout cardLayout = (CardLayout) jPanel_showListOrderOrCreateOrder.getLayout();
        cardLayout.show(jPanel_showListOrderOrCreateOrder, "card3");
    }//GEN-LAST:event_btn_ListCompanyOrderActionPerformed

    private void btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveActionPerformed
        // TODO add your handling code here:
        //String[] columeNames = new String[]{"Product Id", "Product Name", "Unit", "Quantity", "cost", "into money", "Note"};
        ArrayList<DTO_CompanyOrderDetail> listProductBuy = new ArrayList<DTO_CompanyOrderDetail>();
        DTO_CompanyOrder companyOder = null;
        try {
            int SupplierId = Integer.parseInt(cmb_SupplierId.getSelectedItem().toString());
            int EmployeeId = ((CustomCombo) (cmb_Employee.getSelectedItem())).getID();
            String DateCreate = txt_DateCreateOrder.getText();

            double VAT = Double.parseDouble(txt_VAT.getValue().toString());
            double CK = Double.parseDouble(txt_CK.getValue().toString());
            double TotalMoney = Double.parseDouble(txt_Total.getText().toString());
            String Description = "";
            try {
                Description = txt_Note.getText().toString();
            } catch (Exception e) {

            }
            if (!Update) {
                companyOder = new DTO_CompanyOrder(SupplierId, EmployeeId, DateCreate, VAT, CK, TotalMoney, Description);
            } else {
                //String[] columeNames = new String[]{"ID", "Supplier Id ", "Employee Id", "TimeStamp ", "VAT", "CK", "TotalMoney", "HavePaid", "Still Owe", "Status", "Description"};
                double HavePaid = Double.parseDouble(Table_ListCompanyOrder.getValueAt(IndexRowInListCompanyOrder, 7).toString());
                companyOder = new DTO_CompanyOrder(CompanyOrderId_Update, SupplierId, EmployeeId, DateCreate, VAT, CK, TotalMoney, HavePaid, Description);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        for (int i = 0; i < Table_Order_Detail.getRowCount(); i++) {
            int ProductId = -1;
            String Unit = "";
            double Quantity = -1;
            double Cost = -1;
            double intoMoney = -1;
            String Note = "";
            try {
                ProductId = Integer.parseInt(Table_Order_Detail.getValueAt(i, 0).toString());

            } catch (Exception e) {

            }
            try {
                Unit = String.valueOf(Table_Order_Detail.getValueAt(i, 2));

            } catch (Exception e) {
            }
            try {
                Quantity = Double.parseDouble(Table_Order_Detail.getValueAt(i, 3).toString());

            } catch (Exception e) {
            }
            try {
                Cost = Double.parseDouble(Table_Order_Detail.getValueAt(i, 4).toString());

            } catch (Exception e) {
            }
            try {
                intoMoney = Double.parseDouble(Table_Order_Detail.getValueAt(i, 5).toString());

            } catch (Exception e) {
            }
            try {
                Note = String.valueOf(Table_Order_Detail.getValueAt(i, 6));
            } catch (Exception e) {
            }
            //if all the variable is default value when create that is blank row
            // if one of those is default value show mess
            if ((ProductId == -1 && Quantity == -1 && Cost == -1 && intoMoney == -1 && Unit.equals(""))) {
                JOptionPane.showMessageDialog(null, "da vao day");
                // if this row is blank row and is the last row we to ignore this row
                // else we show mess error
                if ((i + 1) == Table_Order_Detail.getRowCount()) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "co san pham khong day du thong tin vui long xoa hoac dien day du thong tin");
                    break;
                }
            } else if ((ProductId == -1 || Quantity == -1 || Cost == -1 || intoMoney == -1 || Unit.equals(""))) {
                JOptionPane.showMessageDialog(null, "co san pham khong day du thong tin vui long xoa hoac dien day du thong tin");
                break;
            }
            int supplierId = Integer.parseInt(cmb_SupplierId.getSelectedItem().toString());
            // CompanyOrderId_Update is trash dose not make any sense
            DTO_CompanyOrderDetail companyOrderDetail = new DTO_CompanyOrderDetail(CompanyOrderId_Update,ProductId, Unit, Quantity, Cost, Note);
            listProductBuy.add(companyOrderDetail);
        }

        try {
            if (listProductBuy.size() > 0) {
                if (!Update) {
                    bLL_CompanyOrder.Insert(companyOder, listProductBuy);
                } else {
                    bLL_CompanyOrder.Update_CompanyOrderWithDetail(companyOder, listProductBuy);
                }
            } else {
                JOptionPane.showMessageDialog(null, " Hoa don khong co san pham nao");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUI_BuyProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btn_SaveActionPerformed
//    public void LoadDataCompanyOrderForUpdate(int CompanyOderId) {
//
//    }

    private void btn_DeleteRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteRowActionPerformed
        // TODO add your handling code here:
        try {
            int rowselectted = Table_Order_Detail.getSelectedRow();
            if (rowselectted != -1) {
                int modelIndex = Table_Order_Detail.convertRowIndexToModel(rowselectted); // converts the row index in the view to the appropriate index in the model
                DefaultTableModel model = (DefaultTableModel) Table_Order_Detail.getModel();
                model.removeRow(modelIndex);
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_btn_DeleteRowActionPerformed

    private void btn_CreateNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CreateNewActionPerformed
        // TODO add your handling code here:
        btn_CompanyOrderActionPerformed(evt);
    }//GEN-LAST:event_btn_CreateNewActionPerformed

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        // TODO add your handling code here:
        
        try {

            int IndexRowSelected = Table_ListCompanyOrder.getSelectedRow();
            int CompanyOrderId = -1;
            
            try {
                
                CompanyOrderId = Integer.parseInt(Table_ListCompanyOrder.getValueAt(IndexRowSelected, 0).toString());
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            if (CompanyOrderId != -1) {
                
                // get CompanyOrder
                //String[] columeNames = new String[]{"ID", "Supplier Id ", "Employee Id", "TimeStamp ", "VAT", "CK", "TotalMoney", "HavePaid", "Still Owe", "Status", "Description"};
                int SupplierId = Integer.parseInt(Table_ListCompanyOrder.getValueAt(IndexRowSelected, 1).toString());
                int EmployeeId = Integer.parseInt(Table_ListCompanyOrder.getValueAt(IndexRowSelected, 2).toString());
                String TimeStamp = Table_ListCompanyOrder.getValueAt(IndexRowSelected, 3).toString();
                double VAT = Double.parseDouble(Table_ListCompanyOrder.getValueAt(IndexRowSelected, 4).toString());
                double CK = Double.parseDouble(Table_ListCompanyOrder.getValueAt(IndexRowSelected, 5).toString());

                double TotalMoney = Double.parseDouble(Table_ListCompanyOrder.getValueAt(IndexRowSelected, 6).toString());
                double havePaid = Double.parseDouble(Table_ListCompanyOrder.getValueAt(IndexRowSelected, 7).toString());
                String Description = Table_ListCompanyOrder.getValueAt(IndexRowSelected, 10).toString();
                // set for companyUpdate
                cmb_SupplierId.getModel().setSelectedItem(SupplierId);
                DTO_employee employee = bLL_Employee.GetById(EmployeeId);
                CustomCombo ItemCombo_Employee = new CustomCombo(employee.getId(), employee.getName());
                cmb_Employee.setSelectedItem(ItemCombo_Employee);
                // get list product buy in CompanyOder buy ID
                ArrayList<DTO_CompanyOrderDetail> companyOrderDetails = bLL_CompanyOrderDetail.GetCompanyOrderDetailById(CompanyOrderId);
                DefaultTableModel model = (DefaultTableModel) Table_Order_Detail.getModel();
                NumberFormat f = NumberFormat.getInstance();
                        f.setGroupingUsed(false);
                if (companyOrderDetails.size() > 0) {
                    model.setRowCount(0);
                    for (int i = 0; i < companyOrderDetails.size(); i++) {
                        double Quantity = companyOrderDetails.get(i).getQuantity();
                        double Cost = companyOrderDetails.get(i).getCost();
                        double IntoMoney = Quantity * Cost;
                        
                        model.insertRow(i, new Object[]{"",
                            "",
                            companyOrderDetails.get(i).getProductUnit(), Quantity,
                            Cost, f.format(IntoMoney), companyOrderDetails.get(i).getDescription()});
                        Table_Order_Detail.setValueAt(companyOrderDetails.get(i).getProductId(), i, 0);
                        Table_Order_Detail.setValueAt(f.format(IntoMoney), i, 5);
                    }
                    Update = true;
                    txt_VAT.setValue(VAT);
                    txt_CK.setValue(CK);
                    txt_Total.setText(f.format(TotalMoney).toString());
                    CompanyOrderId_Update = CompanyOrderId;
                    IndexRowInListCompanyOrder = IndexRowSelected;
                    btn_CompanyOrderActionPerformed(evt);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Update = false;
        ClearCompanyInput();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_PayMoneyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PayMoneyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_PayMoneyActionPerformed

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
    private javax.swing.JPanel JPane_ListCompanyOrder;
    private javax.swing.JTable Table_ListCompanyOrder;
    private javax.swing.JTable Table_Order_Detail;
    private javax.swing.JButton btn_CompanyOrder;
    private javax.swing.JButton btn_CreateNew;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_DeleteRow;
    private javax.swing.JButton btn_ListCompanyOrder;
    private javax.swing.JButton btn_PayMoney;
    private javax.swing.JButton btn_Save;
    private javax.swing.JButton btn_Update;
    private javax.swing.JComboBox<String> cmb_Employee;
    private javax.swing.JComboBox<String> cmb_SupplierId;
    private javax.swing.JComboBox<String> cmb_SupplierName;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txt_Address;
    private javax.swing.JFormattedTextField txt_CK;
    private javax.swing.JTextField txt_DateCreateOrder;
    private javax.swing.JTextArea txt_Note;
    private javax.swing.JTextField txt_PhoneNumber;
    private javax.swing.JTextField txt_Total;
    private javax.swing.JFormattedTextField txt_VAT;
    // End of variables declaration//GEN-END:variables
}
