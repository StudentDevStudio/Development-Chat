package message;

import users.User;
/**
 * Данный месседж отправляется клиенту или клиентом 
 * для выполнения закрытия соединения. Если клиент или
 * сервер получил данное сообщение, то получатель
 * должен закрыть соединение и высвободить 
 * все занимаемые ресурсы.
 * 
 * @author Almaz
 *
 */
public class CloseConnectionMessage extends Message {
	private static final long serialVersionUID = 4634875225589236739L;

	public CloseConnectionMessage(User user) {
		super(user, "Close connection message");
	}

}
