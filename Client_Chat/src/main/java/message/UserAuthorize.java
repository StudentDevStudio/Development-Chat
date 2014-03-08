package message;

import users.User;

public class UserAuthorize extends Message {
	private static final long serialVersionUID = -8487546504487822739L;

	public UserAuthorize(User user) {
		super(user);
	}

}
