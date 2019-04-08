package carte;

import carte.Enseigne.Couleur;
import paquet.Paquet;

public class Carte {

    private Enseigne enseigne;
    private Valeur valeur;

    public Carte(Enseigne enseigne, Valeur v) {
        setEnseigne(enseigne);
        setValeur(v);
    }

    public Carte(String s) {
        setValeur(new Valeur(s.substring(0, 1)));
        switch (s.charAt(1)) {
        case 's':
            setEnseigne(Enseigne.Pique);
            break;
        case 'h':
            setEnseigne(Enseigne.Coeur);
            break;
        case 'd':
            setEnseigne(Enseigne.Carreau);
            break;
        case 'c':
            setEnseigne(Enseigne.Trefle);
            break;
        default:
            break;
        }
        setEnseigne(enseigne);
    }

    public Carte(Paquet deck) {
        Carte c = deck.piocher();
        setEnseigne(c.getEnseigne());
        setValeur(c.getValeur());
    }

    /**
     * @return the enseigne (Coeur,Pique,Carreau,Trefle)
     */
    public Enseigne getEnseigne() {
        return enseigne;
    }

    /**
     * @param enseigne the couleur to set
     */
    private void setEnseigne(Enseigne enseigne) {
        this.enseigne = enseigne;
    }

    /**
     * @return the valeur
     */
    public Valeur getValeur() {
        return valeur;
    }

    /**
     * @param valeur the valeur to set
     */
    private void setValeur(Valeur valeur) {
        this.valeur = valeur;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Carte other = (Carte) obj;
        if (enseigne != other.enseigne)
            return false;
        if (valeur == null) {
            if (other.valeur != null)
                return false;
        } else if (!valeur.equals(other.valeur))
            return false;
        return true;
    }

    public String toString() {
        return valeur.toString() + enseigne.toString();
    }

    public boolean EstNoir() {
        return enseigne.getCouleur() == Couleur.Noir;
    }

    public boolean EstRouge() {
        return enseigne.getCouleur() == Couleur.Rouge;
    }
}