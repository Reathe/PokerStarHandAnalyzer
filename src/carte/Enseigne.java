package carte;

public enum Enseigne {
    Pique, Trefle, Carreau, Coeur;
    public enum Couleur {
        Rouge, Noir;
    }

    public static final Enseigne[] lesEnseignes = new Enseigne[] { Pique, Trefle, Carreau, Coeur };

    private Couleur couleur;
    static {
        Pique.couleur = Couleur.Noir;
        Trefle.couleur = Couleur.Noir;
        Carreau.couleur = Couleur.Rouge;
        Coeur.couleur = Couleur.Rouge;
    }

    public Couleur getCouleur() {
        return this.couleur;
    }

    @Override
    public String toString() {
        switch (this){
            case Pique:
            return "s";
            case Coeur:
            return "h";
            case Carreau:
            return "d";
            case Trefle:
            return "c";
            default:
            return "";
        }
    }

    public boolean equals(Enseigne e) {
        return this == e;
    }
}