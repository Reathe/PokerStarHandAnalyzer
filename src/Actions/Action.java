package Actions;

public class Action {

    private ActionName action;
    private int ammount;

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
        if (getAction().equals(ActionName.Raise) || getAction().equals(ActionName.Raise))
            return ammount;
        else
            throw new IllegalAccessError(getAction().toString() + " n'a pas de valeur.");
    }

    /**
     * @param ammount the ammount to set
     */
    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    /**
     * @param action the action to set
     */
    public void setAction(ActionName action) {
        this.action = action;
    }
    
}