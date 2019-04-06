package pokershand;

public class EspaceDansNomException extends Exception {
    private static final long serialVersionUID = 1L;
    private String nom, replacement;

    public EspaceDansNomException(String msg, String nom, String replacement) {
        super(msg);
        setNom(nom);
        setReplacement(replacement);
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    private void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the replacement
     */
    public String getReplacement() {
        return replacement;
    }

    /**
     * @param replacement the replacement to set
     */
    private void setReplacement(String replacement) {
        this.replacement = replacement;
    }
}