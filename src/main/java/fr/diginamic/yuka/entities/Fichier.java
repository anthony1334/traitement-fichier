package fr.diginamic.yuka.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anthony
 */
public class Fichier {

    private List<Produit> produits = new ArrayList<>();

    public Fichier() {
    }

    public Fichier(List<Produit> produits) {
        this.produits = produits;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    @Override
    public String toString() {
        return "Fichier{" +
                "produits=" + produits +
                '}';
    }
}
