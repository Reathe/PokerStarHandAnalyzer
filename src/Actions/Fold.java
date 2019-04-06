package actions;

public class Fold extends Action {
    public Fold() {
        super();
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
        return "Fold";
    }

    @Override
    public int getValeurMise() {
        return 0;
    }
}