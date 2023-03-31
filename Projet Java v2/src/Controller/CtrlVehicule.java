/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Entities.Vehicule;
import Tools.ConnexionBDD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amout
 */
public class CtrlVehicule {
     
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public CtrlVehicule() {
        cnx = ConnexionBDD.getCnx();
    }
    
    
    public ArrayList<Vehicule> getAllVehicule()
    {
        ArrayList<Vehicule> lesVehicules = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("select * from vehicule");
            rs = ps.executeQuery();
            while(rs.next())
            {
                Vehicule vehicule = new Vehicule(rs.getString("Immatriculation"),rs.getInt("Annee"),rs.getInt("CodeCategorie"),rs.getString("Modele"), rs.getString("Marque"));
                lesVehicules.add(vehicule);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlEleve.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesVehicules;
    }
    
    public Vehicule getVehiculeByImmatriculation(String imma)
    {
        Vehicule vehicule = null;
        try {
            ps = cnx.prepareStatement("select * from vehicule where Immatriculation = '"+imma+"';");
            rs = ps.executeQuery();
            while(rs.next())
            {
                vehicule = new Vehicule(rs.getString("Immatriculation"),rs.getInt("Annee"),rs.getInt("CodeCategorie"),rs.getString("Modele"), rs.getString("Marque"));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlEleve.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehicule;
    }
   
    public ArrayList<Vehicule> getVehiculeByCatByDate(int codeCat, String date, String heure)
    {
        ArrayList<Vehicule> lesVehicules = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("select distinct Marque, Modele, vehicule.Immatriculation, Annee, CodeCategorie from vehicule join lecon on vehicule.Immatriculation = lecon.Immatriculation where vehicule.CodeCategorie = "+codeCat+" and vehicule.Immatriculation not in (select vehicule.Immatriculation from vehicule join lecon on vehicule.Immatriculation = lecon.Immatriculation where lecon.Date = '"+date+"' and lecon.Heure = '"+heure+"' and vehicule.CodeCategorie = "+codeCat+");");
            rs = ps.executeQuery();
            while(rs.next())
            {
                Vehicule vehicule = new Vehicule(rs.getString("Immatriculation"),rs.getInt("Annee"),rs.getInt("CodeCategorie"),rs.getString("Modele"), rs.getString("Marque"));
                lesVehicules.add(vehicule);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlEleve.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesVehicules;
    }
    
    
    public void addVehicule(String imma, int annee, String modele, String marque, int codeCat)
    {
        
        try {           
            ps = cnx.prepareStatement("insert into vehicule (Immatriculation, Annee, Modele, Marque, CodeCategorie) values ('"+imma+"', '"+annee+"', '"+modele+"', '"+marque+"', "+codeCat+");");
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlLecon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void modifierVehicule(String imma, int annee, String modele, String marque, int codeCat)
    {
        
        try {
            ps = cnx.prepareStatement("UPDATE `vehicule` SET Annee = "+ annee +", Modele = '"+ modele +"', Marque = '"+ marque +"', CodeCategorie = "+ codeCat +" WHERE Immatriculation = '"+ imma +"';");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
