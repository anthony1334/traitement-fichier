
package fr.diginamic.yuka.connexion;

import fr.diginamic.yuka.entities.*;
import fr.diginamic.yuka.utils.FichierUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.List;

/**
 * @author Anthony
 */

public class IntegrationOpenFoodFacts {
    public static void main(String[] args) {

        /**
         * Lis le fichier csv
         */
        String filePath = ClassLoader.getSystemClassLoader().getResource("open-food-facts.csv").getFile();
        Fichier fichier = FichierUtils.lire(filePath);


        /**
         * Recupere l instance de connexion à la base de donnée et crée une transaction
         */
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("yuka");
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();

        /**
         * Persiste les produits en base de donnée
         */
      int count =0;
        for (Produit produit : fichier.getProduits()) {
            produit.setMarque(IntegrationOpenFoodFacts.findMarque(produit.getMarque(),em));
            produit.setCategorie(IntegrationOpenFoodFacts.findCategorie(produit.getCategorie(),em));

            HashSet<Ingredient> listeIngredients = new HashSet<>();
            for (Ingredient ingredient : produit.getIngredients()) {
                listeIngredients.add(IntegrationOpenFoodFacts.findIngredient(ingredient,em));
            }

            HashSet<Additif> listeAdditifs= new HashSet<>();
            for (Additif additif : produit.getAdditifs()) {
                listeAdditifs.add(IntegrationOpenFoodFacts.findAdditif(additif,em));

            }

            HashSet<Allergene> listeAllergenes= new HashSet<>();
            for (Allergene allergene : produit.getAllergenes()) {
                listeAllergenes.add(IntegrationOpenFoodFacts.findAllergene(allergene,em));
            }

            produit.setIngredients(listeIngredients);
            produit.setAdditifs(listeAdditifs);
            produit.setAllergenes(listeAllergenes);

            em.persist(produit);
            count++;
            System.out.println("valeur compteur" + count + " "+ produit.getNom());


        }



        et.commit();
        em.close();



    }

    /**
     *  Crée une marque si elle n’existe pas.
     *  Si elle existe la récupére.
     * @param marque Marque
     * @param em EntityManager
     * @return Marque
     */
    private static Marque findMarque(Marque marque, EntityManager em){

        TypedQuery<Marque> query = em.createQuery("SELECT m from Marque m WHERE m.nom =:nom",Marque.class);
        query.setParameter("nom",marque.getNom());
        List<Marque> marques = query.getResultList();
        if(marques.isEmpty()){
            em.persist(marque);
        } else {
           marque =  marques.get(0);
        }
        return  marque;

    }

    /**
     * Crée une categorie si elle n’existe pas.
     * Si elle existe la récupére.
     * @param categorie Categorie
     * @param em EntityManager
     * @return Categorie
     */
    private static Categorie findCategorie(Categorie categorie, EntityManager em){
        TypedQuery<Categorie> query = em.createQuery("SELECT m from Categorie m WHERE m.nom =:nom",Categorie.class);
        query.setParameter("nom",categorie.getNom());
        List<Categorie> categories = query.getResultList();
        if(categories.isEmpty()){
            em.persist(categorie);
        } else {
            categorie =  categories.get(0);
        }
        return  categorie;

    }

    /**
     * Crée un ingredient si il n’existe pas.
     * Si il existe le récupére.
     * @param ingredient Ingredient
     * @param em EntityManager
     * @return Ingredient
     */

    private static Ingredient findIngredient (Ingredient ingredient, EntityManager em){
        TypedQuery<Ingredient> query = em.createQuery("SELECT m from Ingredient m WHERE m.nom =:nom",Ingredient.class);
        query.setParameter("nom",ingredient.getNom());
        List<Ingredient> ingredients = query.getResultList();
        if(ingredients.isEmpty()){
            em.persist(ingredient);
        } else {
            ingredient =  ingredients.get(0);
        }
        return  ingredient;

    }

    /**
     * Crée un Allergene si il n’existe pas.
     * Si il existe le récupére.
     * @param allergene Allergene
     * @param em EntityManager
     * @return Allergene
     */
    private static Allergene findAllergene (Allergene allergene, EntityManager em){

        TypedQuery<Allergene> query = em.createQuery("SELECT m from Allergene m WHERE m.nom =:nom",Allergene.class);
        query.setParameter("nom",allergene.getNom());
        List<Allergene> allergenes = query.getResultList();
        if(allergenes.isEmpty()){
            em.persist(allergene);
        } else {
            allergene =  allergenes.get(0);
        }
        return  allergene;

    }

    /**
     * Crée un Additif si il n’existe pas.
     * Si il existe le récupére
     * @param additif Additif
     * @param em EntityManager
     * @return Additif
     */

    private static Additif findAdditif (Additif additif, EntityManager em){
        TypedQuery<Additif> query = em.createQuery("SELECT m from Additif m WHERE m.nom =:nom",Additif.class);
        query.setParameter("nom",additif.getNom());
        List<Additif> additifs = query.getResultList();
            if(additifs.isEmpty()){
                em.persist(additif);
        } else {
            additif =  additifs.get(0);
        }
        return  additif;

    }
}

