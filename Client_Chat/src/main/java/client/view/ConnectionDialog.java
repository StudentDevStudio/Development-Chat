package client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import message.AuthorizationMessage;
import client.model.ChatModel;

/**
 * Этот диалог предназначен для оформления подключения к серверу
 *
 * @author Almaz
 */
public class ConnectionDialog extends JDialog {
    private static final long serialVersionUID = 4320409399029665870L;
    private JButton registerButton;
    private JButton connectButton;
    private JLabel constHostLabel;
    private JLabel constPortLabel;
    private JLabel constLoginLabel;
    private JLabel constPasswordLabel;
    private JPanel paramPanel;
    private JPanel buttonsPanel;
    
    private ChatView parent;
    private JPasswordField passwordTextField;
    private JTextField hostTextField;
    private JTextField portTextField;
    private JTextField loginTextField;
    
    public ConnectionDialog(JFrame parent, boolean modal) {
        super(parent, modal);
        this.parent = (ChatView) parent;
        this.setTitle("Connection");
        this.setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.getRootPane().setDefaultButton(this.connectButton);
        initComponents();
    }          
    
    private void initComponents() {
        paramPanel = new JPanel();
        hostTextField = new JTextField();
        portTextField = new JTextField();
        loginTextField = new JTextField();
        passwordTextField = new JPasswordField();
        
        constHostLabel = new JLabel();
        constLoginLabel = new JLabel();
        constPortLabel = new JLabel();
        constPasswordLabel = new JLabel();
        buttonsPanel = new JPanel();
        registerButton = new JButton();
        connectButton = new JButton();
        paramPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Parameters"));
        buttonsPanel.setBorder(BorderFactory.createTitledBorder(""));

        constHostLabel.setText("Host");
        constPortLabel.setText("Port");
        constLoginLabel.setText("Login");
        constPasswordLabel.setText("Password");

        registerButton.setText("Sign up");
        registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerButtonClicked(e);
			}
		});

        connectButton.setText("Connect");
        connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connectButtonClicked(e);
			}
		});
        initLayouts();

        pack();
    }
    protected void connectButtonClicked(ActionEvent e) {
		try{
			String host = this.hostTextField.getText();
			int port = Integer.parseInt(this.portTextField.getText());
			
			// Получаем chat-model - коннектимся
			ChatModel model = ChatModel.connect(host, port);
			if(model != null){
				String login = this.loginTextField.getText();
				String pass = new String(this.passwordTextField.getPassword());
				
				// Готовим сообщение для отправки на сервер
				AuthorizationMessage message = new  AuthorizationMessage(login, pass);
				
				// Пытаемся авторизоваться
				if(model.authorize(message)){
					// Далее устанавливаем ChatModel - для ChatView'a
					this.parent.setChatModel(model);
					this.parent.setAuthorized(true);
					
					this.showInformationMessage("Connected");
					
					// Закрываем данный фрейм
					this.dispose();
				} else{
					this.showErrorMessage("Connection failed!");
				}
			}
		} catch(NumberFormatException exx){ 
			showErrorMessage("Invalid port number");
		} catch(IOException ex){
			showErrorMessage(ex.getMessage());
		}
	}

   

	protected void registerButtonClicked(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	protected void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
	protected void showInformationMessage(String message) {
		JOptionPane.showMessageDialog(this, message, "Information message",
				JOptionPane.PLAIN_MESSAGE);
	}
	



	/**
     * This is auto-generated code!
     */
    private void initLayouts(){

        GroupLayout jPanel1Layout = new GroupLayout(paramPanel);
        paramPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(constHostLabel)
                    .addComponent(constPortLabel)
                    .addComponent(constLoginLabel)
                    .addComponent(constPasswordLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(hostTextField)
                    .addComponent(portTextField)
                    .addComponent(loginTextField)
                    .addComponent(passwordTextField, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(hostTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(constHostLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(portTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(constPortLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(loginTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(constLoginLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(constPasswordLabel))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        

        
        GroupLayout jPanel2Layout = new GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(connectButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(registerButton)
                    .addComponent(connectButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(paramPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paramPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
    
    
  
}






































