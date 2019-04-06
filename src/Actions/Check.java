package actions;

public class Check extends Action {
    public Check() {
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
        return "Check";
    }

    @Override
    public int getValeurMise() {
        return 0;
    }
}