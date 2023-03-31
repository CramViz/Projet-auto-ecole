/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Tools.ConnexionBDD;
import Entities.Admin;
import Entities.Moniteur;
import Entities.Eleve;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;


/**
 *
 * @author amout
 */
public class CtrlConnexion {
    
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;
    

    
    public CtrlConnexion() {
        this.cnx = null;
        this.cnx = ConnexionBDD.getCnx();
        
    }
    

    public Eleve getUnEleve(String nom, int code)
    {

        Eleve unEleve = null;
        try {
            ps = cnx.prepareStatement("select CodeEleve, Nom, Prenom, Sexe, DateDeNaissance, Adresse1, CodePostal, Ville, Telephone from Eleve where Nom = '"+nom+"' and CodeEleve = '"+code+"'");
            rs = ps.executeQuery();
            if(rs.next())
            {
                unEleve = new Eleve(rs.getInt("CodeEleve"), rs.getString("Nom"), rs.getString("Prenom"), rs.getInt("Sexe"), rs.getDate("DateDeNaissance"),rs.getString("Adresse1"), rs.getString("CodePostal"), rs.getString("Ville"), rs.getString("Telephone"));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return unEleve;
    }
    
    
    public Moniteur getUnMoniteur(String nom, int code)
    {
        Moniteur unMoniteur = null;
        try {
            ps = cnx.prepareStatement("select CodeMoniteur, Nom, Prenom, Sexe, DateDeNaissance, Adresse1, CodePostal, Ville, Telephone from Moniteur where Prenom = '"+nom+"' and CodeMoniteur = "+code+"");
            System.out.println(ps);
            rs = ps.executeQuery();
            if(rs.next())
            {
                unMoniteur = new Moniteur(rs.getInt("CodeMoniteur"), rs.getString("Nom"), rs.getString("Prenom"), rs.getInt("Sexe"), rs.getDate("DateDeNaissance"),rs.getString("Adresse1"), rs.getString("CodePostal"), rs.getString("Ville"), rs.getString("Telephone"));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return unMoniteur;
    }
    
    
    
    public Admin getAdmin(String id, String pw)
    {
        Admin admin = null;
        try {
            ps = cnx.prepareStatement("select * from admin where ID = '"+id+"' and Password = '"+pw+"'");
            rs = ps.executeQuery();
            if(rs.next())
            {
                admin = new Admin(rs.getString("ID"), rs.getString("Password"));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
    }
}
