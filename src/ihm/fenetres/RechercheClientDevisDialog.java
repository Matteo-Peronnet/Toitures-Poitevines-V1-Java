/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.fenetres;

import com.itextpdf.text.pdf.PdfReader;
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
import modele.DevisClient;
import modele.Materiel;
import modele.Sessions;

/**
 *
 * @author Travail Ruel
 */
public class RechercheClientDevisDialog extends JDialog{
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
    private ArrayList<DevisClient> devisClientArray;
    
     public RechercheClientDevisDialog(JFrame parent,ArrayList<DevisClient> listeDevisClients,String title, boolean modal, Sessions session) throws IOException{  
        super(parent, title, modal);
        sessionCourante = session;
        this.devis=devis;
        this.setTitle(title);
        this.setSize(900, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initContenu();
        
        envoiInfo = false;
        this.setVisible(true);
    }    
     
    private void initContenu() throws IOException{
        devisClientArray = new ArrayList();
        soutPanel = new JScrollPane();
        btnAjouter = new JButton();
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
              /*  
               
              Client newClient = new Client(nomField.getText(),prenomField.getText(),commune.getText(),adresse.getText(),Integer.parseInt(numero.getText()),Integer.parseInt(codePostale.getText()),email.getText());
              
              sessionCourante.getSaveDonnees().enregistrerNouveauClient(newClient);
              sessionCourante.getRecupDonnees().addClient(newClient);
              listeClients = sessionCourante.getRecupDonnees().recupererClients();
              model.setData(listeClients);
          } catch (IOException ex) {
              Logger.getLogger(RechercheClientDevisDialog.class.getName()).log(Level.SEVERE, null, ex);
          */
            }});
        
        btnValider.addActionListener(new ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        
        if(tabSout.getSelectedRow()!=-1){
            for (Client listeClient : listeClients) {
                if(listeClient.getNom()==model.getValueAt(tabSout.getSelectedRow(), 0)){
                    if(listeClient.getPrenom()==model.getValueAt(tabSout.getSelectedRow(), 1)){
                        if(listeClient.getCommune()==model.getValueAt(tabSout.getSelectedRow(), 2)){
                            if(listeClient.getAdresse()==model.getValueAt(tabSout.getSelectedRow(), 3)){
                                try {
                                   devisClientArray = sessionCourante.getRecupDonnees().recupererClientDevis(listeClient);
                                    for (DevisClient listeDevisClient : devisClientArray) {
                                        sessionCourante.getRecupDonnees().enregistrerClientDevis(listeDevisClient.getNumero(),listeDevisClient.getDate(),listeDevisClient.getChantier());
                                    }
                                   
                                    setVisible(false);
                                } catch (IOException ex) {
                                    Logger.getLogger(RechercheClientDevisDialog.class.getName()).log(Level.SEVERE, null, ex);
                                }
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
        JPboutons.add(btnValider);
        this.add(titreLabel,BorderLayout.NORTH);
        this.add(soutPanel, BorderLayout.CENTER);
        this.add(JPboutons,BorderLayout.SOUTH);
         
    }
    
}
