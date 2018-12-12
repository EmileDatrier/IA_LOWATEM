package lowatem;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Joueur implémentant les actions possibles à partir d'un plateau, pour un
 * niveau donné.
 */
public class JoueurLowatem implements IJoueurLowatem {

    /**
     * Nombre de lignes du plateau.
     */
    final static int NB_LIGNES = 14;

    /**
     * Nombre de colonnes du plateau.
     */
    final static int NB_COLONNES = 14;

    /**
     * Compte le nombre d'actions possibles déjà entrées dans le tableau des
     * actions possibles.
     */
    int nbActions;

    /**
     * Renvoie, pour un plateau donné et un joueur donné, toutes les actions
     * possibles pour ce joueur.
     *
     * @param plateau le plateau considéré
     * @param couleurJoueur couleur du joueur
     * @param niveau le niveau de la partie à jouer
     * @return l'ensemble des actions possibles
     */
    @Override
    public String[] actionsPossibles(Case[][] plateau, char couleurJoueur, int niveau) {
        // afficher l'heure de lancement
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        System.out.println("actionsPossibles : lancement le " + format.format(new Date()));
        // compte le nombre de soldats
        int nbSoldats;
        nbSoldats = compterSoldats(plateau);
        // calculer les actions possibles        
        String actions[] = new String[NB_LIGNES * NB_COLONNES * nbSoldats]; // à agrandir si besoin
        nbActions = 0;
        // déplacements possibles depuis une case quelconque
        cherchePositionSoldat(plateau, actions, couleurJoueur);
        System.out.println("actionsPossibles : fin");
        return Utils.nettoyerTableau(actions);
    }

    /**
     * Ajoute au tableau actions tous les déplacements depuis une case donnée.
     *
     * @param ligne ligne de la case d'origine
     * @param colonne colonne de la case d'origine
     * @param plateau le plateau considéré
     * @param actions tableau des actions possibles
     * @param couleur couleur du joueur
     */
    void ajoutDeplDepuis(int ligne, int colonne, Case[][] plateau, String[] actions, char couleur) {

        // déplacements horizontaux
        ajoutDeplHorizDepuis(ligne, colonne, actions, plateau, couleur);

        // déplacements verticaux
        ajoutDeplVerticDepuis(ligne, colonne, actions, plateau, couleur);

        // rester sur place
        ajouterDeplSurPlace(plateau, actions, ligne, colonne, couleur);
    }

    /**
     * Ajoute tous les déplacements horizontaux depuis une case donnée. Le
     * déplacement vers cette case d'origine n'est pas inclus.
     *
     * @param ligne ligne de la case d'origine
     * @param colonne colonne de la case d'origine
     * @param actions tableau des actions possibles tableau des actions
     * possibles, à compléter
     * @param plateau le plateau considéré
     * @param couleur couleur du joueur
     */
    void ajoutDeplHorizDepuis(int ligne, int colonne, String[] actions, Case[][] plateau, char couleur) {

        ajouterDeplDroite(plateau, actions, ligne, colonne, couleur);
        ajouterDeplGauche(plateau, actions, ligne, colonne, couleur);
    }

    /**
     * Ajoute au tableau actions tous les déplacements verticaux depuis une case
     * donnée. Le déplacement vers cette case d'origine n'est pas inclus.
     *
     * @param ligne ligne de la case d'origine
     * @param colonne colonne de la case d'origine
     * @param actions tableau des actions possibles
     * @param plateau le plateau considéré
     * @param couleur couleur du joueur
     */
    void ajoutDeplVerticDepuis(int ligne, int colonne, String[] actions,
            Case[][] plateau, char couleur) {

        ajouterDeplHaut(plateau, actions, ligne, colonne, couleur);
        ajouterDeplBas(plateau, actions, ligne, colonne, couleur);
    }

    /**
     * Ajoute au tableau actions le déplacement sur place d'une case donnée.
     *
     * @param plateau le plateau considéré
     * @param actions tableau des actions possibles
     * @param ligne ligne de la case d'origine
     * @param colonne colonne de la case d'origine
     * @param couleur couleur du joueur
     */
    void ajouterDeplSurPlace(Case[][] plateau, String[] actions,
            int ligne, int colonne, char couleur) {

        if (couleur == plateau[ligne][colonne].couleurUnite) {

            Case caseDepart = plateau[ligne][colonne];
            int pos = 0;
            String[] attaques = attaque(plateau, ligne, colonne, ligne, colonne, couleur, caseDepart.typeUnite);

            do {
                ajouterAction(plateau, actions, ligne, colonne, ligne, colonne, attaques, pos, couleur);
                pos++;
            } while (attaques[pos] != null);

        }
    }

