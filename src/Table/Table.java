package Table;

import Joueur.Joueur;

public class Table {

    private String nom;
    private Joueur[] seats = new Joueur[9];
    private int button;

    public Table(String n, int button) {
        setNom(n);
        setButton(button);
    }

    public void addJoueur(int i, Joueur j) {
        seats[i] = j;
    }

    /**
     * @return the seats
     */
    public Joueur[] getSeats() {
        return seats;
    }

    public Joueur getJoueur(int seat) {
        return seats[seat];
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
        int i = 1;
        for (Joueur j : seats) {
            if (j != null) {
                s += "-" + j.toString() + " seat #" + i + "\n";
            }
            i++;
        }
        s += "]";
        return s;
    }

}