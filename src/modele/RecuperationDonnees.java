/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Travail Ruel
 */
public class RecuperationDonnees {
    ArrayList<Materiel> CouvertureArdoise = new ArrayList<>();
    ArrayList<Materiel> CouvertureTuile = new ArrayList<>();
    ArrayList<Materiel> Demoussage = new ArrayList<>();
    ArrayList<Materiel> Velux = new ArrayList<>();
    ArrayList<Materiel> Zinguerie = new ArrayList<>();
    ArrayList<Materiel> EquipementDeChantier = new ArrayList<>();
    ArrayList<Materiel> Couverture = new ArrayList<>();
    ArrayList<Materiel> Plancher = new ArrayList<>();
    Entreprise ToiturePoitevine = new Entreprise(null, null,null,null);
    ArrayList<Client> Client = new ArrayList<>();
    ArrayList<DevisContenu> Devis = new ArrayList<>();
    ArrayList<Integer> fichiers = new ArrayList<>();
    ArrayList<DevisClient> devisClient = new ArrayList<>();
    
    
    public RecuperationDonnees() {
    }
    
    public ArrayList<Materiel> recupererCouvertureArdoise() throws FileNotFoundException, IOException{
        CouvertureArdoise.clear();
        BufferedReader br = new BufferedReader(new FileReader("CouvertureArdoise.csv"));
        String ligne = null;
        int u = 3;
        while ((ligne = br.readLine()) != null)
        {
            String[] data = ligne.split(";");
            for (String val : data)
            {
                if(u==3){
                    //System.out.println(data[0] +" "+ data[1] +" "+ Double.parseDouble(data[2]));
                    CouvertureArdoise.add(new Materiel(data[0],data[1],Double.parseDouble(data[2])));
                    u=0;
                }
            u++;
            }
        }
        br.close();
        return CouvertureArdoise;
    }
        public ArrayList<Materiel> recupererCouvertureTuile() throws FileNotFoundException, IOException{
        CouvertureTuile.clear();
        BufferedReader br = new BufferedReader(new FileReader("CouvertureTuile.csv"));
        String ligne = null;
        int u = 3;
        while ((ligne = br.readLine()) != null)
        {
            String[] data = ligne.split(";");
            for (String val : data)
            {
                if(u==3){
                    //System.out.println(data[0] +" "+ data[1] +" "+ Double.parseDouble(data[2]));
                    CouvertureTuile.add(new Materiel(data[0],data[1],Double.parseDouble(data[2])));
                    u=0;
                }
            u++;
            }
        }
        br.close();
        return CouvertureTuile;
    }
        
        public ArrayList<Materiel> recupererDemoussage() throws FileNotFoundException, IOException{
        Demoussage.clear();
        BufferedReader br = new BufferedReader(new FileReader("Demoussage.csv"));
        String ligne = null;
        int u = 3;
        while ((ligne = br.readLine()) != null)
        {
            String[] data = ligne.split(";");
            for (String val : data)
            {
                if(u==3){
                    //System.out.println(data[0] +" "+ data[1] +" "+ Double.parseDouble(data[2]));
                    Demoussage.add(new Materiel(data[0],data[1],Double.parseDouble(data[2])));
                    u=0;
                }
            u++;
            }
        }
        br.close();
        return Demoussage;
    }    
    
        
        public ArrayList<Materiel> recupererVelux() throws FileNotFoundException, IOException{
        Velux.clear();
        BufferedReader br = new BufferedReader(new FileReader("Velux.csv"));
        String ligne = null;
        int u = 3;
        while ((ligne = br.readLine()) != null)
        {
            String[] data = ligne.split(";");
            for (String val : data)
            {
                if(u==3){
                    //System.out.println(data[0] +" "+ data[1] +" "+ Double.parseDouble(data[2]));
                    Velux.add(new Materiel(data[0],data[1],Double.parseDouble(data[2])));
                    u=0;
                }
            u++;
            }
        }
        br.close();
        return Velux;
    }    
        
