/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.fenetres;

import ihm.panneaux.DevisP;
import ihm.panneaux.Panneau;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modele.Devis;
import modele.Sessions;

/**
 *
 * @author 
 */
public class ParametreDevisDialog extends JDialog{
    private boolean envoiInfo;
    private JPanel nomPanel, prenomPanel, groupePanel, contenu, boutons;
    private JLabel tvaLabel, numeroLabel, dateLabel;
    private JTextField tvaTextField, numeroTextField,dateTextField;
    private Sessions sessionCourante;
    private Devis devis;
    public ParametreDevisDialog(JFrame parent,Devis devis,String title, boolean modal, Sessions session){  
        super(parent, title, modal);
        sessionCourante = session;
        this.devis=devis;
        this.setTitle(title);
        this.setSize(500, 250);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setLocation(400, 300);
        initContenu();
        
        envoiInfo = false;
        this.setVisible(true);
    }    
    private void initContenu(){
        ArrayList<Object> composants = new ArrayList<>();
        ArrayList<Integer> placements = new ArrayList<>();     
        placements.add(2); //2 colonnes par panel
        
        tvaLabel = new JLabel("TVA : ");
        tvaLabel.setFont(new Font("Arial", Font.BOLD, 16));
        tvaTextField = new JTextField();
        tvaTextField.setText("10");
        tvaTextField.setPreferredSize(new Dimension(50, 35));
        tvaTextField.setFont(new Font("Arial", Font.PLAIN, 15));  
        nomPanel = new JPanel(new BorderLayout());
        nomPanel.add(tvaLabel, BorderLayout.WEST);
        nomPanel.add(tvaTextField, BorderLayout.EAST);
        nomPanel.setPreferredSize(new Dimension(110, 40));
        
        numeroLabel = new JLabel("Numero : ");
        numeroLabel.setFont(new Font("Arial", Font.BOLD, 16));
        numeroTextField = new JTextField();
        numeroTextField.setPreferredSize(new Dimension(130, 35));
        numeroTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        numeroTextField.setText(Integer.toString(sessionCourante.getRecupDonnees().recupererNumeroDevis()));
        
        prenomPanel = new JPanel(new BorderLayout());
        prenomPanel.add(numeroLabel, BorderLayout.WEST);
        prenomPanel.add(numeroTextField, BorderLayout.EAST);
        prenomPanel.setPreferredSize(new Dimension(130, 40));
        
        dateLabel = new JLabel("Date : ");
        dateLabel.setFont(new Font("Arial", Font.BOLD, 16));
        String[] choix = {"Aujourdui", "A1", "A2", "B1", "B2", "C1", "C2", "D1", "D2"};
        dateTextField = new JTextField("Aujourd'hui");
        dateTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        dateTextField.setBackground(Color.WHITE);
        dateTextField.setPreferredSize(new Dimension(100, 35));
//        composants.clear();
//        composants.add(groupeLabel);
//        composants.add(groupe); 
        groupePanel = new JPanel();
        groupePanel.add(dateLabel);
        groupePanel.add(dateTextField);
        groupePanel.setPreferredSize(new Dimension(420, 40));
        
        composants.clear();
        composants.add(nomPanel);
        composants.add(prenomPanel); 
        composants.add(groupePanel);
        placements.add(1); //1 colonne pour la seconde ligne du panel contenu
        contenu = new Panneau(2, 2, new Dimension(470, 110), new Font("Arial", Font.BOLD, 17), Color.DARK_GRAY,  "", composants, placements, new Color(238, 238, 238)); 
       
        JButton btnValider = new JButton("Valider");
        btnValider.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                             
                
                if(tvaTextField.getText().equals("10")){
                    devis.setTva(10);
                }else{
                    devis.setTva(Integer.parseInt((tvaTextField.getText())));
                }
                
                if(numeroTextField.getText().equals(sessionCourante.getRecupDonnees().recupererNumeroDevis())){
                    devis.setNumero(sessionCourante.getRecupDonnees().recupererNumeroDevis());
                }else{
                    devis.setNumero(Integer.parseInt(numeroTextField.getText()));
                }
                
                
                if(dateTextField.getText().equals("Aujourd'hui")){
                    Date actuelle = new Date();
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String dat = dateFormat.format(actuelle);
                    devis.setDate(dat);
                }else{
                   devis.setDate(dateTextField.getText());
                }
                
                
                setVisible(false);
           }
        });               
        boutons = new JPanel();
        boutons.add(btnValider);
        
        composants.clear();
        composants.add(contenu);
        composants.add(boutons);
        placements.clear();
        for(int ligne = 0; ligne < 2; ligne++)
            placements.add(1);    
        
        this.setContentPane(new Panneau(2, 1, new Dimension(480, 240), new Font("Arial", Font.BOLD, 17), Color.DARK_GRAY,  "", composants, placements, new Color(238, 238, 238)));
        
    }   

    public Object recupererInfos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
