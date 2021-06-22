package fr.diginamic.yuka.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Anthony
 */

@Entity
public class Ingredient {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(columnDefinition = "text")
    private String nom;

    // Jointure avec la table Produit
    @ManyToMany
    @JoinTable(name="PRODUIT_INGREDIENT",
            joinColumns = @JoinColumn(name = "ID_INGREDIENT",referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_PRODUIT",referencedColumnName = "ID"))
    private Set<Produit> produits = new HashSet<>();

    public Ingredient() {
    }

    public Ingredient(String nom) {
        this.nom = nom;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
