package message;

import users.User;

public class CloseConnectionMessage extends Message {
	private static final long serialVersionUID = 4634875225589236739L;

	public CloseConnectionMessage(User user) {
		super(user, "Close connection message");
	}

}
