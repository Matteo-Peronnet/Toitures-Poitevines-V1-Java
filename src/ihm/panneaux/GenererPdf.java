/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.panneaux;

import java.util.ArrayList;
import modele.DevisContenu;
import modele.Sessions;
import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
import modele.Devis;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import ihm.fenetres.EnvoieDevis;
import static java.awt.Color.red;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static sun.net.www.http.HttpClient.New;
import static sun.net.www.http.HttpClient.New;
/**
 *
 * @author Travail Ruel
 */
public class GenererPdf {
    private Devis devis;
    private Sessions session;
    ArrayList<DevisContenu> listeMaterielDevis; 
    private ArrayList<DevisContenu> CouvertureArdoise;
    private ArrayList<DevisContenu> CouvertureTuile;
    private ArrayList<DevisContenu> Demoussage;
    private ArrayList<DevisContenu> EquipementDeChantier;
    private ArrayList<DevisContenu> Velux;
    private ArrayList<DevisContenu> Zinguerie;
    private ArrayList<DevisContenu> Couverture;
    private ArrayList<DevisContenu> Plancher;
    private String chantier;
    
    public GenererPdf(Sessions session,Devis devis,ArrayList<DevisContenu> listeMaterielDevis,String chantier) throws DocumentException, IOException {
        this.devis = devis;
        this.session=session;
        this.listeMaterielDevis=listeMaterielDevis;
        CouvertureArdoise = new ArrayList<>();
        CouvertureTuile = new ArrayList<>();
        Demoussage = new ArrayList<>();
        EquipementDeChantier = new ArrayList<>(); 
        Velux = new ArrayList<>();
        Zinguerie = new ArrayList<>();
        Couverture = new ArrayList<>();
        Plancher = new ArrayList<>();
        
        this.chantier=chantier;
        for (DevisContenu listeMaterielDevi : listeMaterielDevis) {
            if(listeMaterielDevi.getCategorie().equalsIgnoreCase("CouvertureArdoise")){
              
                CouvertureArdoise.add(listeMaterielDevi);
            }
            
        }
        for (DevisContenu listeMaterielDevi : listeMaterielDevis) {
            if(listeMaterielDevi.getCategorie().equalsIgnoreCase("CouvertureTuile")){
                CouvertureTuile.add(listeMaterielDevi);
            }
        }
        for (DevisContenu listeMaterielDevi : listeMaterielDevis) {
            if(listeMaterielDevi.getCategorie().equalsIgnoreCase("Demoussage")){
                Demoussage.add(listeMaterielDevi);
            }
        }
        for (DevisContenu listeMaterielDevi : listeMaterielDevis) {
            if(listeMaterielDevi.getCategorie().equalsIgnoreCase("EquipementDeChantier")){
                EquipementDeChantier.add(listeMaterielDevi);
            }
        }
        for (DevisContenu listeMaterielDevi : listeMaterielDevis) {
            if(listeMaterielDevi.getCategorie().equalsIgnoreCase("Velux")){
                Velux.add(listeMaterielDevi);
            }
        }
        for (DevisContenu listeMaterielDevi : listeMaterielDevis) {
            if(listeMaterielDevi.getCategorie().equalsIgnoreCase("Zinguerie")){
                Zinguerie.add(listeMaterielDevi);
            }
        }
        for (DevisContenu listeMaterielDevi : listeMaterielDevis) {
            if(listeMaterielDevi.getCategorie().equalsIgnoreCase("Couverture")){
                Couverture.add(listeMaterielDevi);
            }
        }
        for (DevisContenu listeMaterielDevi : listeMaterielDevis) {
            if(listeMaterielDevi.getCategorie().equalsIgnoreCase("Plancher")){
                Plancher.add(listeMaterielDevi);
            }
        }
        
        initComponement();
    }

