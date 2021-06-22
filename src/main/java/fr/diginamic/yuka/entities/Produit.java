package fr.diginamic.yuka.entities;

import fr.diginamic.yuka.entities.Additif;
import fr.diginamic.yuka.entities.Allergene;
import fr.diginamic.yuka.entities.Ingredient;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Anthony
 */
@Entity
public class Produit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String nom;
    private String scoreNutritionnel;

    // Jointure avec la table Additif
    @ManyToMany
    @JoinTable(name="PRODUIT_ADDITIF",
            joinColumns = @JoinColumn(name = "ID_PRODUIT",referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_ADDITIF",referencedColumnName = "ID")
    )
    private Set<Additif> additifs = new HashSet<>();

    // Jointure avec la table Allergene
    @ManyToMany
    @JoinTable(name="PRODUIT_ALLERGENE",
            joinColumns= @JoinColumn(name ="ID_PRODUIT",referencedColumnName="ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_ALLERGENE",referencedColumnName = "ID")
    )
    private Set<Allergene> allergenes = new HashSet<>();

    // Jointure avec la table Ingredient
    @ManyToMany
    @JoinTable(name="PRODUIT_INGREDIENT",
            joinColumns = @JoinColumn(name = "ID_PRODUIT",referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_INGREDIENT",referencedColumnName = "ID")
    )
    private Set<Ingredient> ingredients = new HashSet<>();

    //Jointure avec la table Categorie
    @ManyToOne
    @JoinColumn(name = "CATEGORIE_ID")
    private Categorie categorie;

    //Jointure avec la table Marque
    @ManyToOne
    @JoinColumn(name = "MARQUE_ID")
    private Marque marque;

    public Produit() {
    }

    public Produit(String nom, String scoreNutritionnel, Categorie categorie, Marque marque) {
        this.nom = nom;
        this.scoreNutritionnel = scoreNutritionnel;
        this.categorie = categorie;
        this.marque = marque;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Additif> getAdditifs() {
        return additifs;
    }

    public void setAdditifs(Set<Additif> additifs) {
        this.additifs = additifs;
    }

    public Set<Allergene> getAllergenes() {
        return allergenes;
    }

    public void setAllergenes(Set<Allergene> allergenes) {
        this.allergenes = allergenes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public String getScoreNutritionnel() {
        return scoreNutritionnel;
    }

    public void setScoreNutritionnel(String scoreNutritionnel) {
        this.scoreNutritionnel = scoreNutritionnel;
    }

    public Set<Ingredient> getListeIngredients() {
        return ingredients;
    }

    public void setListeIngredients(Set<Ingredient> listeIngredients) {
        this.ingredients = listeIngredients;
    }

    public Set<Additif> getListeAdditifs() {
        return additifs;
    }

    public void setListeAdditifs(Set<Additif> listeAdditifs) {
        this.additifs = listeAdditifs;
    }

    public Set<Allergene> getListeAllergenes() {
        return allergenes;
    }

    public void setListeAllergenes(Set<Allergene> listeAllergenes) {
        this.allergenes = listeAllergenes;
    }


    /**
     * Permet d'ajoute un ingredient à la liste d'ingredients
     * @param ingredient Ingredient
     */
    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    /**
     * Permet d'ajouter un additif à la liste d'additifs
     * @param additif Additif
     */
    public void addAdditif(Additif additif){
        this.additifs.add(additif);
    }

    /**
     * Permet d'ajouter un allergene à la liste d'allergenes
     * @param allergene Allergene
     */
    public void addAllergene(Allergene allergene){
        this.allergenes.add(allergene);
    }
}
