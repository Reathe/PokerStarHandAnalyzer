package Actions;

public class Action {

    private ActionName action;
    private int ammount;

    public Action(ActionName action, int ammount) {
        setAction(action);
        setAmmount(ammount);
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
    public int getAmmount() {
        if (getAction().equals(ActionName.Blinds) || getAction().equals(ActionName.Call) || getAction().equals(ActionName.Raise))
            return ammount;
        else
            throw new IllegalAccessError(getAction().toString() + " n'a pas de valeur.");
    }

    /**
     * @param ammount the ammount to set
     */
    public void setAmmount(int ammount) {
        if (getAction().equals(ActionName.Blinds) || getAction().equals(ActionName.Raise))
            this.ammount = ammount;
        else
            throw new IllegalAccessError(getAction().toString() + " n'a pas de valeur.");

    }

    /**
     * @param action the action to set
     */
    public void setAction(ActionName action) {
        this.action = action;
    }

}