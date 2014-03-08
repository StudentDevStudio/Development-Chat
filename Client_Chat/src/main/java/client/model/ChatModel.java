package client.model;

import client.view.ChatView;
import message.*;
import users.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Класс, реализующий логику клиента.
 * В частности, здесь описаны методы 
 * для работы с сервером.
 * 
 * @author Almaz
 *
 */
public class ChatModel {
	private Socket socket;
	private ChatView view;
	private boolean isConnected;
	private boolean isAuthorized;
	private User user;

    private volatile ObjectInputStream inputStream;
	private volatile ObjectOutputStream outputStream;

	private ChatModel(ChatView view, Socket ss) throws IOException{
	    this.view = view;
		this.socket = ss;
		this.inputStream = new ObjectInputStream(ss.getInputStream());
		this.outputStream = new ObjectOutputStream(ss.getOutputStream());
	}

	public void sendMessage(Message message) throws IOException{
		System.out.println("Trying to send message: " + message.getMessage());
		
        synchronized (this.outputStream) {
            try {
                this.outputStream.writeObject(message);
                this.outputStream.flush();
            } catch (IOException e) {
                this.close();
            }
        }
	}
	public boolean authorize(AuthorizationMessage message) throws IOException {
		try {
			this.sendMessage(message);
			Message msg = getResponce();
			if(msg instanceof ErrorMessage){
				return false;
			} else if(msg instanceof UserAuthorize){
				return true;
			}
			
		} catch (ClassNotFoundException e) {
			throw new IOException("Class not found");
		}
		
		return false;
	}
	public Message getResponce() throws ClassNotFoundException, IOException {
		synchronized (this.inputStream) {
			return (Message)inputStream.readObject();
		}
	}

	public static ChatModel connect(String host, int port, ChatView view) throws IOException {
		Socket ss = new Socket(host, port);
		return new ChatModel(view, ss);
	}

	public Socket getSocket() {
		return socket;
	}
	public void close() throws IOException {
		if(this.socket != null && this.socket.isConnected()){
			sendMessage(new CloseConnectionMessage(this.user));
			
			this.inputStream.close();
			this.outputStream.close();
			this.socket.close();
			
			this.setConnected(false);
			this.setAuthorized(false);
		}
	}

	/**
     * Данный метод пингует сервер на наличие изменений в сервере
     * с интервалом в полсекунды.
     * 
     * секунда - для отладочных целей
     * 
     * В будуших версиях интервал пингования можно считывать из
     * конфиг файла
     */    
	public void startServerListening() {
		Thread th = new Thread(new Runnable() {
			int count = 0;
			@Override
			public void run() {
				while (isConnected && isAuthorized) {
					try {
						Message msg = getResponce();
						System.out.println("Iteration " + count++);
						executeMessage(msg);
					} catch (IOException
							| ClassNotFoundException e) {
						view.showErrorMessage(e.getMessage());
					}
				}
			}
		});
		th.start();
	}
    @Deprecated
    /**
     * Не дописана основная логика
     * @param msg
     */
    protected void executeMessage(Message msg) {
        if(msg instanceof PingMessage)
            return;
        if(msg instanceof UserAuthorize)
            this.view.publishMessage("[" + msg.getUser().getLogin() + "] joined!");
        else
            this.view.publishMessage("[" + msg.getUser().getLogin() + "] say: " + msg.getMessage());
    }  

    public boolean isConnected() {
        return isConnected;
    }
    public void setConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }
    public boolean isAuthorized() {
        return isAuthorized;
    }
    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }


}
