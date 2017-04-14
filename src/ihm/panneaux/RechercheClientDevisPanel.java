/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.panneaux;

//import iutlr.dutinfo.bd.AccesBD;
import com.sun.mail.smtp.SMTPTransport;
import ihm.fenetres.EnvoieDevis;
import ihm.fenetres.RechercheClientDevisDialog;
import ihm.fenetres.RechercheClientDialog;
import ihm.renderers.StyleCellules;
import ihm.renderers.StyleEntete;
import ihm.tableaux.TableauClient;
import ihm.tableaux.TableauDevisClients;
import ihm.tableaux.TableauMateriel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import modele.Sessions;
import modele.Materiel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modele.DevisClient;

/**
 *
 * @author tpoyault
 */
public class RechercheClientDevisPanel extends JPanel {
    ArrayList<DevisClient> listeDevisClients;
    private JButton btnOuvrir;
    private JButton btnOuvrirDevis;
    private JButton btnEnvoyer;
    private JScrollPane soutPanel;
    private JTable tabSout;
    private Sessions sessionCourante;
    private JComboBox typeMateriel;
    private JLabel titreLabel;
    
    public RechercheClientDevisPanel(Sessions session) throws IOException {
        sessionCourante = session;
        
        setPreferredSize(new Dimension(450, 500));
        initComponents();
    }
                      
