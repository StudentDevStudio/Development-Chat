package server.socketServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

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
   
    public void send(Message message) throws IOException{
    	this.outputStream.writeObject(message);
    	this.outputStream.flush();
    }
    public void send(List<Message> messages) throws IOException{
    	this.outputStream.writeObject(messages);
    	this.outputStream.flush();
    }
    public void run() {
    	System.out.println("Client thread started");
    	// Пока не выключили
    	while(this.isAlive){
			try {
				// Читаем Message из потока
				
				// - Ошибка - че за нах! Откуда здесь берется String?
				Object o = inputStream.readObject();
				if(o instanceof String){
					System.out.println((String)o);
				}
				Message message = (Message) o; 
				System.out.println("Get a message: " + message.getMessage());
				// Что за сообщение пришло?!
				if (message instanceof AuthorizationMessage) {
					authorizeUser((AuthorizationMessage)message);
				} else if (message instanceof RegistrationMessage) {
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
		User res = this.server.autorizeUser(message.getLogin(), message.getPass());
    	if(res != null){
    		this.user = res;
    		
    		// Передаем клиенту - авторизационное сообщение 
			Message msg = new Message("OK");
			this.send(msg);
			
			// Инициализируемся
			initialize();
			System.out.println(this.user.getLogin() + " authorized");
		} else {
			// Отправляем сообщение о том что авторизация не удалась
			this.send(new ErrorMessage("Authorization failed"));
		}
	}
    private void registerNewUser(RegistrationMessage message) throws IOException {
		User user = new User(message.getLogin(), message.getPass());
		if(this.server.registerNewUser(user)){
			this.user = user;
			// Передаем юзеру что регистрация успешно прошла
			this.send(new Message("Registration successfull"));
			
			initialize();
		} else{
			this.send(new ErrorMessage("Registration failed"));
		}
	}
	private void initialize() throws IOException {
		this.isAuthorized = true;
		// Отправляем новичку историю чата
		//this.send(server.getChatHistory());
		
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










































