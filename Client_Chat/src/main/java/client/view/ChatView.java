package client.view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;

import message.Message;
import client.dialogs.About;
import client.dialogs.ConnectionDialog;
import client.model.ChatModel;
import client.userstree.UsersTree;
import client.userstree.UsersTreeModel;

/**
 * Главный клиентский интерфейс
 * 
 * @author Almaz
 */
public class ChatView extends JFrame {
    private static final long serialVersionUID = -7279794261227376309L;
    private static final String TITLE = "Life chat";

    private UsersTree usersTree;
    private SmileChooser smileChooser;
	private ChatModel chatModel;
    private ChatViewMenu menu;
    
    private JRadioButton connectionStatus;
    private JButton sendButton;
    private JButton smileButton;
    private JButton attachButton;
    
    private JTextPane publishTextPane;
    private JTextPane mainTextPane;
    private JTextArea infoTextArea;

    
	public ChatView() {
        initComponents();
    
        this.infoTextArea.setText("Calendar or another text");
    }
    
    public void publishMessage(String message) {
		String text = this.publishTextPane.getText();
		this.publishTextPane.setText(text + "\n" + message);
	}

    private void initComponents() {
        createElements();
        this.setTitle(TITLE);
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icons/comments.png")).getImage());
        this.setResizable(true);
        this.setMinimumSize(new Dimension(600, 480));
        this.setLocation(250,150);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
        	 public void windowClosing(WindowEvent event) {
        		 exitMenuItemClicked(null);
             }
		});
        
        
        mainTextPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,2), "enter");
        mainTextPane.getActionMap().put("enter", new AbstractAction() {
        	private static final long serialVersionUID = -2615341909557131301L;

			public void actionPerformed(ActionEvent e) {
				sendButtonClicked(e);
           	}
        });

        connectionStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connectionStatusChanged(e);
            }
        });
        
        publishTextPane.setEditable(false);
        
        sendButton.setIcon(new ImageIcon(getClass().getResource("/images/icons/edit.png")));
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendButtonClicked(e);
            }
        });
        smileButton.setIcon(new ImageIcon(getClass().getResource("/images/icons/smiley_smile.png"))); 
        smileButton.setPreferredSize(new java.awt.Dimension(80, 23));
        smileButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				smileButtonClicked(e);
			}
		});
        attachButton.setIcon(new ImageIcon(getClass().getResource("/images/icons/folder_document.png")));
        attachButton.setPreferredSize(new java.awt.Dimension(80, 23));
        attachButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attachButtonClicked(e);
            }
        });
     
        JMenuBar bar = new JMenuBar();
        bar.add(new JMenu("Hello"));
        
        setJMenuBar(menu);
        
        initLayouts();
        pack();
    }
    
	/**
     * Этот метод реализует функцию отображения 
     * статуса подключения. Основная логика в этом
     * методе: работа с radioButton'ом.
     * 
     * @param e
     */
    protected void connectionStatusChanged(ActionEvent e) {
        if(!this.connectionStatus.isSelected()){
            if(this.chatModel == null)
                return;
			if (this.chatModel.isConnected()) {
				disconnectMenuItemClicked(e);
			}
        } else{
        	this.connectionStatus.setSelected(false); // Если это убрать - получится так, что даже если еще не подключились, радиобаттон будет отображать статус: Подключено
        
        	this.connectMenuItemClicked(e);
        	if(this.chatModel != null && this.chatModel.isAuthorized()){
        		this.connectionStatus.setSelected(true);
        		this.chatModel.setConnected(true);
        	}
        }
    }
    protected void disconnectMenuItemClicked(ActionEvent e) {
        if(this.chatModel != null && this.chatModel.isConnected()){
            try {
                this.chatModel.close();
                this.connectionStatus.setSelected(false);
                
                this.showInformationMessage("Connection closed");
                this.usersTree.clear();
                this.setTitle(TITLE);
            } catch (IOException ex) {
                this.showErrorMessage(ex.getMessage());
            }
        }else{
            this.showInformationMessage("You have not active connection");
        }
    }
    
    protected void sendButtonClicked(ActionEvent e) {
    	try {
			if(this.chatModel != null && this.chatModel.isConnected()){
				if (this.chatModel.isAuthorized()) {
					String message = this.mainTextPane.getText();
					this.chatModel.sendMessage(new Message(chatModel.getUser(), message));
					this.mainTextPane.setText("");
				} else {
					this.showErrorMessage("You are not authorized");
				}
			} else{
				this.showErrorMessage("You are not connected");
			}
		} catch (IOException ex) {
			this.showErrorMessage(ex.getMessage());
		}
    }

	protected void attachButtonClicked(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        
        if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            this.showInformationMessage("File ready to send");
        }
        
    }
    protected void exitMenuItemClicked(ActionEvent e) {
        int reply = JOptionPane.showConfirmDialog(this, "Do you really want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.NO_OPTION)
        	return;
        
    	if(this.chatModel != null && this.chatModel.isConnected())
			try {
				this.chatModel.close();
			} catch (IOException ex) {
				this.showErrorMessage(ex.getMessage());
			}
        this.dispose();
    }
    protected void smileButtonClicked(ActionEvent e) {
    	Point pos = this.getLocationOnScreen();
		smileChooser.setLocation(pos.x + 15, pos.y + 290);
    	
		if(this.smileChooser.isActive()){
    		this.smileChooser.setVisible(false);
    	} else{
    		this.smileChooser.setVisible(true);
    	}
	}
   
	protected void connectMenuItemClicked(ActionEvent e) {
        ConnectionDialog con = new ConnectionDialog(this, true);
        con.setVisible(true);
    }
    protected void aboutMenuItemClicked(ActionEvent e){
    	About frame = new About(this, true);
    	Point pos = this.getLocationOnScreen();
    	pos.x += 200;
    	pos.y += 160;
    	
    	frame.setLocation(pos);
		frame.setVisible(true);
    }
    
    public void setChatModel(ChatModel model) {
		this.chatModel = model;
	}
	
    public void showErrorMessage(String message){
        JOptionPane.showMessageDialog(this, message,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
    public void showInformationMessage(String message){
        JOptionPane.showMessageDialog(this, message,
                "Information message",
                JOptionPane.PLAIN_MESSAGE);
    }
   
    private void createElements(){
        smileButton = new JButton();
        attachButton = new JButton();
        sendButton = new JButton();
        publishTextPane = new JTextPane();
        publishTextPane.setText("Life chat");
        
        mainTextPane = new JTextPane();
        infoTextArea = new JTextArea();
        connectionStatus = new JRadioButton();
        
        smileChooser = new SmileChooser(this, false);
        
        usersTree = new UsersTree(new UsersTreeModel(new DefaultMutableTreeNode("Users")));
        menu = new ChatViewMenu(this);
    }
    public void insertIconToTextPane(Icon icon) {
       /* TODO: В данном методе есть бага: Нельзя вставить 2 и более одинаковые смайлики последовательно
		*	Т.е. нельзя вставить вот такую конструкцию:  
		*	
		*	Текст... Smile_1 Smile_1 ... текст
		*	
		*	В результате будет отображено 
		*	
		*	Текст... Smile_1 ... текст
    	*/
    	this.mainTextPane.insertIcon(icon);
    	
    	
    }
    public UsersTree getMainTree() {
        return usersTree;
    }

    /**
     * This is auto-generated code
     */
    private void initLayouts(){
    	JPanel jPanel1 = new JPanel();
		JPanel jPanel2 = new JPanel();
		JPanel jPanel3 = new JPanel();    
	    JPanel connectionPane = new JPanel();   
	    
	    JLabel constConnectionStatusLabel = new JLabel("Connection status: ");
	    
	    JScrollPane jScrollPane1 = new JScrollPane();
	    JScrollPane jScrollPane2 = new JScrollPane();
	    JScrollPane jScrollPane3 = new JScrollPane();
	    JScrollPane jScrollPane4 = new JScrollPane();
	    
	    jScrollPane1.setViewportView(publishTextPane);
        jScrollPane2.setViewportView(usersTree);
        jScrollPane3.setViewportView(mainTextPane);
        jScrollPane4.setViewportView(infoTextArea);
        
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(smileButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(attachButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup() 
            .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 361, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 8, GroupLayout.DEFAULT_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(smileButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(attachButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3)
                    .addComponent(sendButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        GroupLayout jPanel4Layout = new GroupLayout(connectionPane);
        connectionPane.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(constConnectionStatusLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connectionStatus))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(connectionStatus)
                    .addComponent(constConnectionStatusLabel))
                .addContainerGap())
        );
        
        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(connectionPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(connectionPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }  
}






























