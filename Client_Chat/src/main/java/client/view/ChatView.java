package client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

/**
 * Главный клиентский интерфейс
 * 
 * @author Almaz
 */
public class ChatView extends JFrame {
    private static final long serialVersionUID = -7279794261227376309L;
    private JRadioButton connectionStatus;
    private JLabel constConnectionStatusLabel;
    private JButton sendButton;
    private JButton smileButton;
    private JButton attachButton;
    private JMenu menuFile;
    private JMenu menuParams;
    private JMenuBar mainMenuBar;
    private JMenuItem connectMenuItem;
    private JMenuItem disconnectMenuItem;
    private JMenuItem exitMenuItem;
    private JMenuItem infoMenuItem;
    private JMenuItem chatSettingsMenuItems;
    private JMenuItem sysSettingsMenuItem;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane4;
    private JTextArea mainTextArea;
    private JTextArea infoTextArea;
    private JTextPane mainTextPane;
    private JTree mainTree;         
    
    public ChatView() {
        initComponents();
        
        this.mainTextArea.setText("[Almaz say:]  Hello world!");
        this.mainTextArea.setText(this.mainTextArea.getText() + "\n[Robot say:]  Hi, Almaz!");
        
        this.infoTextArea.setText("Calendar and another informations");
    }
    private void initComponents() {
        initElements();
        this.setTitle("Life chat");
        this.setIconImage(new ImageIcon(getClass().getResource("/images/comments.png")).getImage());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        connectionStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connectionStatusChanged(e);
            }
        });
        connectionStatus.setText("Disconnected");
        constConnectionStatusLabel.setText("Connection: ");
        
        mainTextArea.setEditable(false);
        mainTextArea.setColumns(20);
        mainTextArea.setRows(5);

        sendButton.setIcon(new ImageIcon(getClass().getResource("/images/edit.png")));
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendButtonClicked(e);
            }
        });
        smileButton.setIcon(new ImageIcon(getClass().getResource("/images/smiley_smile.png"))); 
        smileButton.setPreferredSize(new java.awt.Dimension(80, 23));

        attachButton.setIcon(new ImageIcon(getClass().getResource("/images/folder_document.png")));
        attachButton.setPreferredSize(new java.awt.Dimension(80, 23));
        attachButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attachButtonClicked(e);
            }
        });
        
        jScrollPane3.setViewportView(mainTextPane);
        jScrollPane2.setViewportView(mainTree);
        jScrollPane4.setViewportView(infoTextArea);
        jScrollPane1.setViewportView(mainTextArea);
        
        this.mainTextPane.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    showInformationMessage("Enter pressed");
                }
            }
        });
        
        
        initMenuFile();
        initMenuParams();
        setJMenuBar(mainMenuBar);
        
        initLayouts();
        pack();
    }
    
    protected void connectionStatusChanged(ActionEvent e) {
        if(this.connectionStatus.isSelected()){
            this.connectionStatus.setText("Disconnected");
            this.connectionStatus.setSelected(false);
        } else{
            this.connectionStatus.setText("Connected");
            this.connectionStatus.setSelected(true);
        }
    }
    protected void sendButtonClicked(ActionEvent e) {
        this.showErrorMessage("Send button clicked");
    }
    protected void attachButtonClicked(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        
        if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            this.showInformationMessage("File ready to send");
        }
        
    }
    @Deprecated
    protected void exitMenuItemClicked(ActionEvent e) {
        int reply = JOptionPane.showConfirmDialog(this, "Realy exit?", "Exit", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION){
            /* TODO: 
             * 
             * Не забудь потом закрыть все открытые соединения.
             * Сохранить изменения и т.д.
             * 
            */
            this.dispose();
        }
    }
    
    protected void disconnectMenuItemClicked(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
    protected void connectMenuItemClicked(ActionEvent e) {
        ConnectionDialog con = new ConnectionDialog(this, true);
        con.setVisible(true);
        
    }
    
    
    protected void showErrorMessage(String message){
        JOptionPane.showMessageDialog(this, message,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
    protected void showInformationMessage(String message){
        JOptionPane.showMessageDialog(this, message,
                "Information message",
                JOptionPane.PLAIN_MESSAGE);
    }
    
    private void initMenuParams() {
        menuParams.setText("Settings");

        chatSettingsMenuItems.setText("Chat Settings");
        menuParams.add(chatSettingsMenuItems);

        sysSettingsMenuItem.setText("System Settings");
        menuParams.add(sysSettingsMenuItem);
        menuParams.add(new JSeparator());
        mainMenuBar.add(menuParams);
        infoMenuItem.setText("About");
        menuParams.add(infoMenuItem);
    }
    private void initMenuFile() {
        menuFile.setText("File");
        
        connectMenuItem.setText("Connect");
        connectMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connectMenuItemClicked(e);
            }
        });
        menuFile.add(connectMenuItem);

        disconnectMenuItem.setText("Disconnect");
        disconnectMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                disconnectMenuItemClicked(e);
            }
        });
        menuFile.add(disconnectMenuItem);
        menuFile.add(new JSeparator());

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitMenuItemClicked(e);
            }
        });
        menuFile.add(exitMenuItem);

        mainMenuBar.add(menuFile);
    }
  
  
    private void initElements(){
        smileButton = new JButton();
        attachButton = new JButton();
        sendButton = new JButton();
        mainTextArea = new JTextArea();
        mainTextPane = new JTextPane();
        infoTextArea = new JTextArea();
        connectionStatus = new JRadioButton();
        constConnectionStatusLabel = new JLabel();
        
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();
        jPanel4 = new JPanel();
        
        jScrollPane1 = new JScrollPane();
        jScrollPane2 = new JScrollPane();
        jScrollPane3 = new JScrollPane();
        jScrollPane4 = new JScrollPane();
        mainTree = new JTree();
        
        mainMenuBar = new JMenuBar();
        infoMenuItem = new JMenuItem();
        connectMenuItem = new JMenuItem();
        disconnectMenuItem = new JMenuItem();
        chatSettingsMenuItems = new JMenuItem();
        sysSettingsMenuItem = new JMenuItem();
        exitMenuItem = new JMenuItem();
        
        menuFile = new JMenu();
        menuParams = new JMenu();
    }
    /**
     * This is auto-generated code
     */

    private void initLayouts(){
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(smileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(attachButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(smileButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(attachButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3)
                    .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(constConnectionStatusLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connectionStatus))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addComponent(jPanel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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






























