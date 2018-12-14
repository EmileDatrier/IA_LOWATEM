/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lowatem;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author edatrier
 */
public class IALowatemTest {

    public IALowatemTest() {
    }

    /**
     * Test of connexion method, of class IALowatem.
     */
    @Test
    public void testConnexion() throws Exception {
    }

    /**
     * Test of toursDeJeu method, of class IALowatem.
     */
    @Test
    public void testToursDeJeu() throws Exception {
    }

    /**
     * Test of jouer method, of class IALowatem.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testJouer() throws Exception {
        char couleur = 'R';
        JoueurLowatem joueur = new JoueurLowatem();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIV_10);
        String[] actions = joueur.actionsPossibles(plateau, couleur, 6);

        for (int i = 0; i < actions.length; i++) {
            if (actions[i] != null) {
                System.out.println(actions[i]);
                System.out.println(i);
            }
        }
    }
    final String PLATEAU_NIV_10
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|SN7|   |   |   |   |   |SR1|   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |SN1|   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |   |   |   |   |   |SR5|   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |   |SN2|   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |SR7|   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |   |   |   |   |   |   |SR2|   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |   |   |   |   |   |SN5|   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";

    /**
     * Test of choisirAction method, of class IALowatem.
     */
    @Test
    public void testChoisirAction() {
    }

    /**
     * Test of enleverPointsDeVie method, of class IALowatem.
     */
    @Test
    public void testEnleverPointsDeVie() {
    }

    /**
     * Test of suivant method, of class IALowatem.
     */
    @Test
    public void testSuivant() {
    }

    /**
     * Test of mettreAJour method, of class IALowatem.
     */
    @Test
    public void testMettreAJour() {
    }

    /**
     * Test of deplacerUnite method, of class IALowatem.
     */
    @Test
    public void testDeplacerUnite() {
    }

    /**
     * Test of attaquerUnite method, of class IALowatem.
     */
    @Test
    public void testAttaquerUnite() {
    }

    /**
     * Test of retirerUnite method, of class IALowatem.
     */
    @Test
    public void testRetirerUnite() {
    }

    /**
     * Test of main method, of class IALowatem.
     */
    @Test
    public void testMain() {
    }

}
