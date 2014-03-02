package server;

import java.io.File;

import server.utils.ServerUtil;
   
/**
 * Этот класс является запускающим сервер. Для запуска сервера необходимо
 * вызвать метод main(String[] args) этого класса
 * 
 * @author Almaz
 */
   
public class ServerStarter{
    private WebSocketServer server;
    public ServerStarter(){
        
    }
    public ServerStarter(ServerConfiguration configs){
        if(configs == null){
            initializeWithDefaultParams();
        } else
            initialize(configs);
    }
    
    
    private void initialize(ServerConfiguration configs) {
        
    }
    private void initializeWithDefaultParams() {
        
    }
    private void start() {
        server.run();
    }
    
    
    public static void main(String[] args) {
        File conf = new File("server-config.xml");
        ServerConfiguration configs = null;
        if(conf.exists())
            configs = ServerUtil.parseServerConfigs(conf);
        
        ServerStarter server = new ServerStarter(configs);
        
        server.start();    
    }
}