/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author amout
 */
public class Categorie {
    private int codeCategorie;
    private String libelle;
    private int prix;

    public Categorie(int codeCategorie, String libelle, int prix) {
        this.codeCategorie = codeCategorie;
        this.libelle = libelle;
        this.prix = prix;
    }

    public int getCodeCategorie() {
        return codeCategorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public int getPrix() {
        return prix;
    }
    
    
    
    
}
