package logging;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import org.apache.logging.log4j.Level;

/**
 * Данный класс реализует функционал но логированию 
 * событий
 * 
 * @author Almaz
 * https://vk.com/almaz_kg
 */
public class Logger {
	 
	private SimpleDateFormat formatter;
	private static Logger instance;
	private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getRootLogger();
	private Logger() {
	
		
		this.formatter = new SimpleDateFormat("YYYY:MM:dd hh:mm:ss z", Locale.ENGLISH);
		
		
	}

	public void logErrorMessage(String message){
		logger.log(Level.ERROR, "[ERROR] ");
		logger.log(Level.ERROR, this.formatter.format(new Date()));
		logger.log(Level.ERROR,message);

		
		
	}
	public void logInformationMessage(String message){
		logger.log(Level.INFO, "[INFO] ");
		logger.log(Level.INFO, this.formatter.format(new Date()));
		logger.log(Level.INFO, message);
		
	}
	
	private void printMessage(String message){
		logger.log(Level.TRACE, message);
	}
	

	public void close(){
		logger.exit();
	}

	public static Logger getLogger() {
		 if (instance == null) {
	            instance = new Logger();
	        }
	        return instance;
	}
}
