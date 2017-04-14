/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Loïc
 */
public class EnregistrementDonnees {
    
    public EnregistrementDonnees() {
    }
    public int enregistrerNouveauMaterielCouvertureArdoise(Materiel materiel) throws IOException{
        
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("CouvertureArdoise.csv", true));
        bufferedWriter.write(materiel.getNom()+";"+materiel.getUnite()+";"+materiel.getPrix());
        bufferedWriter.newLine();
        bufferedWriter.close();
        
        return 1;
    }
    public int enregistrerNouveauMaterielCouvertureTuile(Materiel materiel) throws IOException{
        
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("CouvertureTuile.csv", true));
        bufferedWriter.write(materiel.getNom()+";"+materiel.getUnite()+";"+materiel.getPrix());
        bufferedWriter.newLine();
        bufferedWriter.close();
        
        return 1;
    }
    public int enregistrerNouveauMaterielDemoussage(Materiel materiel) throws IOException{
        
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Demoussage.csv", true));
        bufferedWriter.write(materiel.getNom()+";"+materiel.getUnite()+";"+materiel.getPrix());
        bufferedWriter.newLine();
        bufferedWriter.close();
        
        return 1;
    }
    
    public int enregistrerNouveauMaterielVelux(Materiel materiel) throws IOException{
        
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Velux.csv", true));
        bufferedWriter.write(materiel.getNom()+";"+materiel.getUnite()+";"+materiel.getPrix());
        bufferedWriter.newLine();
        bufferedWriter.close();
        
        return 1;
    }
    public int enregistrerNouveauMaterielZinguerie(Materiel materiel) throws IOException{
        
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Zinguerie.csv", true));
        bufferedWriter.write(materiel.getNom()+";"+materiel.getUnite()+";"+materiel.getPrix());
        bufferedWriter.newLine();
        bufferedWriter.close();
        
        return 1;
    }
    public int enregistrerNouveauMaterielEquipementDeChantier(Materiel materiel) throws IOException{
        
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("EquipementDeChantier.csv", true));
        bufferedWriter.write(materiel.getNom()+";"+materiel.getUnite()+";"+materiel.getPrix());
        bufferedWriter.newLine();
        bufferedWriter.close();
        
        return 1;
    }
    public int enregistrerNouveauMaterielCouverture(Materiel materiel) throws IOException{
        
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Couverture.csv", true));
        bufferedWriter.write(materiel.getNom()+";"+materiel.getUnite()+";"+materiel.getPrix());
        bufferedWriter.newLine();
        bufferedWriter.close();
        
        return 1;
    }
    public int enregistrerNouveauMaterielPlancher(Materiel materiel) throws IOException{
        
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Plancher.csv", true));
        bufferedWriter.write(materiel.getNom()+";"+materiel.getUnite()+";"+materiel.getPrix());
        bufferedWriter.newLine();
        bufferedWriter.close();
        
        return 1;
    }
    private String enleveLesErreursDuesAuxApostrophes(String ligne){
        int indexApo = 0;
        String debutLigne, finLigne;
        while(ligne.indexOf("'", indexApo) != -1){
            debutLigne = ligne.substring(0, ligne.indexOf("'", indexApo));
            debutLigne += "''";
            finLigne = ligne.substring(ligne.indexOf("'", indexApo)+1);
            indexApo = ligne.indexOf("'", indexApo)+1;
            ligne = debutLigne + finLigne;   
            indexApo++; //on incrémente une fois de plus comme il y a un caractère en plus
        }
        return ligne;
    }
    
    public void enregistrerParametres(String nom,String tel, String siege, String coGerant, String info) throws IOException{
        
        PrintWriter pw =  new PrintWriter(new BufferedWriter
           //(new FileWriter("nomFichier.type", true))); // >>>> append = ajout
            //ou bien:
            (new FileWriter("Parametre.csv"))); // >>>> on remplace (on "écrase")
            //ou encore (par défaut):
            //new FileWriter("nomFichier.type"))), // on remplace (on "écrase")

// Et on ajoute une ligne de longueur 0 (""):
             pw.println(nom+";"+tel+";"+siege+";"+coGerant+";"+info);
             pw.close();
    }
    public int enregistrerNouveauClient(Client client) throws IOException{
        
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Clients.csv", true));
        bufferedWriter.write(client.getNom()+";"+client.getPrenom()+";"+client.getCommune()+";"+client.getAdresse()+";"+client.getNumero()+";"+client.getCodePostale()+";"+client.getEmail());
        bufferedWriter.newLine();
        bufferedWriter.close();
        
        return 1;
    }
    public int enregistrerNouvelleInformationDevis(DevisContenu devis) throws IOException{
        
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Devis.csv", true));
        bufferedWriter.write(devis.getMateriel().getNom()+";"+devis.getMateriel().getUnite()+";"+devis.getQuantite()+";"+devis.getMateriel().getPrix()+";"+devis.getPvtHT()+";"+devis.getCategorie());
        bufferedWriter.newLine();
        bufferedWriter.close();
        
        return 1;
    }
    
    public void enregistrementModificationCouvertureArdoise(int index,String nom,String unite,Double prix) throws FileNotFoundException, IOException{
        Vector monVector = new Vector(); 
        File f = new File("CouvertureArdoise.csv"); 
        BufferedReader B = new BufferedReader(new FileReader(f)); 
        String ligne = B.readLine(); 
        while (ligne != null){ 
            monVector.addElement(ligne); 
            ligne = B.readLine(); 
        } 
        int numeroLigne=index;
        monVector.set(numeroLigne,nom+";"+unite+";"+prix);
        PrintWriter P = new PrintWriter (new FileWriter(f)); 
        for (int i = 0; i < monVector.size(); i++){ 
            P.println(monVector.get(i)); 
        } 
        P.close();      
    }
    public void enregistrementModificationCouvertureTuile(int index,String nom,String unite,Double prix) throws FileNotFoundException, IOException{
        Vector monVector = new Vector(); 
        File f = new File("CouvertureTuile.csv"); 
        BufferedReader B = new BufferedReader(new FileReader(f)); 
        String ligne = B.readLine(); 
        while (ligne != null){ 
            monVector.addElement(ligne); 
            ligne = B.readLine(); 
        } 
        int numeroLigne=index;
        monVector.set(numeroLigne,nom+";"+unite+";"+prix);
        PrintWriter P = new PrintWriter (new FileWriter(f)); 
        for (int i = 0; i < monVector.size(); i++){ 
            P.println(monVector.get(i)); 
        } 
        P.close();      
    }
    public void enregistrementModificationDemoussage(int index,String nom,String unite,Double prix) throws FileNotFoundException, IOException{
        Vector monVector = new Vector(); 
        File f = new File("Demoussage.csv"); 
        BufferedReader B = new BufferedReader(new FileReader(f)); 
        String ligne = B.readLine(); 
        while (ligne != null){ 
            monVector.addElement(ligne); 
            ligne = B.readLine(); 
        } 
        int numeroLigne=index;
        monVector.set(numeroLigne,nom+";"+unite+";"+prix);
        PrintWriter P = new PrintWriter (new FileWriter(f)); 
        for (int i = 0; i < monVector.size(); i++){ 
            P.println(monVector.get(i)); 
        } 
        P.close();      
    }
    public void enregistrementModificationEquipementDeChantier(int index,String nom,String unite,Double prix) throws FileNotFoundException, IOException{
        Vector monVector = new Vector(); 
        File f = new File("EquipementDeChantier.csv"); 
        BufferedReader B = new BufferedReader(new FileReader(f)); 
        String ligne = B.readLine(); 
        while (ligne != null){ 
            monVector.addElement(ligne); 
            ligne = B.readLine(); 
        } 
        int numeroLigne=index;
        monVector.set(numeroLigne,nom+";"+unite+";"+prix);
        PrintWriter P = new PrintWriter (new FileWriter(f)); 
        for (int i = 0; i < monVector.size(); i++){ 
            P.println(monVector.get(i)); 
        } 
        P.close();      
    }
    public void enregistrementModificationVelux(int index,String nom,String unite,Double prix) throws FileNotFoundException, IOException{
        Vector monVector = new Vector(); 
        File f = new File("Velux.csv"); 
        BufferedReader B = new BufferedReader(new FileReader(f)); 
        String ligne = B.readLine(); 
        while (ligne != null){ 
            monVector.addElement(ligne); 
            ligne = B.readLine(); 
        } 
        int numeroLigne=index;
        monVector.set(numeroLigne,nom+";"+unite+";"+prix);
        PrintWriter P = new PrintWriter (new FileWriter(f)); 
        for (int i = 0; i < monVector.size(); i++){ 
            P.println(monVector.get(i)); 
        } 
        P.close();      
    }
    public void enregistrementModificationZinguerie(int index,String nom,String unite,Double prix) throws FileNotFoundException, IOException{
        Vector monVector = new Vector(); 
        File f = new File("Zinguerie.csv"); 
        BufferedReader B = new BufferedReader(new FileReader(f)); 
        String ligne = B.readLine(); 
        while (ligne != null){ 
            monVector.addElement(ligne); 
            ligne = B.readLine(); 
        } 
        int numeroLigne=index;
        monVector.set(numeroLigne,nom+";"+unite+";"+prix);
        PrintWriter P = new PrintWriter (new FileWriter(f)); 
        for (int i = 0; i < monVector.size(); i++){ 
            P.println(monVector.get(i)); 
        } 
        P.close();      
    }
        public void enregistrementModificationCouverture(int index,String nom,String unite,Double prix) throws FileNotFoundException, IOException{
        Vector monVector = new Vector(); 
        File f = new File("Couverture.csv"); 
        BufferedReader B = new BufferedReader(new FileReader(f)); 
        String ligne = B.readLine(); 
        while (ligne != null){ 
            monVector.addElement(ligne); 
            ligne = B.readLine(); 
        } 
        int numeroLigne=index;
        monVector.set(numeroLigne,nom+";"+unite+";"+prix);
        PrintWriter P = new PrintWriter (new FileWriter(f)); 
        for (int i = 0; i < monVector.size(); i++){ 
            P.println(monVector.get(i)); 
        } 
        P.close();      
    }
    public void enregistrementModificationPlancher(int index,String nom,String unite,Double prix) throws FileNotFoundException, IOException{
        Vector monVector = new Vector(); 
        File f = new File("Plancher.csv"); 
        BufferedReader B = new BufferedReader(new FileReader(f)); 
        String ligne = B.readLine(); 
        while (ligne != null){ 
            monVector.addElement(ligne); 
            ligne = B.readLine(); 
        } 
        int numeroLigne=index;
        monVector.set(numeroLigne,nom+";"+unite+";"+prix);
        PrintWriter P = new PrintWriter (new FileWriter(f)); 
        for (int i = 0; i < monVector.size(); i++){ 
            P.println(monVector.get(i)); 
        } 
        P.close();      
    }
    public void viderFichierdevis() throws IOException{
    PrintWriter pw =  new PrintWriter(new BufferedWriter
           //(new FileWriter("nomFichier.type", true))); // >>>> append = ajout
            //ou bien:
            (new FileWriter("Devis.csv", false))); // >>>> on remplace (on "écrase")
            //ou encore (par défaut):
            //new FileWriter("nomFichier.type"))), // on remplace (on "écrase")

            // Et on ajoute une ligne de longueur 0 (""):
                         pw.println("");
    }

    public void enregistrerNouveauDevisClient(Devis devis,String chantier) throws IOException {
        
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("devis/data/devis-"+devis.getNumero()+".csv", true));
        bufferedWriter.write(devis.getNumero()+";"+devis.getDate()+";"+chantier+";"+devis.getClient().getPrenom()+";"+devis.getClient().getNom()+";"+devis.getClient().getAdresse());
        bufferedWriter.newLine();
        bufferedWriter.close();    

    }
    public int recupererNumeroDevisActuel() {
        File repertoire = new File("devis/");
        String [] listefichiers; 
        int index=0;


        listefichiers=repertoire.list(); 
            for(int i=0;i<listefichiers.length;i++){ 
                if(listefichiers[i].endsWith(".pdf")==true){
                    
                    index++;
                } 
            }
        return index;
    }
    public void viderNouveauDevisClients()throws IOException{
    PrintWriter pw =  new PrintWriter(new BufferedWriter
           //(new FileWriter("nomFichier.type", true))); // >>>> append = ajout
            //ou bien:
            (new FileWriter("devis/data/ClientDevis.csv", false))); // >>>> on remplace (on "écrase")
            //ou encore (par défaut):
            //new FileWriter("nomFichier.type"))), // on remplace (on "écrase")

            // Et on ajoute une ligne de longueur 0 (""):
                         pw.println("");
    }


}
