package client;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import client.view.ChatView;

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;

/**
 * Этот класс отвечает за запуск клиента
 * Здесь возможен запуск клиентской части написанной 
 * Swing, AWT или на других технологиях.
 * 
 * @author Almaz
 */

public class Client {
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new AluminiumLookAndFeel());
        
        ChatView view = new ChatView();
        view.setVisible(true);
    }
}
