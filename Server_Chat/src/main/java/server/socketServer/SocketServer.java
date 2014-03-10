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
import message.PingMessage;
import users.User;
import utils.Configurator;
import utils.Util;
import utils.XmlWorker;

/**
 * Класс реализующий логику ServerSocket'a сервера.
 *
 * @author Almaz
 */
@SuppressWarnings("restriction")
public class SocketServer implements Runnable {
    public static final int DEFAULT_PORT = 81;

    private int port;
    private boolean isActive = false;
    private          Logger             logger;
    private volatile List<Message>      chatHistory;
    private volatile List<ClientThread> activeUsers;
    private volatile List<ClientThread> authorizedUsers;
    // Похорошему - надо бы здесь использовать что-то типо мапы!
    private volatile List<User>         allUsers;
    private XmlWorker worker;

    public SocketServer(Logger logger) throws IOException {
        this(DEFAULT_PORT, logger);
    }

    public SocketServer(int port, Logger logger) throws IOException {
        this.port = port;
        this.logger = logger;
        
        isActive = false;
        final Configurator config = new Configurator();
        worker = new XmlWorker(config);
        
        activeUsers = new ArrayList<ClientThread>();
        authorizedUsers = new ArrayList<ClientThread>();
        try {
            allUsers = worker.load().getUsers();
        } catch (JAXBException e) {
            allUsers = new ArrayList<User>();
            logger.logErrorMessage(e.toString());
        }

        chatHistory = Util.getChatHistory(new File("system_files/history.obj"));

        Thread pinger = new Thread(
                new Runnable() {
                    public void run() {
                        while (isActive) {
                            try {
                                Thread.sleep(15000);
                                sendToActiveUsers(new PingMessage(null));
                            } catch (InterruptedException ignored) {
                            }
                        }
                    }
                }
        );
        pinger.setDaemon(true);
        pinger.start();
    }

    public void run() {
        logger.logInformationMessage("Socket server started");
        logger.logInformationMessage("Port: " + port);
        System.out.println("Socket server started");
        System.out.println("Port: " + port);
        isActive = true;

        try {
            ServerSocket ss = new ServerSocket(port);

            while (isActive) {
                Socket client = ss.accept();

                System.out.println("New client");

                ClientThread c = new ClientThread(this, client);
                Thread th = new Thread(c);
                th.start();
                synchronized (activeUsers) {
                    activeUsers.add(c);
                }

                logger.logInformationMessage(
                        "Client "
                        + client.getInetAddress() + " connected"
                );
            }
            ss.close();
        } catch (IOException e) {
            logger.logErrorMessage(e.getMessage());
        }
    }
    public void start(){
        Thread th = new Thread(this);
        th.setDaemon(true);
        th.start();
    }
    public void stop() {
        // Save all users
        worker.setUserData(allUsers);
        try {
            worker.save();
        } catch (JAXBException e) {
            logger.logErrorMessage(e.toString());
        }
        isActive = false;
        logger.logInformationMessage("Socket server stoped");
    }


    public List<Message> getChatHistory() {
        return chatHistory;
    }
    public List<ClientThread> getActiveUsers() {
        return activeUsers;
    }
    public List<User> getAllUsers() {
        return allUsers;
    }
    public List<ClientThread> getAuthorizedUsers() {
        return authorizedUsers;
    }
    public void sendToActiveUsers(Message message) {
        synchronized (chatHistory) {
            chatHistory.add(message);
        }
        synchronized (authorizedUsers) {
            for (ClientThread client : authorizedUsers) {
                try {
                    client.send(message);
                } catch (IOException e) {
                    logger.logErrorMessage(
                            "User "
                            + client.getUser().getLogin() + " have a problem\n"
                            + e.getMessage()
                    );
                }
            }
        }
    }
    public void addAuthorizedUser(ClientThread client) {
        synchronized (authorizedUsers) {
            authorizedUsers.add(client);
        }
    }
    public void removeAuthorizedUser(ClientThread client) {
        synchronized (authorizedUsers) {
            authorizedUsers.remove(client);
        }
    }
    public User autorizeUser(String login, String pass) {
        synchronized (allUsers) {
            for (User user : allUsers) {
                if (user.authorize(login, pass))
                    return user;
            }
        }
        return null;
    }
    public boolean registerNewUser(User user) {
        /**
         * Простая валидация. Проверка на существование другого пользователя с
         * таким же логином
         */
        synchronized (allUsers) {
            for (User oldUser : allUsers) {
                if (oldUser.getLogin().equals(user.getLogin())) {
                    return false;
                }
            }

            allUsers.add(user);
            return true;
        }
    }

    public int getPort() {
        return port;
    }
    public void setLogger(Logger log) {
        logger = log;
    }
    public Logger getLogger() {
        return logger;
    }

}
