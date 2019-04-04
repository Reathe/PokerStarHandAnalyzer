package paquet;

import java.util.ArrayList;
import carte.Carte;
import carte.Enseigne;
import carte.Valeur;

public class Paquet {
    private ArrayList<Carte> jeu = new ArrayList<Carte>();

    public Paquet() {

    }

    // 32 ou 52 cartes
    public Paquet(int taille) {
        if (taille == 52) {
            for (Enseigne e : (Enseigne.lesEnseignes)) {
                for (int i = 2; i < 15; i++) {
                    addCarte(new Carte(e, new Valeur(i)));
                }
            }
        } else if (taille == 32) {
            for (Enseigne e : (Enseigne.lesEnseignes)) {
                for (int i = 7; i < 15; i++) {
                    addCarte(new Carte(e, new Valeur(i)));
                }
            }
        }

    }

    public ArrayList<Carte> getJeu() {
        return jeu;
    }

    public void setJeu(ArrayList<Carte> jeu) {
        this.jeu = jeu;
    }

    public void addCarte(Carte c) {
        jeu.add(c);
    }

    public void removeCarte(Carte c) {
        jeu.remove(c);
    }

    public Carte piocher() {
        int alea = (int) (Math.random() * jeu.size());
        Carte tiree = jeu.get(alea);
        removeCarte(tiree);
        return tiree;
    }
    public Carte regarderCarte() {
        int alea = (int) (Math.random() * jeu.size());
        Carte tiree = jeu.get(alea);
        return tiree;
    }

}