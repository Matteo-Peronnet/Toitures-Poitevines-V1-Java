/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.fenetres;

import ihm.panneaux.AjouterBase;
import ihm.renderers.StyleCellules;
import ihm.renderers.StyleEntete;
import ihm.tableaux.TableauClient;
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
import javax.swing.Box;
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
import javax.swing.RowSorter;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableRowSorter;
import modele.Client;
import modele.Devis;
import modele.Materiel;
import modele.Sessions;

/**
 *
 * @author Travail Ruel
 */
public class RechercheClientDialog extends JDialog{
    private boolean envoiInfo;
    private JPanel nomPanel, prenomPanel, groupePanel, contenu, JPboutons;
    private JLabel tvaLabel, numeroLabel, dateLabel;
    private JTextField tvaTextField, numeroTextField,dateTextField;
    private Sessions sessionCourante;
    private Devis devis;
    ArrayList<Client> listeClients;
    private JButton btnAjouter;
    private JButton btnSupprimer;
    private JButton btnValider;
    private JScrollPane soutPanel;
    private JTable tabSout;
    private JComboBox typeMateriel;
    private JLabel titreLabel;
    
     public RechercheClientDialog(JFrame parent,Devis devis,String title, boolean modal, Sessions session) throws IOException{  
        super(parent, title, modal);
        sessionCourante = session;
        this.devis=devis;
        this.setTitle(title);
        this.setSize(900, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initContenu();
        
        envoiInfo = false;
        this.setVisible(true);
    }    
     
    private void initContenu() throws IOException{
        soutPanel = new JScrollPane();
        btnAjouter = new JButton();
        btnSupprimer = new JButton();
        btnValider = new JButton();
        btnValider.setText("Valider");
        
        titreLabel = new JLabel("Recherche Client");
        titreLabel.setFont(new Font("Arial", Font.BOLD, 16)); 
        listeClients = sessionCourante.getRecupDonnees().recupererClients();
        String[] intitules = {"Nom", "Prenom", "Commune","Adresse","Numero","Code Postal","Email"};
        //tabSout = new JTable(new TableauMateriel(listeMateriel, intitules, sessionCourante));
        TableauClient model = new TableauClient(listeClients, intitules, sessionCourante);
        tabSout= new JTable(model);
        model.setData(listeClients);
        
        for(int numColonne = 0; numColonne < tabSout.getColumnCount(); numColonne++){
            tabSout.getColumnModel().getColumn(numColonne).setCellRenderer(new StyleCellules());     
            tabSout.getColumnModel().getColumn(numColonne).setHeaderRenderer(new StyleEntete()); 
        }
        tabSout.getColumnModel().getColumn(0).setPreferredWidth(80);
        tabSout.getColumnModel().getColumn(1).setPreferredWidth(80);   
        tabSout.getColumnModel().getColumn(2).setPreferredWidth(200);
        tabSout.getColumnModel().getColumn(3).setPreferredWidth(300);
        tabSout.getColumnModel().getColumn(4).setPreferredWidth(100);   
        tabSout.getColumnModel().getColumn(5).setPreferredWidth(80);
        tabSout.getColumnModel().getColumn(6).setPreferredWidth(100); 
        soutPanel.setViewportView(this.tabSout);
        RowSorter<TableauClient> sorter = new TableRowSorter<TableauClient>(model);
        tabSout.setRowSorter(sorter);
        btnAjouter.setText("Ajouter Client");
        
        btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
      JTextField nomField = new JTextField(5);
      JTextField prenomField = new JTextField(5);
      JTextField commune = new JTextField(5);
      JTextField adresse = new JTextField(5);       
      JTextField numero = new JTextField(5);
      JTextField codePostale = new JTextField(5);
      JTextField email = new JTextField(5);
              
      JPanel myPanelTop = new JPanel();
      myPanelTop.add(new JLabel("Nom :"));
      myPanelTop.add(nomField);
      myPanelTop.add(Box.createHorizontalStrut(15)); // a spacer
      myPanelTop.add(new JLabel("Prenom :"));
      myPanelTop.add(prenomField);
      myPanelTop.add(Box.createHorizontalStrut(15)); // a spacer
      myPanelTop.add(new JLabel("Commune :"));
      myPanelTop.add(commune);
      myPanelTop.add(Box.createHorizontalStrut(15)); // a spacer
      
      JPanel myPanelCenter = new JPanel();
      myPanelCenter.add(new JLabel("Adresse :"));
      myPanelCenter.add(adresse);
      myPanelCenter.add(Box.createHorizontalStrut(15)); // a spacer
      myPanelCenter.add(new JLabel("Tel :"));
      myPanelCenter.add(numero);
      myPanelCenter.add(Box.createHorizontalStrut(15)); // a spacer
      myPanelCenter.add(new JLabel("CodePostale :"));
      myPanelCenter.add(codePostale);     
      
      
      JPanel myPanelBot = new JPanel();
      myPanelBot.add(Box.createHorizontalStrut(15)); // a spacer
      myPanelBot.add(new JLabel("Email :"));
      myPanelBot.add(email);
      
      JPanel myPanelGeneral = new JPanel();
      myPanelGeneral.add(myPanelTop,BorderLayout.NORTH);
      myPanelGeneral.add(myPanelCenter,BorderLayout.CENTER);
      myPanelGeneral.add(myPanelBot,BorderLayout.SOUTH);
      
      int result = JOptionPane.showConfirmDialog(null, myPanelGeneral, 
               "Merci d'entrer les informations", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
          
          try {
              if(nomField.getText().equals("")){
                  nomField.setText(" ");
              }
              if(prenomField.getText().equals("")){
                  prenomField.setText(" ");
              }
              if(commune.getText().equals("")){
                  commune.setText(" ");
              }
              if(adresse.getText().equals("")){
                  adresse.setText(" ");
              }
              if(numero.getText().equals("")){
                  numero.setText("0000000000");
              }
              if(codePostale.getText().equals("")){
                  codePostale.setText("00000");
              }
              if(email.getText().equals("")){
                  email.setText(" ");
              }
              
              Client newClient = new Client(nomField.getText(),prenomField.getText(),commune.getText(),adresse.getText(),Integer.parseInt(numero.getText()),Integer.parseInt(codePostale.getText()),email.getText());
              
              sessionCourante.getSaveDonnees().enregistrerNouveauClient(newClient);
              sessionCourante.getRecupDonnees().addClient(newClient);
              listeClients = sessionCourante.getRecupDonnees().recupererClients();
              model.setData(listeClients);
          } catch (IOException ex) {
              Logger.getLogger(RechercheClientDialog.class.getName()).log(Level.SEVERE, null, ex);
          }
                    
      }                                  
      }

            
        });
        btnSupprimer.setText("Supprimer Client");
        btnSupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int reponse = JOptionPane.showConfirmDialog(null,
			       "Pascal tu es sur de supprimer ce client ?",
			       "Attention", 
			        JOptionPane.YES_NO_OPTION);
	  if (reponse == JOptionPane.YES_OPTION){
                try {
                    sessionCourante.getRecupDonnees().supprimerClient(tabSout.getSelectedRow());
                    model.setData(listeClients);
                    
                } catch (IOException ex) {
                    Logger.getLogger(AjouterBase.class.getName()).log(Level.SEVERE, null, ex);
                }
          }}});
        
        btnValider.addActionListener(new ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        
        if(tabSout.getSelectedRow()!=-1){
            for (Client listeClient : listeClients) {
                if(listeClient.getNom()==model.getValueAt(tabSout.getSelectedRow(), 0)){
                    if(listeClient.getPrenom()==model.getValueAt(tabSout.getSelectedRow(), 1)){
                        if(listeClient.getCommune()==model.getValueAt(tabSout.getSelectedRow(), 2)){
                            if(listeClient.getAdresse()==model.getValueAt(tabSout.getSelectedRow(), 3)){
                                devis.setClient(listeClient);
                                setVisible(false);
                            }
                        }
                    }
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
        
        JPboutons = new JPanel();
        JPboutons.add(btnAjouter);
        JPboutons.add(btnSupprimer);
        JPboutons.add(btnValider);
        this.add(titreLabel,BorderLayout.NORTH);
        this.add(soutPanel, BorderLayout.CENTER);
        this.add(JPboutons,BorderLayout.SOUTH);
         
    }
}
