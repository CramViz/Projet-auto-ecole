/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;
import Controller.CtrlMoniteur;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amout
 */
public class Lecon {
    private int codeLecon;
    private String eleve;
    private String moniteur;
    private String date;
    private String heure;
    private String immatriculation;
    private int reglee;

    public Lecon(String date, String heure, String eleve, String moniteur, int reglee) {
        this.date = date;
        this.heure = heure;
        this.moniteur = moniteur;
        this.reglee = reglee;
        this.eleve = eleve;
    }
    
    public int getCodeLecon() {
        return codeLecon;
    }

    public String getEleve() {
        return eleve;
    }

    public String getMoniteur() {
        return moniteur;
    }

    public String getDate() {
        return date;
    }

    public String getHeure() {
        return heure;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public int getReglee() {
        return reglee;
    }
    

}
