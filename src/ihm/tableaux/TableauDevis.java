/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.tableaux;

import java.util.ArrayList;
import java.util.EventObject;
import javax.swing.table.AbstractTableModel;
import modele.DevisContenu;
import modele.Sessions;
import modele.Materiel;

/**
 *
 * @author lfabre
 */
public class TableauDevis extends AbstractTableModel{
    private ArrayList<DevisContenu> donnees;
    private final String[] intitules;
    private Sessions sessionCourante;
    
    public TableauDevis(ArrayList<DevisContenu> donnees, String[] intitules, Sessions session){
        super();
        sessionCourante = session;
        this.donnees = donnees;
        this.intitules = intitules;  
    }
    @Override
    public boolean isCellEditable(int row, int col){
          return true;
        
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
                return donnees.get(rowIndex).getMateriel().getNom();
            case 1:
                return donnees.get(rowIndex).getMateriel().getUnite();
            case 2:
                return donnees.get(rowIndex).getQuantite();
            case 3:
                return donnees.get(rowIndex).getMateriel().getPrix();
            case 4:
                return donnees.get(rowIndex).getPvtHT();
            case 5:
                return donnees.get(rowIndex).getCategorie();
            default:
                return null; //Ne devrait jamais arriver
        }
    }
    public void setData(ArrayList<DevisContenu> newData){
        this.donnees = newData;
        super.fireTableDataChanged();
    }
     public void removeUnite(int rowIndex) {
        
        donnees.remove(rowIndex);
        
        super.fireTableRowsDeleted(rowIndex, rowIndex);
    }

}