        public ArrayList<Materiel> recupererZinguerie() throws FileNotFoundException, IOException{
        Zinguerie.clear();
        BufferedReader br = new BufferedReader(new FileReader("Zinguerie.csv"));
        String ligne = null;
        int u = 3;
        while ((ligne = br.readLine()) != null)
        {
            String[] data = ligne.split(";");
            for (String val : data)
            {
                if(u==3){
                    //System.out.println(data[0] +" "+ data[1] +" "+ Double.parseDouble(data[2]));
                    Zinguerie.add(new Materiel(data[0],data[1],Double.parseDouble(data[2])));
                    u=0;
                }
            u++;
            }
        }
        br.close();
        return Zinguerie;
    } 
            
    public ArrayList<Materiel> recupererEquipementDeChantier() throws FileNotFoundException, IOException{
        EquipementDeChantier.clear();
        BufferedReader br = new BufferedReader(new FileReader("EquipementDeChantier.csv"));
        String ligne = null;
        int u = 3;
        while ((ligne = br.readLine()) != null)
        {
            String[] data = ligne.split(";");
            for (String val : data)
            {
                if(u==3){
                    //System.out.println(data[0] +" "+ data[1] +" "+ Double.parseDouble(data[2]));
                    EquipementDeChantier.add(new Materiel(data[0],data[1],Double.parseDouble(data[2])));
                    u=0;
                }
            u++;
            }
        }
        br.close();
        return EquipementDeChantier;
    } 
    public ArrayList<Materiel> recupererCouverture() throws FileNotFoundException, IOException{
        Couverture.clear();
        BufferedReader br = new BufferedReader(new FileReader("Couverture.csv"));
        String ligne = null;
        int u = 3;
        while ((ligne = br.readLine()) != null)
        {
            String[] data = ligne.split(";");
            for (String val : data)
            {
                if(u==3){
                    //System.out.println(data[0] +" "+ data[1] +" "+ Double.parseDouble(data[2]));
                    Couverture.add(new Materiel(data[0],data[1],Double.parseDouble(data[2])));
                    u=0;
                }
            u++;
            }
        }
        br.close();
        return Couverture;
    }     
    
    public ArrayList<Materiel> recupererPlancher() throws FileNotFoundException, IOException{
        Plancher.clear();
        BufferedReader br = new BufferedReader(new FileReader("Plancher.csv"));
        String ligne = null;
        int u = 3;
        while ((ligne = br.readLine()) != null)
        {
            String[] data = ligne.split(";");
            for (String val : data)
            {
                if(u==3){
                    //System.out.println(data[0] +" "+ data[1] +" "+ Double.parseDouble(data[2]));
                    Plancher.add(new Materiel(data[0],data[1],Double.parseDouble(data[2])));
                    u=0;
                }
            u++;
            }
        }
        br.close();
        return Plancher;
    }   
    
