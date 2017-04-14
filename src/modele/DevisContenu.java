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
public class DevisContenu {
    private Materiel materiel;
    private double quantite;
    private double pvtHT;
    private String categorie;

    public DevisContenu(Materiel materiel, double quantite, double pvtHT, String categorie) {
        this.materiel = materiel;
        this.quantite = quantite;
        this.pvtHT = pvtHT;
        this.categorie = categorie;
    }
    
    public Materiel getMateriel() {
        return materiel;
    }


    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

    public void setPvtHT(double pvtHT) {
        this.pvtHT = pvtHT;
    }


    public double getPvtHT() {
        return pvtHT;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }



    public double getQuantite() {
        return quantite;
    }



    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCategorie() {
        return categorie;
    }

    

    
}
