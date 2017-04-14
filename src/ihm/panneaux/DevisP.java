/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.panneaux;

//import iutlr.dutinfo.bd.AccesBD;
import com.itextpdf.text.DocumentException;
import ihm.fenetres.AjoutMaterielDevis;
import ihm.fenetres.FenetrePrincipale;
import ihm.fenetres.ParametreDevisDialog;
import ihm.fenetres.RechercheClientDialog;
import ihm.renderers.StyleCellules;
import ihm.renderers.StyleEntete;
import ihm.tableaux.TableauDevis;
import ihm.tableaux.TableauMateriel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import modele.Sessions;
import modele.Materiel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modele.Devis;
import modele.DevisClient;
import modele.DevisContenu;
import modele.Entreprise;

/**
 *
 * @author tpoyault
 */
public class DevisP extends JPanel {
    ArrayList<DevisContenu> listeMaterielDevis;
    private JButton btnAjouter;
    private JButton btnSupprimer;
    private JScrollPane soutPanel;
    private JTable tabSout;
    private Sessions sessionCourante;
    private JComboBox typeMateriel;
    private JLabel titreLabel;
    private ImageIcon imgParametre;
    private JLabel parametre;
    private Devis devis;
    private Entreprise entreprise;
    private JLabel tvaLabel,htTotalLabel,ttcTotalLabel;
    private JLabel htTotal;
    private JLabel ttcTotal;
    private JLabel tva;
    private JLabel chantierLabel;
    private JTextField chantierTextField;
    private JButton btnEnvoyer;
    private int selectedIndex;
    public DevisP(Sessions session) throws IOException {
        sessionCourante = session;
        entreprise = new Entreprise(sessionCourante.getRecupDonnees().recupererNomEntreprise(),sessionCourante.getRecupDonnees().recupererSiegeEntreprise(),sessionCourante.getRecupDonnees().recupererNumeroEntreprise(),sessionCourante.getRecupDonnees().recupererCoGerant());
        devis = new Devis(0, null, null, 0, entreprise, sessionCourante.getRecupDonnees().recupererInformationsComplementaire());
        // Entreprise Devis Initialisé
        ParametreDevisDialog rechercheInformationDevis = new ParametreDevisDialog(null,this.devis, "Parametre Devis", true, sessionCourante); 
        // TVA NUMERO DATE 
        RechercheClientDialog rechercheClient = new RechercheClientDialog(null,this.devis,"Recherche Client",true,sessionCourante);
        // Client
        setPreferredSize(new Dimension(450, 550));
        selectedIndex=0;
        initComponents();
    }
                      
