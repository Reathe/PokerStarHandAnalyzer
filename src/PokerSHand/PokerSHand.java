package PokerSHand;

import java.util.ArrayList;

import Actions.Action;
import Joueur.Joueur;
import Table.Table;

public class PokerSHand {
    // TODO : Fnir les actions
    private Table table;
    ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    ArrayList<Action> actions = new ArrayList<Action>();

    public PokerSHand() {

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

    @Override
    public String toString() {
        return getTable().toString();
    }

}