    private void initComponement() throws FileNotFoundException, DocumentException, IOException {
                        String DEST ="devis/devis-"+devis.getNumero()+".pdf";
    			OutputStream file = new FileOutputStream(new File(DEST));

			com.itextpdf.text.Document document = new com.itextpdf.text.Document();
			PdfWriter.getInstance((com.itextpdf.text.Document) document, file);
                        // PAS TOUCHE
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
        
        Rectangle rect = new Rectangle(30, 30, 550, 800);
        writer.setBoxSize("art", rect);
        HeaderFooterPageEvent event = new HeaderFooterPageEvent();
        writer.setPageEvent(event);
	document.open();    
        PdfContentByte canvas = writer.getDirectContent();
        
        
        Image logo = Image.getInstance("images/logo.png");
        logo.scaleAbsolute(200, 57);
        
        
        // INFO DEVIS
        Image devisImg = Image.getInstance("images/imgDevis.png");
        devisImg.scaleAbsolute(110, 34);
        devisImg.setAbsolutePosition((PageSize.POSTCARD.getWidth()+150),
                (PageSize.POSTCARD.getHeight()-devisImg.getScaledHeight())*2);
        Font fontImgDevis = new Font(Font.FontFamily.TIMES_ROMAN  , 12, Font.BOLD);
        Phrase numeroInfo = new Phrase(   "Numéro  "+devis.getNumero(), fontImgDevis);
        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, numeroInfo, devisImg.getScaledWidth()+413, devisImg.getScaledHeight()+710, 0);
        Phrase dateInfo = new Phrase( "Le  "+devis.getDate(),fontImgDevis);
        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, dateInfo, devisImg.getScaledWidth()+420, devisImg.getScaledHeight()+695, 0);
        Image devisImgBordure = Image.getInstance("images/bordureDevis.png");
        devisImgBordure.setAbsolutePosition((devisImg.getScaledWidth()+318),devisImg.getScaledHeight()+660);
        devisImgBordure.scaleAbsolute(120, 122);
        
        // INFO ENTREPRISE
        Font fontEntreprise = new Font(Font.FontFamily.TIMES_ROMAN  , 11, Font.BOLD);
        Phrase coGerants = new Phrase(   "Co-gérants : "+devis.getEntreprise().getCoGerant(), fontEntreprise);
        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, coGerants,devisImg.getScaledWidth()+210,devisImg.getScaledHeight()+660, 0);
        Phrase siege = new Phrase(   "Siège : "+devis.getEntreprise().getSiege(), fontEntreprise);
        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, siege,devisImg.getScaledWidth()+149,devisImg.getScaledHeight()+640, 0);
        Phrase tel = new Phrase(   "TEL : "+devis.getEntreprise().getTel(), fontEntreprise);
        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, tel,devisImg.getScaledWidth()+113,devisImg.getScaledHeight()+625, 0);
        Image bordureEntreprise = Image.getInstance("images/bordureEntreprise.png");
        bordureEntreprise.setAbsolutePosition(devisImg.getScaledWidth()-80, devisImg.getScaledHeight()+610);
        
        
        // INFO CLIENT
        Font fontClient = new Font(Font.FontFamily.TIMES_ROMAN  , 11, Font.NORMAL);
        Phrase nomPrenom = new Phrase(devis.getClient().getNom()+" "+devis.getClient().getPrenom(), fontClient);
        Phrase commune = new Phrase(devis.getClient().getAdresse(), fontClient);
        Phrase adresse = new Phrase(devis.getClient().getCodePostale()+" " +devis.getClient().getCommune(), fontClient); 
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, nomPrenom,PageSize.A4.getWidth()-280,devisImg.getScaledHeight()+595, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, commune,PageSize.A4.getWidth()-280,devisImg.getScaledHeight()+580, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, adresse,PageSize.A4.getWidth()-280,devisImg.getScaledHeight()+565, 0);

        
        document.add(logo);
        document.add(devisImg);
        document.add(devisImgBordure);
        document.add(bordureEntreprise);
        
        
       // AJOUT DES DONNEES DU TABLEAUX 
       
        float[] columnWidths = {14, 3, 4,3,4};
        Font fontCategorie = new Font(Font.FontFamily.TIMES_ROMAN  , 12, Font.BOLD);
        fontCategorie.setColor(BaseColor.GREEN);
        
        PdfPTable table = new PdfPTable(columnWidths);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        
        table.setTotalWidth(PageSize.A4.getWidth()-15);
        
        table.setSpacingBefore(150);
        
        table.setLockedWidth(true);
        
        Font Categorie = new Font(FontFamily.HELVETICA, 13, Font.BOLD);
        Font Chantier = new Font(FontFamily.HELVETICA, 11, Font.UNDERLINE);
        
        PdfPCell cellChantier = new PdfPCell(new Phrase("CHANTIER : "+chantier,Chantier));
        cellChantier.setColspan(3);
        cellChantier.setBorder(Rectangle.NO_BORDER);
        table.addCell(cellChantier);
        table.addCell(" ");
        table.addCell(" ");    

        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" "); 
        table.addCell(" ");
        
        table.addCell(new Phrase("Désignation", Categorie));
        PdfPCell unite = new PdfPCell(new Phrase("Unite", Categorie));
        PdfPCell quantite = new PdfPCell(new Phrase("Quantite", Categorie));
        PdfPCell prixht = new PdfPCell(new Phrase("Prix HT", Categorie));
        PdfPCell pvtht = new PdfPCell(new Phrase("Pvt HT", Categorie));
        
        unite.setHorizontalAlignment(Element.ALIGN_CENTER);
        quantite.setHorizontalAlignment(Element.ALIGN_CENTER);
        prixht.setHorizontalAlignment(Element.ALIGN_CENTER);
        pvtht.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        unite.setBorder(Rectangle.NO_BORDER);
        quantite.setBorder(Rectangle.NO_BORDER);
        prixht.setBorder(Rectangle.NO_BORDER);
        pvtht.setBorder(Rectangle.NO_BORDER);
        
        table.addCell(unite);
        table.addCell(quantite);
        table.addCell(prixht);
        table.addCell(pvtht);
        
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        
        double htTotalParcour = 0;
        double ttcTotalParcour = 0;
        
        if(this.EquipementDeChantier.isEmpty()==false){
            table.addCell(new Phrase("Equipement De Chantier",fontCategorie));
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            
            for (DevisContenu listeMaterielDevi : EquipementDeChantier) {
                
                
                table.addCell(listeMaterielDevi.getMateriel().getNom());
                PdfPCell cellunite = new PdfPCell(new Phrase(listeMaterielDevi.getMateriel().getUnite()));
                PdfPCell cellQuantite = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getQuantite())));
                PdfPCell cellprix = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getMateriel().getPrix())+"€"));
                PdfPCell cellpvtHT = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getPvtHT())+"€"));
                cellunite.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellQuantite.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellprix.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellpvtHT.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                cellunite.setBorder(Rectangle.NO_BORDER);
                cellQuantite.setBorder(Rectangle.NO_BORDER);
                cellprix.setBorder(Rectangle.NO_BORDER);
                cellpvtHT.setBorder(Rectangle.NO_BORDER);
                
                table.addCell(cellunite);
                table.addCell(cellQuantite);
                table.addCell(cellprix);
                table.addCell(cellpvtHT);
                
                /* BACKUP
                table.addCell(listeMaterielDevi.getMateriel().getNom());
                table.addCell(listeMaterielDevi.getMateriel().getUnite());
                table.addCell(Double.toString(listeMaterielDevi.getQuantite()));
                table.addCell(Double.toString(listeMaterielDevi.getMateriel().getPrix())+"€");
                table.addCell(Double.toString(listeMaterielDevi.getPvtHT())+"€");
                */
                ttcTotalParcour+=listeMaterielDevi.getPvtHT();
                htTotalParcour+=listeMaterielDevi.getPvtHT();
            }
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
        }
        if(Couverture.isEmpty()==false){
            table.addCell(new Phrase("Couverture",fontCategorie));
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            for (DevisContenu listeMaterielDevi : Couverture) {
                table.addCell(listeMaterielDevi.getMateriel().getNom());
                PdfPCell cellunite = new PdfPCell(new Phrase(listeMaterielDevi.getMateriel().getUnite()));
                PdfPCell cellQuantite = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getQuantite())));
                PdfPCell cellprix = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getMateriel().getPrix())+"€"));
                PdfPCell cellpvtHT = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getPvtHT())+"€"));
                cellunite.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellQuantite.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellprix.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellpvtHT.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                cellunite.setBorder(Rectangle.NO_BORDER);
                cellQuantite.setBorder(Rectangle.NO_BORDER);
                cellprix.setBorder(Rectangle.NO_BORDER);
                cellpvtHT.setBorder(Rectangle.NO_BORDER);
                
                table.addCell(cellunite);
                table.addCell(cellQuantite);
                table.addCell(cellprix);
                table.addCell(cellpvtHT);

                ttcTotalParcour+=listeMaterielDevi.getPvtHT();
                htTotalParcour+=listeMaterielDevi.getPvtHT();
            }
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
        }       
        if(CouvertureArdoise.isEmpty()==false){
            table.addCell(new Phrase("Couverture Ardoise",fontCategorie));
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            for (DevisContenu listeMaterielDevi : CouvertureArdoise) {
                table.addCell(listeMaterielDevi.getMateriel().getNom());
                PdfPCell cellunite = new PdfPCell(new Phrase(listeMaterielDevi.getMateriel().getUnite()));
                PdfPCell cellQuantite = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getQuantite())));
                PdfPCell cellprix = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getMateriel().getPrix())+"€"));
                PdfPCell cellpvtHT = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getPvtHT())+"€"));
                cellunite.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellQuantite.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellprix.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellpvtHT.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                cellunite.setBorder(Rectangle.NO_BORDER);
                cellQuantite.setBorder(Rectangle.NO_BORDER);
                cellprix.setBorder(Rectangle.NO_BORDER);
                cellpvtHT.setBorder(Rectangle.NO_BORDER);
                
                table.addCell(cellunite);
                table.addCell(cellQuantite);
                table.addCell(cellprix);
                table.addCell(cellpvtHT);

                ttcTotalParcour+=listeMaterielDevi.getPvtHT();
                htTotalParcour+=listeMaterielDevi.getPvtHT();
            }
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
        }
        if(CouvertureTuile.isEmpty()==false){
            table.addCell(new Phrase("Couverture Tuile",fontCategorie));
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            for (DevisContenu listeMaterielDevi : CouvertureTuile) {
                table.addCell(listeMaterielDevi.getMateriel().getNom());
                PdfPCell cellunite = new PdfPCell(new Phrase(listeMaterielDevi.getMateriel().getUnite()));
                PdfPCell cellQuantite = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getQuantite())));
                PdfPCell cellprix = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getMateriel().getPrix())+"€"));
                PdfPCell cellpvtHT = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getPvtHT())+"€"));
                cellunite.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellQuantite.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellprix.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellpvtHT.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                cellunite.setBorder(Rectangle.NO_BORDER);
                cellQuantite.setBorder(Rectangle.NO_BORDER);
                cellprix.setBorder(Rectangle.NO_BORDER);
                cellpvtHT.setBorder(Rectangle.NO_BORDER);
                
                table.addCell(cellunite);
                table.addCell(cellQuantite);
                table.addCell(cellprix);
                table.addCell(cellpvtHT);

                ttcTotalParcour+=listeMaterielDevi.getPvtHT();
                htTotalParcour+=listeMaterielDevi.getPvtHT();
            }
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
        }
        if(this.Demoussage.isEmpty()==false){
            table.addCell(new Phrase("Demoussage",fontCategorie));
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            for (DevisContenu listeMaterielDevi : Demoussage) {
                table.addCell(listeMaterielDevi.getMateriel().getNom());
                PdfPCell cellunite = new PdfPCell(new Phrase(listeMaterielDevi.getMateriel().getUnite()));
                PdfPCell cellQuantite = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getQuantite())));
                PdfPCell cellprix = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getMateriel().getPrix())+"€"));
                PdfPCell cellpvtHT = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getPvtHT())+"€"));
                cellunite.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellQuantite.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellprix.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellpvtHT.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                cellunite.setBorder(Rectangle.NO_BORDER);
                cellQuantite.setBorder(Rectangle.NO_BORDER);
                cellprix.setBorder(Rectangle.NO_BORDER);
                cellpvtHT.setBorder(Rectangle.NO_BORDER);
                
                table.addCell(cellunite);
                table.addCell(cellQuantite);
                table.addCell(cellprix);
                table.addCell(cellpvtHT);

                ttcTotalParcour+=listeMaterielDevi.getPvtHT();
                htTotalParcour+=listeMaterielDevi.getPvtHT();
            }
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            
        }
        if(this.Velux.isEmpty()==false){
            table.addCell(new Phrase("Velux",fontCategorie));
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            for (DevisContenu listeMaterielDevi : Velux) {
                table.addCell(listeMaterielDevi.getMateriel().getNom());
                PdfPCell cellunite = new PdfPCell(new Phrase(listeMaterielDevi.getMateriel().getUnite()));
                PdfPCell cellQuantite = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getQuantite())));
                PdfPCell cellprix = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getMateriel().getPrix())+"€"));
                PdfPCell cellpvtHT = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getPvtHT())+"€"));
                cellunite.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellQuantite.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellprix.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellpvtHT.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                cellunite.setBorder(Rectangle.NO_BORDER);
                cellQuantite.setBorder(Rectangle.NO_BORDER);
                cellprix.setBorder(Rectangle.NO_BORDER);
                cellpvtHT.setBorder(Rectangle.NO_BORDER);
                
                table.addCell(cellunite);
                table.addCell(cellQuantite);
                table.addCell(cellprix);
                table.addCell(cellpvtHT);

                ttcTotalParcour+=listeMaterielDevi.getPvtHT();
                htTotalParcour+=listeMaterielDevi.getPvtHT();
            }
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            
        }
        if(this.Zinguerie.isEmpty()==false){
            table.addCell(new Phrase("Zinguerie", fontCategorie));
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            for (DevisContenu listeMaterielDevi : Zinguerie) {
                table.addCell(listeMaterielDevi.getMateriel().getNom());
                PdfPCell cellunite = new PdfPCell(new Phrase(listeMaterielDevi.getMateriel().getUnite()));
                PdfPCell cellQuantite = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getQuantite())));
                PdfPCell cellprix = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getMateriel().getPrix())+"€"));
                PdfPCell cellpvtHT = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getPvtHT())+"€"));
                cellunite.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellQuantite.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellprix.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellpvtHT.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                cellunite.setBorder(Rectangle.NO_BORDER);
                cellQuantite.setBorder(Rectangle.NO_BORDER);
                cellprix.setBorder(Rectangle.NO_BORDER);
                cellpvtHT.setBorder(Rectangle.NO_BORDER);
                
                table.addCell(cellunite);
                table.addCell(cellQuantite);
                table.addCell(cellprix);
                table.addCell(cellpvtHT);

                ttcTotalParcour+=listeMaterielDevi.getPvtHT();
                htTotalParcour+=listeMaterielDevi.getPvtHT();
            }
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
        }
        if(Plancher.isEmpty()==false){
            table.addCell(new Phrase("Plancher",fontCategorie));
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            for (DevisContenu listeMaterielDevi : Plancher) {
                table.addCell(listeMaterielDevi.getMateriel().getNom());
                PdfPCell cellunite = new PdfPCell(new Phrase(listeMaterielDevi.getMateriel().getUnite()));
                PdfPCell cellQuantite = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getQuantite())));
                PdfPCell cellprix = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getMateriel().getPrix())+"€"));
                PdfPCell cellpvtHT = new PdfPCell(new Phrase(Double.toString(listeMaterielDevi.getPvtHT())+"€"));
                cellunite.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellQuantite.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellprix.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellpvtHT.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                cellunite.setBorder(Rectangle.NO_BORDER);
                cellQuantite.setBorder(Rectangle.NO_BORDER);
                cellprix.setBorder(Rectangle.NO_BORDER);
                cellpvtHT.setBorder(Rectangle.NO_BORDER);
                
                table.addCell(cellunite);
                table.addCell(cellQuantite);
                table.addCell(cellprix);
                table.addCell(cellpvtHT);

                ttcTotalParcour+=listeMaterielDevi.getPvtHT();
                htTotalParcour+=listeMaterielDevi.getPvtHT();
            }
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
        }
        
        ttcTotalParcour = (ttcTotalParcour * devis.getTva()/100)+ttcTotalParcour;
        
        LineDash solid = new SolidLine();
        PdfPCell cell,cell2,cellSOUSTOTAL,cellhttotal,tva,getTva,totalttc,gettotalttc;
        
        table.addCell(new Phrase(" "));
        table.addCell(new Phrase(" "));
        table.addCell(new Phrase(" "));
        table.addCell(new Phrase(" "));
        
        cell = new PdfPCell(new Phrase(""));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setCellEvent(new CustomBorder(null, null, null, solid));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        table.addCell(new Phrase(""));
        table.addCell(new Phrase(""));
        cellSOUSTOTAL = new PdfPCell(new Phrase("SOUS-TOTAL"));
        cellSOUSTOTAL.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        cellSOUSTOTAL.setBorder(Rectangle.NO_BORDER);
        table.addCell(cellSOUSTOTAL);
        table.addCell(new Phrase(""));
        double prixHT_2Chiffre = Math.round((htTotalParcour) * Math.pow(10,2)) / Math.pow(10,2);
        String httotal = String.valueOf(prixHT_2Chiffre);
        cellhttotal = new PdfPCell(new Phrase(httotal+"€"));
        cellhttotal.setBorder(Rectangle.NO_BORDER);
        cellhttotal.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cellhttotal);

        table.addCell(new Phrase(""));
        table.addCell(new Phrase(""));
        tva = new PdfPCell(new Phrase("TVA"));
        tva.setBorder(Rectangle.NO_BORDER);
        tva.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(tva);
        
        getTva = new PdfPCell(new Phrase(devis.getTva()+"%"));
        getTva.setBorder(Rectangle.NO_BORDER);
        getTva.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(getTva);
        
        String tvaprix = String.valueOf(htTotalParcour * devis.getTva()/100);
        double prixTVA_2Chiffre = Math.round(Double.parseDouble(tvaprix) * Math.pow(10,2)) / Math.pow(10,2);
        cell2 = new PdfPCell(new Phrase(Double.toString(prixTVA_2Chiffre)+"€"));
        cell2.setBorder(PdfPCell.NO_BORDER);
        cell2.setCellEvent(new CustomBorder(null, null, null, solid));
        cell2.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell2);
        
        table.addCell("");
        table.addCell("");
        totalttc = new PdfPCell(new Phrase("Total TTC"));
        totalttc.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        totalttc.setBorder(PdfPCell.NO_BORDER);
        table.addCell(totalttc);
        table.addCell("");
        double prixTTC_2Chiffre = Math.round(ttcTotalParcour * Math.pow(10,2)) / Math.pow(10,2);
        String ttctotal = String.valueOf(Double.toString(prixTTC_2Chiffre));
        gettotalttc = new PdfPCell(new Phrase(ttctotal +"€"));
        gettotalttc.setBorder(PdfPCell.NO_BORDER);
        gettotalttc.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(gettotalttc);
        
        table.setSpacingAfter(10);
        
        document.add(table);

        Pattern p = Pattern.compile("[.^\\.]+") ;  
        String texte = devis.getInformationComplementaire() ;
        
        Font infoComptBlue = new Font(Font.getFamily("TIMES_ROMAN"), 8, Font.NORMAL);
        infoComptBlue.setColor(BaseColor.BLUE);
        Font infoComptRed = new Font(Font.getFamily("TIMES_ROMAN"), 8, Font.NORMAL);
        infoComptRed.setColor(BaseColor.RED);
        Font infoComptBlack = new Font(Font.getFamily("TIMES_ROMAN"), 8, Font.NORMAL);
        infoComptBlack.setColor(BaseColor.BLACK);
        
        
        
        Paragraph infoComp = new Paragraph();
        Image BonPourAccord = null;
        BonPourAccord = Image.getInstance("images/BonPourAccord.png");
        infoComp.setAlignment(Element.ALIGN_LEFT);
        BonPourAccord.setAlignment(Image.TEXTWRAP| Image.ALIGN_RIGHT);
        
        String [] phrase = p.split(texte) ; 
         for (int i =  0 ; i < phrase.length ; i++) {
            if(i==0||i==1){ 
                infoComp.add(new Paragraph(phrase[i],infoComptBlue));
            }
            if(i==2||i==3||i==4||i==5){
                infoComp.add(new Paragraph(phrase[i],infoComptRed));
                
                
            }
            if(i>5 && i<=11)
                infoComp.add(new Paragraph(phrase[i],infoComptBlack));
            if(i>11)
                infoComp.add(new Paragraph(phrase[i],infoComptRed));
         }
        /* A OPTIMISER */ 
       
        float taille_tableau=table.calculateHeights()+table.getRowHeight(table.getLastCompletedRowIndex());
        
        if(taille_tableau>386){
            document.newPage();
        }
        document.add(BonPourAccord);
        document.add(infoComp);
        
        
        document.close();
    
        EnvoieDevis envoie = new EnvoieDevis(null,true,session,devis);
        this.CouvertureArdoise.clear();
        this.CouvertureTuile.clear();
        this.Demoussage.clear();
        this.EquipementDeChantier.clear();
        this.Velux.clear();
        this.Zinguerie.clear();
        this.Couverture.clear();
        this.Plancher.clear();
    }
    public class HeaderFooterPageEvent extends PdfPageEventHelper {
    public void onEndPage(PdfWriter writer,Document document) {
    	Rectangle rect = writer.getBoxSize("art");
        ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, new Phrase("SARL au capital de 10000€     SIRET 484 494 570 000 27"), rect.getLeft()+140, rect.getBottom(), 0);
        ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, new Phrase("APE 4391 A   TVA FR 264 844 945 70"), rect.getRight()-120, rect.getBottom(), 0);
        ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_RIGHT, new Phrase(Integer.toString(document.getPageNumber())),rect.getRight()+35, rect.getBottom(), 0);
    }
    
} 
}
    interface LineDash {
        public void applyLineDash(PdfContentByte canvas);
    }
 
    class SolidLine implements LineDash {
        public void applyLineDash(PdfContentByte canvas) { }
    }
 
    class DottedLine implements LineDash {
        public void applyLineDash(PdfContentByte canvas) {
            canvas.setLineCap(PdfContentByte.LINE_CAP_ROUND);
            canvas.setLineDash(0, 4, 2);
        }
    }
 
    class DashedLine implements LineDash {
        public void applyLineDash(PdfContentByte canvas) {
            canvas.setLineDash(3, 3);
        }
    }
 
    class CustomBorder implements PdfPCellEvent {
        protected LineDash left;
        protected LineDash right;
        protected LineDash top;
        protected LineDash bottom;
        public CustomBorder(LineDash left, LineDash right,
                LineDash top, LineDash bottom) {
            this.left = left;
            this.right = right;
            this.top = top;
            this.bottom = bottom;
        }
        public void cellLayout(PdfPCell cell, Rectangle position,
            PdfContentByte[] canvases) {
            PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
            if (top != null) {
                canvas.saveState();
                top.applyLineDash(canvas);
                canvas.moveTo(position.getRight(), position.getTop());
                canvas.lineTo(position.getLeft(), position.getTop());
                canvas.stroke();
                canvas.restoreState();
            }
            if (bottom != null) {
                canvas.saveState();
                bottom.applyLineDash(canvas);
                canvas.moveTo(position.getRight()-35, position.getBottom());
                canvas.lineTo(position.getLeft(), position.getBottom());
                canvas.stroke();
                canvas.restoreState();
            }
            if (right != null) {
                canvas.saveState();
                right.applyLineDash(canvas);
                canvas.moveTo(position.getRight(), position.getTop());
                canvas.lineTo(position.getRight(), position.getBottom());
                canvas.stroke();
                canvas.restoreState();
            }
            if (left != null) {
                canvas.saveState();
                left.applyLineDash(canvas);
                canvas.moveTo(position.getLeft(), position.getTop());
                canvas.lineTo(position.getLeft(), position.getBottom());
                canvas.stroke();
                canvas.restoreState();
            }
        }

    }

