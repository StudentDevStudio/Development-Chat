package utils;

/**
 * Created by IAUglov on 06.03.14.
 */
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
