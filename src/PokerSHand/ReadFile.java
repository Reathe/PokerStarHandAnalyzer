package pokershand;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import actions.Action;
import actions.Blind;
import actions.Call;
import actions.Check;
import actions.Fold;
import actions.Raise;
import carte.Carte;
import joueur.Joueur;
import main.Main;
import table.Table;

public class ReadFile {

	public static final String MESSAGE = ".*(.*was removed from the table.*|is (dis)?connected.*|.*has timed out.*|.* said, \\\".*\\\"|.* collected .*|.* doesn't show hand.*|.* leaves the table.*|.* joins the table at.*|.*is sitting out.*|.*will be allowed to play.*).*";
	public static final String CARD = "[TJQKA1-9][schd]";
	public static final String MAIN = ".*PokerStars Hand #[0-9]{12}:  .*\\([0-9]+/[0-9]+\\) - [0-9]{4}\\/[0-9]{2}\\/[0-9]{2} [0-9]+:[0-9]+:[0-9]+ .{3} \\[[0-9]{4}\\/[0-9]{2}\\/[0-9]{2} [0-9]+:[0-9]+:[0-9]+ .+\\].*";
	public static final String TABLE = "Table '.*' [0-9]-max \\(.*\\) Seat #[0-9] is the button";
	public static final String JOUEUR = "Seat [0-9]: .* \\([0-9]+ in .*\\).*";
	public static final String BLINDS = ".*: posts (small|big|small & big) blinds? [0-9]+";
	public static final String FLOP = "\\*\\*\\* FLOP \\*\\*\\* \\[" + CARD + " " + CARD + " " + CARD + "\\]";
	public static final String TURN = "\\*\\*\\* TURN \\*\\*\\* \\[" + CARD + " " + CARD + " " + CARD + "\\] \\[" + CARD
			+ "\\]";
	public static final String RIVER = "\\*\\*\\* RIVER \\*\\*\\* \\[" + CARD + " " + CARD + " " + CARD + " " + CARD
			+ "\\] \\[" + CARD + "\\]";
	public static final String SHOWDOWN = "\\*\\*\\* SHOW DOWN \\*\\*\\*";
	public static final String SUMMARY = "\\*\\*\\* SUMMARY \\*\\*\\*";
	public static final String CHECK = ".*: checks ";
	public static final String FOLD = ".*: folds (\\[" + CARD + "\\])?";
	public static final String RAISE = ".*: (bets|raises [0-9]+ to) [0-9]+.*";
	public static final String CALL = ".*: calls [0-9]+.*";
	public static final String UNCALLED_BET = "Uncalled bet \\([0-9]+\\) returned to .*";

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
		String[] lines;
		lines = s.split("\r\n");
		int i = 0;
		boolean match = lines[i].matches(MAIN);
		if (!match)
			throw new IllegalArgumentException("Premiere ligne ne match pas");
		String TableNum = lines[0].split(" ")[2].replaceAll("[#:]", "");
		String[] dateTime = lines[0].split("-")[1].split(" ");
		String date = dateTime[1].replaceAll("/", "-");
		String time = dateTime[2];
		if (dateTime[1].length() == 7)
			time = "0" + time;
		PokerSHand pkh = new PokerSHand(Long.parseLong(TableNum));
		pkh.setD(date + " " + time);
		i++;
		Table t = StringToTable(lines[i]);
		i++;

