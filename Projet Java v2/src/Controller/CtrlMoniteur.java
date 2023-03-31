/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Entities.Eleve;
import Entities.Moniteur;
import Entities.Lecon;
import Entities.Categorie;
import Tools.ConnexionBDD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
/**
 *
 * @author amout
 */
public class CtrlMoniteur {
    
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;
    private CtrlCategorie ctrlCategorie;
    
    public CtrlMoniteur() {
        cnx = ConnexionBDD.getCnx();
        
    }
    
    
    public void modifierUnMoniteur(int codeMoniteur, String nom, String prenom, String adresse, String telephone, int sexe, String codePostal, String ville, String dateNaiss)
    {
        
        try {
            ps = cnx.prepareStatement("UPDATE `moniteur` SET Ville = '"+ ville +"', Nom = '"+ nom +"', Prenom = '"+ prenom +"', Sexe = "+ sexe +", Adresse1 = '"+ adresse +"', Telephone = '"+ telephone +"', CodePostal = '"+ codePostal +"', DateDeNaissance = '"+ dateNaiss +"'  WHERE CodeMoniteur = "+ codeMoniteur +";");
                        System.out.println(ps);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlMoniteur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    
    public ArrayList<Categorie> getAllLicenceByCodeMoniteur(int codeMoniteur)
    {
        ArrayList<Categorie> lesCategories = new ArrayList<>();

        try {
            ps = cnx.prepareStatement("select * from categorie join licence on categorie.codeCategorie = licence.codeCategorie where licence.codeMoniteur = " + codeMoniteur +";");
            rs = ps.executeQuery();
            while(rs.next())
            {
                Categorie categorie = new Categorie(rs.getInt("CodeCategorie"),rs.getString("Libelle"),rs.getInt("Prix"));
                lesCategories.add(categorie);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlMoniteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesCategories;
    }
    

    
    
    public void addLicence(int idCat, int codeMoniteur)
    {
        try {
            ps = cnx.prepareStatement("insert into licence (CodeMoniteur, CodeCategorie) values("+codeMoniteur+","+idCat+")");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlMoniteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }
    
    
        public ArrayList<Moniteur> getAllMoniteur()
    {
        ArrayList<Moniteur> lesMoniteurs = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("select * from moniteur");
            rs = ps.executeQuery();
            while(rs.next())
            {
                Moniteur moniteur = new Moniteur(rs.getInt("CodeMoniteur"),rs.getString("Nom"),rs.getString("Prenom"),rs.getInt("Sexe"), rs.getDate("DateDeNaissance"), rs.getString("Adresse1"), rs.getString("CodePostal"), rs.getString("Ville"), rs.getString("Telephone"));
                lesMoniteurs.add(moniteur);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlEleve.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesMoniteurs;
        
    }
        
        
    public int getCodeMoniteurByPrenom(String nom)
    {
        int codeMoniteur = 0;
        try {
            ps = cnx.prepareStatement("select CodeMoniteur from Moniteur where Prenom = '"+nom+"';");
            rs = ps.executeQuery();
            if(rs.next())
            {
                codeMoniteur = rs.getInt("CodeMoniteur");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codeMoniteur;
    }
    
    
    
    public ArrayList<Moniteur> getMoniteursAvailableByCat(String date, String heure, String categorie)
    {
        ArrayList<Moniteur> lesMoniteurs = new ArrayList<>();

        try {
            ps = cnx.prepareStatement("SELECT *\n" +
                                        "FROM moniteur\n" +
                                        "JOIN licence on moniteur.CodeMoniteur = licence.CodeMoniteur\n" +
                                        "join categorie on licence.CodeCategorie = categorie.CodeCategorie\n" +
                                        "WHERE categorie.Libelle = '"+categorie+"'\n" +
                                        "AND moniteur.CodeMoniteur NOT IN (SELECT lecon.CodeMoniteur FROM lecon WHERE Date = '"+date+"' AND heure = '"+heure+"');");
            rs = ps.executeQuery();
            while(rs.next())
            {
                Moniteur moniteur = new Moniteur(rs.getInt("CodeMoniteur"),rs.getString("Nom"),rs.getString("Prenom"),rs.getInt("Sexe"), rs.getDate("DateDeNaissance"), rs.getString("Adresse1"), rs.getString("CodePostal"), rs.getString("Ville"), rs.getString("Telephone"));
                lesMoniteurs.add(moniteur);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlMoniteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(lesMoniteurs);
        return lesMoniteurs;
    }
    
    public void addMoniteur(int codeMoniteur, String nom, String prenom, int sexe, String adresse, String telephone, String codePostal, String ville, String dateNaiss)
    {
        System.out.println("");
        try {           
            ps = cnx.prepareStatement("insert into moniteur (CodeMoniteur, Nom, Prenom, Adresse1, Telephone, CodePostal, Ville, DateDeNaissance, Sexe) values ("+codeMoniteur+", '"+nom +"', '"+prenom+"', '"+adresse +"', '"+telephone+"', '"+codePostal +"', '"+ville+"', '"+dateNaiss +"', "+ sexe +");");
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlMoniteur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Moniteur getMoniteurByCodeMono(int codeMono)
    {
        Moniteur mono = null;
        try {
            ps = cnx.prepareStatement("select * from moniteur where CodeMoniteur = "+codeMono+";");
            rs = ps.executeQuery();
            while(rs.next())
            {
                mono = new Moniteur(rs.getInt("CodeMoniteur"), rs.getString("Nom"), rs.getString("Prenom"), rs.getInt("Sexe"), rs.getDate("DateDeNaissance"), rs.getString("Adresse1"), rs.getString("CodePostal"), rs.getString("Ville"), rs.getString("Telephone"));
            }

            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlMoniteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mono;
    } 
    
    
    public int getLastIDMoniteur()
    {
        int lastID = 0;
        try {
            ps = cnx.prepareStatement("select CodeMoniteur from moniteur order by CodeMoniteur desc limit 1");
            rs = ps.executeQuery();
            while(rs.next())
            {
                lastID = rs.getInt("CodeMoniteur");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlMoniteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastID;
    }
    
}
