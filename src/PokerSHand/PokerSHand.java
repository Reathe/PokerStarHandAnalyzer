package PokerSHand;

import Table.Table;

public class PokerSHand {

    private Table table;

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