package lowatem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Tests unitaires de la classe JoueurLowatem.
 */
public class JoueurLowatemTest {

    @Test
    public void testAjoutDeplDepuis() {
        // joueur créé pour pouvoir tester les méthodes
        JoueurLowatem joueur = new JoueurLowatem();
        // un plateau sur lequel on veut tester actionsPossibles()
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_TEST);
        // on choisit la couleur du joueur
        char couleur = 'R';
        // on choisit le niveau
        int niveau = 8;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau
                = joueur.actionsPossibles(plateau, couleur, niveau);
        // on peut afficher toutes les actions possibles calculées :
        Utils.afficherActionsPossibles(actionsPossiblesDepuisPlateau);
        for (int i = 0; i < joueur.nbActions; i++) {
            System.out.println(actionsPossiblesDepuisPlateau[i]);
        }
        // on peut aussi tester si une action est dans les actions possibles :
        assertTrue(Utils.actionsPossiblesContient(actionsPossiblesDepuisPlateau, "bEDbEAaE,25,19"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossiblesDepuisPlateau, "bEDbJ,22,20"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossiblesDepuisPlateau, "bEDbF,25,20"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossiblesDepuisPlateau, "hJDhN,24,20"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossiblesDepuisPlateau, "hJDhE,23,20"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossiblesDepuisPlateau, "bEDhE,21,20"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossiblesDepuisPlateau, "hJDlJAlI,21,16"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossiblesDepuisPlateau, "hJDhLAhK,21,12"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossiblesDepuisPlateau, "hJDaJAaK,19,13"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossiblesDepuisPlateau, "iDDhD,25,20"));
        // et si elle ne l'est pas
        assertFalse(Utils.actionsPossiblesContient(actionsPossiblesDepuisPlateau, "bEDnE,21,20"));
        assertFalse(Utils.actionsPossiblesContient(actionsPossiblesDepuisPlateau, "bEDjE,24,20"));
        assertFalse(Utils.actionsPossiblesContient(actionsPossiblesDepuisPlateau, "bEDiE,24,20"));
        assertFalse(Utils.actionsPossiblesContient(actionsPossiblesDepuisPlateau, "hJDhD,24,20"));
        assertFalse(Utils.actionsPossiblesContient(actionsPossiblesDepuisPlateau, "hJDeD,24,20"));
        assertFalse(Utils.actionsPossiblesContient(actionsPossiblesDepuisPlateau, "bEDbE,28,20"));
        assertFalse(Utils.actionsPossiblesContient(actionsPossiblesDepuisPlateau, "bEDbE,24,23"));
        assertFalse(Utils.actionsPossiblesContient(actionsPossiblesDepuisPlateau, "cADcM,25,23"));
    }

    /**
     * Test of compterSoldats method, of class JoueurLowatem.
     */
    @Test
    public void testCompterSoldats() {
        System.out.println("compterSoldats");
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_TEST);
        JoueurLowatem instance = new JoueurLowatem();
        int expResult = 8;
        int result = instance.compterSoldats(plateau);
        assertEquals(expResult, result);
    }

    /**
     * Test of obtenirPointsDeVie method, of class JoueurLowatem.
     */
    @Test
    public void testObtenirPointsDeVie() {
        System.out.println("obtenirPointsDeVie");
        char couleur = 'R';
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_TEST);
        JoueurLowatem instance = new JoueurLowatem();
        int expResult = 25;
        int result = instance.obtenirPointsDeVie(couleur, plateau);
        assertEquals(expResult, result);
        couleur = 'N';
        expResult = 20;
        result = instance.obtenirPointsDeVie(couleur, plateau);
        assertEquals(expResult, result);
    }

    /**
     * Test of pointsDeVieApresAttaque method, of class JoueurLowatem.
     */
    @Test
    public void testPointsDeVieApresAttaque() {
        System.out.println("pointsDeVieApresAttaque");
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_TEST);
        int oldpointsDeVieAttaquant = 9;
        int oldpointsDeVieAttaque = 4;
        char typeUnite = Utils.CAR_LM;
        JoueurLowatem instance = new JoueurLowatem();
        int expResult = 4;
        int result = instance.pointsDeVieApresAttaque(oldpointsDeVieAttaquant, oldpointsDeVieAttaque, typeUnite);
        assertEquals(expResult, result);
    }

    /**
     * Test of pointsDeVieApresAttaquant method, of class JoueurLowatem.
     */
    @Test
    public void testPointsDeVieApresAttaquant() {
        System.out.println("pointsDeVieApresAttaquant");
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_TEST);
        int oldpointsDeVieAttaquant = 4;
        int oldpointsDeVieAttaque = 8;
        int pointsDeVieApresDeplacement = 3;
        JoueurLowatem instance = new JoueurLowatem();
        int expResult = 0;
        int result = instance.pointsDeVieApresAttaquant(pointsDeVieApresDeplacement, oldpointsDeVieAttaque, oldpointsDeVieAttaquant, Utils.CAR_CHAR);
        //assertEquals(expResult, result);
    }

    /**
     * Test of degatsSubis method, of class JoueurLowatem.
     */
    @Test
    public void testDegatsSubis() {
        System.out.println("degatsSubis");
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_TEST);
        int pointsDeVieApresDeplacement = 5;
        int oldpointsDeVieAttaque = 7;
        char couleurJoueur = 'N';
        char couleurEquipe = 'N';
        int oldpointsDeVieAttaquant = 9;
        JoueurLowatem instance = new JoueurLowatem();
        int expResult = 7;
        int result = instance.degatsSubis(pointsDeVieApresDeplacement, oldpointsDeVieAttaque, couleurJoueur, couleurEquipe, oldpointsDeVieAttaquant, Utils.CAR_SOLDATS);
        assertEquals(expResult, result);
    }

    /**
     * Test of pointsDeVieApresDeplacement method, of class JoueurLowatem.
     */
    @Test
    public void testPointsDeVieApresDeplacement() {
        System.out.println("pointsDeVieApresDeplacement");
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_TEST);
        int ligneAvant = 1;
        int colonneAvant = 4;
        int ligneApres = 1;
        int colonneApres = 8;
        JoueurLowatem instance = new JoueurLowatem();
        int expResult = 5;
        int result = instance.pointsDeVieApresDeplacement(plateau, ligneAvant, colonneAvant, ligneApres, colonneApres);
        assertEquals(expResult, result);
    }

    /**
     * Un plateau de base, sous forme de chaîne. Pour construire une telle
     * chaîne depuis votre sortie.log, déclarez simplement final String
     * MON_PLATEAU = ""; puis copiez le plateau depuis votre sortie.log, et
     * collez-le entre les guillemets. Puis Alt+Shift+f pour mettre en forme.
     */
    final String PLATEAU_TEST
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |SN1|   |   |   |   |   |SN7|   |   |   |\n"
            + " +E--+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|   |   |   |   |AR7|   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|SR1|   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+E--+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |LR9|SN8|   |   |   |\n"
            + " +---+---+---+E--+E--+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |NR8|   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+E--+E--+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |   |   |   |   |CN4|   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";

    final String PLATEAU_TEST1
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|   |   |   |   |   |   |   |   |   |   |   |CN4|   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |   |   |   |   |   |   |   |   |   |   |   |CR4|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |   |AR9|   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |   |   |   |   |   |SR5|   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |   |   |   |AN9|   |   |   |   |   |   |\n"
            + " +---+---+E--+E--+E--+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |NN5|NR5|   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";

    /**
     * Test of ajoutDeplVerticDepuis method, of class JoueurLowatem.
     */
    /**
     * Test of caseEstVide method, of class JoueurLowatem.
     */
    @Test
    public void testCaseEstVide() {
        JoueurLowatem j = new JoueurLowatem();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_TEST);
        assertTrue(j.caseEstVide(plateau[3][5]));
        assertFalse(j.caseEstVide(plateau[1][4]));
    }

    /**
     * Test of caseEstValide method, of class JoueurLowatem.
     */
    @Test
    public void testCaseEstValide() {
        JoueurLowatem j = new JoueurLowatem();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_TEST);
        assertTrue(j.caseEstValide(Utils.CAR_NAVIRE, plateau[7][3]));
        assertFalse(j.caseEstValide(Utils.CAR_LM, plateau[8][4]));
    }
}
