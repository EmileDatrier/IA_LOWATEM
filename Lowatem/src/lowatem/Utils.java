package lowatem;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Quelques fonctions utiles au projet. Vous devez comprendre ce que font ces
 * méthodes (voir leur documentation), mais pas comment elles le font (leur
 * code).
 *
 * À faire évoluer en fonction des nouvelles natures de case, de nouveaux types
 * d'unités, etc.
 */
public class Utils {

    /**
     * Indice de la première ligne.
     */
    public final static int NUM_LIGNE_MIN = 0;

    /**
     * Indice de la première colonne.
     */
    public final static int NUM_COLONNE_MIN = 0;

    /**
     * Indice de la dernière ligne.
     */
    public final static int NUM_LIGNE_MAX = 13;

    /**
     * Indice de la dernière COLONNE.
     */
    public final static int NUM_COLONNE_MAX = 13;

    /**
     * Caractère de la première ligne.
     */
    public final static char CAR_PREMIERE_LIGNE = 'a';

    /**
     * Caractère de la première colonne.
     */
    public final static char CAR_PREMIERE_COLONNE = 'A';

    /**
     * Caractère pour indiquer une unité noire.
     */
    public final static char CAR_NOIR = 'N';

    /**
     * Caractère pour indiquer une unité rouge.
     */
    public final static char CAR_ROUGE = 'R';

    /**
     * Caractère pour indiquer une case sans unité.
     */
    public final static char CAR_VIDE = ' ';

    /**
     * Caractère pour indiquer une case avec une unité de soldat.
     */
    public final static char CAR_SOLDATS = 'S';

    /**
     * Convertit un numéro de ligne (par exemple 2) en nom de ligne (ici 'c').
     *
     * @param numLigne le numéro de ligne à convertir
     * @return le caractère pour cette ligne
     */
    public static char numVersCarLigne(final int numLigne) {
        if ((numLigne < NUM_LIGNE_MIN) || (numLigne > NUM_LIGNE_MAX)) {
            throw new IllegalArgumentException(
                    "Appel incorrect à numVersCarLigne, avec numLigne = "
                    + numLigne
                    + ". Les valeurs autorisées sont les entiers entre "
                    + NUM_LIGNE_MIN + " et " + NUM_LIGNE_MAX);
        }
        return (char) (CAR_PREMIERE_LIGNE + numLigne);
    }

    /**
     * Convertit un numéro de colonne (par exemple 2) en nom de colonne (ici
     * 'C').
     *
     * @param numColonne le numéro de colonne à convertir
     * @return le caractère pour cette ligne
     */
    public static char numVersCarColonne(final int numColonne) {
        if ((numColonne < NUM_COLONNE_MIN) || (numColonne > NUM_COLONNE_MAX)) {
            throw new IllegalArgumentException(
                    "Appel incorrect à numVersCarColonne, avec numLigne = "
                    + numColonne
                    + ". Les valeurs autorisées sont les entiers entre "
                    + NUM_COLONNE_MIN + " et " + NUM_COLONNE_MAX);
        }
        return (char) (CAR_PREMIERE_COLONNE + numColonne);
    }

    /**
     * Convertit un nom de ligne (par exemple 'c') en numéro de ligne (ici 2).
     *
     * @param nomLigne le nom de ligne à convertir
     * @return le numéro de cette ligne
     */
    public static int carLigneVersNum(final char nomLigne) {
        final char carMin = CAR_PREMIERE_LIGNE + NUM_LIGNE_MIN;
        final char carMax = CAR_PREMIERE_LIGNE + NUM_LIGNE_MAX;
        if ((nomLigne < carMin) || (nomLigne > carMax)) {
            throw new IllegalArgumentException(
                    "Appel incorrect à carVersNum, avec car = " + nomLigne
                    + ". Les valeurs autorisées sont les caractères entre "
                    + carMin + " et " + carMax + ".");
        }
        return nomLigne - CAR_PREMIERE_LIGNE;
    }

    /**
     * Convertit un nom de colonnes (par exemple 'C') en numéro de colonne (ici
     * 2).
     *
     * @param nomColonne le nom de colonne à convertir
     * @return le numéro de cette colonne
     */
    public static int carColonneVersNum(final char nomColonne) {
        final char carMin = CAR_PREMIERE_COLONNE + NUM_COLONNE_MIN;
        final char carMax = CAR_PREMIERE_COLONNE + NUM_COLONNE_MAX;
        if ((nomColonne < carMin) || (nomColonne > carMax)) {
            throw new IllegalArgumentException(
                    "Appel incorrect à carVersNum, avec car = " + nomColonne
                    + ". Les valeurs autorisées sont les caractères entre "
                    + carMin + " et " + carMax + ".");
        }
        return nomColonne - CAR_PREMIERE_COLONNE;
    }

