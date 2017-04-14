/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author Travail Ruel
 */
public class Entreprise{
    private String nom;
    private String siege;
    private String tel;
    private String coGerant;

    public Entreprise(String nom, String siege, String tel, String coGerant) {
        this.nom = nom;
        this.siege = siege;
        this.tel = tel;
        this.coGerant = coGerant;
    }

    public void setCoGerant(String coGerant) {
        this.coGerant = coGerant;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSiege(String siege) {
        this.siege = siege;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCoGerant() {
        return coGerant;
    }

    public String getSiege() {
        return siege;
    }

    public String getNom() {
        return nom;
    }

    public String getTel() {
        return tel;
    }
    
    
    
}
