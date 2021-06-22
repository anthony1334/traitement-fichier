package fr.diginamic.yuka.utils;

import fr.diginamic.yuka.entities.*;

/**
 * @author Anthony
 */

public class ParseurFichier {

    /**
     * On parse le document csv  et on ajoute ces lignes dans une liste de produits
     * @param   fichier Fichier
     * @param ligne String
     */
    public static void ajoutLigne(Fichier fichier, String ligne){

        String[] morceaux = ligne.split("\\|");
        if(morceaux.length> 29) {
            Categorie categorie = new Categorie(morceaux[0]);
            Marque marque = new Marque(morceaux[1]);
            String nom = morceaux[2];
            String nutritionGradeFr = morceaux[3];
            String ingredients = morceaux[4];
            String energie100g = morceaux[5];
            String graisse100g = morceaux[6];
            String sucres100g = morceaux[7];
            String fibres100g = morceaux[8];
            String proteines100g = morceaux[9];
            String sel100g = morceaux[10];
            String vitA100g = morceaux[11];
            String vitD100g = morceaux[12];
            String vitE100g = morceaux[13];
            String vitK100g = morceaux[14];
            String vitC100g = morceaux[15];
            String vitB1100g = morceaux[16];
            String vitB2100g = morceaux[17];
            String vitPP100g = morceaux[18];
            String vitB6100g = morceaux[19];
            String vitB9100g = morceaux[20];
            String vitB12100g = morceaux[21];
            String calcium100g = morceaux[22];
            String magnesium100g = morceaux[23];
            String iron100g = morceaux[24];
            String fer100g = morceaux[25];
            String betaCarotene100g = morceaux[26];
            String presenceHuilePalme = morceaux[27];
            String allergenes = morceaux[28];
            String additifs = morceaux[29];





            // On cree maintenant le produit avec toutes ses données
            Produit produit = new Produit( nom, nutritionGradeFr,  categorie, marque);

            //On split la liste des ingredients par une virgule
            String[] listeIngredients= ingredients.split(",");
            for (String listeIngredient : listeIngredients) {
                produit.addIngredient(new Ingredient(listeIngredient));
            }

            //On split la liste des allergenes par une virgule
            String[] listeAllergenes= allergenes.split(",");
            for (String listeAllergene : listeAllergenes) {
                produit.addAllergene(new Allergene(listeAllergene));
            }

            //On split la liste des additifs par une virgule

               String[] listeAdditifs= additifs.split(",");
               for (String listeAdditif : listeAdditifs) {
                   produit.addAdditif(new Additif(listeAdditif));
               }



            // On ajoute le produit à la liste des produits
            fichier.getProduits().add(produit);
        }

    }
}
