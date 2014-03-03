package server.socketServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import server.AbstractServer;

/**
 * Класс реализующий логику
 * ServerSocket'a сервера.
 * 
 * @author Almaz
 *
 */
public class SocketServer extends AbstractServer{
    private List<ClientThread> clients;
    private String host = "localhost";
    private int port = 81;
    private boolean isActive = false; 

    public SocketServer(){
        this.clients = new ArrayList<ClientThread>();
    }
    public SocketServer(String host, int port){
        this.host = host;
        this.port = port;
        this.clients = new ArrayList<ClientThread>();
    }
    
    @Override
    public void start(){
        this.isActive  = true;
        
        try {
            ServerSocket ss = new ServerSocket(port);
            
            while(this.isActive){
                Socket client = ss.accept();
                ClientThread c = new ClientThread(this, client);
                this.clients.add(c);
                
                update();
                System.out.println("Client " + client.getInetAddress() + " connected");
            }
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Метод, вызывающийся для обновления данных
     * При подключении нового пользователя, необходимо 
     * "предупредить" всех остальных о том, что пришел 
     * новый юзер и т.д.
     */
    private void update() {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void stop() {
        this.isActive = false;
    }
    
    public String getHost() {
        return host;
    }
    public int getPort() {
        return port;
    }
    
    @Override
    public void publishMessage(String message) {
        for (ClientThread client : this.clients) {
            client.publish(message);
        }
    }
}
