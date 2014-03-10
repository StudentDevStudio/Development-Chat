package message;

import users.User;
/**
 * Данный месседж отправляется всем активным клиентам 
 * если какой-то клиент закрыл соединение
 * 
 * @author Almaz
 */
public class UserDisconnected extends Message {
	private static final long serialVersionUID = 9016941917277866541L;

	public UserDisconnected(User user) {
		super(user, " User disconnected message");
	}

}
