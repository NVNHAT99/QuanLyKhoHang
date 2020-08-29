/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultils;

import DTO.Custom_DTO_ForModelTable.DTO_Product_ModelTable;
import java.awt.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class Product_TableModel extends AbstractTableModel {

    private String[] columnNames = {"Id ", "Name", "Price", "Supplier", "Category", "Unit", "UnitsInStock",
        "ImagePath"};
    private ArrayList<DTO_Product_ModelTable> listProduct = new ArrayList<DTO_Product_ModelTable>();

    public Product_TableModel(ArrayList<DTO_Product_ModelTable> listProduct) {
        this.listProduct.addAll(listProduct);
    }

    @Override
    public int getRowCount() {
        return listProduct.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object returnValue = null;
        DTO_Product_ModelTable product_forModelTable = listProduct.get(rowIndex);

        switch (columnIndex) {
            case 0:
                returnValue = rowIndex + 1;
                break;
            case 1:
                returnValue = product_forModelTable.getId();
                break;
            case 2:
                returnValue = product_forModelTable.getName();
                break;
            case 3:
                returnValue = product_forModelTable.getPrice();
                break;
            case 4:
                returnValue = product_forModelTable.getSupplier();
                break;
            case 5:
                returnValue = product_forModelTable.getCategory();
                break;
            case 6:
                returnValue = product_forModelTable.getUnit();
                break;
            case 7:
                returnValue = product_forModelTable.getUnitsInStock();
                break;
            case 8:
                returnValue = product_forModelTable.getImagePath();
                break;
        }

        return returnValue;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        DTO_Product_ModelTable product_ModelTable = listProduct.get(rowIndex);

        switch (columnIndex) {
            case 1:
                product_ModelTable.setId((int) value);
                break;
            case 2:
                product_ModelTable.setName(String.valueOf(value));
                break;
            case 3:
                product_ModelTable.setPrice((float) value);
                break;
            case 4:
                product_ModelTable.setSupplier((CustomCombo) value);
                break;
            case 5:
                product_ModelTable.setCategory((CustomCombo) value);
                break;
            case 6:
                product_ModelTable.setUnit(String.valueOf(value));
                break;
            case 7:
                product_ModelTable.setUnit(String.valueOf(value));
                break;
            case 8:
                product_ModelTable.setImagePath(String.valueOf(value));
                break;
        }
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 0;
    }

}
