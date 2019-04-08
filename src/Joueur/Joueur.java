package joueur;

import actions.Action;
import carte.Carte;
import main.Main;

public class Joueur {
    private String nom;
    private Main main;
    private int stack;
    private int seat;
    private int mise = 0;
    private int gagne = 0;
    private String pos;
    private String action;

    public Joueur(String n, int stack, int seat) {
        setNom(n);
        setStack(stack);
        setSeat(seat);
    }

    public Joueur(String n, int stack) {
        setNom(n);
        setStack(stack);
    }

    public Joueur(int stack) {
        setNom("Reathe");
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

    /**
     * @return the seat
     */
    public int getSeat() {
        return seat;
    }

    /**
     * @param seat the seat to set
     */
    public void setSeat(int seat) {
        this.seat = seat;
    }

    /**
     * @return the mise
     */
    public int getMise() {
        return mise;
    }

    public void Fais(Action a) {
        setMise(a.getValeurMise() + getMise());
    }

    public void addToMise(int mise) {
        setMise(mise + getMise());
    }

    /**
     * @param mise the mise to set
     */
    private void setMise(int mise) {
        this.mise = mise;
    }

    public String toString() {
        String s = "";
        s += "Joueur[nom=" + getNom() + ",pos = "+getPos()+ ",stack = " + getStack();
        if (main != null)
            s += ",hand=" + getMain().toString();
        s += ",Mise=" + getMise();
        s+= ","+getAction();

        s += "]";
        return s;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((main == null) ? 0 : main.hashCode());
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        result = prime * result + seat;
        result = prime * result + stack;
        return result;
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
        if (main == null) {
            if (other.main != null)
                return false;
        } else if (!main.equals(other.main))
            return false;
        if (nom == null) {
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;
        if (seat != other.seat)
            return false;
        if (stack != other.stack)
            return false;
        return true;
    }

    /**
     * @return the gagne
     */
    public int getGagne() {
        return gagne;
    }

    /**
     * @param gagne the gagne to set
     */
    public void setGagne(int gagne) {
        this.gagne = gagne;
    }

    /**
     * @return the pos
     */
    public String getPos() {
        return pos;
    }

    /**
     * @param pos the pos to set
     */
    public void setPos(String pos) {
        this.pos = pos;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }
}