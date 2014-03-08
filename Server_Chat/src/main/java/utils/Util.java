package utils;

import message.Message;
import users.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Это класс заглушка! В дальнейщем функционал, реализуемый данным классом будет реализована в других классах.
 * <p/>
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
        ObjectInputStream inputStream = new ObjectInputStream(in);
        try {
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
        if (!file.exists())
            return new ArrayList<Message>();

        FileInputStream in = new FileInputStream(file);
        ObjectInputStream inputStream = new ObjectInputStream(in);
        try {
            return (List<Message>) inputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Сохранение списка юзеров в файл Сериализация
     *
     * @param file
     *         - куда сохранять, если файла нет - файл создатся
     * @param users
     *         - список для сохранения
     *
     * @throws IOException
     */
    public static void saveAllUsers(File file, List<User> users) throws IOException {
        if (!file.exists())
            file.createNewFile();


        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(users);

        out.flush();
        out.close();
    }

    /**
     * Сохранение списка месседжев в файл Сериализация
     *
     * @param file
     *         - куда сохранять, если файла нет - файл создатся
     * @param history
     *         - список для сохранения
     *
     * @throws IOException
     */
    public static void saveChatHistory(File file, List<Message> history) throws IOException {
        if (!file.exists())
            file.createNewFile();


        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(history);

        out.flush();
        out.close();
    }


}
