package message;

import users.User;
/**
 * Данный месседж отправляется сервером клиенту 
 * если авторизация успешна для самого клиента, т.е. при 
 * авторизации этого клиента или же
 * при появлении нового клиента данный месседж направляется
 * другим активным клиентам.
 * 
 * @author Almaz
 */
public class UserAuthorize extends Message {
	private static final long serialVersionUID = -8487546504487822739L;

	public UserAuthorize(User user) {
		super(user);
	}

}
