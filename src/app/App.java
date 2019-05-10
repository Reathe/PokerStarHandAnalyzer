package app;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import joueur.Joueur;
import pokershand.PokerSHand;
import pokershand.ReadFile;

public class App {
    /**
     *
     */

    // private static final String LIGNE =
    // "---------------------------------------------------------------------------------------------------\n\n";

    public static void main(String[] args) throws Exception {
        File folder = new File("C:\\Users\\bacho\\AppData\\Local\\PokerStars.FR\\HandHistory\\Reathe");

        ArrayList<PokerSHand> PlayedHands = ReadFile.FolderToListHand(folder);

        Connection DataBase = getConnection();
        Statement statement = DataBase.createStatement();

        Hashtable<String, Joueur> joueurs = GetPlayersIn(PlayedHands);
        InsertHandsInDB(PlayedHands, statement);
        InsertPlayersIntoDB(joueurs, statement);
        InsertJouerForPlayedHands(PlayedHands, statement);
        // PrintNomInJoueur(statement);
        DataBase.close();
    }

    private static void InsertHandsInDB(ArrayList<PokerSHand> PlayedHands, Statement statement) {
        for (PokerSHand hand : PlayedHands) {
            InsertPokerHand(hand, statement);
        }
    }

    public static long ResetTime() {
        long startTime;
        startTime = System.nanoTime();
        return startTime;
    }

    public static void PrintTimeSince(long startTime, String ToDoMethod) {
        System.out.printf("%.1f ms" + ToDoMethod, (System.nanoTime() - startTime) / 1000000.0);
    }

    public static void PrintNomInJoueur(Statement statement) throws SQLException {
        ResultSet res = statement.executeQuery("SELECT nom FROM Joueur");
        while (res.next()) {
            System.out.println(res.getString("nom"));
        }
    }

    /**
     * Insert each joueur of joueurs into the database using statement
     * 
     * @param joueurs   the joueurs to insert
     * @param statement the statement to use
     */
    private static void InsertPlayersIntoDB(Hashtable<String, Joueur> joueurs, Statement statement) {
        int errcode;
        String j;
        String command;
        /**
         * For each key (names) in joueurs Inserts it in the db using the statement
         */
        for (Enumeration<Joueur> e = joueurs.elements(); e.hasMoreElements();) {
            // to stop errors caused by ' in names
            j = e.nextElement().getNom().replaceAll("'", "\\\\'");

            command = "INSERT IGNORE INTO `Joueur` (nom) " + "VALUES ('" + j + "')";
            try {
                statement.executeUpdate(command);
            } catch (SQLException ex) {
                errcode = ex.getErrorCode();
                System.out.println(ex.getMessage() + "\n" + command);
                // If the error is not duplicate key error
                if (errcode != 1062) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    /**
     * @param PlayedHands
     * @return players who played in any of the PlayedHands
     */
    private static Hashtable<String, Joueur> GetPlayersIn(ArrayList<PokerSHand> PlayedHands) {
        Hashtable<String, Joueur> joueurs = new Hashtable<String, Joueur>();
        for (PokerSHand h : PlayedHands) {
            // System.out.println(LIGNE + h.toString());
            joueurs.putAll(h.getTable().getSeats());
        }
        return joueurs;
    }

    private static void InsertPokerHand(PokerSHand hand, Statement statement) {
        String pokershandNum = "" + hand.getNum();
        String cartes = hand.boardToString();
        String date = hand.getD();
        String command = "INSERT IGNORE INTO `PokerSHand` (num,CartesTable,LaDate) " + "VALUES ('" + pokershandNum + "','"
                + cartes + "','"+ date + "')";

        try {
            statement.executeUpdate(command);
        } catch (Exception e) {
            int errcode = ((SQLException) e).getErrorCode();
            System.out.println(e.getMessage() + "\n" + command);
            // If the error is not duplicate key error
            if (errcode != 1062) {
                throw new IllegalArgumentException();
            }
        }
    }

    private static void InsertJouerForPlayedHands(ArrayList<PokerSHand> PlayedHands, Statement statement) {
        for (PokerSHand hand : PlayedHands) {

            InsertJouerForHand(hand, statement);
        }
    }

    private static void InsertJouerForHand(PokerSHand pkh, Statement statement) {
        for (Enumeration<Joueur> e = pkh.getTable().getSeats().elements(); e.hasMoreElements();) {
            Joueur j = e.nextElement();
            InsertJouerInDB(j, pkh.getNum(), statement);
        }
    }

    private static void InsertJouerInDB(Joueur j, long pokershandNum, Statement statement) {
        String nom = j.getNom().replaceAll("'", "\\\\'");
        int mise = j.getMise();
        String main = "NULL";
        if (j.getMain() != null)
            main = "'" + j.getMain().toString() + "'";

        String pos = "NULL";
        if (j.getPos() != null)
            pos = "'" + j.getPos() + "'";
        j.getPos();

        String action = j.getAction();
        int gagne = j.getGagne();
        String command = "INSERT IGNORE INTO `Jouer` (PokerSHand_num,Joueur_nom,Mise,Main,Pos,Action,Gagne) "
                + "VALUES ('" + pokershandNum + "','" + nom + "','" + mise + "'," + main + "," + pos + ",'" + action
                + "','" + gagne + "')";

        try {
            statement.executeUpdate(command);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * returns the connextion to the PokerDB
     * 
     * @return
     * @throws SQLException
     */
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

        conn = DriverManager.getConnection("jdbc:" + dbms + "://" + serverName + ":" + portNumber + "/" + dbName + "?serverTimezone=UTC",
                connectionProps);

        return conn;
    }
}