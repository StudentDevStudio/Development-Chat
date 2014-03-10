package server.socketServer;


import java.io.IOException;
import java.util.Scanner;

import logging.Logger;

/**
 * Этот класс является запускающим сервер. Для запуска сервера необходимо вызвать метод main(String[] args) этого
 * класса
 *
 * @author Almaz
 */

public class ServerStarter {
    private SocketServer socketServer;
    private Logger       logger;

    public ServerStarter(Logger logger) {
        this.logger = logger;
    }
    
    public void start() {
        try {
            socketServer = new SocketServer(logger);
            socketServer.start();
            System.out.println("Socket server startded");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } 
    }

    public static void main(String[] args) {
        Logger logger = new Logger();
        Scanner sc = new Scanner(System.in);
        ServerStarter ss = new ServerStarter(logger);
        
        ss.start();
        
        String command;
        do{
            command = sc.nextLine().toLowerCase();
            System.out.println("Command: " + command);
        } while(!"stop".equals(command));
        
        ss.stop();
        sc.close();
    }

    private void stop() {
        this.socketServer.stop();
        System.out.println("Socket server stoped");
        this.logger.logInformationMessage("Server stoped");
        this.logger.close();
    }
}
