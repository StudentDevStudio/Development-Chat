package server.socketServer;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import logging.Logger;
import message.Message;
import message.RegistrationMessage;
import users.User;
import utils.Configurator;
import utils.Util;
import utils.XmlWorker;

/**
 * Класс реализующий логику ServerSocket'a сервера.
 * 
 * @author Almaz https://vk.com/almaz_kg
 * 
 */
public class SocketServer {
	private int port = 81;
	private boolean isActive = false;
	private Logger logger;
	private volatile List<Message> chatHistory;
	private volatile List<ClientThread> activeUsers;
	private volatile List<ClientThread> authorizedUsers;
	private volatile List<User> allUsers;
	private XmlWorker worker = new XmlWorker();

	public SocketServer(Logger log) throws IOException {
		this.logger = log;

		init();

		this.chatHistory.add(new Message("Hello world"));
		Util.saveChatHistory(new File("system_files/history.obj"),
				this.chatHistory);
	}

	public SocketServer(int port, Logger log) throws IOException {
		this.port = port;
		this.logger = log;
		init();
	}

	public void start() {
		this.logger.logInformationMessage("Socket server started");
		this.logger.logInformationMessage("Port: " + this.port);
		System.out.println("Port: " + this.port);
		this.isActive = true;

		try {
			ServerSocket ss = new ServerSocket(port);

			while (this.isActive) {
				Socket client = ss.accept();

				System.out.println("New client");

				ClientThread c = new ClientThread(this, client);
				Thread th = new Thread(c);
				th.start();
				synchronized (this.activeUsers) {
					this.activeUsers.add(c);
				}

				logger.logInformationMessage("Client "
						+ client.getInetAddress() + " connected");
			}
			ss.close();
		} catch (IOException e) {
			this.logger.logErrorMessage(e.getMessage());
		}
	}

	public void stop() {
		// Save all users
		worker.setUserData(allUsers);
		try {
			worker.save();
		} catch (JAXBException e) {
			logger.logErrorMessage(e.toString());
		}
		this.isActive = false;
		this.logger.logInformationMessage("Socket server stoped");
	}

	private void init() throws IOException {
		this.activeUsers = new ArrayList<>();
		this.authorizedUsers = new ArrayList<>();
		try {
			this.allUsers = worker.load().getUsers();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			allUsers = new ArrayList<>();
			logger.logErrorMessage(e.toString());
		}
		this.chatHistory = Util.getChatHistory(new File("system_files/history.obj"));
	}

	public List<Message> getChatHistory() {
		return this.chatHistory;
	}

	public List<ClientThread> getActiveUsers() {
		return this.activeUsers;
	}

	public List<User> getAllUsers() {
		return allUsers;
	}

	public List<ClientThread> getAuthorizedUsers() {
		return authorizedUsers;
	}

	public void sendToActiveUsers(Message message) {
		synchronized (this.chatHistory) {
			this.chatHistory.add(message);
		}
		synchronized (this.authorizedUsers) {
			for (ClientThread client : this.authorizedUsers) {
				try {
					client.send(message);
				} catch (IOException e) {
					this.logger.logErrorMessage("User "
							+ client.getUser().getLogin() + " have a problem\n"
							+ e.getMessage());
				}
			}
		}
	}

	public void addAuthorizedUser(ClientThread client) {
		synchronized (this.authorizedUsers) {
			this.authorizedUsers.add(client);
		}
	}

	public User autorizeUser(String login, String pass) {
		System.out.println(allUsers.size());
		synchronized (this.allUsers) {
			for (User user : this.allUsers) {
				if (user.authorize(login, pass))
					return user;
			}
		}
		return null;
	}

	public boolean registerNewUser(RegistrationMessage message) {
		User user = new User(message.getLogin(), message.getPass());

		/**
		 * Простая валидация. Проверка на существование другого пользователя с
		 * таким же логином
		 */
		synchronized (this.allUsers) {
			for (User oldUser : this.allUsers) {
				if (oldUser.getLogin().equals(user.getLogin())) {
					return false;
				}
			}

			this.allUsers.add(user);
			return true;
		}
	}

	public int getPort() {
		return port;
	}

	public void setLogger(Logger log) {
		this.logger = log;
	}

	public Logger getLogger() {
		return this.logger;
	}

}
