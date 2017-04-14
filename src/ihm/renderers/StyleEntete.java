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
public class StyleEntete extends DefaultTableCellRenderer{
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
        setFont(new Font("Arial", Font.BOLD, 12));
        setHorizontalAlignment(JLabel.CENTER);         
        setBackground(Color.LIGHT_GRAY);
        if(value.equals("Nombre de places restantes"))
            setPreferredSize(new Dimension(120, 25));
        if(column == 0)
            setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
        else 
            setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.GRAY));
        return this;
    }
    
}