    /**
     * Ajoute au tableau actions les déplacement vers le haut d'une case donnée.
     *
     * @param plateau le plateau considéré
     * @param actions tableau des actions possibles
     * @param ligne ligne de la case d'origine
     * @param colonne colonne de la case d'origine
     * @param couleur couleur du joueur
     */
    void ajouterDeplBas(Case[][] plateau, String[] actions,
            int ligne, int colonne, char couleur) {

        if (couleur == plateau[ligne][colonne].couleurUnite) {

            int l = ligne + 1;
            String[] attaques;
            Case caseDepart = plateau[ligne][colonne];
            Case caseCible;

            while (l < NB_LIGNES && caseEstValide(caseDepart.typeUnite, caseCible = plateau[l][colonne])) {

                if (l != ligne) {

                    if (caseEstVide(caseCible)) {

                        attaques = attaque(plateau, ligne, colonne, l, colonne, couleur, caseDepart.typeUnite);
                        int pos = 0;

                        do {
                            ajouterAction(plateau, actions, ligne, colonne, l, colonne, attaques, pos, couleur);
                            pos++;
                        } while (attaques[pos] != null);
                    }
                }
                l++;
            }
        }
    }

    /**
     * Ajoute au tableau actions les déplacement vers le bas d'une case donnée.
     *
     * @param plateau le plateau considéré
     * @param actions tableau des actions possibles
     * @param ligne ligne de la case d'origine
     * @param colonne colonne de la case d'origine
     * @param couleur couleur du joueur
     */
    void ajouterDeplHaut(Case[][] plateau, String[] actions, int ligne, int colonne, char couleur) {

        if (couleur == plateau[ligne][colonne].couleurUnite) {

            int l = ligne - 1;
            String[] attaques;
            Case caseDepart = plateau[ligne][colonne];
            Case caseCible;

            while (l >= 0 && caseEstValide(caseDepart.typeUnite, caseCible = plateau[l][colonne])) {
                if (l != ligne) {

                    if (caseEstVide(caseCible)) {

                        attaques = attaque(plateau, ligne, colonne, l, colonne, couleur, caseDepart.typeUnite);
                        int pos = 0;

                        do {
                            ajouterAction(plateau, actions, ligne, colonne, l, colonne, attaques, pos, couleur);
                            pos++;
                        } while (attaques[pos] != null);
                    }
                }
                l--;
            }
        }
    }

    /**
     * Ajoute au tableau actions les déplacement vers la droite d'une case
     * donnée.
     *
     * @param plateau le plateau considéré
     * @param actions tableau des actions possibles
     * @param ligne ligne de la case d'origine
     * @param colonne colonne de la case d'origine
     * @param couleur couleur du joueur
     */
    void ajouterDeplDroite(Case[][] plateau, String[] actions, int ligne, int colonne, char couleur) {

        if (couleur == plateau[ligne][colonne].couleurUnite) {

            int c = colonne + 1;
            String[] attaques;
            Case caseDepart = plateau[ligne][colonne];
            Case caseCible;

            while (c < NB_COLONNES && caseEstValide(caseDepart.typeUnite, caseCible = plateau[ligne][c])) {

                if (c != colonne) {

                    if (caseEstVide(caseCible)) {

                        attaques = attaque(plateau, ligne, colonne, ligne, c, couleur, caseDepart.typeUnite);
                        int pos = 0;

                        do {
                            ajouterAction(plateau, actions, ligne, colonne, ligne, c, attaques, pos, couleur);
                            pos++;
                        } while (attaques[pos] != null);
                    }
                    c++;
                }
            }
        }
    }

