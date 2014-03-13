package client.dialogs;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
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

import message.Message;
import message.RegistrationMessage;
import message.UserAuthorize;
import users.User;
import client.model.ChatModel;
import client.view.ChatView;

/**
 * Диалог отвечающий за регистрацию нового пользователя
 *
 * @author Almaz
 */
public class RegistrationDialog extends JDialog {
	private static final long serialVersionUID = -8366523917557569174L;
	
	private ChatView parent;
	private JButton registerButton;
	private JLabel constLoginLabel;
	private JLabel constPassLabel;
	private JLabel constVerifyLabel;
	private JLabel constHostLabel;
	private JLabel constPortLabel;
	
	
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPasswordField passField;
	private JPasswordField verufyField;
	private JTextField loginField;
	private JTextField hostField;
	private JTextField portField;
	
	public RegistrationDialog(JFrame parent, boolean modal) {
        super(parent, modal);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        
        this.parent = (ChatView) parent;
        this.setTitle("Registration");
        
        Point pos = this.parent.getLocationOnScreen();
        pos.x += 250;
        pos.y += 120;
        this.setLocation(pos);
        
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icons/administrator.png")).getImage());
        this.getRootPane().setDefaultButton(registerButton);
        
        this.registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerButtonClicked(e);
			}
		});
    }

    protected void registerButtonClicked(ActionEvent e) {
		if(!this.loginField.getText().isEmpty()){
			if(verifyPass() && comparePass()){
				register();
			} else
				this.showErrorMessage("Passwords do not match!");	
		} else {
			this.showErrorMessage("Please, enter login");
		}
	}    
		
	private void register() {
		try {
			String host = this.hostField.getText();
			int port = Integer.parseInt(this.portField.getText());
			
			// Получаем chat-model - коннектимся
			ChatModel model = ChatModel.connect(host, port, parent);
			if(model != null){
			    String login = this.loginField.getText(); 
			    String pass  = new String(this.passField.getPassword()); 
				
			    RegistrationMessage regMess = new RegistrationMessage(login, pass);
				
				model.sendMessage(regMess);
				Message answ = model.getResponce();
				
				// Если сервер ответил так - то регистрация успешна
				if(answ instanceof UserAuthorize){
				    // Авторизация успешна
                    // Далее устанавливаем ChatModel - для ChatView'a
                    model.setAuthorized(true);
                    model.setConnected(true);
                    model.setUser(new User(login, pass));
                    this.parent.setChatModel(model);
                    this.showInformationMessage("Registration succesful");
                    model.startServerListening();
				    
					String title = this.parent.getTitle();
					this.parent.setTitle(title + ": " + model.getUser().getLogin());
					
					// Закрываем данное диалогое окно
					this.dispose();
				} else
					this.showErrorMessage(answ.getMessage());
			} else{
				this.showErrorMessage("Connection failed!");
			}
        } catch (IOException e) {
            this.showErrorMessage(e.getMessage());
        } catch (NumberFormatException e) {
            this.showErrorMessage(e.getMessage());
        } catch (ClassNotFoundException e) {
            this.showErrorMessage(e.getMessage());
        }
	}

	// Простая валидация пароля - в дальнейщем этот метод может быть переписан
	private boolean verifyPass() {
		return this.passField.getPassword().length > 0;
	}

	protected void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
	protected void showInformationMessage(String message) {
		JOptionPane.showMessageDialog(this, message, "Information message",
				JOptionPane.PLAIN_MESSAGE);
	}
	

	private boolean comparePass() {
		char[] pass = this.passField.getPassword();
		char[] ver = this.verufyField.getPassword();
		
		return Arrays.equals(pass, ver);
	}
    
    
	private void initComponents() {
		constLoginLabel = new JLabel();
		constPassLabel = new JLabel();
		constVerifyLabel = new JLabel();
		constHostLabel = new JLabel();
		constPortLabel = new JLabel();
		
		hostField = new JTextField();  
		portField = new JTextField();	
		loginField = new JTextField();
        passField = new JPasswordField();
        verufyField = new JPasswordField();
        
        registerButton = new JButton();
 
		jPanel1 = new JPanel();
        jPanel2 = new JPanel();

        jPanel1.setBorder(BorderFactory.createTitledBorder("Parameters"));

        constLoginLabel.setText("Login");
        constPassLabel.setText("Password");
        constVerifyLabel.setText("Verify");
        constHostLabel.setText("Host");
        constPortLabel.setText("Port");

        initLayouts();
        pack();
    }
     
     /**
      * This auto-generated code!
      */
    private void initLayouts(){
    	 GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
         jPanel1.setLayout(jPanel1Layout);
         jPanel1Layout.setHorizontalGroup(
             jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
             .addGroup(jPanel1Layout.createSequentialGroup()
                 .addContainerGap()
                 .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                	 .addComponent(constHostLabel)	 
                	 .addComponent(constPortLabel)	 
                     .addComponent(constLoginLabel)
                     .addComponent(constPassLabel)
                     .addComponent(constVerifyLabel))
                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                 .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                     .addComponent(verufyField)
                     .addComponent(passField)
                     .addComponent(loginField)
                     .addComponent(portField)
                     .addComponent(hostField, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                 .addContainerGap())
         );
         jPanel1Layout.setVerticalGroup(
             jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
             .addGroup(jPanel1Layout.createSequentialGroup()
                 .addContainerGap()
                 .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                     .addComponent(constHostLabel)
                     .addComponent(hostField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                 .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                 .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                     .addComponent(constPortLabel)
                     .addComponent(portField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                 .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                 .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                     .addComponent(constLoginLabel)
                     .addComponent(loginField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                 .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                 .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                     .addComponent(constPassLabel)
                     .addComponent(passField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                 .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                 .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                     .addComponent(constVerifyLabel)
                     .addComponent(verufyField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                 .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
         );

         jPanel2.setBorder(BorderFactory.createTitledBorder(""));

         registerButton.setText("Register");

         GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
         jPanel2.setLayout(jPanel2Layout);
         jPanel2Layout.setHorizontalGroup(
             jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
             .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                 .addContainerGap(206, Short.MAX_VALUE)
                 .addComponent(registerButton)
                 .addContainerGap())
         );
         jPanel2Layout.setVerticalGroup(
             jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
             .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                 .addContainerGap(15, Short.MAX_VALUE)
                 .addComponent(registerButton)
                 .addContainerGap())
         );

         GroupLayout layout = new GroupLayout(getContentPane());
         getContentPane().setLayout(layout);
         layout.setHorizontalGroup(
             layout.createParallelGroup(GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addContainerGap()
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                     .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                     .addComponent(jPanel2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                 .addContainerGap())
         );
         layout.setVerticalGroup(
             layout.createParallelGroup(GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addContainerGap()
                 .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                 .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                 .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                 .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
         );
     }

}
