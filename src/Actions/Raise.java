package actions;

public class Raise extends Action {
    private int byAmmount, toAmmount;

    public Raise(int byAmmount, int toAmmount) {
        super();
        setByAmmount(byAmmount);
        setToAmmount(toAmmount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Raise " + getByAmmount() + " to " + getToAmmount();
    }

    /**
     * @return the ammount
     */
    public int getByAmmount() {
        return byAmmount;
    }

    /**
     * @param ammount the ammount to set
     */
    private void setByAmmount(int ammount) {
        if (ammount < 0)
            throw new IllegalArgumentException("Négatif");
        this.byAmmount = ammount;
    }

    /**
     * @return the toAmmount
     */
    public int getToAmmount() {
        return toAmmount;
    }

    /**
     * @param toAmmount the toAmmount to set
     */
    private void setToAmmount(int toAmmount) {
        this.toAmmount = toAmmount;
    }

    @Override
    public int getValeurMise() {
        return getByAmmount();
    }
}