/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.fenetres;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel;
import ihm.panneaux.Accueil;
import ihm.panneaux.AjouterBase;
import ihm.panneaux.DevisP;
import ihm.panneaux.Menu;
import ihm.panneaux.Parametre;
import ihm.panneaux.RechercheClientDevisPanel;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.UIManager;
import modele.Materiel;
import modele.Sessions;

/**
 *
 * @author Travail Ruel
 */
public class FenetrePrincipale extends JFrame implements Observer{
    private Etat etat;
    private ConnexionDialog cD;
    private Sessions sessionCourante;
    
    public FenetrePrincipale(Sessions session) throws HeadlessException {
        sessionCourante = session;
        sessionCourante.setFenetre(this);
        sessionCourante.addObserver(this);
        setTitle("Gestion Devis");
        setDefaultCloseOperation(EXIT_ON_CLOSE);        
        setPreferredSize(new Dimension(700, 500));
        this.etat=Etat.DECONNECTE;
        cD = new ConnexionDialog(null, "Connexion", true, sessionCourante);
        
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(etat==Etat.ACCUEIL){
        setContentPane(new Accueil(sessionCourante));
        }
        else if(etat==Etat.AJOUTERBASE){
            try {
                setContentPane(new AjouterBase(sessionCourante));
            } catch (IOException ex) {
                Logger.getLogger(FenetrePrincipale.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else if(etat==Etat.DEVIS){
            try {
                setContentPane(new DevisP(sessionCourante));
            } catch (IOException ex) {
                Logger.getLogger(FenetrePrincipale.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(etat==Etat.PARAMETRE){
            setContentPane(new Parametre(sessionCourante));
        }else if(etat==Etat.RECHERCHEDEVIS){
            try {
                setContentPane(new RechercheClientDevisPanel(sessionCourante));
            } catch (IOException ex) {
                Logger.getLogger(FenetrePrincipale.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        setJMenuBar(new Menu(sessionCourante));
        pack();
        this.revalidate();
        setVisible(true);
        
    }
    public static void main(String[] args) throws SQLException, FileNotFoundException, IOException{
        try {
            UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
        }catch (Exception e){
            e.printStackTrace();
        }
        Sessions session = new Sessions();
        FenetrePrincipale fP = new FenetrePrincipale(session);
        session.setFenetre(fP);
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }
    
    public enum Etat{
        ACCUEIL, AJOUTERBASE,DECONNECTE,DEVIS,PARAMETRE,ENVOIEDEVIS,RECHERCHEDEVIS;
    }
}
