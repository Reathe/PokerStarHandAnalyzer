package table;

import java.util.Enumeration;
import java.util.Hashtable;

import joueur.Joueur;

public class Table {

    private String nom;
    private Hashtable<String, Joueur> seats = new Hashtable<String, Joueur>();
    private int button;

    public Table(String n, int button) {
        setNom(n);
        setButton(button);
    }

    public void addJoueur(Joueur j) {
        getSeats().put(j.getNom(), j);
    }

    /**
     * @return the seats
     */
    public Hashtable<String, Joueur> getSeats() {
        return seats;
    }

    public Joueur getJoueur(int seat) {
        for (Enumeration<Joueur> e = seats.elements(); e.hasMoreElements();) {
            Joueur j = e.nextElement();
            if (j.getSeat() == seat)
                return j;
        }
        return null;
    }

    public Joueur getJoueur(String name) {
        return getSeats().get(name);
    }

    /**
     * @return the button
     */
    public int getButton() {
        return button;
    }

    /**
     * @param button the button to set
     */
    public void setButton(int button) {
        this.button = button;
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
        this.nom = nom;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Table other = (Table) obj;
        if (button != other.button)
            return false;
        if (nom == null) {
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;
        if (seats == null) {
            if (other.seats != null)
                return false;
        } else if (!seats.equals(other.seats))
            return false;
        return true;
    }

    @Override
    public String toString() {
        String s = "Table[" + getNom() + ",Bouton seat #" + getButton() + "\nJoueurs:\n";

        for (Enumeration<Joueur> e = seats.elements(); e.hasMoreElements();) {
            Joueur j = e.nextElement();
            s += "-" + j.toString() + " seat #" + j.getSeat() + "\n";
        }
        for (Enumeration<Joueur> e = seats.elements(); e.hasMoreElements();) {
            Joueur j = e.nextElement();
            s += "-" + j.toString() + " seat #" + j.getSeat() + "\n";
        }
        s += "]";
        return s;
    }

}