    public Utilisateur recupererUtilisateur(ConnexionDialogInfo infoConnexion) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("Compte.csv"));
        String ligne = null;
        int u = 2;
        Utilisateur util = null;
        while ((ligne = br.readLine()) != null)
        {
            String[] data = ligne.split(";");
            for (String val : data)
            {
                if(u==2){
                    //System.out.println(data[0] +" "+ data[1]);
                    util = new Utilisateur(data[0],data[1]);
                    u=0;
                }
            u++;
            }
        }
        br.close();
        return util;     
        
    }    
    public void addMaterielCouvertureArdoise(Materiel materiel){
        this.CouvertureArdoise.add(materiel);
    }
    public void addMaterielCouvertureTuile(Materiel materiel){
        this.CouvertureTuile.add(materiel);
    }
    public void addMaterielDemoussage(Materiel materiel){
        this.Demoussage.add(materiel);
    }
    public void addMaterielVelux(Materiel materiel){
        this.Velux.add(materiel);
    }
    public void addMaterielZinguerie(Materiel materiel){
        this.Zinguerie.add(materiel);
    }
    public void addMaterielEquipementDeChantier(Materiel materiel){
        this.EquipementDeChantier.add(materiel);
    }
    public void addMaterielCouverture(Materiel materiel){
        this.Couverture.add(materiel);
    }
    public void addMaterielPlancher(Materiel materiel){
        this.Plancher.add(materiel);
    }
    public void addClient(Client client){
            this.Client.add(client);
    }
    public void addInformationDevis(DevisContenu devis){
            this.Devis.add(devis);
    }
    public void addNouveauDevisClient(DevisClient newMateriel) {
            this.addNouveauDevisClient(newMateriel);
    }

    public ArrayList<Materiel> supprimerCouvertureArdoise(int index) throws FileNotFoundException, IOException {
        System.out.println("index : " + index);
        Vector monVector = new Vector(); 
        File f = new File("CouvertureArdoise.csv"); 
        BufferedReader B = new BufferedReader(new FileReader(f)); 
        String ligne = B.readLine(); 
        while (ligne != null){ 
            monVector.addElement(ligne); 
            ligne = B.readLine(); 
        } 
        int numeroLigne=index;
        monVector.removeElementAt(numeroLigne); 
        PrintWriter P = new PrintWriter (new FileWriter(f)); 
        for (int i = 0; i < monVector.size(); i++){ 
            P.println(monVector.get(i)); 
        } 
        P.close(); 
        this.CouvertureArdoise.remove(index);
        return this.CouvertureArdoise;
    }

    public ArrayList<Materiel> supprimerCouvertureTuile(int index) throws FileNotFoundException, IOException {
        Vector monVector = new Vector(); 
        File f = new File("CouvertureTuile.csv"); 
        BufferedReader B = new BufferedReader(new FileReader(f)); 
        String ligne = B.readLine(); 
        while (ligne != null){ 
            monVector.addElement(ligne); 
            ligne = B.readLine(); 
        } 
        int numeroLigne=index;
        monVector.removeElementAt(numeroLigne); 
        PrintWriter P = new PrintWriter (new FileWriter(f)); 
        for (int i = 0; i < monVector.size(); i++){ 
            P.println(monVector.get(i)); 
        } 
        P.close();
        this.CouvertureTuile.remove(index);
        return this.CouvertureTuile;
    }

    public ArrayList<Materiel> supprimerDemoussage(int index) throws FileNotFoundException, IOException {
        Vector monVector = new Vector(); 
        File f = new File("Demoussage.csv"); 
        BufferedReader B = new BufferedReader(new FileReader(f)); 
        String ligne = B.readLine(); 
        while (ligne != null){ 
            monVector.addElement(ligne); 
            ligne = B.readLine(); 
        } 
        int numeroLigne=index;
        monVector.removeElementAt(numeroLigne); 
        PrintWriter P = new PrintWriter (new FileWriter(f)); 
        for (int i = 0; i < monVector.size(); i++){ 
            P.println(monVector.get(i)); 
        } 
        P.close();
        this.Demoussage.remove(index);
        return this.Demoussage; 
    }

    public ArrayList<Materiel> supprimerEquipementDeChantier(int index) throws FileNotFoundException, IOException {
        Vector monVector = new Vector(); 
        File f = new File("EquipementDeChantier.csv"); 
        BufferedReader B = new BufferedReader(new FileReader(f)); 
        String ligne = B.readLine(); 
        while (ligne != null){ 
            monVector.addElement(ligne); 
            ligne = B.readLine(); 
        } 
        int numeroLigne=index;
        monVector.removeElementAt(numeroLigne); 
        PrintWriter P = new PrintWriter (new FileWriter(f)); 
        for (int i = 0; i < monVector.size(); i++){ 
            P.println(monVector.get(i)); 
        } 
        P.close();
        this.EquipementDeChantier.remove(index);
        return this.EquipementDeChantier;
    }

    public ArrayList<Materiel> supprimerVelux(int index) throws FileNotFoundException, IOException {
        Vector monVector = new Vector(); 
        File f = new File("Velux.csv"); 
        BufferedReader B = new BufferedReader(new FileReader(f)); 
        String ligne = B.readLine(); 
        while (ligne != null){ 
            monVector.addElement(ligne); 
            ligne = B.readLine(); 
        } 
        int numeroLigne=index;
        monVector.removeElementAt(numeroLigne); 
        PrintWriter P = new PrintWriter (new FileWriter(f)); 
        for (int i = 0; i < monVector.size(); i++){ 
            P.println(monVector.get(i)); 
        } 
        P.close();
        this.Velux.remove(index);
        return this.Velux;
    }

    public ArrayList<Materiel> supprimerZinguerie(int index) throws FileNotFoundException, IOException {
        Vector monVector = new Vector(); 
        File f = new File("Zinguerie.csv"); 
        BufferedReader B = new BufferedReader(new FileReader(f)); 
        String ligne = B.readLine(); 
        while (ligne != null){ 
            monVector.addElement(ligne); 
            ligne = B.readLine(); 
        } 
        int numeroLigne=index;
        monVector.removeElementAt(numeroLigne); 
        PrintWriter P = new PrintWriter (new FileWriter(f)); 
        for (int i = 0; i < monVector.size(); i++){ 
            P.println(monVector.get(i)); 
        } 
        P.close();
        this.Zinguerie.remove(index);
        return this.Zinguerie;
     }
    public ArrayList<Materiel> supprimerCouverture(int index) throws FileNotFoundException, IOException {
        Vector monVector = new Vector(); 
        File f = new File("Couverture.csv"); 
        BufferedReader B = new BufferedReader(new FileReader(f)); 
        String ligne = B.readLine(); 
        while (ligne != null){ 
            monVector.addElement(ligne); 
            ligne = B.readLine(); 
        } 
        int numeroLigne=index;
        monVector.removeElementAt(numeroLigne); 
        PrintWriter P = new PrintWriter (new FileWriter(f)); 
        for (int i = 0; i < monVector.size(); i++){ 
            P.println(monVector.get(i)); 
        } 
        P.close();
        this.Couverture.remove(index);
        return this.Couverture;
     }
   public ArrayList<Materiel> supprimerPlancher(int index) throws FileNotFoundException, IOException {
        Vector monVector = new Vector(); 
        File f = new File("Plancher.csv"); 
        BufferedReader B = new BufferedReader(new FileReader(f)); 
        String ligne = B.readLine(); 
        while (ligne != null){ 
            monVector.addElement(ligne); 
            ligne = B.readLine(); 
        } 
        int numeroLigne=index;
        monVector.removeElementAt(numeroLigne); 
        PrintWriter P = new PrintWriter (new FileWriter(f)); 
        for (int i = 0; i < monVector.size(); i++){ 
            P.println(monVector.get(i)); 
        } 
        P.close();
        this.Plancher.remove(index);
        return this.Plancher;
     }
    public ArrayList<DevisContenu> supprimerDevisMateriel(int index) throws FileNotFoundException, IOException {
        Vector monVector = new Vector(); 
        File f = new File("Devis.csv"); 
        BufferedReader B = new BufferedReader(new FileReader(f)); 
        String ligne = B.readLine(); 
        while (ligne != null){ 
            monVector.addElement(ligne); 
            ligne = B.readLine(); 
        } 
        int numeroLigne=index;
        monVector.removeElementAt(numeroLigne); 
        PrintWriter P = new PrintWriter (new FileWriter(f)); 
        for (int i = 0; i < monVector.size(); i++){ 
            P.println(monVector.get(i)); 
        } 
        P.close();
        this.Devis.remove(index);
        return this.Devis;
     }
    
    public String recupererNomEntreprise() throws FileNotFoundException, IOException{
        
        BufferedReader br = new BufferedReader(new FileReader("Parametre.csv"));
        String ligne = null;
        int u = 4;
        String nomEntreprise = null;
        while ((ligne = br.readLine()) != null)
        {
            String[] data = ligne.split(";");
            for (String val : data)
            {
                if(u==4){
                    //System.out.println(data[0] +" "+ data[1] +" "+ Double.parseDouble(data[2]));
                    nomEntreprise=data[0];
                    u=0;
                }
            u++;
            }
        }
        br.close();
        ToiturePoitevine.setNom(nomEntreprise);
        return nomEntreprise;
    
    }
    public String recupererNumeroEntreprise() throws FileNotFoundException, IOException{
        
        BufferedReader br = new BufferedReader(new FileReader("Parametre.csv"));
        String ligne = null;
        int u = 4;
        String donnee = null;
        while ((ligne = br.readLine()) != null)
        {
            String[] data = ligne.split(";");
            for (String val : data)
            {
                if(u==4){
                    //System.out.println(data[0] +" "+ data[1] +" "+ Double.parseDouble(data[2]));
                    donnee=data[1];
                    u=0;
                }
            u++;
            }
        }
        br.close();
        ToiturePoitevine.setTel(donnee);
        return donnee;
    
    }
        public String recupererSiegeEntreprise() throws FileNotFoundException, IOException{
        
        BufferedReader br = new BufferedReader(new FileReader("Parametre.csv"));
        String ligne = null;
        int u = 4;
        String donnee = null;
        while ((ligne = br.readLine()) != null)
        {
            String[] data = ligne.split(";");
            for (String val : data)
            {
                if(u==4){
                    //System.out.println(data[0] +" "+ data[1] +" "+ Double.parseDouble(data[2]));
                    donnee=data[2];
                    u=0;
                }
            u++;
            }
        }
        br.close();
        ToiturePoitevine.setSiege(donnee);
        return donnee;
    
    }
    public String recupererCoGerant() throws FileNotFoundException, IOException{
        
        BufferedReader br = new BufferedReader(new FileReader("Parametre.csv"));
        String ligne = null;
        int u = 4;
        String donnee = null;
        while ((ligne = br.readLine()) != null)
        {
            String[] data = ligne.split(";");
            for (String val : data)
            {
                if(u==4){
                    //System.out.println(data[0] +" "+ data[1] +" "+ Double.parseDouble(data[2]));
                    donnee=data[3];
                    u=0;
                }
            u++;
            }
        }
        br.close();
        ToiturePoitevine.setCoGerant(donnee);
        return donnee;
    
    }
    public String recupererInformationsComplementaire() throws FileNotFoundException, IOException{
        
        BufferedReader br = new BufferedReader(new FileReader("Parametre.csv"));
        String ligne = null;
        int u = 4;
        String donnee = null;
        while ((ligne = br.readLine()) != null)
        {
            String[] data = ligne.split(";");
            for (String val : data)
            {
                if(u==4){
                    //System.out.println(data[0] +" "+ data[1] +" "+ Double.parseDouble(data[2]));
                    donnee=data[4];
                    u=0;
                }
            u++;
            }
        }
        br.close();
        return donnee;
    
    }
    public ArrayList<Client> recupererClients() throws FileNotFoundException, IOException{
        Client.clear();
        BufferedReader br = new BufferedReader(new FileReader("Clients.csv"));
        String ligne = null;
        int u = 7;
        while ((ligne = br.readLine()) != null)
        {
            String[] data = ligne.split(";");
            for (String val : data)
            {
                if(u==7){
                    //System.out.println(data[0] +" "+ data[1] +" "+ Double.parseDouble(data[2]));
                    Client.add(new Client(data[0],data[1],data[2],data[3],Integer.parseInt(data[4]),Integer.parseInt(data[5]),data[6]));
                    u=0;
                }
            u++;
            }
        }
        br.close();
        return Client;
    }
    
    
        public ArrayList<Materiel> supprimerClient(int index) throws FileNotFoundException, IOException {
        Vector monVector = new Vector(); 
        File f = new File("Clients.csv"); 
        BufferedReader B = new BufferedReader(new FileReader(f)); 
        String ligne = B.readLine(); 
        while (ligne != null){ 
            monVector.addElement(ligne); 
            ligne = B.readLine(); 
        } 
        int numeroLigne=index;
        monVector.removeElementAt(numeroLigne); 
        PrintWriter P = new PrintWriter (new FileWriter(f)); 
        for (int i = 0; i < monVector.size(); i++){ 
            P.println(monVector.get(i)); 
        } 
        P.close();
        this.Client.remove(index);
        return this.Velux;
    }

    public ArrayList<DevisContenu> recupererDevis() throws FileNotFoundException {
        try {
            Devis.clear();
            BufferedReader br = new BufferedReader(new FileReader("Devis.csv"));
            String ligne = null;
            int u = 6;
            while ((ligne = br.readLine()) != null)
            {
                String[] data = ligne.split(";");
                for (String val : data)
                {
                    if(u==6){
                        //System.out.println(data[0] +" "+ data[1] +" "+ Double.parseDouble(data[2]));
            
                        Devis.add(new DevisContenu(new Materiel(data[0],data[1],Double.parseDouble(data[3])),Double.parseDouble(data[2]), Double.parseDouble(data[4]),data[5]));
                        u=0;
                    }
                    u++;
                }
            }
            br.close();
            
        } catch (IOException ex) {
            Logger.getLogger(RecuperationDonnees.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Devis;
}
    public String recupererCategorieMateriel(String mat,String unite,double prix) throws IOException{
       recupererCouvertureArdoise();
        boolean trouver=false;
        String type = null;
        
        while(trouver==false){
            for (Materiel materiel : this.CouvertureArdoise) {
                if(materiel.getNom().equalsIgnoreCase(mat) && materiel.getUnite().equalsIgnoreCase(unite) && materiel.getPrix()==prix){
                   type="CouvertureArdoise";
                   trouver=true;
                }
            }
            for (Materiel materiel : CouvertureTuile) {
                if(materiel.getNom().equalsIgnoreCase(mat) && materiel.getUnite().equalsIgnoreCase(unite) && materiel.getPrix()==prix){
                   type="CouvertureTuile";
                   trouver=true;
                }
            }
            for (Materiel materiel : Demoussage) {
                if(materiel.getNom().equalsIgnoreCase(mat) && materiel.getUnite().equalsIgnoreCase(unite) && materiel.getPrix()==prix){
                   type="Demoussage";
                   trouver=true;
                }
            }
            for (Materiel materiel : EquipementDeChantier) {
                if(materiel.getNom().equalsIgnoreCase(mat) && materiel.getUnite().equalsIgnoreCase(unite) && materiel.getPrix()==prix){
                   type="EquipementDeChantier";
                   trouver=true;
                }
            }
            for (Materiel materiel : Velux) {
                if(materiel.getNom().equalsIgnoreCase(mat) && materiel.getUnite().equalsIgnoreCase(unite) && materiel.getPrix()==prix){
                   type="Velux";
                   trouver=true;
                }
            }
            for (Materiel materiel : Zinguerie) {
                if(materiel.getNom().equalsIgnoreCase(mat) && materiel.getUnite().equalsIgnoreCase(unite) && materiel.getPrix()==prix){
                   type="Zinguerie";
                   trouver=true;
                }
            }
            for (Materiel materiel : Couverture) {
                if(materiel.getNom().equalsIgnoreCase(mat) && materiel.getUnite().equalsIgnoreCase(unite) && materiel.getPrix()==prix){
                   type="Couverture";
                   trouver=true;
                }
            }
            for (Materiel materiel : Plancher) {
                if(materiel.getNom().equalsIgnoreCase(mat) && materiel.getUnite().equalsIgnoreCase(unite) && materiel.getPrix()==prix){
                   type="Plancher";
                   trouver=true;
                }
            }
        }
        return type;
        
    }
    public String augmenterNumeroDevis(){ 
        
    File repertoire = new File("devis/");
    String [] listefichiers; 
    int index=0;
    
    
    listefichiers=repertoire.list(); 
        for(int i=0;i<listefichiers.length;i++){ 
            if(listefichiers[i].endsWith(".pdf")==true){ 
                index++;
            } 
        }
      
        index++;
        
        String fichiers = "devis-"+index+".pdf";
        return fichiers;
    }
    public int recupererNumeroDevis(){ 
        
    File repertoire = new File("devis/");
    String [] listefichiers; 
    int index=0;
    
    
    listefichiers=repertoire.list(); 
        for(int i=0;i<listefichiers.length;i++){ 
            if(listefichiers[i].endsWith(".pdf")==true){ 
                index++;
            } 
        }
      
        
        
        return index+1;
    }
    public void enregistrementModificationClients(int index,Client client) throws FileNotFoundException, IOException{
        //String nom,String prenom,String commune,String adresse,int numero,int codepostal,String email
        Vector monVector = new Vector(); 
        File f = new File("Clients.csv"); 
        BufferedReader B = new BufferedReader(new FileReader(f)); 
        String ligne = B.readLine(); 
        while (ligne != null){ 
            monVector.addElement(ligne); 
            ligne = B.readLine(); 
        } 
        int numeroLigne=index;
        monVector.set(numeroLigne,client.getNom()+";"+client.getPrenom()+";"+client.getCommune()+";"+client.getAdresse()+";"+client.getNumero()+";"+client.getCodePostale()+";"+client.getEmail());
        PrintWriter P = new PrintWriter (new FileWriter(f)); 
        for (int i = 0; i < monVector.size(); i++){ 
            P.println(monVector.get(i)); 
        } 
        P.close();      
    }
    public int recupererLigneModificationClients(Client client){
        int index = 0;
        int index_trouver=0;
        for (Client unclient : Client) {
            if(unclient.getNom()==client.getNom())
                if(unclient.getPrenom()==client.getPrenom())
                    index_trouver=index;
            index++;
            
        }
        return index_trouver;
    }

    public ArrayList<DevisClient> recupererDevisClient() throws FileNotFoundException, IOException {
        devisClient.clear();
        BufferedReader br = new BufferedReader(new FileReader("ClientDevis.csv"));
        String ligne = null;
        int u = 3;
        while ((ligne = br.readLine()) != null)
        {
            String[] data = ligne.split(";");
            for (String val : data)
            {
                if(u==3){
                    //System.out.println(data[0] +" "+ data[1] +" "+ Double.parseDouble(data[2]));
                    devisClient.add(new DevisClient(Integer.parseInt(data[0]),data[1],data[2],data[3],data[4],data[5]));
                    u=0;
                }
            u++;
            }
        }
        br.close();
        return devisClient;
        
    
    }
    public void viderArraylistdevisClient(){
    devisClient.clear();
    }
    public ArrayList<DevisClient> recupererDevisClientVide() throws FileNotFoundException, IOException {
       devisClient.clear();
       return devisClient;
    }
    
    public ArrayList<DevisClient> recupererClientDevis(Client client) throws IOException{
        
    File repertoire = new File("devis/data/");
    String [] listefichiers;                    
                
    listefichiers=repertoire.list(); 
        for(int i=0;i<listefichiers.length;i++){ 
            if(listefichiers[i].endsWith(".csv")==true){
                BufferedReader br = new BufferedReader(new FileReader("devis/data/"+listefichiers[i]));
                String ligne = null;
                int u = 6;
                while ((ligne = br.readLine()) != null)
                {
                    String[] data = ligne.split(";");
                    for (String val : data)
                    {
                        if(u==6){
                            if(client.getAdresse().equalsIgnoreCase(data[5])){
                                devisClient.add(new DevisClient(Integer.parseInt(data[0]),data[1],data[2],data[3],data[4],data[5]));
                            }
                            u=0;
                        }
                    u++;
                    }
                }
            } 
        }    
        
       return devisClient;
    }

    public void recupererDonneesDevis(Client client) {
        File repertoire = new File("devis/data/");
        String [] listefichiers; 
        int index=0;


        listefichiers=repertoire.list(); 
            for(int i=0;i<listefichiers.length;i++){ 
                if(listefichiers[i].endsWith(".csv")==true){
                    
                    index++;
                } 
            }
    }
    public int recupererNumeroDevisActuel() {
        File repertoire = new File("devis/data/");
        String [] listefichiers; 
        int index=0;


        listefichiers=repertoire.list(); 
            for(int i=0;i<listefichiers.length;i++){ 
                if(listefichiers[i].endsWith(".csv")==true){
                    
                    index++;
                } 
            }
        return index;
    }
    
    public void enregistrerClientDevis(int numero,String date,String chantier) throws IOException {
        
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("ClientDevis.csv", true));
        bufferedWriter.write(numero+";"+date+";"+chantier);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }

    public ArrayList<DevisContenu> getDevis() {
        return Devis;
    }

    public ArrayList<DevisClient> getDevisClient() {
        return devisClient;
    }
    
}