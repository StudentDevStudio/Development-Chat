package server.socketServer;


import logging.Logger;

import java.io.IOException;

/**
 * Этот класс является запускающим сервер. Для запуска сервера необходимо вызвать метод main(String[] args) этого
 * класса
 *
 * @author Almaz https://vk.com/almaz_kg
 */

public class ServerStarter {
    private SocketServer socketServer;
    private Logger       logger;

    public ServerStarter(Logger logger) {
        this.logger = logger;
    }

    /**
     * Пока выпилена WebSocketServer-ная часть, поскольку его продвижение остановилось
     */
    public void start() {
        try {
            socketServer = new SocketServer(logger);
            socketServer.start();
            System.out.println("Socket server startded");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            logger.close();
            System.out.println("Log file closed");
        }
    }

    public static void main(String[] args) {
        Logger logger = new Logger();
        ServerStarter serverStarter = new ServerStarter(logger);
        serverStarter.start();
    }
}
