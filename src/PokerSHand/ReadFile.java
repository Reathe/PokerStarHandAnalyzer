package PokerSHand;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import Actions.Action;
import Actions.ActionName;
import Joueur.Joueur;
import Table.Table;

public class ReadFile {
	public static final String MAIN = ".*PokerStars Hand #[0-9]+:  .*\\([0-9]+/[0-9]+\\) - [0-9]{4}\\/[0-9]{2}\\/[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2} .{3} \\[[0-9]{4}\\/[0-9]{2}\\/[0-9]{2} [0-9]+:[0-9]+:[0-9]+ .+\\].*";
	public static final String TABLE = "Table '.*' [0-9]-max \\(.*\\) Seat #[0-9] is the button";
	public static final String JOUEUR = "Seat [0-9]: .* \\([0-9]+ in .*\\) .*";
	public static final String BLINDS = ".*: posts (small|big) blind [0-9]+";

	public static void main(String[] args) throws Exception {
		File folder = new File("C:\\Users\\bacho\\AppData\\Local\\PokerStars.FR\\HandHistory\\Reathe");
		for (PokerSHand h : FolderToListHand(folder)) {
			System.out.println(h.toString());
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
		if (match) {
			i++;
			Table t = StringToTable(lines[i]);
			i++;
			while (lines[i].matches(JOUEUR)) {
				t.addJoueur(Integer.parseInt("" + lines[i].charAt(5)), StringToJoueur(lines[i]));
				i++;
			}
			pkh.setTable(t);
		} else
			throw new IllegalArgumentException("Premiere ligne ne match pas");

		return pkh;
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
		return new Joueur(nom, stack);
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
