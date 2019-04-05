package Actions;

public class Action {

    private ActionName action;
    private int amount;

    public Action(ActionName action, int amount) {
        setAction(action);
        setAmmount(amount);
    }

    /**
     * @return the action
     */
    public ActionName getAction() {
        return action;
    }

    /**
     * @return the ammount
     */
    public int getAmount() {
        if (hasAmount())
            return amount;
        else
            throw new IllegalAccessError(getAction().toString() + " n'a pas de valeur.");
    }

    private boolean hasAmount() {
        return getAction().equals(ActionName.Blinds) || getAction().equals(ActionName.Call)
                || getAction().equals(ActionName.Raise);
    }

    /**
     * @param amount the ammount to set
     */
    public void setAmmount(int amount) {
        if (hasAmount())
            this.amount = amount;
        else
            throw new IllegalAccessError(getAction().toString() + " n'a pas de valeur.");

    }

    /**
     * @param action the action to set
     */
    public void setAction(ActionName action) {
        this.action = action;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((action == null) ? 0 : action.hashCode());
        result = prime * result + amount;
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
        Action other = (Action) obj;
        if (action != other.action)
            return false;
        if (amount != other.amount)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return getAction().toString() + " " + getAmount();
    }

}