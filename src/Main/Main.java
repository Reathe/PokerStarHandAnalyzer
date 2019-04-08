package main;

import carte.Carte;
import paquet.Paquet;

public class Main {
    private Carte c1, c2;

    public Main(Paquet deck) {
        c1 = new Carte(deck);
        c2 = new Carte(deck);
    }

    public Main(Carte c1, Carte c2) {
        setC1(c1);
        setC2(c2);
    }

    public Main(String s) {
        String[] subs = s.split(" ");
        setC1(new Carte(subs[0]));
        setC2(new Carte(subs[1]));
    }

    /**
     * @return the c1
     */
    public Carte getC1() {
        return c1;
    }

    /**
     * @param c1 the c1 to set
     */
    public void setC1(Carte c1) {
        this.c1 = c1;
    }

    /**
     * @return the second card
     */
    public Carte getC2() {
        return c2;
    }

    /**
     * @param c2 the c2 to set
     */
    public void setC2(Carte c2) {
        this.c2 = c2;
    }

    public boolean isSuited() {
        return getC1().getEnseigne() == getC2().getEnseigne();
    }

    public String toString() {
        return "[" + c1.toString() + " " + c2.toString() + "]";
    }

    public boolean unRouge() {
        return (getC1().EstRouge() || getC2().EstRouge());
    }

    public boolean DeuxRouge(Main this) {
        return (getC1().EstRouge() && getC2().EstRouge());
    }

    public boolean unNoir(Main this) {
        return (getC1().EstNoir() || getC2().EstNoir());
    }

    public boolean DeuxNoir(Main this) {
        return (getC1().EstNoir() && getC2().EstNoir());
    }
}
