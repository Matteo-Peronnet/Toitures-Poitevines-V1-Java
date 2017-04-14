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
import modele.Sessions;
import modele.Materiel;

/**
 *
 * @author lfabre
 */
public class TableauMateriel extends AbstractTableModel{
    private ArrayList<Materiel> donnees;
    private final String[] intitules;
    private Sessions sessionCourante;
    
    public TableauMateriel(ArrayList<Materiel> donnees, String[] intitules, Sessions session){
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
                return donnees.get(rowIndex).getUnite();
            case 2:
                return donnees.get(rowIndex).getPrix();
            default:
                return null; //Ne devrait jamais arriver
        }
    }
    public void setData(ArrayList<Materiel> newData){
        this.donnees = newData;
        super.fireTableDataChanged();
    }
     public void removeUnite(int rowIndex) {
        
        donnees.remove(rowIndex);
        
        super.fireTableRowsDeleted(rowIndex, rowIndex);
    }

    @Override
        public void setValueAt(Object value, int row, int col) {
                
        try {
            String valeur = getValueAt(row, 0).toString();
            String unite = getValueAt(row, 1).toString();
            String prix = getValueAt(row, 2).toString();
            String Categorie = sessionCourante.getRecupDonnees().recupererCategorieMateriel(valeur,unite,Double.parseDouble(prix));
            
            switch(col)
            {
                case 0 : ((Materiel)donnees.get(row)).setNom((String)value);break;
                case 1 : ((Materiel)donnees.get(row)).setUnite((String)value);break;
                case 2 : ((Materiel)donnees.get(row)).setPrix(Double.parseDouble((String) value));break;
            }
            switch(Categorie){
                case "CouvertureArdoise":sessionCourante.getSaveDonnees().enregistrementModificationCouvertureArdoise(row,donnees.get(row).getNom(),donnees.get(row).getUnite(),donnees.get(row).getPrix());break;
                case "CouvertureTuile":sessionCourante.getSaveDonnees().enregistrementModificationCouvertureTuile(row,donnees.get(row).getNom(),donnees.get(row).getUnite(),donnees.get(row).getPrix());break;
                case "Demoussage":sessionCourante.getSaveDonnees().enregistrementModificationDemoussage(row,donnees.get(row).getNom(),donnees.get(row).getUnite(),donnees.get(row).getPrix());break;
                case "EquipementDeChantier":sessionCourante.getSaveDonnees().enregistrementModificationEquipementDeChantier(row,donnees.get(row).getNom(),donnees.get(row).getUnite(),donnees.get(row).getPrix());break;
                case "Velux":sessionCourante.getSaveDonnees().enregistrementModificationVelux(row,donnees.get(row).getNom(),donnees.get(row).getUnite(),donnees.get(row).getPrix());break;
                case "Zinguerie":sessionCourante.getSaveDonnees().enregistrementModificationZinguerie(row,donnees.get(row).getNom(),donnees.get(row).getUnite(),donnees.get(row).getPrix());break;
                case "Couverture":sessionCourante.getSaveDonnees().enregistrementModificationCouverture(row,donnees.get(row).getNom(),donnees.get(row).getUnite(),donnees.get(row).getPrix());break;
                case "Plancher":sessionCourante.getSaveDonnees().enregistrementModificationPlancher(row,donnees.get(row).getNom(),donnees.get(row).getUnite(),donnees.get(row).getPrix());break;
            }
            fireTableCellUpdated(row,col);
        } catch (IOException ex) {
            Logger.getLogger(TableauMateriel.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
    
}