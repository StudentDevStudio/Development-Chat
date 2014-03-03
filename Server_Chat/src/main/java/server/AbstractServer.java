package server;

public abstract class AbstractServer {
    public abstract void start();
    public abstract void stop();
    public abstract int getPort();
    public abstract String getHost();
    public abstract void publishMessage(String message);
    
}