    private void initComponents() throws IOException {     
        
        soutPanel = new JScrollPane();
        btnAjouter = new JButton();
        btnSupprimer = new JButton();
        htTotal = new JLabel();
        ttcTotal = new JLabel();
        titreLabel = new JLabel("Creation Devis");
        titreLabel.setFont(new Font("Arial", Font.BOLD, 16)); 
        
            String[] intitules = {"Description", "Unite", "Quantite","Prix HT","PVT HT","Categorie"};
            
            listeMaterielDevis = sessionCourante.getRecupDonnees().recupererDevis();
            TableauDevis model = new TableauDevis(listeMaterielDevis, intitules, sessionCourante);
            tabSout= new JTable(model);
            model.setData(listeMaterielDevis);

            for(int numColonne = 0; numColonne < tabSout.getColumnCount(); numColonne++){
                tabSout.getColumnModel().getColumn(numColonne).setCellRenderer(new StyleCellules());     
                tabSout.getColumnModel().getColumn(numColonne).setHeaderRenderer(new StyleEntete()); 
            }
            tabSout.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabSout.getColumnModel().getColumn(1).setPreferredWidth(100);   
            tabSout.getColumnModel().getColumn(2).setPreferredWidth(100);
            tabSout.getColumnModel().getColumn(3).setPreferredWidth(150);
            tabSout.getColumnModel().getColumn(4).setPreferredWidth(150);
            tabSout.getColumnModel().getColumn(5).setPreferredWidth(200); 
            RowSorter<TableauDevis> sorter = new TableRowSorter<TableauDevis>(model);
            tabSout.setRowSorter(sorter);
            soutPanel.setViewportView(this.tabSout);
        
        
        btnAjouter.setText("Ajouter Ligne");
        
        btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    
                    AjoutMaterielDevis ajoutMateriel = new AjoutMaterielDevis(null, "Ajout Materiel", true, sessionCourante,selectedIndex);
                    selectedIndex = ajoutMateriel.getSelectedIndex();
                    listeMaterielDevis = sessionCourante.getRecupDonnees().recupererDevis();
                   
                    /* Affichage total ht */
                    double htTotalParcour = 0;
                    for (DevisContenu listeMaterielDevi : listeMaterielDevis) {
                        htTotalParcour+=listeMaterielDevi.getPvtHT();
                    }
                    double prixHT_2Chiffre = Math.round(htTotalParcour * Math.pow(10,2)) / Math.pow(10,2);
                    htTotal.setText(Double.toString(prixHT_2Chiffre));
                    /* Affichage total ttc */
                    double ttcTotalParcour = 0;
                    for (DevisContenu listeMaterielDevi : listeMaterielDevis) {
                        ttcTotalParcour+=listeMaterielDevi.getPvtHT();
                    }
                    ttcTotalParcour = (ttcTotalParcour * devis.getTva()/100)+ttcTotalParcour;
                    double prixTTC_2Chiffre = Math.round(ttcTotalParcour * Math.pow(10,2)) / Math.pow(10,2);
                    ttcTotal.setText(Double.toString(prixTTC_2Chiffre));
                    
                    model.setData(listeMaterielDevis);
                } catch (IOException ex) {
                    Logger.getLogger(DevisP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btnSupprimer.setText("Supprimer Ligne");
        btnSupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            int reponse = JOptionPane.showConfirmDialog(null,
			       "Pascal tu es sur de supprimer la ligne ?",
			       "Attention", 
			        JOptionPane.YES_NO_OPTION);
	  if (reponse == JOptionPane.YES_OPTION){
                try {
                    listeMaterielDevis = sessionCourante.getRecupDonnees().supprimerDevisMateriel(tabSout.getSelectedRow());
                    
                    /* Affichage total ht */
                    double htTotalParcour = 0;
                    for (DevisContenu listeMaterielDevi : listeMaterielDevis) {
                        htTotalParcour+=listeMaterielDevi.getPvtHT();
                    }
                    double prixHT_2Chiffre = Math.round(htTotalParcour * Math.pow(10,2)) / Math.pow(10,2);
                    htTotal.setText(Double.toString(prixHT_2Chiffre));
                    /* Affichage total ttc */
                    double ttcTotalParcour = 0;
                    for (DevisContenu listeMaterielDevi : listeMaterielDevis) {
                        ttcTotalParcour+=listeMaterielDevi.getPvtHT();
                    }
                    ttcTotalParcour = (ttcTotalParcour * devis.getTva()/100)+ttcTotalParcour;
                    double prixTTC_2Chiffre = Math.round(ttcTotalParcour * Math.pow(10,2)) / Math.pow(10,2);
                    ttcTotal.setText(Double.toString(prixTTC_2Chiffre));
                    
                    
                    model.setData(listeMaterielDevis);
                } catch (IOException ex) {
                    Logger.getLogger(DevisP.class.getName()).log(Level.SEVERE, null, ex);
                }
          }
            }
        });
        
        

        soutPanel = new JScrollPane(tabSout);
        soutPanel.getViewport().add(tabSout);
        soutPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.GRAY), "Informations"
                    + "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 17), Color.DARK_GRAY));
        soutPanel.setPreferredSize(new Dimension(600, 300));
        soutPanel.setBackground(new Color(238, 238, 238));
     
        btnEnvoyer = new JButton();
        btnEnvoyer.setText("Envoyer");
        btnEnvoyer.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    String chantier = chantierTextField.getText();
                    sessionCourante.getSaveDonnees().enregistrerNouveauDevisClient(devis, chantier);
                    new GenererPdf(sessionCourante,devis,listeMaterielDevis,chantier);
                    listeMaterielDevis.clear();
                    sessionCourante.getSaveDonnees().viderFichierdevis();
                    sessionCourante.changerAffichage(FenetrePrincipale.Etat.ACCUEIL);
                } catch (DocumentException ex) {
                    Logger.getLogger(DevisP.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(DevisP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
        tva = new JLabel();
        tva.setText(Integer.toString(devis.getTva()));
        
        tvaLabel = new JLabel();
        htTotalLabel = new JLabel();
        ttcTotalLabel = new JLabel();
        tvaLabel.setText("Tva :");
        htTotalLabel.setText("Total HT :");
        ttcTotalLabel.setText("Total TTC :");
        
        JPanel mid = new JPanel();
        JPanel bot = new JPanel();
        
        mid.add(tvaLabel,BorderLayout.NORTH);
        mid.add(tva,BorderLayout.NORTH);
        mid.add(htTotalLabel,BorderLayout.NORTH);
        mid.add(htTotal,BorderLayout.NORTH);
        mid.add(ttcTotalLabel,BorderLayout.NORTH);
        mid.add(ttcTotal,BorderLayout.NORTH);
        
        chantierLabel = new JLabel();
        chantierLabel.setText("Chantier:");
        chantierTextField = new JTextField();
        chantierTextField.setPreferredSize( new Dimension( 110, 24 ) );
        chantierTextField.setText(devis.getClient().getAdresse()+" "+devis.getClient().getCommune());
        
        bot.add(btnAjouter,BorderLayout.NORTH);
        bot.add(btnSupprimer,BorderLayout.NORTH);
        bot.add(chantierLabel,BorderLayout.NORTH);
        bot.add(chantierTextField,BorderLayout.NORTH);
        bot.add(btnEnvoyer,BorderLayout.NORTH);
        
           
        
        
        this.add(titreLabel,BorderLayout.NORTH);
        this.add(soutPanel,BorderLayout.NORTH);
        this.add(mid,BorderLayout.SOUTH);
        this.add(bot,BorderLayout.SOUTH);
        /*
        this.add(titreLabel,BorderLayout.NORTH);
        this.add(soutPanel, BorderLayout.CENTER);
        this.add(tva,BorderLayout.SOUTH);
        this.add(htTotal,BorderLayout.SOUTH);
        this.add(ttcTotal,BorderLayout.SOUTH);
        this.add(btnAjouter, BorderLayout.PAGE_END);
        this.add(btnSupprimer, BorderLayout.PAGE_END);
        this.add(btnEnvoyer,BorderLayout.PAGE_END);
        */
    }

    public Devis getDevis() {
        return devis;
    }

    public ArrayList<DevisContenu> getListeMaterielDevis() {
        return listeMaterielDevis;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }
    
}