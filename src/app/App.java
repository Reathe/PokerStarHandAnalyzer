package app;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import joueur.Joueur;
import pokershand.PokerSHand;
import pokershand.ReadFile;

public class App {
    /**
     *
     */

    private static final String LIGNE = "---------------------------------------------------------------------------------------------------\n\n";

    public static void main(String[] args) throws Exception {
        File folder = new File("C:\\Users\\bacho\\AppData\\Local\\PokerStars.FR\\HandHistory\\Reathe");

        ArrayList<PokerSHand> PlayedHands = ReadFile.FolderToListHand(folder);
        Joueur j = new Joueur(3);

        Connection DB = getConnection();
        ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
        for (PokerSHand h : PlayedHands) {
            System.out.println(LIGNE + h.toString());
        }
    }

    public static Connection getConnection() throws SQLException {
        String userName = "root";
        String password = "Maman123789";
        String dbms = "mysql";
        String serverName = "localhost";
        String portNumber = "3306";
        String dbName = "PokerDB";
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);

        if (dbms.equals("mysql")) {
            conn = DriverManager.getConnection("jdbc:" + dbms + "://" + serverName + ":" + portNumber + "/" + dbName,
                    connectionProps);
        }
        System.out.println("Connected to database");
        return conn;
    }
}