package server.socketServer;

import message.*;
import users.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 * Данный класс инкапсулирует данные и метода для работы с конретным пользователем.
 *
 * @author Almaz
 */
public class ClientThread implements Runnable {
    private SocketServer       server;
    private Socket             clientSocket;
    private User               user;
    private boolean            isAuthorized;
    private boolean            isAlive;
    private ObjectInputStream  inputStream;
    private ObjectOutputStream outputStream;

    public ClientThread(SocketServer server, Socket client) throws IOException {
        this.server = server;
        this.clientSocket = client;
        outputStream = new ObjectOutputStream(client.getOutputStream());
        inputStream = new ObjectInputStream(client.getInputStream());
        isAlive = true;
    }

    public void send(Message message) throws IOException {
        outputStream.writeObject(message);
        outputStream.flush();
    }
    public void send(List<Message> messages) throws IOException {
        for (Message message : messages) {
            send(message);
        }
    }

    public void run() {
        System.out.println("Client thread started");
        // Пока не выключили
        while (isAlive) {
            try {
                // Читаем Message из потока
                Message message = (Message) inputStream.readObject();

                /**
                 * TODO: По замыслу: при получении от клиента пинг-месседжа -
                 * 	сервер должен проверить на наличие новых сообщений, если есть
                 *  новые сообщения передать его клиенту! Если новых сообщений нет
                 *  пинг месседж просто игнорируется - в ответ клиенту отправляется
                 *  пустой пинг-месседж
                 */
                if ((message instanceof PingMessage)) {
                    send(new PingMessage(getUser()));

                    continue;
                }


                System.out.println("Get a message: " + message.getMessage());

                // Что за сообщение пришло?!
                if (message instanceof AuthorizationMessage) {
                    authorizeUser((AuthorizationMessage) message);
                } else if (message instanceof RegistrationMessage) {
                    registerNewUser((RegistrationMessage) message);
                } else if (message instanceof CloseConnectionMessage) {
                    closeConnection();
                } else {
                    if (isAuthorized) {
                        server.sendToActiveUsers(message);
                    }
                }
            } catch (ClassNotFoundException e) {
                server.getLogger().logErrorMessage(
                        "Client " + clientSocket.getInetAddress()
                        + " has a problem: " + e.getMessage()
                );
                try {
                    closeConnection();
                } catch (IOException e1) {
                    server.getLogger().logErrorMessage(
                            "Client " + clientSocket.getInetAddress()
                            + " has a problem: " + e.getMessage()
                    );
                }
            } catch (IOException e) {
                server.getLogger().logErrorMessage(
                        "Client " + clientSocket.getInetAddress()
                        + " has a problem: " + e.getMessage()
                )
                ;
                try {
                    closeConnection();
                } catch (IOException e1) {
                    server.getLogger().logErrorMessage(
                            "Client " + clientSocket.getInetAddress()
                            + " has a problem: " + e.getMessage()
                    );
                }
            }
        }
    }

    private void closeConnection() throws IOException {
        if (isAuthorized) {
            server.sendToActiveUsers(new UserDisconnected(user));
            server.removeAuthorizedUser(this);
            System.out.println(user.getLogin() + " disconnected");
        }
        isAlive = false;
        clientSocket.close();
    }

    private void authorizeUser(AuthorizationMessage message) throws IOException {
        User res = server.autorizeUser(message.getLogin(), message.getPass());
        if (res != null) {
            user = res;

            // Передаем клиенту - авторизационное сообщение
            send(new UserAuthorize(getUser()));

            // Инициализируемся
            initialize();
            System.out.println(user.getLogin() + " authorized");
        } else {
            // Отправляем сообщение о том что авторизация не удалась
            send(new ErrorMessage("Authorization failed"));
        }
    }

    private void registerNewUser(RegistrationMessage message) throws IOException {
        User user = new User(message.getLogin(), message.getPass());
        if (server.registerNewUser(user)) {
            this.user = user;
            // Передаем юзеру что регистрация успешно прошла
            send(new UserAuthorize(getUser()));

            initialize();
        } else {
            send(new ErrorMessage("Registration failed"));
        }
    }

    private void initialize() throws IOException {
        isAuthorized = true;


        // TODO: - разработать эффективный алгоритм передачи истории чата клиенту
        // Отправляем новичку историю чата -
        //send(server.getChatHistory());

        // И сообщаем всем клиентам, что подключился новый пользователь
        server.sendToActiveUsers(new UserAuthorize(user));

        // Добавляем к списку пользователей - нового пользователя
        server.addAuthorizedUser(this);
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

    public User getUser() {
        return user;
    }

}










































