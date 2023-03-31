/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Entities.Eleve;
import Entities.Lecon;
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
public class CtrlEleve {
    
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public CtrlEleve() {
        cnx = ConnexionBDD.getCnx();
    }
    
    public void modifierUnEleve(int codeEleve, String nom, String prenom, String adresse, String telephone, String codePostal, String ville)
    {
        
        try {
            ps = cnx.prepareStatement("UPDATE `eleve` SET Ville = '"+ ville +"', Prenom = '"+ prenom +"', Adresse1 = '"+ adresse +"', Telephone = '"+ telephone +"', CodePostal = '"+ codePostal +"'  WHERE CodeEleve = "+ codeEleve +";");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public ArrayList<Eleve> getAllEleve()
    {
        ArrayList<Eleve> lesEleves = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("select * from eleve");
            rs = ps.executeQuery();
            while(rs.next())
            {
                Eleve eleve = new Eleve(rs.getInt("CodeEleve"),rs.getString("Nom"),rs.getString("Prenom"),rs.getInt("Sexe"), rs.getDate("DateDeNaissance"), rs.getString("Adresse1"), rs.getString("CodePostal"), rs.getString("Ville"), rs.getString("Telephone"));
                lesEleves.add(eleve);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlEleve.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesEleves;
    }
    
    
}
