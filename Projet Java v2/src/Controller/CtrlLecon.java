/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


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
import org.jfree.data.general.PieDataset;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.util.Rotation;
import org.jfree.data.general.DefaultPieDataset;


/**
 *
 * @author amout
 */
public class CtrlLecon {
    
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    
    public CtrlLecon() {
        cnx = ConnexionBDD.getCnx();
    }
      

    
        public ArrayList<String> getMoniteurByDiplome(String categorie)
    {
        ArrayList<String> lesMoniteurs = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("select moniteur.Prenom "
                    + "from moniteur "
                    + "join licence on moniteur.CodeMoniteur = licence.CodeMoniteur "
                    + "join categorie on categorie.CodeCategorie = licence.codeCategorie "
                    + "where categorie.Libelle = '"+ categorie +"'");
            rs = ps.executeQuery();
            while(rs.next())
            {
                String moniteur = rs.getString("Prenom" );
                lesMoniteurs.add(moniteur);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlLecon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesMoniteurs;
    }
        
        
    public ArrayList<Lecon> getAllLeconByCodeEleve(int codeEleve)
    {
        ArrayList<Lecon> lesLecons = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("select lecon.Date, lecon.Heure, eleve.Prenom as PrenomEleve, moniteur.Prenom as PrenomMoniteur,  lecon.Reglee from lecon join eleve on eleve.CodeEleve = lecon.CodeEleve join moniteur on lecon.CodeMoniteur = moniteur.CodeMoniteur where lecon.CodeEleve = "+ codeEleve +" and lecon.Date > '2017-01-01';");
            rs = ps.executeQuery();
            while(rs.next())
            {
                Lecon lecon = new Lecon(rs.getString("Date"),rs.getString("Heure"),rs.getString("PrenomEleve"),rs.getString("PrenomMoniteur"),rs.getInt("Reglee"));
                lesLecons.add(lecon);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlEleve.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesLecons;
    }
    
    
    public ArrayList<Lecon> getAllLeconByCodeMoniteur(int codeMoniteur)
    {
        ArrayList<Lecon> lesLecons = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("select lecon.Date, lecon.Heure, eleve.Prenom as PrenomEleve, moniteur.Prenom as PrenomMoniteur, lecon.Reglee from lecon join eleve on lecon.CodeEleve = eleve.CodeEleve join moniteur on moniteur.CodeMoniteur = lecon.CodeMoniteur where lecon.codeMoniteur = "+ codeMoniteur +" and lecon.Date > '2017-01-01';");
            rs = ps.executeQuery();
            while(rs.next())
            {
                Lecon lecon = new Lecon(rs.getString("Date"),rs.getString("Heure"),rs.getString("PrenomEleve"),rs.getString("PrenomMoniteur"),rs.getInt("Reglee"));
                lesLecons.add(lecon);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlMoniteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesLecons;
    }
    
    
    public ArrayList<Lecon> getAllLecon()
    {
        ArrayList<Lecon> lesLecons = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("select lecon.Date, lecon.Heure, eleve.Prenom as PrenomEleve, moniteur.Prenom as PrenomMoniteur, lecon.Reglee from lecon join eleve on lecon.CodeEleve = eleve.CodeEleve join moniteur on lecon.CodeMoniteur = moniteur.CodeMoniteur where lecon.Date > '2017-01-01';");
            rs = ps.executeQuery();
            while(rs.next())
            {
                Lecon lecon = new Lecon(rs.getString("Date"),rs.getString("Heure"),rs.getString("PrenomEleve"),rs.getString("PrenomMoniteur"),rs.getInt("Reglee"));
                lesLecons.add(lecon);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlLecon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesLecons;
    }
    
    public int getLastIDLecon()
    {
        int lastID = 0;
        try {
            ps = cnx.prepareStatement("select CodeLecon from lecon order by CodeLecon desc limit 1");
            rs = ps.executeQuery();
            while(rs.next())
            {
                lastID = rs.getInt("CodeLecon");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlLecon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastID;
    }
    
    public boolean isEleveAvailable(int id, String heure, String date)
    {
        int idLecon = -1;
        try {
            ps = cnx.prepareStatement("select CodeLecon from lecon where Date = '"+date +"' and Heure = '"+ heure+"' and CodeEleve = "+id+";");
            rs = ps.executeQuery();
            while(rs.next())
            {
                idLecon = rs.getInt("CodeLecon");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlLecon.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (idLecon != -1){
                        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + idLecon);

            return false;
        }
        else {
                        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +idLecon);

            return true;
        }
    }

    
    
    public void inscrireLecon(int codeMoniteur, int codeEleve, String date, String heure, int leconID, String immatriculation)
    {
        
        try {
            System.out.println("imma" +immatriculation);
            System.out.println("eleve" +codeEleve);
            System.out.println("mono" +codeMoniteur);
            System.out.println("date" +date);
            System.out.println("heure" +heure);
            leconID = leconID + 1;
            
            ps = cnx.prepareStatement("insert into lecon (CodeLecon, Date, Heure, CodeMoniteur, CodeEleve, Immatriculation, Reglee) values ("+leconID+", '"+date+"', '"+heure+"', "+codeMoniteur+", "+codeEleve+", '"+immatriculation+"', 0);");
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlLecon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    
    public int getCA()
    {
        int ca = 0;
        try {
            ps = cnx.prepareStatement("select SUM(prix) as CA from lecon join vehicule on vehicule.Immatriculation = lecon.Immatriculation join categorie on categorie.CodeCategorie = vehicule.CodeCategorie where lecon.Reglee = 1");
            rs = ps.executeQuery();
            while(rs.next())
            {
                ca = rs.getInt("CA");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlLecon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ca;
    }
    
    
    public int getNonReglee()
    {
        int nr = 0;
        try {
            ps = cnx.prepareStatement("select SUM(prix) as NR from lecon join vehicule on vehicule.Immatriculation = lecon.Immatriculation join categorie on categorie.CodeCategorie = vehicule.CodeCategorie where lecon.Reglee = 0");
            rs = ps.executeQuery();
            while(rs.next())
            {
                nr = rs.getInt("NR");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlLecon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nr;
    }
    
    public int getNonRegleeByEleve(int code)
    {
        int nr = 0;
        try {
            ps = cnx.prepareStatement("select SUM(prix) as NR from lecon join vehicule on vehicule.Immatriculation = lecon.Immatriculation join categorie on categorie.CodeCategorie = vehicule.CodeCategorie where lecon.Reglee = 0 and lecon.CodeEleve = "+ code +"");
            rs = ps.executeQuery();
            while(rs.next())
            {
                nr = rs.getInt("NR");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlLecon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nr;
    }
    
    public int getCAByMono(int code)
    {
        int ca = 0;
        try {
            ps = cnx.prepareStatement("select SUM(prix) as CA from lecon join vehicule on vehicule.Immatriculation = lecon.Immatriculation join categorie on categorie.CodeCategorie = vehicule.CodeCategorie where lecon.Reglee = 1 and lecon.CodeMoniteur = "+ code +"");
            rs = ps.executeQuery();
            while(rs.next())
            {
                ca = rs.getInt("CA");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlLecon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ca;
    }
    
    
}
