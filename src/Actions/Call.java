package actions;

public class Call extends Action {
    private int ammount;

    public Call(int ammount) {
        super();
        setAmmount(ammount);
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
        return "Call " + getAmmount();
    }

    /**
     * @return the ammount
     */
    public int getAmmount() {
        return ammount;
    }

    /**
     * @param ammount the ammount to set
     */
    private void setAmmount(int ammount) {
        if (ammount < 0)
            throw new IllegalArgumentException("Négatif");
        this.ammount = ammount;
    }

    @Override
    public int getValeurMise() {
        return getAmmount();
    }
}