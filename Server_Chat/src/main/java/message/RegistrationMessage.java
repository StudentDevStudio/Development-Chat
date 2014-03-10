package message;

/**
 * Экземпляр этого класса посылается серверу 
 * при регистрации нового пользователя
 * 
 * Здесь есть дыра в безопасности.
 * Если знаете как решить эту проблему - дайте знать :)
 *
 * @author Almaz
 */
public class RegistrationMessage extends Message {
	private static final long serialVersionUID = -4046750420541211695L;
	private String login;
	private String pass;
	
	public RegistrationMessage(String login, String pass){
		super(null, "Registration message");
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
