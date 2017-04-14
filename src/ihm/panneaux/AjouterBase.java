/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.panneaux;

//import iutlr.dutinfo.bd.AccesBD;
import ihm.renderers.StyleCellules;
import ihm.renderers.StyleEntete;
import ihm.tableaux.TableauClient;
import ihm.tableaux.TableauMateriel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import modele.Sessions;
import modele.Materiel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author tpoyault
 */
public class AjouterBase extends JPanel {
    ArrayList<Materiel> listeMateriel;
    private JButton btnAjouter;
    private JButton btnSupprimer;
    private JScrollPane soutPanel;
    private JTable tabSout;
    private Sessions sessionCourante;
    private JComboBox typeMateriel;
    private JLabel typeMaterielLabel;
    
    public AjouterBase(Sessions session) throws IOException {
        sessionCourante = session;
        
        setPreferredSize(new Dimension(450, 500));
        initComponents();
    }
                      
    private void initComponents() throws IOException {
        soutPanel = new JScrollPane();
        btnAjouter = new JButton();
        btnSupprimer = new JButton();
        String[] choix = new String[] {"CouvertureArdoise", "CouvertureTuile","Demoussage","EquipementDeChantier","Velux","Zinguerie","Couverture","Plancher"};
        typeMateriel = new JComboBox(choix);
        typeMateriel.setSelectedIndex(0);
        typeMateriel.setFont(new Font("Arial", Font.PLAIN, 15));
        typeMateriel.setBackground(Color.WHITE);
        typeMateriel.setPreferredSize(new Dimension(160, 40));
        typeMaterielLabel = new JLabel("Type Materiel : ");
        typeMaterielLabel.setFont(new Font("Arial", Font.BOLD, 16)); 
        listeMateriel = sessionCourante.getRecupDonnees().recupererCouvertureArdoise();
        String[] intitules = {"Nom", "Unite", "Prix"};
        //tabSout = new JTable(new TableauMateriel(listeMateriel, intitules, sessionCourante));
        TableauMateriel model = new TableauMateriel(listeMateriel, intitules, sessionCourante);
        tabSout= new JTable(model);
        model.setData(listeMateriel);
        typeMateriel.addActionListener(new ActionListener() {//add actionlistner to listen for change
        
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int index = typeMateriel.getSelectedIndex();
                switch(index){
                   
                    case 0:listeMateriel = sessionCourante.getRecupDonnees().recupererCouvertureArdoise();model.setData(listeMateriel);break;
                    case 1:listeMateriel = sessionCourante.getRecupDonnees().recupererCouvertureTuile();model.setData(listeMateriel);break;
                    case 2:listeMateriel = sessionCourante.getRecupDonnees().recupererDemoussage();model.setData(listeMateriel);break;
                    case 3:listeMateriel = sessionCourante.getRecupDonnees().recupererEquipementDeChantier();model.setData(listeMateriel);break;
                    case 4:listeMateriel = sessionCourante.getRecupDonnees().recupererVelux();model.setData(listeMateriel);break;
                    case 5:listeMateriel = sessionCourante.getRecupDonnees().recupererZinguerie();model.setData(listeMateriel);break;
                    case 6:listeMateriel = sessionCourante.getRecupDonnees().recupererCouverture();model.setData(listeMateriel);break;
                    case 7:listeMateriel = sessionCourante.getRecupDonnees().recupererPlancher();model.setData(listeMateriel);break;
                }
            } catch (IOException ex) {
                Logger.getLogger(AjouterBase.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
        });


              

        
        for(int numColonne = 0; numColonne < tabSout.getColumnCount(); numColonne++){
            tabSout.getColumnModel().getColumn(numColonne).setCellRenderer(new StyleCellules());     
            tabSout.getColumnModel().getColumn(numColonne).setHeaderRenderer(new StyleEntete()); 
        }
        tabSout.getColumnModel().getColumn(0).setPreferredWidth(300);
        tabSout.getColumnModel().getColumn(1).setPreferredWidth(200);   
        tabSout.getColumnModel().getColumn(2).setPreferredWidth(100); 
        RowSorter<TableauMateriel> sorter = new TableRowSorter<TableauMateriel>(model);
        tabSout.setRowSorter(sorter);
        soutPanel.setViewportView(this.tabSout);
        
        btnAjouter.setText("Ajouter Ligne");
        
        btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int i=0;
                JOptionPane valide = new JOptionPane();
                String nom = null,unite = null,prix = null;
                if(i==0){
                    nom = (String)JOptionPane.showInputDialog( null, "Entrez le nom :", "Ajout Nom", JOptionPane.PLAIN_MESSAGE);
                    i++;
                }
                if(i==1){
                  unite = (String)JOptionPane.showInputDialog( null, "Entrez l'unité :", "Ajout Unite", JOptionPane.PLAIN_MESSAGE);
                  i++;
                }
                if(i==2){
                  prix = (String)JOptionPane.showInputDialog( null, "Entrez le prix :", "Ajout Prix", JOptionPane.PLAIN_MESSAGE);
                  i++;
                }
                if(i==3 && nom!=null && unite != null && prix != null){
         
                    try {
                    Materiel newMateriel = new Materiel(nom,unite,Double.parseDouble(prix));
                    switch(typeMateriel.getSelectedIndex()){
        case 0: sessionCourante.getSaveDonnees().enregistrerNouveauMaterielCouvertureArdoise(newMateriel);
                                sessionCourante.getRecupDonnees().addMaterielCouvertureArdoise(newMateriel);
                                listeMateriel = sessionCourante.getRecupDonnees().recupererCouvertureArdoise();
                                model.setData(listeMateriel);
                                break;
                        case 1: sessionCourante.getSaveDonnees().enregistrerNouveauMaterielCouvertureTuile(newMateriel);
                                sessionCourante.getRecupDonnees().addMaterielCouvertureTuile(newMateriel);
                                listeMateriel = sessionCourante.getRecupDonnees().recupererCouvertureTuile();
                                model.setData(listeMateriel);
                                break;
                                
                        case 2: sessionCourante.getSaveDonnees().enregistrerNouveauMaterielDemoussage(newMateriel);
                                sessionCourante.getRecupDonnees().addMaterielDemoussage(newMateriel);
                                listeMateriel = sessionCourante.getRecupDonnees().recupererDemoussage();
                                model.setData(listeMateriel);
                                break;
                                
                        case 3: sessionCourante.getSaveDonnees().enregistrerNouveauMaterielEquipementDeChantier(newMateriel);
                                sessionCourante.getRecupDonnees().addMaterielEquipementDeChantier(newMateriel);
                                listeMateriel = sessionCourante.getRecupDonnees().recupererEquipementDeChantier();
                                model.setData(listeMateriel);
                                break;
                                
                        case 4: sessionCourante.getSaveDonnees().enregistrerNouveauMaterielVelux(newMateriel);
                                sessionCourante.getRecupDonnees().addMaterielVelux(newMateriel);
                                listeMateriel = sessionCourante.getRecupDonnees().recupererVelux();
                                model.setData(listeMateriel);
                                break;
                        case 5: sessionCourante.getSaveDonnees().enregistrerNouveauMaterielZinguerie(newMateriel);
                                sessionCourante.getRecupDonnees().addMaterielZinguerie(newMateriel);
                                listeMateriel = sessionCourante.getRecupDonnees().recupererZinguerie();
                                model.setData(listeMateriel);
                                break;
                        case 6: sessionCourante.getSaveDonnees().enregistrerNouveauMaterielCouverture(newMateriel);
                                sessionCourante.getRecupDonnees().addMaterielCouverture(newMateriel);
                                listeMateriel = sessionCourante.getRecupDonnees().recupererCouverture();
                                model.setData(listeMateriel);
                                break;
                        case 7: sessionCourante.getSaveDonnees().enregistrerNouveauMaterielPlancher(newMateriel);
                                sessionCourante.getRecupDonnees().addMaterielPlancher(newMateriel);
                                listeMateriel = sessionCourante.getRecupDonnees().recupererPlancher();
                                model.setData(listeMateriel);
                                break;
                    }                
                  valide.showMessageDialog(null, "Informations ajouté : " + nom + " , " + unite  + " , " + prix, "Validation", JOptionPane.INFORMATION_MESSAGE);                    
                } catch (IOException ex) {
                    Logger.getLogger(AjouterBase.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                }else{
                    valide.showMessageDialog(null, "Erreur lors de l'ajout", "Erreur", JOptionPane.WARNING_MESSAGE);
                }
                                    
                
                
            }

            
        });
        btnSupprimer.setText("Supprimer Ligne");
        btnSupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int reponse = JOptionPane.showConfirmDialog(null,
			       "Pascal tu es sur de supprimer la ligne ?",
			       "Attention", 
			        JOptionPane.YES_NO_OPTION);
	  if (reponse == JOptionPane.YES_OPTION){
                try {
                    int index = typeMateriel.getSelectedIndex();
                    switch(index){
                        
                        case 0:listeMateriel = sessionCourante.getRecupDonnees().supprimerCouvertureArdoise(tabSout.getSelectedRow());break;
                        case 1:listeMateriel = sessionCourante.getRecupDonnees().supprimerCouvertureTuile(tabSout.getSelectedRow());break;
                        case 2:listeMateriel = sessionCourante.getRecupDonnees().supprimerDemoussage(tabSout.getSelectedRow());break;
                        case 3:listeMateriel = sessionCourante.getRecupDonnees().supprimerEquipementDeChantier(tabSout.getSelectedRow());break;
                        case 4:listeMateriel = sessionCourante.getRecupDonnees().supprimerVelux(tabSout.getSelectedRow());break;
                        case 5:listeMateriel = sessionCourante.getRecupDonnees().supprimerZinguerie(tabSout.getSelectedRow());break;
                        case 6:listeMateriel = sessionCourante.getRecupDonnees().supprimerCouverture(tabSout.getSelectedRow());break;
                        case 7:listeMateriel = sessionCourante.getRecupDonnees().supprimerPlancher(tabSout.getSelectedRow());break;
                    }
                    model.setData(listeMateriel);
                } catch (IOException ex) {
                    Logger.getLogger(AjouterBase.class.getName()).log(Level.SEVERE, null, ex);
                }
          }}});
        
        soutPanel = new JScrollPane(tabSout);
        soutPanel.getViewport().add(tabSout);
        soutPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.GRAY), "Informations"
                + "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 17), Color.DARK_GRAY));
        soutPanel.setPreferredSize(new Dimension(600, 300));
        soutPanel.setBackground(new Color(238, 238, 238));
       
        this.add(typeMaterielLabel,BorderLayout.NORTH);
        this.add(typeMateriel,BorderLayout.NORTH);
        this.add(soutPanel, BorderLayout.CENTER);
        this.add(btnAjouter, BorderLayout.PAGE_END);
        this.add(btnSupprimer, BorderLayout.PAGE_END);
        
    }                
}