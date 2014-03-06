package message;

import users.User;

public class PingMessage extends Message {
    private static final long serialVersionUID = 8776410625476206053L;

    public PingMessage(User user) {
        super(user,"Ping message");
    }

}
