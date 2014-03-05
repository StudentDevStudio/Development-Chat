package message;

/**
 * Экземпляр этого класса посылается серверу 
 * при регистрации нового пользователя
 * 
 * Здесь есть дыра в безопасности.
 * Если знаете как решить эту проблему - дайте знать :)
 *
 * @author Almaz
 * https://vk.com/almaz_kg
 */
public class RegistrationMessage extends Message {
	private static final long serialVersionUID = -4046750420541211695L;
	private String login;
	private String pass;
	
	public RegistrationMessage(){
		super(null,  "");
	}
	public RegistrationMessage(String login, String pass){
		super(null, "");
	}
	
	
	public String getLogin() {
		return login;
	}

	public String getPass() {
		return pass;
	}
}
