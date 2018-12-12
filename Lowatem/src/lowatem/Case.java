package lowatem;

/**
 * Case du plateau.
 * 
 * VOUS NE DEVEZ PAS MODIFIER CE FICHIER.
 */
public final class Case {

    /**
     * Indique le type d'unité se trouvant sur cette case. 
     * L'absence d'unité est indiquée par le caractère Utils.CAR_VIDE.
     */
    char typeUnite;
    
    /**
     * Indique la couleur de l'unité sur cette case (s'il y en a une).
     * Convention : 'R' pour rouge, 'N' pour noir.
     */
    char couleurUnite;
    
    /**
     * Points de vie de l'unité, le cas échéant.
     */
    int pointsDeVie;
    
    /**
     * Altitude d'une case.
     */
    int altitude;
    
    /**
     * Nature d'une case.
     */
    char nature;

    /**
     * Constructeur d'une case.
     * 
     * @param unTypeUnite type d'unité sur la case
     * @param uneCouleurUnite couleur de l'unité sur cette case
     * @param desPointsDeVie points de vie de l'unité sur cette case
     * @param uneAltitude altitude de la case
     * @param uneNature nature de la case
     */
    public Case(char unTypeUnite, char uneCouleurUnite, int desPointsDeVie,
            int uneAltitude, char uneNature) {
        this.typeUnite = unTypeUnite;
        this.couleurUnite = uneCouleurUnite;
        this.pointsDeVie = desPointsDeVie;
        this.altitude = uneAltitude;
        this.nature = uneNature;
    }    
}
