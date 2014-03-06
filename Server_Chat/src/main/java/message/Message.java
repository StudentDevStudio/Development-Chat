package message;

import java.io.Serializable;
import java.util.Date;

import users.User;

/**
 * Данный класс предназначен для обмена данными
 * с клиентами
 *
 * UPD: В дальнейщем здесь появится fiel как список destination User's
 * Т.е. в мессадже будет указан список - кому доставить это сообщение
 * 
 * @author Almaz
 * https://vk.com/almaz_kg
 */
public class Message implements Serializable{
	private static final long serialVersionUID = -5853517790837772843L;
	private Date date;
	private User user;
	private String message;
	
	/**
	 * Мессадж для клиентов от самого сервера
	 * @param message
	 */
	public Message(String message){
		this.message = message;
		this.date = new Date();
	}
	
	public Message(User user){
		this.user = user;
		this.message = "";
		this.date = new Date();
	}
	public Message(User user, String message){
		this.user = user;
		this.message = message;
		this.date = new Date();
	}
	
	public User getUser() {
		return user;
	}
	public String getMessage() {
		return message;
	}
	public Date getDate() {
		return date;
	}

}
