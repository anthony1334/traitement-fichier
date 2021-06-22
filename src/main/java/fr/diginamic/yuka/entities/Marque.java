package fr.diginamic.yuka.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Anthony
 */

@Entity
public class Marque {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    public String nom;
    // Jointure avec la table produit
    @OneToMany(mappedBy = "marque")
    private Set<Produit> produits;

    public Marque() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    public Marque(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
