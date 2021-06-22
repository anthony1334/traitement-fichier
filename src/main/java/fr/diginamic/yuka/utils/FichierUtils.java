package fr.diginamic.yuka.utils;

import fr.diginamic.yuka.entities.Fichier;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Anthony
 */

public class FichierUtils {

    /**
     * Lis le fichier
     * @param cheminFichier
     * @return Fichier
     */

    public static Fichier lire(String cheminFichier){
        Fichier fichier = new Fichier();

        List<String> lignes = null;
        try {
            File file = new File(cheminFichier);
            lignes = FileUtils.readLines(file, "UTF-8");

            // On supprime la ligne d'entéte avec les noms des colonnes
            lignes.remove(0);

            for (String ligne: lignes){
                ParseurFichier.ajoutLigne(fichier, ligne);
            }
            return fichier;

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
}
