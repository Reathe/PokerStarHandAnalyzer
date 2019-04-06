package pokershand;

import java.util.ArrayList;

import actions.Action;
import joueur.Joueur;
import table.Table;

public class PokerSHand {
    private Table table;
    private long num;
    private int pot = 0;
    private ArrayList<Joueur> jPreFlop = new ArrayList<Joueur>();
    private ArrayList<Action> aPreFlop = new ArrayList<Action>();

    private ArrayList<Joueur> jFlop = new ArrayList<Joueur>();
    private ArrayList<Action> aFlop = new ArrayList<Action>();

    private ArrayList<Joueur> jTurn = new ArrayList<Joueur>();
    private ArrayList<Action> aTurn = new ArrayList<Action>();

    private ArrayList<Joueur> jRiver = new ArrayList<Joueur>();
    private ArrayList<Action> aRiver = new ArrayList<Action>();

    public PokerSHand(long num) {
        setNum(num);
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
        String s = "Hand #" + getNum() + "\n" + getTable().toString() + "\n*** PRE-FLOP ***";
        for (int i = 0; i < jPreFlop.size(); i++) {
            s += "\n" + jPreFlop.get(i).toString() + " ";
            s += aPreFlop.get(i).toString();
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

    /**
     * @return the num
     */
    public long getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    private void setNum(long num) {
        this.num = num;
    }

    /**
     * @return the jPreFlop
     */
    public ArrayList<Joueur> getjPreFlop() {
        return jPreFlop;
    }

    /**
     * @param jPreFlop the jPreFlop to set
     */
    public void setjPreFlop(ArrayList<Joueur> jPreFlop) {
        this.jPreFlop = jPreFlop;
    }

    /**
     * @return the aPreFlop
     */
    public ArrayList<Action> getaPreFlop() {
        return aPreFlop;
    }

    /**
     * @param aPreFlop the aPreFlop to set
     */
    public void setaPreFlop(ArrayList<Action> aPreFlop) {
        this.aPreFlop = aPreFlop;
    }

    /**
     * @return the jFlop
     */
    public ArrayList<Joueur> getjFlop() {
        return jFlop;
    }

    /**
     * @param jFlop the jFlop to set
     */
    public void setjFlop(ArrayList<Joueur> jFlop) {
        this.jFlop = jFlop;
    }

    /**
     * @return the aFlop
     */
    public ArrayList<Action> getaFlop() {
        return aFlop;
    }

    /**
     * @param aFlop the aFlop to set
     */
    public void setaFlop(ArrayList<Action> aFlop) {
        this.aFlop = aFlop;
    }

    /**
     * @return the jTurn
     */
    public ArrayList<Joueur> getjTurn() {
        return jTurn;
    }

    /**
     * @param jTurn the jTurn to set
     */
    public void setjTurn(ArrayList<Joueur> jTurn) {
        this.jTurn = jTurn;
    }

    /**
     * @return the aTurn
     */
    public ArrayList<Action> getaTurn() {
        return aTurn;
    }

    /**
     * @param aTurn the aTurn to set
     */
    public void setaTurn(ArrayList<Action> aTurn) {
        this.aTurn = aTurn;
    }

    /**
     * @return the jRiver
     */
    public ArrayList<Joueur> getjRiver() {
        return jRiver;
    }

    /**
     * @param jRiver the jRiver to set
     */
    public void setjRiver(ArrayList<Joueur> jRiver) {
        this.jRiver = jRiver;
    }

    /**
     * @return the aRiver
     */
    public ArrayList<Action> getaRiver() {
        return aRiver;
    }

    /**
     * @param aRiver the aRiver to set
     */
    public void setaRiver(ArrayList<Action> aRiver) {
        this.aRiver = aRiver;
    }

    /**
     * @return the pot
     */
    public int getPot() {
        return pot;
    }

    /**
     * @param pot the pot to set
     */
    public void setPot(int pot) {
        this.pot = pot;
    }
}