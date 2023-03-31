/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tools;

import Controller.CtrlCategorie;
import Entities.Eleve;
import Entities.Moniteur;
import Entities.Vehicule;
import Entities.Categorie;
import Entities.Lecon;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jacqu
 */
public class ModelJTable extends AbstractTableModel
{

    CtrlCategorie ctrlCategorie;
    
    private String[] nomsColonnes;
    private Object[][] rows;

    @Override
    public String getColumnName(int column) {
        return nomsColonnes[column];
    }

    @Override
    public int getRowCount() {
        return rows.length;
    }

    @Override
    public int getColumnCount() {
        return nomsColonnes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows[rowIndex][columnIndex];
    }
    public void loadDatasPlanningEleve(ArrayList<Lecon> lesLecons)
    {
        nomsColonnes = new String[]{"Date", "Heure", "Moniteur", "Payé"};
        rows = new Object[lesLecons.size()][7];
        int i = 0;
        for(Lecon lecon : lesLecons)
        {
            rows[i][0] = lecon.getDate();
            rows[i][1] = lecon.getHeure();
            rows[i][2] = lecon.getMoniteur();
            rows[i][3] = lecon.getReglee();
            i++;
        }
        fireTableChanged(null);
    }
    
    public void loadDatasPlanningMoniteur(ArrayList<Lecon> lesLecons)
    {
        nomsColonnes = new String[]{"Date", "Heure", "Élève", "Payé"};
        rows = new Object[lesLecons.size()][7];
        int i = 0;
        for(Lecon lecon : lesLecons)
        {
            rows[i][0] = lecon.getDate();
            rows[i][1] = lecon.getHeure();
            rows[i][2] = lecon.getEleve();
            rows[i][3] = lecon.getReglee();
            i++;
        }
        fireTableChanged(null);
    }
    
    
    public void loadDatasPlanningAdmin(ArrayList<Lecon> lesLecons)
    {
        nomsColonnes = new String[]{"Date", "Heure", "Élève", "Moniteur", "Payé"};
        rows = new Object[lesLecons.size()][7];
        int i = 0;
        for(Lecon lecon : lesLecons)
        {
            rows[i][0] = lecon.getDate();
            rows[i][1] = lecon.getHeure();
            rows[i][2] = lecon.getMoniteur();
            rows[i][3] = lecon.getEleve();            
            rows[i][4] = lecon.getReglee();
            i++;
        }
        fireTableChanged(null);
    }
    
    
    public void loadDatasCategorie(ArrayList<Categorie> lesCat)
    {
        nomsColonnes = new String[]{"ID", "Licence", "Prix"};
        rows = new Object[lesCat.size()][7];
        int i = 0;
        for(Categorie cat : lesCat)
        {
            rows[i][0] = cat.getCodeCategorie();
            rows[i][1] = cat.getLibelle();
            rows[i][2] = cat.getPrix();
            
            i++;
        }
        fireTableChanged(null);
    }
    
    
    
    
    
    
    ////////////////////////////////////////////////////////////////
    
    
    
    
    public void loadDatasVehicule(ArrayList<Vehicule> lesVehicules)
    {
        ctrlCategorie = new CtrlCategorie();
        nomsColonnes = new String[]{"Immatriculation", "Marque", "Modèle", "Année", "Categorie"};
        rows = new Object[lesVehicules.size()][7];
        int i = 0;
        for(Vehicule v : lesVehicules)
        {
            rows[i][0] = v.getImmatriculation();
            rows[i][1] = v.getMarque();
            rows[i][2] = v.getModele();
            rows[i][3] = v.getAnnee();
            rows[i][4] = ctrlCategorie.getLibByCodeCat(v.getCodeCategorie());
            i++;
        }
        fireTableChanged(null);
    }
    
    
    public void loadDatasVehiculeLecon(ArrayList<Vehicule> lesVehicules)
    {
        ctrlCategorie = new CtrlCategorie();
        nomsColonnes = new String[]{"Immatriculation", "Marque", "Modèle", "Année"};
        rows = new Object[lesVehicules.size()][7];
        int i = 0;
        for(Vehicule v : lesVehicules)
        {
            rows[i][0] = v.getImmatriculation();
            rows[i][1] = v.getMarque();
            rows[i][2] = v.getModele();
            rows[i][3] = v.getAnnee();
            i++;
        }
        fireTableChanged(null);
    }
    
    
    
    

    public void loadDatasMoniteur(ArrayList<Moniteur> lesMonos)
    {
        nomsColonnes = new String[]{"ID", "Prénom", "Nom"};
        rows = new Object[lesMonos.size()][7];
        int i = 0;
        for(Moniteur mono : lesMonos)
        {
            rows[i][0] = mono.getCodeMoniteur();
            rows[i][1] = mono.getPrenom();
            rows[i][2] = mono.getNom();
            System.out.println(mono.getPrenom());
            i++;
        }
        fireTableChanged(null);
    }
    
    public void loadDatasMoniteurLecon(ArrayList<Moniteur> lesMonos)
    {
        nomsColonnes = new String[]{"Prénom", "Nom"};
        rows = new Object[lesMonos.size()][7];
        int i = 0;
        for(Moniteur mono : lesMonos)
        {
            rows[i][0] = mono.getPrenom();
            rows[i][1] = mono.getNom();
            System.out.println(mono.getPrenom());
            i++;
        }
        fireTableChanged(null);
    }
        

    public void loadDatasEleve(ArrayList<Eleve> lesEleves)
    {
        nomsColonnes = new String[]{"ID", "Prénom", "Nom"};
        rows = new Object[lesEleves.size()][7];
        int i = 0;
        for(Eleve eleve : lesEleves)
        {
            rows[i][0] = eleve.getCodeEleve();
            rows[i][1] = eleve.getPrenom();
            rows[i][2] = eleve.getNom();
            i++;
        }
        fireTableChanged(null);
    }

    
    
    
}
