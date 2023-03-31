/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author amout
 */
public class Vehicule {
    
    private String immatriculation;
    private int annee;
    private int codeCategorie;
    private String marque;
    private String modele;

    public Vehicule(String immatriculation, int annee, int codeCategorie, String marque, String modele) 
    {
        this.immatriculation = immatriculation;
        this.annee = annee;
        this.codeCategorie = codeCategorie;
        this.marque = marque;
        this.modele = modele;
    }
    
    
    
    
    

    public String getImmatriculation() {
        return immatriculation;
    }

    public int getAnnee() {
        return annee;
    }

    public int getCodeCategorie() {
        return codeCategorie;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }
    
    
    
    
}
