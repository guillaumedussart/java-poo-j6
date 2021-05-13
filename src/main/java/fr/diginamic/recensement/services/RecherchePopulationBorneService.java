package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.exception.NotFindDepartement;
import fr.diginamic.exception.NotNumberException;
import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 *
 * @author DIGINAMIC
 */
public class RecherchePopulationBorneService extends MenuService {

    /**
     * traiter
     *
     * @param rec rec
     * @param scanner scanner
     * @return {@link Throwable}
     * @see Throwable
     * @throws NotNumberException fr.diginamic.exception. not number exception
     * @throws NotFindDepartement fr.diginamic.exception. not find departement
     */
    @Override
    public Throwable traiter(Recensement rec, Scanner scanner) throws NotNumberException, NotFindDepartement {

        System.out.println("Quel est le code du département recherché ? ");
        String choix = scanner.nextLine();

        if (!NumberUtils.isDigits(choix)) {
            throw new NotNumberException("Vous devez entrer un nombre");
        }
        DepartementService dept = new DepartementService();
        boolean find = dept.listDept(rec, choix);

        if (!find) {
            throw new NotFindDepartement("Vous n'avez pas choisi le bon dept");
        }


        System.out.println("Choississez une population minimum (en milliers d'habitants): ");
        String saisieMin = scanner.nextLine();

        if (!NumberUtils.isDigits(saisieMin)) {
            throw new NotNumberException("Vous devez entrer un nombre");
        }

        System.out.println("Choississez une population maximum (en milliers d'habitants): ");
        String saisieMax = scanner.nextLine();

        if (!NumberUtils.isDigits(saisieMax)) {
            throw new NotNumberException("Vous devez entrer un nombre");
        }

        int min = Integer.parseInt(saisieMin) * 1000;
        int max = Integer.parseInt(saisieMax) * 1000;

        if (min < 0 || max < 0 || min < max) {
            throw new NotNumberException("Vous devez entrer un nombre > 0");
        }

        List<Ville> villes = rec.getVilles();
        for (Ville ville : villes) {
            if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
                if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
                    System.out.println(ville);
                }
            }
        }
        return null;
    }

}