    private void initComponents() throws IOException {
        soutPanel = new JScrollPane();
        btnOuvrir = new JButton();
        btnOuvrirDevis = new JButton();
        btnEnvoyer = new JButton();
        btnEnvoyer.setText("Envoyer");
        
        titreLabel = new JLabel("Recherche Devis");
        titreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        listeDevisClients = sessionCourante.getRecupDonnees().recupererDevisClientVide();
        
        String[] intitules = {"Numero", "Date", "Chantier"};
        //tabSout = new JTable(new TableauMateriel(listeMateriel, intitules, sessionCourante));
        TableauDevisClients model = new TableauDevisClients(listeDevisClients, intitules, sessionCourante);
        tabSout= new JTable(model);         

        
        for(int numColonne = 0; numColonne < tabSout.getColumnCount(); numColonne++){
            tabSout.getColumnModel().getColumn(numColonne).setCellRenderer(new StyleCellules());     
            tabSout.getColumnModel().getColumn(numColonne).setHeaderRenderer(new StyleEntete()); 
        }
        tabSout.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabSout.getColumnModel().getColumn(1).setPreferredWidth(100);   
        tabSout.getColumnModel().getColumn(2).setPreferredWidth(300); 
        RowSorter<TableauDevisClients> sorter = new TableRowSorter<TableauDevisClients>(model);
        tabSout.setRowSorter(sorter);
        soutPanel.setViewportView(this.tabSout);
        
        btnOuvrir.setText("Recherche Client");
        
        btnOuvrir.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {                                           
                try {
                    listeDevisClients = sessionCourante.getRecupDonnees().recupererDevisClientVide();
                    model.setData(listeDevisClients);
                    RechercheClientDevisDialog rechercheClient = new RechercheClientDevisDialog(null,listeDevisClients,"Recherche Client",true,sessionCourante);
                    
                    listeDevisClients = sessionCourante.getRecupDonnees().getDevisClient();
                    model.setData(listeDevisClients);
                } catch (IOException ex) {
                    Logger.getLogger(RechercheClientDevisPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        
        btnOuvrirDevis.setText("Ouvrir devis");
        btnOuvrirDevis.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(tabSout.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne", "Attention",JOptionPane.INFORMATION_MESSAGE);

                }else{
                    try {
                        
                        Object numero = model.getValueAt(tabSout.getSelectedRow(), 0);
                        int indexFile = (Integer) numero;
                        
                        Desktop d = Desktop.getDesktop();
                        d.open(new File("devis/devis-"+indexFile+".pdf"));
                        
                        
                        
                    } catch (IOException ex) {
                        Logger.getLogger(RechercheClientDevisPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            
            }});
        btnEnvoyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tabSout.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne", "Attention",JOptionPane.INFORMATION_MESSAGE);

                }else{
                    
                        
                Object numero = model.getValueAt(tabSout.getSelectedRow(), 0);
                int numeroDevis = (Integer) numero;
                        
                        
                        
                        
                    
                JCheckBox chSecreteriat = new JCheckBox("Secretaire");
                JCheckBox chClient = new JCheckBox("Client");
                String msge = "A qui veut tu envoyer le devis ?";
                Object[] msgContent = {msge,chSecreteriat,chClient};

                
                int n =  JOptionPane.showConfirmDialog (null, "Veux tu l'envoyer à ta secretaire?","Envois d'email",JOptionPane.YES_NO_OPTION);
                
                if(n==0){
                    
                try {
                        Properties props = null;
                        Session session = null;
                        SMTPTransport transport = null;
                        MimeMessage mimeMsg = null;
                        String sProtocole = "xxxxx"; // ou "smtps"
                        String sURLServeurSMTP = "xxxxxxxx";
                        String sNumPort = "587"; // ou 465 pour TLS
                        String sUserName = "xxxxx@xxxxxx.xx";
                        String sMdp = "xxxxxxxxx";
                        
                        
                        String secreteriat="xxxxx@xxxxxx.xx";
                        
                        
                        props = System.getProperties();
                        props.put("mail." + sProtocole + ".host", sURLServeurSMTP);
                        props.put("mail." + sProtocole + ".port", sNumPort);
                        props.put("mail." + sProtocole + ".auth", "true");
                        if( sProtocole.equals("smtp") && sNumPort.equals("587") ) {
                            props.put("mail.smtp.starttls.enable", "true");
                        }
                        
                        Session sessionS = Session.getDefaultInstance(props,
                                new javax.mail.Authenticator(){
                                    protected PasswordAuthentication getPasswordAuthentication() {
                                        return new PasswordAuthentication(
                                                "xxxxx@xxxxxx.xx", "xxxxxxxxxxxx");// Specify the Username and the PassWord
                                    }
                        });
                        transport = (SMTPTransport)sessionS.getTransport(sProtocole);
                        transport.connect(sURLServeurSMTP, Integer.parseInt(sNumPort), sUserName, sMdp);
                        
                        
                        /*
                        Construire ici son mail
                        */
                        // Instantiate a message
                        Message msg = new MimeMessage(sessionS);
                        
                        //Set message attributes
                        msg.setFrom(new InternetAddress("xxxxx@xxxxxx.xx"));
                        InternetAddress[] address = {new InternetAddress(secreteriat)};
                        msg.setRecipients(Message.RecipientType.TO, address);
                        msg.setSubject("Toitures Poitevines Devis n°"+numeroDevis);
                        msg.setSentDate(new Date());
                        //Send the message
                        
                        
                        
                        
                        
                        
                        
                        // Première partie du message
                        BodyPart messageBodyPart = new MimeBodyPart();
                        messageBodyPart.setText("devis n°"+numeroDevis);
                        
                        //Ajout de la première partie du message dans un objet Multipart
                        Multipart multipart = new MimeMultipart();
                        multipart.addBodyPart(messageBodyPart);
                        // Partie de la pièce jointe
                        messageBodyPart = new MimeBodyPart();
                        
                        DataSource source = new FileDataSource("devis/devis-"+numeroDevis+".pdf");
                        messageBodyPart.setDataHandler(new DataHandler(source));
                        messageBodyPart.setFileName("devis-"+numeroDevis+".pdf");
                        
                        //Ajout de la partie pièce jointe
                        multipart.addBodyPart(messageBodyPart);
                        msg.setContent(multipart);
                        Transport.send(msg,msg.getAllRecipients());
                        transport.close();
                        JOptionPane.showMessageDialog(null,"Message envoyer !");
                    } catch (MessagingException ex) {
                        Logger.getLogger(RechercheClientDevisPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    
                
                }
                
            }
            }});
        soutPanel = new JScrollPane(tabSout);
        soutPanel.getViewport().add(tabSout);
        soutPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.GRAY), "Liste des devis du clients"
                + "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 17), Color.DARK_GRAY));
        soutPanel.setPreferredSize(new Dimension(600, 300));
        soutPanel.setBackground(new Color(238, 238, 238));
       
        this.add(titreLabel,BorderLayout.NORTH);
        this.add(soutPanel, BorderLayout.CENTER);
        this.add(btnOuvrir, BorderLayout.PAGE_END);
        this.add(btnOuvrirDevis, BorderLayout.PAGE_END);
        this.add(btnEnvoyer,BorderLayout.PAGE_END);
        
    }                
}