    /**
     * Ajoute au tableau actions les déplacement vers la gauche d'une case
     * donnée.
     *
     * @param plateau le plateau considéré
     * @param actions tableau des actions possibles
     * @param ligne ligne de la case d'origine
     * @param colonne colonne de la case d'origine
     * @param couleur couleur du joueur
     */
    void ajouterDeplGauche(Case[][] plateau, String[] actions, int ligne, int colonne, char couleur) {

        if (couleur == plateau[ligne][colonne].couleurUnite) {

            int c = colonne - 1;
            Case caseDepart = plateau[ligne][colonne];
            Case caseCible;
            String[] attaques;

            while (c >= 0 && caseEstValide(caseDepart.typeUnite, caseCible = plateau[ligne][c])) {

                if (c != colonne && caseEstVide(caseCible)) {

                    attaques = attaque(plateau, ligne, colonne, ligne, c, couleur, caseDepart.typeUnite);
                    int pos = 0;

                    do {
                        ajouterAction(plateau, actions, ligne, colonne, ligne, c, attaques, pos, couleur);
                        pos++;
                    } while (attaques[pos] != null);
                }
                c--;
            }
        }
    }

    /**
     * Cherche la position des soldats.
     *
     * @param plateau le plateau considéré
     * @param actions tableau des actions possibles
     * @param couleur couleur du joueur
     */
    void cherchePositionSoldat(Case[][] plateau, String[] actions,
            char couleur) {

        for (int ligne = 0; ligne < NB_LIGNES; ligne++) {

            for (int colonne = 0; colonne < NB_LIGNES; colonne++) {

                if (plateau[ligne][colonne].typeUnite != Utils.CAR_VIDE) {
                    ajoutDeplDepuis(ligne, colonne, plateau, actions, couleur);
                }
            }
        }
    }

    /**
     * Compte le nombre de soldats
     *
     * @param plateau le plateau considéré
     * @return le nombre de soldats
     */
    int compterSoldats(Case[][] plateau
    ) {
        int nbSoldats = 0;

        for (int ligne = 0; ligne < NB_LIGNES; ligne++) {

            for (int colonne = 0; colonne < NB_LIGNES; colonne++) {

                if (plateau[ligne][colonne].typeUnite != Utils.CAR_VIDE) {
                    nbSoldats++;
                }
            }
        }
        return nbSoldats;
    }

    /**
     * Compte le nombre de points de vies total des unités d'un joueur.
     *
     * @param couleur couleur du joueur
     * @param plateau le plateau considéré
     * @return le nombre de points de vies
     */
    int obtenirPointsDeVie(char couleur, Case[][] plateau) {

        int pointsDeVie = 0;

        for (int ligne = 0; ligne < NB_LIGNES; ligne++) {

            for (int colonne = 0; colonne < NB_COLONNES; colonne++) {

                if ((plateau[ligne][colonne].typeUnite != Utils.CAR_VIDE)
                        && plateau[ligne][colonne].couleurUnite == couleur) {

                    pointsDeVie += plateau[ligne][colonne].pointsDeVie;

                }
            }
        }
        return pointsDeVie;
    }

    /**
     * Renvoie les attaques possibles lors d'un déplacement.
     *
     * @param plateau le plateau considéré
     * @param ligneDep ligne de la case d'origine
     * @param colonneDep colonne de la case d'origine
     * @param ligneDest ligne de la case après déplacement
     * @param colonneDest colonne de la case après déplacement
     * @param couleur couleur du joueur
     * @param typeUnite type de l'unité se déplaçant
     * @return "A" + la case sur laquelle l'attaque est possible, "" si aucune
     * attaque n'est possible.
     */
    String[] attaque(Case[][] plateau, int ligneDep, int colonneDep, int ligneDest, int colonneDest, char couleur, char typeUnite) {

        int portee = Utils.portee(typeUnite);
        int taille = 0;
        String[] attaques = new String[portee * portee * 4];
        attaques[taille] = ""; //Renvoie l'action consistant à ne pas attaquer car possible dans tout les cas.
        taille++;

        for (int l = 0; l < NB_LIGNES; l++) {

            for (int c = 0; c < NB_COLONNES; c++) {

                if (plateau[l][c].typeUnite != Utils.CAR_VIDE && plateau[l][c].couleurUnite != plateau[ligneDep][colonneDep].couleurUnite) {

                    if (Math.abs(Math.abs(l - ligneDest) + Math.abs(c - colonneDest)) <= portee) {

                        attaques[taille] = "A" + Utils.numVersCarLigne(l) + Utils.numVersCarColonne(c);
                        taille++;
                    }
                }
            }
        }
        return attaques;
    }

