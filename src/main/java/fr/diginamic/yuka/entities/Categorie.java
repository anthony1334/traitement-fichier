package fr.diginamic.yuka.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Anthony
 */
@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String nom;

    // Jointure avec la table produit
    @OneToMany(mappedBy = "categorie")
    private Set<Produit>produits;

    public Categorie() {
    }

    public Categorie(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
