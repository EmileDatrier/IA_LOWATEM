package lowatem;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests sur la classe Utils.
 */
public class UtilsTest {

    /**
     * Test de numVersCarLigne pour les valeurs admises.
     */
    @Test
    public void testNumVersCarLigne() {
        assertEquals('a', Utils.numVersCarLigne(0));
        assertEquals('b', Utils.numVersCarLigne(1));
        assertEquals('n', Utils.numVersCarLigne(13));
    }

    /**
     * Test de numVersCarLigne pour un argument trop petit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNumVersCarLigneException1() {
        Utils.numVersCarLigne(-1);
    }

    /**
     * Test de numVersCarLigne pour un argument trop grand.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNumVersCarLigneException2() {
        Utils.numVersCarLigne(14);
    }

    /**
     * Test de carLigneVersNum pour les valeurs admises.
     */
    @Test
    public void testCarLigneVersNum() {
        assertEquals(0, Utils.carLigneVersNum('a'));
        assertEquals(1, Utils.carLigneVersNum('b'));
        assertEquals(13, Utils.carLigneVersNum('n'));
    }

    /**
     * Test de carLigneVersNum pour un argument trop petit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCarLigneVersNumException1() {
        final char c = Utils.CAR_PREMIERE_LIGNE - 1;
        Utils.carLigneVersNum(c);
    }

    /**
     * Test de carLigneVersNum pour un argument trop grand.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCarLigneVersNumException2() {
        final char c = Utils.CAR_PREMIERE_LIGNE + Utils.NUM_LIGNE_MAX
                - Utils.NUM_LIGNE_MIN + 1;
        Utils.carLigneVersNum(c);
    }

    /**
     * Test de numVersCarColonne pour les valeurs admises.
     */
    @Test
    public void testNumVersCarColonne() {
        assertEquals('A', Utils.numVersCarColonne(0));
        assertEquals('B', Utils.numVersCarColonne(1));
        assertEquals('N', Utils.numVersCarColonne(13));
    }

    /**
     * Test de numVersCarColonne pour un argument trop petit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNumVersCarColonneException1() {
        Utils.numVersCarColonne(-1);
    }

    /**
     * Test de numVersCarColonne pour un argument trop grand.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNumVersCarColonneException2() {
        Utils.numVersCarColonne(14);
    }

    /**
     * Test de carColonneVersNum pour les valeurs admises.
     */
    @Test
    public void testCarColonneVersNum() {
        assertEquals(0, Utils.carColonneVersNum('A'));
        assertEquals(1, Utils.carColonneVersNum('B'));
        assertEquals(13, Utils.carColonneVersNum('N'));
    }

    /**
     * Test de carColonneVersNum pour un argument trop petit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCarColonneVersNumException1() {
        final char c = Utils.CAR_PREMIERE_COLONNE - 1;
        Utils.carColonneVersNum(c);
    }

    /**
     * Test de carColonneVersNum pour un argument trop grand.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCarColonneVersNumException2() {
        final char c = Utils.CAR_PREMIERE_COLONNE + Utils.NUM_LIGNE_MAX
                - Utils.NUM_LIGNE_MIN + 1;
        Utils.carColonneVersNum(c);
    }

    /**
     * Test de la fonction caseDepuisCodage.
     */
    @Test
    public void testCaseDepuisCodage() {
        Case laCase;
        // case vide
        laCase = Utils.caseDepuisCodage("---", "   ");
        assertEquals(Utils.CAR_TERRE, laCase.nature);
        assertEquals(0, laCase.altitude);
        assertEquals(Utils.CAR_VIDE, laCase.typeUnite);
        // une unité de soldats rouges
        laCase = Utils.caseDepuisCodage("---", "SR4");
        assertEquals(Utils.CAR_TERRE, laCase.nature);
        assertEquals(0, laCase.altitude);
        assertEquals(Utils.CAR_SOLDATS, laCase.typeUnite);
        assertEquals(Utils.CAR_ROUGE, laCase.couleurUnite);
        assertEquals(4, laCase.pointsDeVie);
        // une unité de soldats noirs
        laCase = Utils.caseDepuisCodage("---", "SN9");
        assertEquals(Utils.CAR_TERRE, laCase.nature);
        assertEquals(0, laCase.altitude);
        assertEquals(Utils.CAR_SOLDATS, laCase.typeUnite);
        assertEquals(Utils.CAR_NOIR, laCase.couleurUnite);
        assertEquals(9, laCase.pointsDeVie);
    }

    /**
     * Test de la fonction plateauDepuisTexte().
     */
    @Test
    public void testPlateauDepuisTexte() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU1);
        Case laCase;
        // une case avec une unité noire
        laCase = plateau[0][0];
        assertEquals(Utils.CAR_TERRE, laCase.nature);
        assertEquals(0, laCase.altitude);
        assertEquals(Utils.CAR_SOLDATS, laCase.typeUnite);
        assertEquals(Utils.CAR_NOIR, laCase.couleurUnite);
        assertEquals(4, laCase.pointsDeVie);
        // une case avec une unité rouge
        laCase = plateau[13][13];
        assertEquals(Utils.CAR_TERRE, laCase.nature);
        assertEquals(0, laCase.altitude);
        assertEquals(Utils.CAR_SOLDATS, laCase.typeUnite);
        assertEquals(Utils.CAR_ROUGE, laCase.couleurUnite);
        assertEquals(1, laCase.pointsDeVie);
        // une case vide
        laCase = plateau[0][1];
        assertEquals(Utils.CAR_TERRE, laCase.nature);
        assertEquals(0, laCase.altitude);
        assertEquals(Utils.CAR_VIDE, laCase.typeUnite);
    }

    /**
     * Test de la fonction nettoyerTableau().
     */
    @Test
    public void testNettoyerTableau() {

        String tab[], tabNettoye[];

        // tableau de taille 0
        tab = new String[0];
        tabNettoye = Utils.nettoyerTableau(tab);
        assertEquals(0, tabNettoye.length);

        // tableau de taille 1 avec 1 élément
        tab = new String[1];
        tab[0] = "coucou";
        tabNettoye = Utils.nettoyerTableau(tab);
        assertEquals(1, tabNettoye.length);
        assertEquals("coucou", tabNettoye[0]);

        // tableau de taille 1 avec 0 élément (null)
        tab = new String[1];
        tab[0] = null;
        tabNettoye = Utils.nettoyerTableau(tab);
        assertEquals(0, tabNettoye.length);

        // tableau de taille 1 avec 0 élément ("")
        tab = new String[1];
        tab[0] = "";
        tabNettoye = Utils.nettoyerTableau(tab);
        assertEquals(0, tabNettoye.length);

        // tableau de taille 2 avec 1 élément ("")
        tab = new String[2];
        tab[0] = "";
        tab[1] = "hello";
        tabNettoye = Utils.nettoyerTableau(tab);
        assertEquals(1, tabNettoye.length);
        assertEquals("hello", tabNettoye[0]);

        // un cas plus complet
        tab = new String[7];
        tab[0] = "";
        tab[1] = "hello";
        tab[2] = null;
        tab[3] = "";
        tab[4] = "hello";
        tab[5] = "";
        tab[6] = null;
        tabNettoye = Utils.nettoyerTableau(tab);
        assertEquals(2, tabNettoye.length);
        assertEquals("hello", tabNettoye[0]);
        assertEquals("hello", tabNettoye[1]);
    }

    final String PLATEAU1
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|SN4|   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|SR9|   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |SR3|   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |   |   |   |   |   |   |   |   |   |SR1|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";
}
