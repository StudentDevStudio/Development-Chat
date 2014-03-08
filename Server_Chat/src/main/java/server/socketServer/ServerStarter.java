package server.socketServer;


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
     */
    public void start(){
    	Logger logger = null;
		try {
			
			logger = Logger.getLogger();
			this.ssServer = new SocketServer(logger);
			ssServer.start();
			System.out.println("Socket server startded");
		} catch(IOException e){
			System.out.println(e.getMessage());
		}finally {
			logger.close();
			System.out.println("Log file closed");
		}
	}
    
    public static void main(String[] args) {
        new ServerStarter().start();
    }
}