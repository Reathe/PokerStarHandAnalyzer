package Partie;

public abstract class Action {
    public enum ActionName {
        Fold, Call, Raise,Check,Default;
    }

    private ActionName action;

    /**
     * @return the action
     */
    public ActionName getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(ActionName action) {
        this.action = action;
    }
    
}