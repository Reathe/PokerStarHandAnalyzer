package carte;

public enum Enseigne {
    Pique, Trefle, Carreau, Coeur;
    public enum Couleur {
        Rouge, Noir;
    }

    public static final Enseigne[] lesEnseignes = new Enseigne[] { Enseigne.Pique, Enseigne.Trefle, Enseigne.Carreau,
            Enseigne.Coeur };

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
        return super.toString();
    }

    public boolean equals(Enseigne e) {
        return this == e;
    }
}