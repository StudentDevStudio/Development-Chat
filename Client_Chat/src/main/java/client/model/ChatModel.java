package client.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import message.AuthorizationMessage;
import message.ErrorMessage;
import message.Message;

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
	private volatile ObjectInputStream inputStream;
	private volatile ObjectOutputStream outputStream;

	private ChatModel(Socket ss) throws IOException{
		this.socket = ss;
		this.inputStream = new ObjectInputStream(ss.getInputStream());
		this.outputStream = new ObjectOutputStream(ss.getOutputStream());
	}
   

	public void sendMessage(Message message) throws IOException {
		synchronized (this.outputStream) {
			this.outputStream.writeObject(message);
		}
	}
	public boolean authorize(AuthorizationMessage message) throws IOException {
		try {
			this.sendMessage(message);
			Message msg = getResponce();
			if(msg instanceof ErrorMessage){
				return false;
			} else{
				if(msg.getMessage().equals("OK")){
					return true;
				}
			}
			
		} catch (ClassNotFoundException e) {
			throw new IOException("Class not found");
		}
		
		return false;
	}
	private Message getResponce() throws ClassNotFoundException, IOException {
		synchronized (this.inputStream) {
			return (Message)inputStream.readObject();
		}
	}



	public static ChatModel connect(String host, int port) throws IOException {
		Socket ss = new Socket(host, port);
		return new ChatModel(ss);
	}

	public Socket getSocket() {
		return socket;
	}

}
