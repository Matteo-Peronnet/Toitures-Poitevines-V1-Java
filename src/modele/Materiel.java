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
public class Materiel {
    private String nom;
    private String unite;
    private double prix;

    public Materiel(String nom, String unite,double prix) {
        this.nom = nom;
        this.unite = unite;
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public String getUnite() {
        return unite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
    
    
    
}
