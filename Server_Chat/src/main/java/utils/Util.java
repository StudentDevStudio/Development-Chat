package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import message.Message;
import users.User;

/**
 * Это класс заглушка!
 * В дальнейщем функционал, реализуемый данным классом 
 * будет реализована в других классах.
 * 
 * В будущем этот класс может быть удален!
 * 
 * @author Almaz
 */
@Deprecated
public class Util {
	@SuppressWarnings("unchecked")
	/**
	 * Основной функционал - считать сериализованный лист юзеров из файла
	 * 
	 * @param file - откуда будут считываться данные
	 * @return Список всех сохранненых пользователей
	 * @throws IOException
	 */
	public static List<User> getAllUsers(File file) throws IOException {
		FileInputStream in = new FileInputStream(file);
		try (ObjectInputStream inputStream = new ObjectInputStream(in)){
			return (List<User>) inputStream.readObject();
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		} 
	}
	@SuppressWarnings("unchecked")
	/**
     * Основной функционал - считать сериализованный лист месседжев, которые были
     * залиты в файла
     * 
     * @param file - откуда будут считываться данные
     * @return Список сохранненых месседжев
     * @throws IOException
     */
	public static List<Message> getChatHistory(File file) throws IOException {
		FileInputStream in = new FileInputStream(file);
		try (ObjectInputStream inputStream = new ObjectInputStream(in)){
			return (List<Message>) inputStream.readObject();
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getMessage());
		}
	}
    /**
     * Сохранение списка юзеров в файл
     * Сериализация
     *  
     * @param file - куда сохранять, если файла нет - файл создатся
     * @param users - список для сохранения
     * @throws IOException
     */
	public static void saveAllUsers(File file, List<User> users) throws IOException{
		if(!file.exists())
			file.createNewFile();
		
		
		FileOutputStream fileOut = new FileOutputStream(file);
		try(ObjectOutputStream out = new ObjectOutputStream(fileOut)){
			out.writeObject(users);
			
			out.flush();
			out.close();
		}
	}
	/**
     * Сохранение списка месседжев в файл
     * Сериализация
     *  
     * @param file - куда сохранять, если файла нет - файл создатся
     * @param history - список для сохранения
     * @throws IOException
     */
	public static void saveChatHistory(File file, List<Message> history) throws IOException{
		if(!file.exists())
			file.createNewFile();
		
		
		FileOutputStream fileOut = new FileOutputStream(file);
		try(ObjectOutputStream out = new ObjectOutputStream(fileOut)){
			out.writeObject(history);
			
			out.flush();
			out.close();
		}
	}



}
