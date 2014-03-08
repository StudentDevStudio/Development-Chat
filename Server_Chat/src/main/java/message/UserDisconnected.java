package message;

import users.User;

public class UserDisconnected extends Message {
	private static final long serialVersionUID = 9016941917277866541L;

	public UserDisconnected(User user) {
		super(user, " User disconnected message");
	}

}
