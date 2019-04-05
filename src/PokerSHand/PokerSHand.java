package PokerSHand;

import java.util.ArrayList;

import Actions.Action;
import Joueur.Joueur;
import Table.Table;

public class PokerSHand {
    private Table table;

    private ArrayList<Joueur> jPreFlop = new ArrayList<Joueur>();
    private ArrayList<Action> aPreFlop = new ArrayList<Action>();

    private ArrayList<Joueur> jFlop = new ArrayList<Joueur>();
    private ArrayList<Action> aFlop = new ArrayList<Action>();

    private ArrayList<Joueur> jTurn = new ArrayList<Joueur>();
    private ArrayList<Action> aTurn = new ArrayList<Action>();

    private ArrayList<Joueur> jRiver = new ArrayList<Joueur>();
    private ArrayList<Action> aRiver = new ArrayList<Action>();

    public PokerSHand() {

    }

    public ArrayList<Joueur> getJRiver() {
        return jRiver;
    }

    public ArrayList<Action> getARiver() {
        return aRiver;
    }

    public void addARiver(Action a, Joueur j) {
        getARiver().add(a);
        getJRiver().add(j);
    }

    public ArrayList<Joueur> getJTurn() {
        return jTurn;
    }

    public ArrayList<Action> getATurn() {
        return aTurn;
    }

    public void addATurn(Action a, Joueur j) {
        getATurn().add(a);
        getJTurn().add(j);
    }

    public ArrayList<Joueur> getJFlop() {
        return jFlop;
    }

    public ArrayList<Action> getAFlop() {
        return aFlop;
    }

    public void addAFlop(Action a, Joueur j) {
        getAFlop().add(a);
        getJFlop().add(j);
    }

    /**
     * @return the table
     */
    public Table getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(Table table) {
        this.table = table;
    }

    /**
     * @return the joueurs
     */
    public ArrayList<Joueur> getJPreFlop() {
        return jPreFlop;
    }

    /**
     * @return the actions
     */
    public ArrayList<Action> getAPreFlop() {
        return aPreFlop;
    }

    public void addAPreFlop(Action a, Joueur j) {
        getAPreFlop().add(a);
        getJPreFlop().add(j);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((table == null) ? 0 : table.hashCode());
        return result;
    }

    @Override
    public String toString() {
        String s = getTable().toString() + "\n*** PRE-FLOP ***";
        for (int i = 0; i < jPreFlop.size(); i++) {
            s += "\n" + jPreFlop.get(i).toString() + " " + aPreFlop.get(i).toString();
        }
        s += "\n*** FLOP ***\n";
        for (int i = 0; i < jFlop.size(); i++) {
            s += "\n" + jFlop.get(i).toString() + " " + aFlop.get(i).toString();
        }
        s += "\n*** TURN ***\n";
        for (int i = 0; i < jTurn.size(); i++) {
            s += "\n" + jTurn.get(i).toString() + " " + aTurn.get(i).toString();
        }
        s += "\n*** RIVER ***\n";
        for (int i = 0; i < jRiver.size(); i++) {
            s += "\n" + jRiver.get(i).toString() + " " + aRiver.get(i).toString();
        }
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
        PokerSHand other = (PokerSHand) obj;
        if (table == null) {
            if (other.table != null)
                return false;
        } else if (!table.equals(other.table))
            return false;
        return true;
    }
}