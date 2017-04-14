/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.panneaux;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Loïc
 */
public class Panneau extends JPanel{
    private GridBagConstraints contraintesPlacement;
    private int nbLignes, nbColonnes;
    private String titre;
    
    public Panneau(int nbLignes, int nbColonnes, Dimension dimension, Font policeTitre, Color couleurTitre, String titre, ArrayList<Object> composants, ArrayList<Integer> placements, Color couleurFond){
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.titre = titre;
        this.setLayout(new GridBagLayout());
        this.setBackground(couleurFond);
        contraintesPlacement = new GridBagConstraints();
        this.setPreferredSize(dimension);        
        if(!titre.equals(""))
            this.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.GRAY), titre, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, policeTitre, couleurTitre));     
        for(int ligneComposant = 0, numComposant = 0; ligneComposant < this.nbLignes; ligneComposant++){
            switch(placements.get(ligneComposant)){
                case 1 : placeComposant(0, ligneComposant, GridBagConstraints.REMAINDER, GridBagConstraints.HORIZONTAL, composants.get(numComposant++));
                         break;
                case 2 : placeComposant(0, ligneComposant, 1, 0, composants.get(numComposant++));
                         placeComposant(1, ligneComposant, GridBagConstraints.REMAINDER, GridBagConstraints.HORIZONTAL, composants.get(numComposant++));
                         break;
                case 3 : placeComposant(0, ligneComposant, 1, 0, composants.get(numComposant++));
                         placeComposant(1, ligneComposant, 1, 0, composants.get(numComposant++));
                         placeComposant(2, ligneComposant, GridBagConstraints.REMAINDER, GridBagConstraints.HORIZONTAL, composants.get(numComposant++));
                         break;
            }
        }   
    }
    
    private void placeComposant(int colonne, int ligne, int largeurCellule, int repliquage, Object composant){
        contraintesPlacement.gridx = colonne;
        contraintesPlacement.gridy = ligne;
        contraintesPlacement.gridwidth = largeurCellule;
        contraintesPlacement.gridheight = 1;
        contraintesPlacement.insets =  new Insets(5, 5, 5, 5);
        if(colonne == 0)            
            contraintesPlacement.anchor = GridBagConstraints.WEST;
        else if (colonne == nbColonnes-1)            
            contraintesPlacement.anchor = GridBagConstraints.EAST;
        if(repliquage != 0)
            contraintesPlacement.fill = repliquage;
        else 
            contraintesPlacement.fill = GridBagConstraints.NONE;
        this.add((Component) composant, contraintesPlacement);
    }
    
}
