package message;

/**
 * Этот месседж отправляется клиентом серверу 
 * при авторизации.
 * 
 * Сервер при получения данного сообщения должен
 * произвести авторизацию пользователя
 * 
 * @author Almaz
 */
public class AuthorizationMessage extends Message {
	private static final long serialVersionUID = 1932382607921498647L;
	private String login;
	private String pass;
	
	public AuthorizationMessage(String login, String pass) {
		super("Authorization");
		this.login = login;
		this.pass = pass;
	}

	public String getLogin() {
		return login;
	}
	public String getPass() {
		return pass;
	}
}
