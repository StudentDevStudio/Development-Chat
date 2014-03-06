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
	//UPD: Все системные файлы хранятся в system_files
	public String getUserFilePath() {
		return "system_files/users.xml";
	}
}
