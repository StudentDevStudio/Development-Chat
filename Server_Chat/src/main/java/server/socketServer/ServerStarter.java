package server.socketServer;

import java.io.File;
import java.io.IOException;

import logging.Logger;
   
/**
 * Этот класс является запускающим сервер. Для запуска сервера необходимо
 * вызвать метод main(String[] args) этого класса
 * 
 * @author Almaz
 * https://vk.com/almaz_kg
 */
   
public class ServerStarter{
    private SocketServer ssServer;
    
    /**
     * Пока выпилена WebSocketServer-ная часть, поскольку его продвижение 
     * остановилось
     * 
     */
    public ServerStarter(){
		try {
			File logfile = new File("SocketServer.log");
			Logger log = new Logger(logfile);
			this.ssServer = new SocketServer(log);
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }
    
    public void start(){
        ssServer.start();
    }
    
    public static void main(String[] args) {
        ServerStarter servers = new ServerStarter();
        servers.start();    
    }
}