		while (lines[i].matches(JOUEUR)) {
			try {
				t.addJoueur(StringToJoueur(lines[i]));
			} catch (EspaceDansNomException e) {
				s = s.replaceAll(e.getNom(), e.getReplacement());
				lines = s.split("\r\n");
				try {
					t.addJoueur(StringToJoueur(lines[i]));
				} catch (EspaceDansNomException ex) {
					throw new IllegalArgumentException("Trop d'espaces dans le nom d'un joueur");
				}
			}
			i++;
		}
		pkh.setTable(t);
		i = skipMessages(lines, i, pkh.getTable());
		while (lines[i].matches(BLINDS)) {
			Joueur j = StringToJoueurInPkh(pkh, lines[i]);
			Action a = stringToBlind(lines[i]);
			String[] str = lines[i].split(" ");
			j.addToMise(Integer.parseInt(str[str.length - 1]));
			pkh.addAPreFlop(a, j);
			i++;
			i = skipMessages(lines, i, pkh.getTable());
		}
		i++;
		i = skipMessages(lines, i, pkh.getTable());
		StringSetMainToJoueur(pkh, lines[i]);
		i++;
		i = AddActionsPreFlop(pkh, lines, i);
		i = AddActionsFlop(pkh, lines, i);
		i = AddActionsTurn(pkh, lines, i);
		i = AddActionsRiver(pkh, lines, i);
		i = ToSummary(lines, i);
		// On est à SUMMARY
		i += 2;
		// A board si il y est
		if (lines[i].matches("Board.*")) {
			String[] cartes = lines[i].replaceAll("(Board \\[|\\])", "").split(" ");
			for (String carte : cartes) {
				try {
					pkh.addToBoard(new Carte(carte));
				} catch (Exception e) {
					System.out.println(e.toString());

				}
			}
			i++;
		}
		// Au recap par joueurs
		while (i < lines.length && lines[i].matches("Seat [0-9]:.*")) {
			StringSetMainToJoueurInSummary(pkh, lines[i]);
			i++;
		}
		return pkh;
	}

	/**
	 * Ajoute les actions de @param lines (lecture à partir de @param i )
	 * dans @param pkh au moment @param moment retourne la ligne à laquelle on
	 * s'arrete
	 */
	private static int AddActionsPreFlop(PokerSHand pkh, String[] lines, int i) {
		i = skipMessages(lines, i, pkh.getTable());

		while (!lines[i].matches(FLOP) && !lines[i].matches(SUMMARY)) {
			Joueur j;
			Action a;
			try {
				j = StringToJoueurInPkh(pkh, lines[i]);
				a = stringToAction(lines[i]);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				throw new IllegalArgumentException("PreFlop");
			}
			j.Fais(a);
			pkh.addAPreFlop(a, j);
			i++;
			i = skipMessages(lines, i, pkh.getTable());
		}
		if (!lines[i].matches(SUMMARY)) {
			i++;
		}

		return i;
	}

	private static int AddActionsFlop(PokerSHand pkh, String[] lines, int i) {
		i = skipMessages(lines, i, pkh.getTable());

		while (!lines[i].matches(TURN) && !lines[i].matches(SUMMARY)) {
			Joueur j;
			Action a;
			try {
				j = StringToJoueurInPkh(pkh, lines[i]);
				a = stringToAction(lines[i]);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				throw new IllegalArgumentException("Flop");
			}
			j.Fais(a);
			pkh.addAFlop(a, j);
			i++;
			i = skipMessages(lines, i, pkh.getTable());
		}
		if (!lines[i].matches(SUMMARY))
			i++;
		return i;
	}

	private static int AddActionsTurn(PokerSHand pkh, String[] lines, int i) {
		i = skipMessages(lines, i, pkh.getTable());

		while (!lines[i].matches(RIVER) && !lines[i].matches(SUMMARY)) {
			Joueur j;
			Action a;
			try {
				j = StringToJoueurInPkh(pkh, lines[i]);
				a = stringToAction(lines[i]);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				throw new IllegalArgumentException("Turn");
			}
			j.Fais(a);
			pkh.addATurn(a, j);
			i++;
			i = skipMessages(lines, i, pkh.getTable());
		}
		if (!lines[i].matches(SUMMARY))
			i++;
		return i;
	}

	private static int AddActionsRiver(PokerSHand pkh, String[] lines, int i) {
		i = skipMessages(lines, i, pkh.getTable());

		while (!lines[i].matches(SHOWDOWN) && !lines[i].matches(SUMMARY)) {
			Joueur j;
			Action a;
			try {
				j = StringToJoueurInPkh(pkh, lines[i]);
				a = stringToAction(lines[i]);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				System.out.println("i=" + i + "\nline[i]=\"" + lines[i] + "\"");
				throw new IllegalArgumentException("In River");
			}
			j.Fais(a);
			pkh.addARiver(a, j);
			i++;
			i = skipMessages(lines, i, pkh.getTable());
		}
		if (!lines[i].matches(SUMMARY))
			i++;
		return i;
	}

	/**
	 * 
	 * @param pkh
	 * @param lines
	 * @param i     s sous forme 'Dealt to nomJoueur [CarteEnseigne CarteEnseigne]'
	 *              'Dealt to Reathe [As 9d]'
	 */
	private static void StringSetMainToJoueur(PokerSHand pkh, String s) {
		String[] split = s.split(" ");
		Joueur j = pkh.getTable().getJoueur(split[2]);
		Main m;
		try {
			m = new Main(split[3].substring(1) + " " + split[4].substring(0, 2));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.out.println(s);
			throw e;
		}
		j.setMain(m);
	}

	/**
	 * returns the "position|Action"
	 * 
	 * @param pkh
	 * @param s
	 * @return
	 */
	private static void StringSetMainToJoueurInSummary(PokerSHand pkh, String s) {
		String[] words = s.split(" ");
		int i = 2;
		Joueur j = pkh.getTable().getJoueur(words[i]);
		Main m = null;

		i++;
		// On est à la pos si existe on passe
		if (words[i].charAt(0) == '(') {
			j.setPos(EnleverParentheses(words[i]));
			i++;
			if (words[i].endsWith(")"))
				i++;
			else if (words[i].startsWith("(")) {
				j.setPos(j.getPos() + " & " + EnleverParentheses(words[i]));
				i+=2;
			}
		}
		// On est à l'action
		j.setAction(words[i]);
		if (words[i].equals("collected")) {
			i++;
			j.setGagne(Integer.parseInt(EnleverParentheses(words[i])));
		} else if (words[i].equals("mucked") | words[i].equals("showed")) {
			i++;
			m = new Main(words[i].substring(1) + " " + words[i + 1].substring(0, 2));
			j.setMain(m);
			i += 3;
			if (words[i - 4].equals("showed") && words[i].equals("won")) {
				i++;
				j.setGagne(Integer.parseInt(EnleverParentheses(words[i])));
			}
		}
	}

	public static Action stringToAction(String s) {
		if (s.matches(FOLD)) {
			return new Fold();
		} else if (s.matches(CHECK)) {
			return new Check();
		} else if (s.matches(RAISE)) {
			String[] somme = s.split(" ");
			int byAmmount = Integer.parseInt(somme[2]);
			int toAmmount = byAmmount;
			if (!somme[1].equals("bets"))
				toAmmount = Integer.parseInt(somme[4]);

			return new Raise(byAmmount, toAmmount);
		} else if (s.matches(CALL)) {
			String[] mots = s.split(" ");
			int ammount = Integer.parseInt(mots[2]);
			return new Call(ammount);
		} else
			throw new IllegalArgumentException("Action non valide");
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
		return new Blind(chips);
	}

	public static Joueur StringToJoueur(String s) throws EspaceDansNomException {
		if (!s.matches(JOUEUR))
			throw new IllegalArgumentException("Joueur invalide");
		String[] mots = s.split(" ");
		if (mots.length == 7) {
			throw new EspaceDansNomException("Espace dans le nom", mots[2] + " " + mots[3], mots[2] + mots[3]);
		} else if (mots.length > 7) {
			throw new EspaceDansNomException("Espace dans nom", mots[2] + " " + mots[3] + " " + mots[4],
					mots[2] + mots[3] + mots[4]);
		}
		String nom = mots[2];
		int stack = Integer.parseInt(mots[3].substring(1));
		int seat = Integer.parseInt("" + s.charAt(5));
		return new Joueur(nom, stack, seat);
	}

	private static Joueur StringToJoueurInPkh(PokerSHand pkh, String line) throws IllegalArgumentException {
		if (line.indexOf(':') <= 0)
			throw new IllegalArgumentException("line not valid");
		String name = line.substring(0, line.indexOf(':'));
		Joueur j = pkh.getTable().getJoueur(name);
		return j;
	}

	private static int skipMessages(String[] lines, int i, Table t) {
		while (lines[i].matches(MESSAGE))
			i++;
		if (lines[i].matches(UNCALLED_BET)) {
			String[] line = lines[i].split(" ");
			int ammount = Integer.parseInt(EnleverParentheses(line[2]));
			String name = line[line.length - 1];
			t.getJoueur(name).addToMise(-ammount);
			i = skipMessages(lines, i + 1, t);
		}

		return i;
	}

	public static int ToSummary(String[] lines, int i) {
		while (i < lines.length && !lines[i].matches(SUMMARY))
			i++;
		return i;
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

	private static String EnleverParentheses(String line) {
		return line.replaceAll("(\\(|\\))", "");
	}
}
