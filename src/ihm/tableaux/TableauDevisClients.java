/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.tableaux;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import modele.DevisClient;
import modele.Sessions;
import modele.Materiel;

/**
 *
 * @author lfabre
 */
public class TableauDevisClients extends AbstractTableModel{
    private ArrayList<DevisClient> donnees;
    private final String[] intitules;
    private Sessions sessionCourante;
    
    public TableauDevisClients(ArrayList<DevisClient> donnees, String[] intitules, Sessions session){
        super();
        sessionCourante = session;
        this.donnees = donnees;
        this.intitules = intitules;  
    }
    @Override
    public boolean isCellEditable(int row, int col){
          return false;
        
    }

    public boolean isCellEditable(EventObject event) {
    return true;
   }
    @Override
    public int getRowCount() {
        return donnees.size();
    }
    @Override
    public int getColumnCount() {
        return intitules.length;
    }
    @Override
    public String getColumnName(int columnIndex) {
        return intitules[columnIndex];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return donnees.get(rowIndex).getNumero();
            case 1:
                return donnees.get(rowIndex).getDate();
            case 2:
                return donnees.get(rowIndex).getChantier();
            default:
                return null; //Ne devrait jamais arriver
        }
    }
    public void setData(ArrayList<DevisClient> newData){
        this.donnees = newData;
        super.fireTableDataChanged();
    }
    
}