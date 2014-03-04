package server.socketServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import message.AuthorizationMessage;
import message.ErrorMessage;
import message.Message;
import message.RegistrationMessage;
import users.User;

/**
 * Данный класс инкапсулирует данные и 
 * метода для работы с конретным 
 * пользователем.
 * 
 * @author Almaz
 * https://vk.com/almaz_kg
 */
public class ClientThread implements Runnable {
	private SocketServer server;
    private Socket clientSocket;
    private User user;
    private boolean isAuthorized;
    private boolean isAlive;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    
    public ClientThread(SocketServer server, Socket client) throws IOException{
        this.server = server;
        this.clientSocket = client;
		this.outputStream = new ObjectOutputStream(client.getOutputStream());
        this.inputStream = new ObjectInputStream(client.getInputStream());
		this.isAlive = true;
    }
   
    public void publish(Message message) throws IOException{
    	this.outputStream.writeObject(message);
    }
    public void run() {
    	// Пока не выключили
    	while(this.isAlive){
			try {
				// Читаем Message из потока
				Message message = (Message) inputStream.readObject();
				System.out.println("Get a message: " + message.getMessage());
				// Что за сообщение пришло?!
				if (message instanceof AuthorizationMessage) {
					authorizeUser((AuthorizationMessage)message);
				} if (message instanceof RegistrationMessage) {
					registerNewUser((RegistrationMessage)message);
				} else {
					if (this.isAuthorized) {
						this.server.sendToActiveUsers(message);
					} 
				}
			} catch (IOException | ClassNotFoundException e) {
				this.server.getLogger().logErrorMessage(
						"Client " + clientSocket.getInetAddress()
								+ " has a problem: " + e.getMessage());
			}
    	}
	}

    private void authorizeUser(AuthorizationMessage message) throws IOException {
		if(this.server.autorizeUser(message.getLogin(), message.getPass())){
			initialize();
		} else {
			// Отправляем сообщение о том что авторизация не удалась
			this.outputStream.writeObject(new ErrorMessage("Authorization failed"));
		}
	}
	private void registerNewUser(RegistrationMessage message) throws IOException {
		if(this.server.registerNewUser(message)){
			// Передаем юзеру что регистрация успешно прошла
			this.outputStream.writeObject(new Message("Registration successfull"));
			
			initialize();
		} else{
			this.outputStream.writeObject(new ErrorMessage("Registration failed"));
		}
	}
	private void initialize() throws IOException {
		this.isAuthorized = true;
		// Отправляем новичку историю чата
		this.outputStream.writeObject(server.getChatHistory());
		
		// И сообщаем всем клиентам, что подключился новый пользователь
		server.sendToActiveUsers(new Message("The user "
				+ this.user.getLogin() + " has been connect"));
		
		// Добавляем к списку пользователей - нового пользователя
		this.server.addAuthorizedUser(this);
	}
	
	
	public SocketServer getServer() {
        return server;
    }
    public Socket getClientSocket() {
        return clientSocket;
    }
 	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public User getUser(){
		return this.user;
	}

}










































