/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Loïc
 */
public class StyleCellules extends DefaultTableCellRenderer{
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
        setFont(new Font("Arial", Font.PLAIN, 12));
        setPreferredSize(new Dimension(60, 40));
        if(column == 2)
            setHorizontalAlignment(JLabel.CENTER);  
        if(column == 0)
            setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.GRAY));      
        return this;
    }
}