    /**
     * Fonction qui renvoie une copie du tableau sans les cases non utilisées,
     * c'est-à-dire contenant null ou la chaîne vide. Par exemple {"Coucou", "",
     * null, "Hello", null} renvoie {"Coucou", "Hello"}.
     *
     * @param actions le tableau à nettoyer
     * @return le tableau nettoyé
     */
    public static String[] nettoyerTableau(final String[] actions) {
        return Arrays.stream(actions)
                .filter(a -> ((a != null) && (!"".equals(a))))
                .collect(Collectors.toList())
                .toArray(new String[0]);
    }

    /**
     * Construit un plateau à partir de sa représentation sour forme texte,
     * comme renvoyé par formatTexte(), avec coordonnées et séparateurs.
     *
     * @param texteOriginal le texte du plateau
     * @return le plateau
     */
    public static Case[][] plateauDepuisTexte(final String texteOriginal) {
        final Case[][] plateau = new Case[JoueurLowatem.NB_LIGNES][JoueurLowatem.NB_COLONNES];
        final String[] lignes = texteOriginal.split("\n");
        for (int lig = 0; lig < JoueurLowatem.NB_LIGNES; lig++) {
            final String ligne1 = lignes[2 * lig + 1];
            final String ligne2 = lignes[2 * lig + 2];
            for (int col = 0; col < JoueurLowatem.NB_COLONNES; col++) {
                final String codageLigne1 = ligne1.substring(2 + 4 * col, 2 + 4 * col + 3);
                final String codageLigne2 = ligne2.substring(2 + 4 * col, 2 + 4 * col + 3);
                plateau[lig][col] = caseDepuisCodage(codageLigne1, codageLigne2);
            }
        }
        return plateau;
    }

    /**
     * Construit une case depuis son codage.
     *
     * @param ligne1 codage de la case, première ligne
     * @param ligne2 codage de la case, deuxième ligne
     * @return case correspondante
     */
    public static Case caseDepuisCodage(final String ligne1, final String ligne2) {
        // vérification des arguments
        if (ligne1.length() != 3 || ligne2.length() != 3) {
            throw new IllegalArgumentException(
                    "Un codage de ligne doit être sur 3 caractères par ligne.");
        }
        Case laCase = new Case(CAR_VIDE, CAR_ROUGE, 0, 0, CAR_VIDE);
        //
        // ligne 1
        //
        // 1er caractère : nature
        char carNature = ligne1.charAt(0);
        if (carNature == '-') {
            laCase.nature = Utils.CAR_VIDE;
        } else {
            laCase.nature = carNature;
        }
        // 2ème caractère : rien
        // 3ème caractère : altitude
        char carAltitude = ligne1.charAt(2);
        if (carAltitude == '-') {
            laCase.altitude = 0;
        } else {
            laCase.altitude = new Integer("" + carAltitude);
        }
        //
        // ligne 2
        //
        // 1er caractère : type d'unité
        laCase.typeUnite = ligne2.charAt(0);
        // 2ème caractère : couleur
        char carCouleur = ligne2.charAt(1);
        if (laCase.typeUnite == CAR_VIDE) {
            if (carCouleur != CAR_VIDE) {
                throw new IllegalArgumentException("Cette case ne contient pas d'unité,"
                        + " donc ne devrait pas avoir de couleur associée.");
            }
        } else {
            if (carCouleur != CAR_NOIR && carCouleur != CAR_ROUGE) {
                throw new IllegalArgumentException(
                        "Caractère couleur non admis : " + carCouleur);
            }
            laCase.couleurUnite = carCouleur;
        }
        // 3ème caractère : points de vie
        char carPdV = ligne2.charAt(2);
        if (laCase.typeUnite == CAR_VIDE) {
            if (carPdV != CAR_VIDE) {
                throw new IllegalArgumentException("Cette case ne contient pas d'unité,"
                        + " donc ne devrait pas avoir de points de vie associés.");
            }
            laCase.pointsDeVie = 0;
        } else {
            laCase.pointsDeVie = new Integer("" + carPdV);
        }
        return laCase;
    }

    /**
     * Afficher les action possibles déjà calculées.
     *
     * @param actionsPossibles les actions possibles calculées
     */
    static void afficherActionsPossibles(String[] actionsPossibles) {
        System.out.println(Arrays.deepToString(actionsPossibles));
    }

    /**
     * Indique si une action est présente parmi les actions possibles calculées.
     *
     * @param actionsPossibles actions possibles calculées
     * @param action l'action à tester
     * @return vrai ssi l'action est présente parmi les actions possibles
     */
    static boolean actionsPossiblesContient(String[] actionsPossibles,
            String action) {
        return Arrays.asList(actionsPossibles).contains(action);
    }

}
