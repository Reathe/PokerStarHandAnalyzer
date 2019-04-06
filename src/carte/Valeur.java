package carte;

public class Valeur {
    public static final int A = 14;
    public static final int K = 13;
    public static final int Q = 12;
    public static final int J = 11;
    public static final int T = 10;
    private int valeur;

    public Valeur(int valeur) {
        setValeur(valeur);
    }

    public Valeur(String valeur) {
        setValeur(valeur);
    }

    /**
     * @return the valeur
     */
    public int getValeur() {
        return valeur;
    }

    /**
     * @param valeur the valeur to set
     */
    private void setValeur(int valeur) {
        if (valeur <= A && valeur >= 1)
            this.valeur = valeur;
        else
            throw new IllegalArgumentException("Valeur inexistante (int)");
    }

    /**
     * @param valeur the valeur to set
     */
    private void setValeur(String valeur) {
        try {
            setValeur(Integer.parseInt(valeur));
        } catch (NumberFormatException e) {
            switch (valeur) {
            case "A":
                setValeur(A);
                break;
            case "K":
                setValeur(K);
                break;
            case "Q":
                setValeur(Q);
                break;
            case "J":
                setValeur(J);
                break;
            case "T":
                setValeur(T);
                break;
            default:
                throw new IllegalArgumentException("Valeur inexistante (string)");
            }
        }
    }

    public boolean gt(Valeur v) {
        return this.valeur > v.valeur;
    }

    public boolean lt(Valeur v) {
        return this.valeur < v.valeur;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Valeur other = (Valeur) obj;
        if (valeur != other.valeur)
            return false;
        return true;
    }

    @Override
    public String toString() {

        switch (valeur) {
        case 14:
            return "A";
        case 13:
            return "K";
        case 12:
            return "Q";
        case 11:
            return "J";
        case 10:
            return "T";
        default:
            return "" + valeur;
        }

    }

}