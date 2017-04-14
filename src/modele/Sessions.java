/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import exception.IdentifiantsInvalidesException;
import ihm.fenetres.FenetrePrincipale;
import java.io.IOException;
import java.util.Observable;
import javax.swing.JOptionPane;

/**
 *
 * @author Loïc
 */
public class Sessions extends Observable{  
    private Utilisateur utilisateurConnecte;
    private ConnexionDialogInfo connexionInfo;
    private boolean etablie;
    private FenetrePrincipale fenetre;  
    private RecuperationDonnees recupDonnees;
    private EnregistrementDonnees saveDonnees;
    
    public Sessions(){        
        connexionInfo = new ConnexionDialogInfo();
        utilisateurConnecte = new Utilisateur();
        recupDonnees = new RecuperationDonnees();
        saveDonnees = new EnregistrementDonnees();
        fenetre = null;
        etablie = false;
    }        
    public boolean connecter(ConnexionDialogInfo connexionInfo) throws IOException{
        try{
            utilisateurConnecte = utilisateurConnecte.connexionPossible(connexionInfo); 
            etablie = true; 
            fenetre.setEtat(FenetrePrincipale.Etat.ACCUEIL);
            this.setChanged();
            this.notifyObservers();
            return true;
        }catch(IdentifiantsInvalidesException e){
            JOptionPane.showMessageDialog(fenetre, e.getMessage(), "Erreur : Connexion", JOptionPane.ERROR_MESSAGE);
        }    
        return false;
    }
    public void deconnecter(){
        utilisateurConnecte = new Utilisateur();
        etablie = false;
        changerAffichage(FenetrePrincipale.Etat.DECONNECTE);
    }    
    public void changerAffichage(FenetrePrincipale.Etat nouvelEtat){ 
        fenetre.setEtat(nouvelEtat);
        this.setChanged();
        this.notifyObservers();
    }
    public void setFenetre(FenetrePrincipale fenetre) {
        this.fenetre = fenetre;
    }
    public Utilisateur getUtilisateurConnecte() {
        return utilisateurConnecte;
    }
    public boolean isEtablie() {
        return etablie;
    }
    public RecuperationDonnees getRecupDonnees() {
        return recupDonnees;
    }   
    public EnregistrementDonnees getSaveDonnees() {
        return saveDonnees;
    }
    
    public FenetrePrincipale getFenetre() {
        return fenetre;
    }
}
