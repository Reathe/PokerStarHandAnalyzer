package app;

public class App {

    public static final String MAIN = "^PokerStars Hand #[0-9]+:  .*[(][0-9]+/[0-9]+[)] - [0-9]{4}/[0-9]{2}/[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2} .{3} [[][0-9]{4}/[0-9]{2}/[0-9]{2} [0-9]+:[0-9]+:[0-9]+ .+]$";
	public static final String TABLE = "^Table '.*' [0-9]-max [(].*[)] Seat #[0-9] is the button$";

	public static void main(String[] args) throws Exception {
		StringATable("Table 'Algorab III' 9-max (Play Money) Seat #2 is the button");
	}

	public static void StringATable(String s) {
		if (!s.matches(TABLE))
            throw new IllegalArgumentException("Table non valide");

        System.out.println(s.substring(s.indexOf('\'')+1, s.lastIndexOf('\'')));
        System.out.println(Integer.parseInt("" + s.charAt(1+s.indexOf('#'))));
	}
}