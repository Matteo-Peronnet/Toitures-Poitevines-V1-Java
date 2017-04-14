/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Calendar;

/**
 *
 * @author Travail Ruel
 */
public class Devis {
    private int numero;
    private String date;
    private Client client;
    private int tva;
    private Entreprise entreprise;
    private String informationComplementaire;

    public Devis(int numero, String date, Client client, int tva, Entreprise entreprise, String informationComplementaire) {
        this.numero = numero;
        this.date = date;
        this.client = client;
        this.tva = tva;
        this.entreprise = entreprise;
        this.informationComplementaire = informationComplementaire;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public void setInformationComplementaire(String informationComplementaire) {
        this.informationComplementaire = informationComplementaire;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setTva(int tva) {
        this.tva = tva;
    }

    public Client getClient() {
        return client;
    }

    public String getDate() {
        return date;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public String getInformationComplementaire() {
        return informationComplementaire;
    }

    public int getNumero() {
        return numero;
    }

    public int getTva() {
        return tva;
    }
    
    
    
    
    
}
