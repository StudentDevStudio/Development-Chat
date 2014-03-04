package logging;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Данный класс реализует функционал но логированию 
 * событий
 * 
 * @author Almaz
 * https://vk.com/almaz_kg
 */
public class Logger {
	private StringBuffer sb;
	private File logFile;
	private PrintWriter writer; 
	private SimpleDateFormat formatter;

	public Logger(File logFile) throws IOException{
		this.logFile = logFile;
		if(!logFile.exists())
			logFile.createNewFile();
		this.writer = new PrintWriter(logFile);
		this.formatter = new SimpleDateFormat("YYYY:MM:dd hh:mm:ss z", Locale.ENGLISH);
		
		this.sb = new StringBuffer();
	}

	public void logErrorMessage(String message){
		this.sb.setLength(0);
		sb.append("[ERROR] ");
		sb.append(this.formatter.format(new Date()) + "\n");
		sb.append(message);
		
		printMessage(sb.toString());
	}
	public void logInformationMessage(String message){
		this.sb.setLength(0);
		sb.append("[INFO] ");
		sb.append(this.formatter.format(new Date()));
		sb.append("\n");
		sb.append(message);
		
		printMessage(sb.toString());
	}
	
	private void printMessage(String message){
		this.writer.write(message + "\n");
		this.writer.flush();
	}
	
	
	public File getLogFile() {
		return logFile;
	}
	public void setLogFile(File logFile) {
		this.logFile = logFile;
	}
	
}
