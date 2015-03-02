package database;

public class Path {

	private static String FIREBIRD_URL = "jdbc:firebirdsql:localhost/3050:";
	private static String FIREBIRD_WINDOWS_PATH = "C:/Users/toram_000/firebird/testdb/test.fdb";
	private static String FIREBIRD_MAC_PATH     = "/Users/loran/firebird/testdb/test.fdb";
	
	public static String getDatabaseURL() {
		String firebirdPath;
		if (System.getProperty("os.name").startsWith("Windows")) {
			firebirdPath = FIREBIRD_WINDOWS_PATH;
		} else {
			firebirdPath = FIREBIRD_MAC_PATH;
		}
		return FIREBIRD_URL + firebirdPath;
	}
}
