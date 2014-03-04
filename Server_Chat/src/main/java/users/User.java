package users;

import java.io.Serializable;

/**
 * TODO:
 * Серьезная уязвимость при сериализации pass
 * По хорошему этому полю не обходимо было бы указать
 * модификатор transient
 *
 * @author Almaz
 * https://vk.com/almaz_kg
 */
public class User implements Serializable {
	private static final long serialVersionUID = -7501038353222306092L;
	private String login;
	private String pass;
	
	public User(String login){
		this.login = login;
	}
	public User(String login, String pass){
		this.login = login;
		this.pass = pass;
	}

	public String getLogin() {
		return login;
	}
	public boolean authorize(String login, String pass){
		return this.login.equals(login) && this.pass.equals(pass);
	}
	
}
