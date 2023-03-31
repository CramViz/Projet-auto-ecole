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
public class Eleve {
    private int codeEleve;
    private String nom;
    private String prenom;
    private int sexe;
    private Date dateDeNaissance;
    private String adresse;
    private String codePostal;
    private String ville;
    private String telephone;

    public Eleve(int codeEleve, String nom, String prenom, int sexe, Date dateDeNaissance, String adresse, String codePostal, String ville, String telephone) {
        this.codeEleve = codeEleve;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.telephone = telephone;
    }

    public int getCodeEleve() {
        return codeEleve;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getSexe() {
        return sexe;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getVille() {
        return ville;
    }

    public String getTelephone() {
        return telephone;
    }
    
    
    
    
    
}
