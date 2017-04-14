/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import exception.IdentifiantsInvalidesException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Loïc
 */
public class Utilisateur{
    private String identifiant, motDePasse;
    private boolean connecte;
    
    public Utilisateur(){
        identifiant = null;
        motDePasse = null;
        connecte = false;
    }
    
    public Utilisateur(String identifiant, String motDePasse){
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
    }
    
    public Utilisateur connexionPossible(ConnexionDialogInfo connexionInfo) throws IdentifiantsInvalidesException, IOException{
        RecuperationDonnees recupUtil = new RecuperationDonnees();
        Utilisateur utilisateurBase = recupUtil.recupererUtilisateur(connexionInfo);
        if(utilisateurBase == null){
            throw new IdentifiantsInvalidesException("Identifiants invalides");   
        }
        return utilisateurBase;
    }
    
    public enum TypeUtilisateur{
        SECRETARIAT, ETUDIANTS2;
    }

    public String getIdentifiant() {
        return identifiant;
    }
    public String getMotDePasse() {
        return motDePasse;
    } 
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    public boolean isConnecte() {
        return connecte;
    }
    
}
