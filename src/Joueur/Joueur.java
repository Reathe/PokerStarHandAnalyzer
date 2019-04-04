package Joueur;

import Main.Main;
import carte.Carte;

public class Joueur {
    private String nom;
    private Main main;
    private int stack;

    public Joueur(String n, int stack) {
        setNom(n);
        setStack(stack);
    }

    public Joueur(int stack) {
        setNom("John DOE");
        setStack(stack);
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        if (nom == null)
            throw new IllegalArgumentException("Nom null");
        if (nom.equals(""))
            throw new IllegalArgumentException("Nom vide...");
        this.nom = nom;
    }

    public String toString() {
        String s = "";
        s += "Joueur[nom=" + getNom() + "Stack = " + getStack()+ "]";
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Joueur other = (Joueur) obj;
        if (nom == null) {
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;
        return true;
    }

    /**
     * @return the main
     */
    public Main getMain() {
        return main;
    }

    /**
     * @param main the main to set
     */
    public void setMain(Main main) {
        this.main = main;
    }

    public void setMain(Carte c1, Carte c2) {
        this.main = new Main(c1, c2);
    }

    /**
     * @return the stack
     */
    public int getStack() {
        return stack;
    }

    /**
     * @param stack the stack to set
     */
    private void setStack(int stack) {
        if (stack < 0)
            throw new IllegalArgumentException("Stack négative...");
        this.stack = stack;
    }

    public void addToStack(int s) {
        setStack(stack + s);
    }

    public void subToStack(int s) {
        setStack(stack - s);
    }
}