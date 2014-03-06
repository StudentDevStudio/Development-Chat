package utils;

public class Configurator {
	private static Configurator instance = null;

	private Configurator() {
	}

	public static Configurator getInstance() {
		if (instance == null) {
			instance = new Configurator();
		}
		return instance;
	}

	public String getUserFilePath() {
		return "users.xml";
	}
}
