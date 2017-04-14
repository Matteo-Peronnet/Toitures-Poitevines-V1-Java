/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author Loïc
 */
public class ConnexionDialogInfo {
    private String identifiant, motDePasse;
    
    public ConnexionDialogInfo(){
        identifiant = null;
        motDePasse = null;
    }
    public ConnexionDialogInfo(String id, String mdP){
        identifiant = id;
        motDePasse = mdP;
    }
    public String getIdentifiant() {
        return identifiant;
    }
    public String getMotDePasse() {
        return motDePasse;
    }      
}
