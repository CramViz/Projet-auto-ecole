/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;
import java.util.Date;

/**
 *
 * @author amout
 */
public class Moniteur {
    private int codeMoniteur;
    private String adresse;
    private String codePostal;
    private Date dateDeNaissance;
    private String nom;
    private String prenom;
    private int sexe;
    private String telephone;
    private String ville;

    public Moniteur(int codeMoniteur, String nom, String prenom, int sexe, Date dateDeNaissance, String adresse, String codePostal, String ville, String telephone) {
        this.codeMoniteur = codeMoniteur;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.dateDeNaissance = dateDeNaissance;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.telephone = telephone;
        this.ville = ville;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getCodeMoniteur() {
        return codeMoniteur;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public int getSexe() {
        return sexe;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getVille() {
        return ville;
    }
    
    
    
}
