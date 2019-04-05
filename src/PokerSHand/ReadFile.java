package PokerSHand;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import Actions.Action;
import Actions.ActionName;
import Joueur.Joueur;
import Main.Main;
import Table.Table;

public class ReadFile {
	public static final String MESSAGE = ".* said, \\\".*\\\"";
	public static final String CARD = "[TJQKA][schd]";
	public static final String MAIN = ".*PokerStars Hand #[0-9]+:  .*\\([0-9]+/[0-9]+\\) - [0-9]{4}\\/[0-9]{2}\\/[0-9]{2} [0-9]+:[0-9]+:[0-9]+ .{3} \\[[0-9]{4}\\/[0-9]{2}\\/[0-9]{2} [0-9]+:[0-9]+:[0-9]+ .+\\].*";
	public static final String TABLE = "Table '.*' [0-9]-max \\(.*\\) Seat #[0-9] is the button";
	public static final String JOUEUR = "Seat [0-9]: .* \\([0-9]+ in .*\\) .*";
	public static final String BLINDS = ".*: posts (small|big|small & big) blinds? [0-9]+";
	public static final String FLOP = "\\*\\*\\* FLOP \\*\\*\\* \\[" + CARD + " " + CARD + " " + CARD + "\\]";
	public static final String TURN = "\\*\\*\\* TURN \\*\\*\\* \\[" + CARD + " " + CARD + " " + CARD + "\\] \\[" + CARD
			+ "\\]";
	public static final String RIVER = "\\*\\*\\* RIVER \\*\\*\\* \\[" + CARD + " " + CARD + " " + CARD + " " + CARD
			+ "\\] \\[" + CARD + "\\]";
	public static final String CHECK = ".*: checks ";
	public static final String FOLD = ".*: folds ";
	public static final String BET = ".*: bets [0-9]+";
	public static final String CALL = ".*: calls [0-9]+";

	// TODO: Finir de read le fichier.
	public static void main(String[] args) throws Exception {
		File folder = new File("C:\\Users\\bacho\\AppData\\Local\\PokerStars.FR\\HandHistory\\Reathe");
		for (PokerSHand h : FolderToListHand(folder)) {
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------\n"
							+ h.toString());
		}
	}

	public static ArrayList<PokerSHand> FolderToListHand(File folder) {
		ArrayList<PokerSHand> hands = new ArrayList<PokerSHand>();

		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory())
				throw new IllegalArgumentException("Il y a un dossier dans le dossier.");

			for (String s : readFile(fileEntry.getAbsolutePath()).split("\r\n\r\n\r\n\r\n")) {
				hands.add(StringToPokerHand(s));
				// s.substring(0, s.indexOf(':'));
			}
		}
		return hands;
	}

	public static PokerSHand StringToPokerHand(String s) {
		PokerSHand pkh = new PokerSHand();
		String[] lines;
		lines = s.split("\r\n");
		int i = 0;
		boolean match = lines[i].matches(MAIN);
		if (!match)
			throw new IllegalArgumentException("Premiere ligne ne match pas");

		i++;
		Table t = StringToTable(lines[i]);
		i++;
		while (lines[i].matches(JOUEUR)) {
			t.addJoueur(StringToJoueur(lines[i]));
			i++;
		}
		pkh.setTable(t);

		while (lines[i].matches(BLINDS)) {
			String name = lines[i].substring(0, lines[i].indexOf(':'));
			Joueur j = pkh.getTable().getJoueur(name);
			Action a = stringToBlind(lines[i]);
			pkh.addAPreFlop(a, j);
			i++;
		}
		i++;
		StringSetMainToJoueur(pkh, lines, i);
		return pkh;
	}

	private static void StringSetMainToJoueur(PokerSHand pkh, String[] lines, int i) {
		String[] split = lines[i].split(" ");
		Joueur j = pkh.getTable().getJoueur(split[2]);
		Main m = new Main(split[3].substring(1) + " " + split[4].substring(0, 2));
		j.setMain(m);
	}

	/**
	 * Ajoute les actions de @param lines (lecture à partir de @param i )
	 * dans @param pkh au moment @param moment retourne la ligne à laquelle on
	 * s'arrete
	 */
	private static int AddActions(PokerSHand pkh, String[] lines, int i, String moment) {
		i = skipMessages(lines, i);

		while (lines[i].matches(moment)) {
			String name = lines[i].substring(0, lines[i].indexOf(':'));
			Joueur j = pkh.getTable().getJoueur(name);
			// Action a = stringtoMoment(lines[i]);
			// pkh.addAPreFlop(a, j);
			i++;
			i = skipMessages(lines, i);
		}

		return i;
	}

	private static int skipMessages(String[] lines, int i) {
		while (lines[i].matches(MESSAGE))
			i++;
		return i;
	}

	public static Table StringToTable(String s) {
		String nom = "";
		int button = 0;

		if (!s.matches(TABLE))
			throw new IllegalArgumentException("Table non valide");

		nom += s.substring(s.indexOf('\'') + 1, s.lastIndexOf('\''));
		button = Integer.parseInt("" + s.charAt(1 + s.indexOf('#')));

		return new Table(nom, button);
	}

	public static Action stringToBlind(String s) {
		String[] strs = s.split(" ");
		int chips = Integer.parseInt(strs[strs.length - 1]);

		return new Action(ActionName.Blinds, chips);
	}

	public static Joueur StringToJoueur(String s) {
		if (!s.matches(JOUEUR))
			throw new IllegalArgumentException("Joueur invalide");
		String[] mots = s.split(" ");
		String nom = mots[2];
		int stack = Integer.parseInt(mots[3].substring(1));
		int seat = Integer.parseInt("" + s.charAt(5));
		return new Joueur(nom, stack, seat);
	}

	public static String readFile(String path) {
		byte[] encoded = new byte[1];
		try {
			encoded = Files.readAllBytes(Paths.get(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(encoded);
	}
}
