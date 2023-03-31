/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Entities.Categorie;
import Tools.ConnexionBDD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amout
 */
public class CtrlCategorie {
    
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public CtrlCategorie() {
        cnx = ConnexionBDD.getCnx();
    }
    
    
    public int getCodeCatByLib(String lib)
    {
        int codeCat = 0;
         try {
            ps = cnx.prepareStatement("select codeCategorie from categorie where categorie.Libelle = '"+ lib +"';");
            rs = ps.executeQuery();
            while(rs.next())
            {
                codeCat = rs.getInt("CodeCategorie");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlMoniteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codeCat;
    }        
        
        
    public String getLibByCodeCat(int code)
    {
        String lib = null;
         try {
            ps = cnx.prepareStatement("select Libelle from categorie where categorie.CodeCategorie = '"+ code +"';");
            rs = ps.executeQuery();
            while(rs.next())
            {
                lib = rs.getString("Libelle");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlMoniteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lib;
    }
    
    
    public ArrayList<Categorie> getAllCategorie()
    {
        ArrayList<Categorie> lesCategories = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("select CodeCategorie, Libelle, Prix from categorie");
            rs = ps.executeQuery();
            while(rs.next())
            {
                Categorie categorie = new Categorie(rs.getInt("CodeCategorie"),rs.getString("Libelle"),rs.getInt("Prix"));
                lesCategories.add(categorie);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlEleve.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesCategories;
    }
    
    
    
    public ArrayList<Categorie> getCategorieOwnedByMono(int idMono)
    {
        ArrayList<Categorie> lesCategories = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("select categorie.CodeCategorie, Libelle, Prix from categorie where categorie.CodeCategorie not in (select licence.CodeCategorie from categorie join licence on categorie.CodeCategorie = licence.CodeCategorie where licence.CodeMoniteur = "+ idMono+")");
            rs = ps.executeQuery();
            while(rs.next())
            {
                Categorie categorie = new Categorie(rs.getInt("CodeCategorie"),rs.getString("Libelle"),rs.getInt("Prix"));
                lesCategories.add(categorie);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlEleve.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesCategories;
    }
    
    
     public void addCategorie(String libelle, int codeCat, int prix)
    {
        System.out.println(libelle);
        System.out.println(codeCat);
        System.out.println(prix);
        try {           
            ps = cnx.prepareStatement("insert into categorie (CodeCategorie, Libelle, Prix) values ("+codeCat+", '"+libelle +"', "+prix+");");
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void modifierCategorie(String libelle, int codeCat, int prix)
    {
        
        try {
            ps = cnx.prepareStatement("UPDATE `categorie` SET Libelle = '"+ libelle +"', Prix = "+ prix +" WHERE CodeCategorie = "+ codeCat +";");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Categorie getCategorieByCodeCat(int codeCat)
    {
        Categorie cat = null;
        try {
            ps = cnx.prepareStatement("select * from categorie where CodeCategorie = '"+codeCat+"';");
            rs = ps.executeQuery();
            while(rs.next())
            {
                cat = new Categorie(rs.getInt("CodeCategorie"), rs.getString("Libelle"), rs.getInt("Prix"));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cat;
    }
    
    
     public int getLastIDCategorie()
    {
        int lastID = 0;
        try {
            ps = cnx.prepareStatement("select CodeCategorie from categorie order by CodeCategorie desc limit 1");
            rs = ps.executeQuery();
            while(rs.next())
            {
                lastID = rs.getInt("CodeCategorie");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastID;
    }
}
