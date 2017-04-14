/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.tableaux;

import java.util.ArrayList;
import java.util.EventObject;
import javax.swing.table.AbstractTableModel;
import modele.Client;
import modele.Sessions;
import modele.Materiel;

/**
 *
 * @author lfabre
 */
public class TableauClient extends AbstractTableModel{
    private ArrayList<Client> donnees;
    private final String[] intitules;
    private Sessions sessionCourante;
    
    public TableauClient(ArrayList<Client> donnees, String[] intitules, Sessions session){
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
                return donnees.get(rowIndex).getNom();
            case 1:
                return donnees.get(rowIndex).getPrenom();
            case 2:
                return donnees.get(rowIndex).getCommune();
            case 3:
                return donnees.get(rowIndex).getAdresse();
            case 4:
                return donnees.get(rowIndex).getNumero();
            case 5:
                return donnees.get(rowIndex).getCodePostale();
            case 6:
                return donnees.get(rowIndex).getEmail();
            default:
                return null; //Ne devrait jamais arriver
        }
    }
    public void setData(ArrayList<Client> newData){
        this.donnees = newData;
        super.fireTableDataChanged();
    }
     public void removeUnite(int rowIndex) {
        
        donnees.remove(rowIndex);
        
        super.fireTableRowsDeleted(rowIndex, rowIndex);
    }

}