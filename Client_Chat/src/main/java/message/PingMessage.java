package message;

import users.User;
/**
 * Пинг месседж предназначен для пингования сервера или клиента.
 * Этим месседжем выявляются отвисщие клиенты.
 * 
 * Так же при получении данного сообщения сервером от клиента
 * сервер должен проверить на наличие новых сообщений, если таковые 
 * имеются - сервер должен отправлять клиенту.
 * 
 * @author Almaz
 */
public class PingMessage extends Message {
    private static final long serialVersionUID = 8776410625476206053L;

    public PingMessage(User user) {
        super(user,"Ping message");
    }

}