    /**
     * Calcule les points de vie de l'attaqué après l'attaque.
     *
     * @param oldPointsDeVieAttaquant anciens points de vie de l'attaquant
     * @param oldPointsDeVieAttaque anciens points de vie de l'attaqué
     * @param typeUnite type de l'unité se déplaçant
     * @return les points de vie de l'attaqué après l'attaque.
     */
    int pointsDeVieApresAttaque(int oldPointsDeVieAttaquant, int oldPointsDeVieAttaque, char typeUnite) {

        int newpointsDeVieAttaque = oldPointsDeVieAttaque - Utils.degatsAttaque(typeUnite) - (int) ((oldPointsDeVieAttaquant - 5) / 2);
        int degatsSubis;

        if (newpointsDeVieAttaque < 0) {
            newpointsDeVieAttaque = 0;
        }

        degatsSubis = oldPointsDeVieAttaque - newpointsDeVieAttaque;
        return degatsSubis;
    }

    /**
     * Calcule les points de vie de l'attaquant après l'attaque.
     *
     * @param oldPointsDeVieAttaquant anciens points de vie de l'attaquant
     * @param oldPointsDeVieAttaque anciens points de vie de l'attaqué
     * @param pointsDeVieApresDeplacement
     * @param typeUnite type de l'unité se déplaçant
     * @return les points de vie de l'attaquant après l'attaque.
     */
    int pointsDeVieApresAttaquant(int oldPointsDeVieAttaquant, int oldPointsDeVieAttaque,
            int pointsDeVieApresDeplacement, char typeUnite) {

        int newPointsDeVieAttaquant;
        int degatsSubis;

        if (oldPointsDeVieAttaque > 0) {

            newPointsDeVieAttaquant = pointsDeVieApresDeplacement - Utils.degatsAttaquant(typeUnite)
                    - (int) ((oldPointsDeVieAttaque - 5) / 2);
        } else {
            newPointsDeVieAttaquant = pointsDeVieApresDeplacement;
        }

        if (newPointsDeVieAttaquant < 0) {
            newPointsDeVieAttaquant = 0;
        }

        degatsSubis = oldPointsDeVieAttaquant - newPointsDeVieAttaquant;

        if (degatsSubis < 0) {
            degatsSubis = 0;
        }

        return degatsSubis;
    }

    /**
     * Les dégats subis par l'attaquant ou par l'attaqué.
     *
     * @param pointsDeVieApresDeplacement
     * @param oldPointsDeVieAttaque anciens points de vie de l'attaqué
     * @param couleurJoueur couleur du joueur courant
     * @param couleurEquipe couleur de l'équipe subissant les dégats
     * @param oldPointsDeVieAttaquant anciens points de vie de l'attaquant
     * @param typeUnite type de l'unité se déplaçant
     * @return les dégats subis de l'attaquant ou de
     */
    int degatsSubis(int pointsDeVieApresDeplacement, int oldPointsDeVieAttaque,
            char couleurJoueur, char couleurEquipe, int oldPointsDeVieAttaquant, char typeUnite) {

        if (couleurJoueur == couleurEquipe) {
            return pointsDeVieApresAttaquant(oldPointsDeVieAttaquant, oldPointsDeVieAttaque, pointsDeVieApresDeplacement, typeUnite);
        }
        return pointsDeVieApresAttaque(pointsDeVieApresDeplacement, oldPointsDeVieAttaque, typeUnite);
    }

    /**
     * Calcule les points de vie après le déplacement de d'une unité.
     *
     * @param plateau le plateau considéré
     * @param ligneDep ligne de la case d'origine
     * @param colonneDep colonne de la case d'origine
     * @param ligneDest ligne de la case après déplacement
     * @param colonneDest colonne de la case après déplacement
     * @return
     */
    int pointsDeVieApresDeplacement(Case[][] plateau, int ligneDep, int colonneDep, int ligneDest, int colonneDest) {

        char typeUnite = plateau[ligneDep][colonneDep].typeUnite;
        int pointsDeVieApresDeplacement;
        int distance = 0;

        if (ligneDep != ligneDest) {
            distance = Math.abs(ligneDep - ligneDest);
        }
        if (colonneDep != colonneDest) {
            distance = Math.abs(colonneDep - colonneDest);
        }

        pointsDeVieApresDeplacement = plateau[ligneDep][colonneDep].pointsDeVie - ((int) (Utils.coutDeplacement(typeUnite) * distance));

        if (pointsDeVieApresDeplacement < 0) {
            pointsDeVieApresDeplacement = 0;
        }
        return pointsDeVieApresDeplacement;
    }

