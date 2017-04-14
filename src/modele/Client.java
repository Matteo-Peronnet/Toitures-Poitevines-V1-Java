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
public class Client {
    private String nom;
    private String prenom;
    private String commune;
    private String adresse;
    private int numero;
    private int codePostale;
    private String email;

    public Client(String nom, String prenom, String commune, String adresse, int numero, int codePostale, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.commune = commune;
        this.adresse = adresse;
        this.numero = numero;
        this.codePostale = codePostale;
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getCodePostale() {
        return codePostale;
    }

    public String getCommune() {
        return commune;
    }

    public String getEmail() {
        return email;
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

    public void setCodePostale(int codePostale) {
        this.codePostale = codePostale;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public void setEmail(String email) {
        this.email = email;
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
