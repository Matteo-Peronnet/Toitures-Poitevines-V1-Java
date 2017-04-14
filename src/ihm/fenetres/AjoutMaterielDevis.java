/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.fenetres;

import ihm.panneaux.AjouterBase;
import ihm.renderers.StyleCellules;
import ihm.renderers.StyleEntete;
import ihm.tableaux.TableauMateriel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import modele.Devis;
import modele.DevisContenu;
import modele.Materiel;
import modele.Sessions;

/**
 *
 * @author Travail Ruel
 */
public class AjoutMaterielDevis extends JDialog{
    private boolean envoiInfo;
    private JPanel nomPanel, prenomPanel, groupePanel, contenu, boutons;
    private JLabel tvaLabel, numeroLabel, dateLabel,quantitematBd,nomwLabel,unitewLabel,prixwLabel;
    private JTextField tvaTextField, numeroTextField,dateTextField,quantitematBdTextField,nomwTextField,unitewTextField,prixwTextField;
    private Sessions sessionCourante;
    private Devis devis;
    ArrayList<Materiel> listeMateriel;
    private JButton btnValidermatBd;
    private JButton btnValiderMatWr;
    private JScrollPane soutPanel;
    private JTable tabSout;
    private JComboBox typeMateriel,typeMaterielManuel;
    private JLabel titreLabel;
    private int indexTypeMateriel;
    private int selectedIndex;
    private JFrame parent;
    public AjoutMaterielDevis(JFrame parent,String title, boolean modal, Sessions session,int selectedIndex) throws IOException {
        super(parent, title, modal);
        this.parent=parent;
        sessionCourante = session;
        this.devis=devis;
        this.selectedIndex=selectedIndex;
        this.setTitle(title);
        this.setSize(1000, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setLocation(100, 100);
        initContenu();
        envoiInfo = false;
        this.setVisible(true);
    }

    private void initContenu() throws IOException {
        soutPanel = new JScrollPane();
        btnValidermatBd = new JButton();
        btnValiderMatWr = new JButton();
        String[] choix = new String[] {"CouvertureArdoise", "CouvertureTuile","Demoussage","EquipementDeChantier","Velux","Zinguerie","Couverture","Plancher"};
        
        typeMateriel = new JComboBox(choix);
        typeMateriel.setSelectedIndex(selectedIndex);
        
        typeMateriel.setFont(new Font("Arial", Font.PLAIN, 15));
        typeMateriel.setBackground(Color.WHITE);
        typeMateriel.setPreferredSize(new Dimension(160, 40));
        titreLabel = new JLabel("Type Materiel : ");
        titreLabel.setFont(new Font("Arial", Font.BOLD, 16)); 
        
        switch(typeMateriel.getSelectedIndex()){
                   
                    case 0:listeMateriel = sessionCourante.getRecupDonnees().recupererCouvertureArdoise();break;
                    case 1:listeMateriel = sessionCourante.getRecupDonnees().recupererCouvertureTuile();break;
                    case 2:listeMateriel = sessionCourante.getRecupDonnees().recupererDemoussage();break;
                    case 3:listeMateriel = sessionCourante.getRecupDonnees().recupererEquipementDeChantier();break;
                    case 4:listeMateriel = sessionCourante.getRecupDonnees().recupererVelux();break;
                    case 5:listeMateriel = sessionCourante.getRecupDonnees().recupererZinguerie();break;
                    case 6:listeMateriel = sessionCourante.getRecupDonnees().recupererCouverture();break;
                    case 7:listeMateriel = sessionCourante.getRecupDonnees().recupererPlancher();break;
                 
        }
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
        soutPanel.setViewportView(this.tabSout);
        
        btnValidermatBd.setText("Ajouter");
        
        btnValidermatBd.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(quantitematBdTextField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Tu as oublie la quantite", "ATTENTION",JOptionPane.INFORMATION_MESSAGE);

                }else{
                    int index = typeMateriel.getSelectedIndex();
                    int selectedRow = tabSout.getSelectedRow();
                    
                
                    for (Materiel materiel : listeMateriel) {
                        if(model.getValueAt(selectedRow, 0) == materiel.getNom()){
                            if(model.getValueAt(selectedRow,1) == materiel.getUnite()){
                                if((double)model.getValueAt(selectedRow, 2)== materiel.getPrix()){
                                    try {
                                        double calcul;
                                        calcul= materiel.getPrix()*Double.parseDouble(quantitematBdTextField.getText());
                                        double prixPVT = Math.round(calcul * Math.pow(10,2)) / Math.pow(10,2);
                                        
                                        DevisContenu newContenuDevis = new DevisContenu(new Materiel(materiel.getNom(),materiel.getUnite(),materiel.getPrix()),Double.parseDouble(quantitematBdTextField.getText()),prixPVT,typeMateriel.getSelectedItem().toString());
                                        sessionCourante.getSaveDonnees().enregistrerNouvelleInformationDevis(newContenuDevis);
                                        sessionCourante.getRecupDonnees().addInformationDevis(newContenuDevis);
                                        selectedIndex = typeMateriel.getSelectedIndex();
                                        setVisible(false);
                                    } catch (IOException ex) {
                                        Logger.getLogger(AjoutMaterielDevis.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }
                    }
                    
                
                }   
                
                
            }});
        
        btnValiderMatWr.setText("Ajouter");
        btnValiderMatWr.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(quantitematBdTextField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Tu as oublie la quantite", "ATTENTION",JOptionPane.INFORMATION_MESSAGE);

                }else{
                    
                    if(nomwTextField.getText().equals("")||unitewTextField.getText().equals("")||prixwTextField.getText().equals("")||quantitematBdTextField.getText().equals("")){
                      JOptionPane.showMessageDialog(null, "Un des champs n'a pas ete renseigner", "ATTENTION",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        try {
                            double calcul;
                            calcul= Double.parseDouble(prixwTextField.getText())*Double.parseDouble(quantitematBdTextField.getText());
                            double prixPVT = Math.round(calcul * Math.pow(10,2)) / Math.pow(10,2);
                            
                            DevisContenu newContenuDevis = new DevisContenu(new Materiel(nomwTextField.getText(),unitewTextField.getText(),Double.parseDouble(prixwTextField.getText())),Double.parseDouble(quantitematBdTextField.getText()),prixPVT,typeMaterielManuel.getSelectedItem().toString());
                            sessionCourante.getSaveDonnees().enregistrerNouvelleInformationDevis(newContenuDevis);
                            sessionCourante.getRecupDonnees().addInformationDevis(newContenuDevis);
                            selectedIndex = typeMaterielManuel.getSelectedIndex();
                            setVisible(false);
                        } catch (IOException ex) {
                            Logger.getLogger(AjoutMaterielDevis.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                }   
                
                
            }});
        
        soutPanel = new JScrollPane(tabSout);
        soutPanel.getViewport().add(tabSout);
        soutPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.GRAY), "Informations"
                + "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 17), Color.DARK_GRAY));
        soutPanel.setPreferredSize(new Dimension(600, 300));
        soutPanel.setBackground(new Color(238, 238, 238));
       
        quantitematBd = new JLabel();
        quantitematBd.setText("Quantite : ");
        
        nomwLabel = new JLabel();
        nomwLabel.setText("Nom :");
        
        unitewLabel = new JLabel();
        unitewLabel.setText("Unite :");
        
        prixwLabel = new JLabel();
        prixwLabel.setText("Prix :");
        
        nomwTextField = new JTextField();
        nomwTextField.setPreferredSize(new Dimension(200, 80));
        unitewTextField = new JTextField();
        unitewTextField.setPreferredSize(new Dimension(60, 80));
        prixwTextField = new JTextField();
        prixwTextField.setPreferredSize(new Dimension(60, 80));
        quantitematBdTextField = new JTextField();
        quantitematBdTextField.setPreferredSize(new Dimension(60, 80));
        
        
        typeMaterielManuel = new JComboBox(choix);
        typeMaterielManuel.setSelectedIndex(selectedIndex);
        
        JPanel matBd = new JPanel();
        matBd.add(titreLabel,BorderLayout.NORTH);
        matBd.add(typeMateriel,BorderLayout.CENTER);
        matBd.add(soutPanel,BorderLayout.SOUTH);
        matBd.add(btnValidermatBd,BorderLayout.SOUTH);
        
        JPanel matWrite = new JPanel();
        matWrite.add(typeMaterielManuel,BorderLayout.NORTH);
        matWrite.add(nomwLabel,BorderLayout.NORTH);
        matWrite.add(nomwTextField,BorderLayout.NORTH);
        matWrite.add(unitewLabel,BorderLayout.CENTER);
        matWrite.add(unitewTextField,BorderLayout.CENTER);
        matWrite.add(prixwLabel,BorderLayout.SOUTH);
        matWrite.add(prixwTextField,BorderLayout.SOUTH);
        
        JPanel General = new JPanel();
        JLabel ajoutbd = new JLabel(new ImageIcon("bdadd.png"));
        ajoutbd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(!(nomwTextField.getText().length()==0 || unitewTextField.getText().length()==0 || prixwTextField.getText().length()==0)){
                JOptionPane d = new JOptionPane();
                 int retour = d.showConfirmDialog(null,"Veux tu l'ajouter a la base de donnée ?", "ATTENTION",JOptionPane.YES_NO_OPTION);
                 if(retour==JOptionPane.YES_OPTION){
                    try {
                        Materiel newMateriel = new Materiel(nomwTextField.getText(),unitewTextField.getText(),Double.parseDouble(prixwTextField.getText()));
                        switch(typeMaterielManuel.getSelectedIndex()){
                            case 0:sessionCourante.getSaveDonnees().enregistrerNouveauMaterielCouvertureArdoise(newMateriel);break;
                            case 1:sessionCourante.getSaveDonnees().enregistrerNouveauMaterielCouvertureTuile(newMateriel);break;
                            case 2:sessionCourante.getSaveDonnees().enregistrerNouveauMaterielDemoussage(newMateriel);break;
                            case 3:sessionCourante.getSaveDonnees().enregistrerNouveauMaterielEquipementDeChantier(newMateriel);break;
                            case 4:sessionCourante.getSaveDonnees().enregistrerNouveauMaterielVelux(newMateriel);break;
                            case 5:sessionCourante.getSaveDonnees().enregistrerNouveauMaterielZinguerie(newMateriel);break;
                            case 6:sessionCourante.getSaveDonnees().enregistrerNouveauMaterielCouverture(newMateriel);break;
                            case 7:sessionCourante.getSaveDonnees().enregistrerNouveauMaterielPlancher(newMateriel);break;
                        }
                        JOptionPane c = new JOptionPane();
                        c.showMessageDialog(null,"Matériel ajouté !","Information",JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException ex) {
                        Logger.getLogger(AjoutMaterielDevis.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 }else{
                     
                 }
                }else{
                    JOptionPane c = new JOptionPane();
                    c.showMessageDialog(null,"Informations vide","ATTENTION",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        General.add(matBd,BorderLayout.NORTH);
        General.add(matWrite,BorderLayout.CENTER);
        General.add(btnValiderMatWr,BorderLayout.SOUTH);
        General.add(quantitematBd,BorderLayout.PAGE_END);
        General.add(quantitematBdTextField,BorderLayout.PAGE_END);
        General.add(ajoutbd, BorderLayout.PAGE_END);
        this.add(General);
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }
    
    
    
}
