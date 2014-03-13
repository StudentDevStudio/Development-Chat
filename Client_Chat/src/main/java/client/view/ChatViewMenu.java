package client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class ChatViewMenu extends JMenuBar {
	private static final long serialVersionUID = 1626240052270249261L;

	private ChatView parent;
    private JMenu menuFile;
    private JMenu menuParams;
    private JMenu menuAbout;
    private JMenuItem connectMenuItem;
    private JMenuItem disconnectMenuItem;
    private JMenuItem exitMenuItem;
    private JMenuItem aboutMenuItem;
    private JMenuItem chatSettingsMenuItems;
    private JMenuItem sysSettingsMenuItem;
    
    public ChatViewMenu(ChatView parent){
    	this.parent = parent;
    	init();
    }

	private void init() {        
		menuFile = new JMenu("File");
		menuParams = new JMenu("Params");
		menuAbout = new JMenu("About");
		aboutMenuItem = new JMenuItem();
        connectMenuItem = new JMenuItem();
        disconnectMenuItem = new JMenuItem();
        chatSettingsMenuItems = new JMenuItem();
        sysSettingsMenuItem = new JMenuItem();
        exitMenuItem = new JMenuItem();	
        
        menuAbout.setText("About");
    	menuAbout.add(aboutMenuItem);
    	aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.aboutMenuItemClicked(e);
			}
		});
        
        menuParams.setText("Settings");
        chatSettingsMenuItems.setText("Chat Settings");
        menuParams.add(chatSettingsMenuItems);
        sysSettingsMenuItem.setText("System Settings");
        menuParams.add(sysSettingsMenuItem);
        menuParams.add(new JSeparator());
        
        menuFile.setText("File");
        connectMenuItem.setText("Connect");
        connectMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.connectMenuItemClicked(e);
            }
        });
        menuFile.add(connectMenuItem);
        disconnectMenuItem.setText("Disconnect");
        disconnectMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.disconnectMenuItemClicked(e);
            }
        });
        menuFile.add(disconnectMenuItem);
        menuFile.add(new JSeparator());
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.exitMenuItemClicked(e);
            }
        });
        menuFile.add(exitMenuItem);

        this.add(menuFile);
        this.add(menuParams);
        this.add(menuAbout);
        
	} 
	
}
