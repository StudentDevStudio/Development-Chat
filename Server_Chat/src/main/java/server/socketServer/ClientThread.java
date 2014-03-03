package server.socketServer;

import java.net.Socket;

import server.AbstractServer;

/**
 * Данный класс инкапсулирует данные и 
 * метода для работы с конретным 
 * пользователем.
 * 
 * @author Almaz
 */
public class ClientThread implements Runnable {
    private AbstractServer server;
    private Socket clientSocket;

    public ClientThread(AbstractServer server, Socket client){
        this.server = server;
        this.clientSocket = client;
    }
    
    public void run() {
        // TODO Auto-generated method stub
        
    }

    
    
    public AbstractServer getServer() {
        return server;
    }
    public Socket getClientSocket() {
        return clientSocket;
    }

    public void publish(String message) {
        // TODO Auto-generated method stub
        
    }
}
