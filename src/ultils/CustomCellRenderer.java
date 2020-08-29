/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultils;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Administrator
 */
public class CustomCellRenderer extends DefaultTableCellRenderer{
    
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof CustomCombo) {
            CustomCombo object = (CustomCombo) value;
            setText(object.getLabel());
        }
         
        return this;
    }
    
}