    /**
     * Méthode permettant de savoir si une case est vide ou non.
     *
     * @param caseArrivee
     * @return true si la case est vide, false sinon
     */
    boolean caseEstVide(Case caseArrivee) {

        boolean estVide = true;

        if (caseArrivee.typeUnite != Utils.CAR_VIDE) {
            estVide = false;
        }
        return estVide;
    }

    /**
     * Méthode renvoyant true si la case est valide (c-à-d si le type de l'unité
     * correspond à la nature de la case).
     *
     * @param typeUnite type de l'unité se déplaçant
     * @param caseArrivee la case à tester pour savoir si l'unité peut s'y
     * déplacer
     * @return true si la nature de la case correspond au type de l'unité, false
     * sinon
     */
    boolean caseEstValide(char typeUnite, Case caseArrivee) {
        boolean caseEstValide = true;

        if (caseArrivee.nature == Utils.CAR_EAU) {

            if (typeUnite != Utils.CAR_AVION && typeUnite != Utils.CAR_NAVIRE) {

                caseEstValide = false;

            }
        } else if (typeUnite == Utils.CAR_NAVIRE) {

            caseEstValide = false;

        }
        return caseEstValide;
    }

    /**
     * Méthode permettant d'ajouter une action au tableau actions.
     *
     * @param plateau le plateau considéré
     * @param actions tableau des actions possibles
     * @param ligneDep ligne de la case d'origine
     * @param colonneDep colonne de la case d'origine
     * @param ligneDest ligne de la case après déplacement
     * @param colonneDest colonne de la case après déplacement
     * @param attaques tableau d'attaques possibles
     * @param pos indice du tableau attaques
     * @param couleur couleur du joueur courant
     */
    void ajouterAction(Case[][] plateau, String[] actions, int ligneDep, int colonneDep,
            int ligneDest, int colonneDest, String[] attaques, int pos, char couleur) {

        Case caseDepart = plateau[ligneDep][colonneDep];
        int oldPointsDeVieAttaquant = caseDepart.pointsDeVie;
        int oldPointsDeVieAttaque;

        if (attaques[pos] != null && !"".equals(attaques[pos])) {

            int ligneAttaque = Utils.carLigneVersNum(attaques[pos].charAt(1));
            int colonneAttaque = Utils.carColonneVersNum(attaques[pos].charAt(2));
            oldPointsDeVieAttaque = plateau[ligneAttaque][colonneAttaque].pointsDeVie;
        } else {
            oldPointsDeVieAttaque = 0;
        }

        int pointsDeVieApresDeplacement = pointsDeVieApresDeplacement(plateau, ligneDep, colonneDep, ligneDest, colonneDest);
        char typeUnite = caseDepart.typeUnite;

        if (pointsDeVieApresDeplacement != 0) {

            String action = ""
                    + Utils.numVersCarLigne(ligneDep)
                    + Utils.numVersCarColonne(colonneDep)
                    + "D"
                    + Utils.numVersCarLigne(ligneDest)
                    + Utils.numVersCarColonne(colonneDest)
                    + attaques[pos]
                    + ","
                    + (obtenirPointsDeVie(Utils.CAR_ROUGE, plateau) - degatsSubis(pointsDeVieApresDeplacement,
                    oldPointsDeVieAttaque, couleur, Utils.CAR_ROUGE, oldPointsDeVieAttaquant, typeUnite))
                    + ","
                    + (obtenirPointsDeVie(Utils.CAR_NOIR, plateau) - degatsSubis(pointsDeVieApresDeplacement,
                    oldPointsDeVieAttaque, couleur, Utils.CAR_NOIR, oldPointsDeVieAttaquant, typeUnite));

            actions[nbActions] = action;
            nbActions++;
        }
    }
}
