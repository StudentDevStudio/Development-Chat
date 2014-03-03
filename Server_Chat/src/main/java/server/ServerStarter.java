package server;

import server.socketServer.SocketServer;
import server.webSocketServer.WebSocketServer;
   
/**
 * Этот класс является запускающим сервер. Для запуска сервера необходимо
 * вызвать метод main(String[] args) этого класса
 * 
 * @author Almaz
 */
   
public class ServerStarter{
    private WebSocketServer wsServer;
    private SocketServer ssServer;
    
    public ServerStarter(){
        this.wsServer =  new WebSocketServer();
        this.ssServer = new SocketServer();
    }
    
    public void start(){
        wsServer.start();
        ssServer.start();
    }
    
    public static void main(String[] args) {
        ServerStarter servers = new ServerStarter();
        servers.start();    
    }
}