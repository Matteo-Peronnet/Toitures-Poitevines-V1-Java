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
public class DevisClient {
   private int numero;
   private String date;
   private String Chantier;
   private String prenom;
   private String nom;
   private String adresse;

    public DevisClient(int numero, String date, String Chantier, String prenom, String nom, String adresse) {
        this.numero = numero;
        this.date = date;
        this.Chantier = Chantier;
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
    }

   
    
    public String getAdresse() {
        return adresse;
    }

    public String getChantier() {
        return Chantier;
    }

    public String getDate() {
        return date;
    }

    public String getNom() {
        return nom;
    }

    public int getNumero() {
        return numero;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setChantier(String Chantier) {
        this.Chantier = Chantier;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
   
   

   
}
