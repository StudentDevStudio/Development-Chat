package client.dialogs;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;

/**
 * 
 * @author Илья Котов 
 * https://vk.com/ilya_kotov99
 */

public class About extends JDialog {
	private static final long serialVersionUID = -5843942439374341014L;
	private JLabel mainLabel;
	private JLabel devStudioLabel;
	private JLabel gitHubLabel;
	private JLabel secondLabel;
	
	public About(JFrame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		this.setTitle("About");
		this.setIconImage(new ImageIcon(getClass().getResource(
				"/images/icons/global_telecom.png")).getImage());
	}
	private void initComponents() {
		mainLabel = new JLabel();
		devStudioLabel = new JLabel();
		gitHubLabel = new JLabel();
		secondLabel = new JLabel();

		mainLabel.setText("Development  Chat");
		/* 
		 * Сейчас разрабатывается сайт этого общества, в котором будет подробно описана каждый проект
		 * В будущем ссылка этого Label'a измениться, и Label будет ссылаться на страницу описания этого
		 * проекта.
		 * 
		mainLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(
							new URI("https://www.development-studio.com/projects/development-chat.html"));
				} catch (URISyntaxException ex) {
					Logger.getLogger(AboutFrame.class.getName()).log(
							Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(AboutFrame.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
			public void mouseEntered(MouseEvent e) {
				mainLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		*/
		devStudioLabel.setForeground(new Color(102, 102, 255));
		devStudioLabel.setText("Development Studio");
		devStudioLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(
							new URI("https://vk.com/development_department"));
				} catch (URISyntaxException ex) {
					Logger.getLogger(About.class.getName()).log(
							Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(About.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
			public void mouseEntered(MouseEvent e) {
				devStudioLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		gitHubLabel.setForeground(new Color(102, 102, 255));
		gitHubLabel.setText("GitHub");
		gitHubLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(
							new URI("https://github.com/StudentDevStudio/Development-Chat"));
				} catch (URISyntaxException ex) {
					Logger.getLogger(About.class.getName()).log(
							Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(About.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
			public void mouseEntered(MouseEvent e) {
				gitHubLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		secondLabel.setText("Simple chat client");

	
		initLayouts();
		pack();
	}
		
	
	private void initLayouts(){
		GroupLayout layout = new GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup().addGap(111, 111, 111)
								.addComponent(gitHubLabel)
								.addContainerGap(123, Short.MAX_VALUE))
				.addGroup(
						layout.createSequentialGroup()
								.addGap(85, 85, 85)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		secondLabel)
																.addContainerGap())
												.addGroup(
														layout.createParallelGroup(
																GroupLayout.Alignment.LEADING)
																.addGroup(
																		layout.createSequentialGroup().addContainerGap())
																.addGroup(
																		layout.createSequentialGroup()
																				.addGroup(
																						layout.createParallelGroup(
																								GroupLayout.Alignment.LEADING)
																								.addComponent(
																										mainLabel)
																								.addComponent(
																										devStudioLabel,
																										GroupLayout.DEFAULT_SIZE,
																										171,
																										Short.MAX_VALUE))
																				.addContainerGap())))));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap(17, Short.MAX_VALUE)
								.addComponent(mainLabel)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(secondLabel)
								.addGap(24, 24, 24)
								.addComponent(devStudioLabel)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(gitHubLabel)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addGap(10, 10, 10)));
	}
}
