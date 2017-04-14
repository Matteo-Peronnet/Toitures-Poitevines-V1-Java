/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.panneaux;

import modele.Sessions;
import ihm.fenetres.FenetrePrincipale;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Loïc
 */
public class Menu extends JMenuBar{
    private JMenu fichier, afficher;
    private JMenuItem accueil, changerMdp, initialiserAppli, seDeconnecter;
    private JMenuItem rechercheClient, etudiants, rechercheEtudiant, soutenances;
    private Sessions sessionCourante;
    private Scanner sc;
    
    public Menu(Sessions session){
        sessionCourante = session;
        fichier = new JMenu("Fichier");
        accueil = new JMenuItem("Accueil");
        accueil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sessionCourante.changerAffichage(FenetrePrincipale.Etat.ACCUEIL);
            }
        });
        seDeconnecter = new JMenuItem("Se déconnecter");        
        seDeconnecter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sessionCourante.deconnecter();
            }
        });        
        fichier.add(accueil);
        fichier.add(seDeconnecter);
        
        afficher = new JMenu("Afficher");
        rechercheClient = new JMenuItem("Recherche Client");
        rechercheClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sessionCourante.changerAffichage(FenetrePrincipale.Etat.RECHERCHEDEVIS);
              }
        });
        afficher.add(rechercheClient);
        
        this.add(fichier);
        this.add(afficher);
    }
}
