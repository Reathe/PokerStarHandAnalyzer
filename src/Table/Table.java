package Table;

import java.util.ArrayList;

import Joueur.Joueur;

public class Table {
    private ArrayList<Joueur> seats = new ArrayList<Joueur>();

    public void addJoueur(int i, Joueur j) {
        seats.add(i, j);
    }
    
}