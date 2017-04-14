/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.fenetres;

import modele.ConnexionDialogInfo;
import modele.Sessions;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Loïc
 */
public class ConnexionDialog extends JDialog{
    private ConnexionDialogInfo connexionInfo;
    private boolean envoiInfo;
    private JLabel idLabel, mdpLabel;
    private JTextField identifiant;
    private JPasswordField motDePasse;
    Sessions sessionCourante;
    
    public ConnexionDialog(JFrame parent, String title, boolean modal, Sessions session){  
        super(parent, title, modal);
        this.sessionCourante = session;
        connexionInfo = new ConnexionDialogInfo();
        this.setTitle(title);
        this.setSize(350, 190);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        initContenu();    
        envoiInfo = false;
        this.setVisible(true);
    }    
    public ConnexionDialogInfo recupererInfos(){
        if(connexionInfo != null)
            return connexionInfo;
        else
            return null;
    }    
    private void initContenu(){
        JPanel idPanel = new JPanel();
        identifiant = new JTextField();
        identifiant.setPreferredSize(new Dimension(130, 40));
        idLabel = new JLabel("Identifiant :  ");        
        idLabel.setPreferredSize(new Dimension(115, 40));
        idLabel.setFont(new Font("Arial", Font.BOLD, 15));
        idPanel.add(idLabel);
        idPanel.add(identifiant);
        
        JPanel mdpPanel = new JPanel();
        motDePasse = new JPasswordField();
        motDePasse.setPreferredSize(new Dimension(130, 40));
        mdpLabel = new JLabel(" Mot de passe : ");
        mdpLabel.setPreferredSize(new Dimension(120, 40));
        mdpLabel.setFont(new Font("Arial", Font.BOLD, 15));
        mdpPanel.add(mdpLabel);
        mdpPanel.add(motDePasse);
        
        JPanel contenu = new JPanel();
        contenu.setLayout(new BorderLayout(0, 0));
        contenu.setPreferredSize(new Dimension(100, 100));
        contenu.add(idPanel, BorderLayout.NORTH);
        contenu.add(mdpPanel, BorderLayout.SOUTH);
        
        JPanel boutons = new JPanel();
        
        JButton btnValider = new JButton("Valider");
        btnValider.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                connexionInfo = new ConnexionDialogInfo(identifiant.getText(), motDePasse.getText()); 
                try {
                    if(sessionCourante.connecter(connexionInfo)){
                        setVisible(false);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ConnexionDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        JButton btnQuitter = new JButton("Quitter");
        btnQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                System.exit(0);
            }
        });
        
        boutons.add(btnValider);
        boutons.add(btnQuitter);
        
        this.getContentPane().add(contenu, BorderLayout.NORTH);
        this.getContentPane().add(boutons, BorderLayout.SOUTH);
    }   
}
