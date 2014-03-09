package logging;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Данный класс реализует функционал но логированию событий
 *
 * @author Almaz https://vk.com/almaz_kg
 */
public class Logger {

    private static final org.apache.log4j.Logger logger = LogManager.getRootLogger();
    private SimpleDateFormat formatter;

    public Logger() {
        formatter = new SimpleDateFormat("YYYY.MM.dd hh.mm.ss z", Locale.ENGLISH);
        System.setProperty("log4j.time", this.formatter.format(new Date()));
        PropertyConfigurator.configure("log4j.properties");

    }

    public void logErrorMessage(String message) {
        logger.log(Level.ERROR, "[ERROR] ");
        logger.log(Level.ERROR, this.formatter.format(new Date()));
        logger.log(Level.ERROR, message);
    }

    public void logInformationMessage(String message) {
        logger.log(Level.INFO, "[INFO] ");
        logger.log(Level.INFO, this.formatter.format(new Date()));
        logger.log(Level.INFO, message);
    }

    public void close() {
        LogManager.shutdown();
    }
}
