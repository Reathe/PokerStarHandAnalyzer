package Partie;

import java.util.ArrayList;

import Joueur.Joueur;
import paquet.Paquet;

public class Partie {
    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    private Joueur actuel;
    private Paquet deck;
    private int pot = 0;

    public Partie() {
        deck = new Paquet(52);
    }

    public Partie(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
        deck = new Paquet(52);
    }

    /**
     * @return the joueurs
     */
    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    /**
     * @param joueurs the joueurs to set
     */
    public void setJoueurs(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    /**
     * @return the deck
     */
    public Paquet getDeck() {
        return deck;
    }

    /**
     * @param deck the deck to set
     */
    public void setDeck(Paquet deck) {
        this.deck = deck;
    }

    /**
     * @return the actuel
     */
    public Joueur getActuel() {
        return actuel;
    }

    /**
     * @param actuel the actuel to set
     */
    public void setActuel(Joueur actuel) {
        this.actuel = actuel;
    }

    public void addJoueur(Joueur j) {
        joueurs.add(j);
    }

    public void removeJoueur(Joueur j) {
        joueurs.remove(j);
    }

    /**
     * @return the pot
     */
    public int getPot() {
        return pot;
    }

    public void resetPot() {
        setPot(0);
    }
    public void addToPot(int s) {
        if (s <= 0) {
            throw new IllegalArgumentException();
        }
        setPot(pot+s);
    }
    /**
     * @param pot the pot to set
     */
    private void setPot(int pot) {
        if (pot <= 0) {
            throw new IllegalArgumentException();
        }
        this.pot = pot;
    }
}