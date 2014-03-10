package message;
/**
 * Этот месседж отправялется клиенту или клиентом
 * при возникновении ошибки. 
 * 
 * Этот же месседж может быть отправлен, к примеру если
 * авторизация не удалась и т.д.
 * 
 * @author Almaz
 */
public class ErrorMessage extends Message{
	private static final long serialVersionUID = 8682690389653315631L;

	public ErrorMessage(String message){
		super(message);
	}
}
