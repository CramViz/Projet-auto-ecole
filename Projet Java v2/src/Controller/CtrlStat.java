/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Tools.ConnexionBDD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author admin
 */
public class CtrlStat 
{
      private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public CtrlStat(){
    cnx = ConnexionBDD.getCnx();
    }

    public HashMap<String,Double> EleveGetDataStat1(int codeE) 
    {   
        HashMap<String,Double> data=new HashMap();
        Double prixtotal;
        Double prixaregler;
        try
        {
            ps=cnx.prepareStatement("SELECT SUM(prix) as ptotal FROM categorie "
                    + "JOIN vehicule ON vehicule.CodeCategorie=categorie.CodeCategorie "
                    + "JOIN lecon ON lecon.Immatriculation=vehicule.Immatriculation "
                    + "WHERE CodeEleve= ?;");
            ps.setInt(1, codeE);
            rs=ps.executeQuery();
            rs.next();
            prixtotal=(rs.getDouble("ptotal"));
            
            ps=cnx.prepareStatement("SELECT SUM(prix) as paregler FROM categorie "
                    + "JOIN vehicule ON vehicule.CodeCategorie=categorie.CodeCategorie "
                    + "JOIN lecon ON lecon.Immatriculation=vehicule.Immatriculation "
                    + "WHERE CodeEleve= ? AND reglee=0;");
            ps.setInt(1, codeE);
            rs=ps.executeQuery();
            rs.next();
            prixaregler=(rs.getDouble("paregler"));
              
            ps.close();
            rs.close();
            data.put("Prix Total",prixtotal);
            data.put("Prix a Regler", prixaregler);
            
        } 
            catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CtrlStat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return data;
        }
    
    public HashMap<String,Integer> EleveGetDataStat2(int codeE)
    {
          HashMap<String,Integer> data=new HashMap();
        try
        {
            ps=cnx.prepareStatement("SELECT libelle , COUNT(CodeLecon) AS nbrlecon" 
                    +" FROM categorie" 
                    +" JOIN vehicule ON vehicule.CodeCategorie=categorie.CodeCategorie" 
                    +" JOIN lecon ON lecon.Immatriculation=vehicule.Immatriculation" 
                    +" WHERE CodeEleve="+codeE+" GROUP BY libelle;");
            // ps.setInt(1, codeE);
            rs=ps.executeQuery();
            while(rs.next())
            {
                data.put(rs.getString("libelle"), rs.getInt("nbrlecon"));
            }  
            ps.close();
            rs.close();
            
        } 
            catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CtrlStat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return data;
    }
    
    public HashMap<String, ArrayList<String>> AdminGetDataStat1()
    {
        HashMap<String, ArrayList<String>> data = new HashMap();
        try {
            ps = cnx.prepareStatement("SELECT moniteur.Nom as Nmoniteur , vehicule.Modele as Mvehicule , COUNT(CodeLecon) as nbrlecon"
                    + " FROM lecon "
                    + "JOIN moniteur ON lecon.CodeMoniteur=moniteur.CodeMoniteur "
                    + "JOIN vehicule ON lecon.Immatriculation=vehicule.Immatriculation "
                    + " GROUP BY moniteur.Nom,vehicule.Modele;"
                   );
            rs = ps.executeQuery();
            while(rs.next())
            {
                if(!data.containsKey(rs.getString("Nmoniteur")))
                {
                    ArrayList<String> lesMagazines = new ArrayList<>();
                    lesMagazines.add(rs.getString("Mvehicule"));
                    lesMagazines.add(rs.getString("nbrlecon"));
                    data.put(rs.getString("Nmoniteur"),lesMagazines);
                }
                else
                {
                    data.get(rs.getString("Nmoniteur")).add(rs.getString("Mvehicule"));
                    data.get(rs.getString("Nmoniteur")).add(rs.getString("nbrlecon"));
               }
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlStat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    } 
    
    public HashMap<String,Double> AdminGetDataStat2()
    {
        HashMap<String,Double> data = new HashMap();
        try
        {
            ps= cnx.prepareStatement("SELECT moniteur.Nom Nmoniteur , SUM(prix) CA_Moniteur "
                    + "FROM categorie "
                    + "JOIN vehicule ON vehicule.CodeCategorie=categorie.CodeCategorie"
                    + " JOIN lecon ON lecon.Immatriculation=vehicule.Immatriculation"
                    + " JOIN moniteur ON moniteur.CodeMoniteur=lecon.CodeMoniteur"
                    + " GROUP BY Nmoniteur;");
            rs=ps.executeQuery();
            while(rs.next())
            {
                data.put(rs.getString("Nmoniteur"),rs.getDouble("CA_Moniteur"));
            }
           
        }
        catch (SQLException ex) {
            Logger.getLogger(CtrlStat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    
    public HashMap<String,Integer> MoniteurGetDataStat(int codeM)
    {
         HashMap<String, Integer> data = new HashMap();
        try {
            ps = cnx.prepareStatement("SELECT categorie.Libelle AS categ , COUNT(CodeEleve) AS nbrlecon"
                    + " FROM lecon "
                    + "JOIN vehicule ON vehicule.Immatriculation=lecon.Immatriculation "
                    + "JOIN categorie ON categorie.CodeCategorie=vehicule.CodeCategorie "
                    + "WHERE CodeMoniteur="+codeM
                    + " GROUP BY categ;");
            rs = ps.executeQuery();
            while(rs.next())
            {
                data.put(rs.getString("categ"), rs.getInt("nbrlecon"));
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlStat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
        }  
            
        
  
    
