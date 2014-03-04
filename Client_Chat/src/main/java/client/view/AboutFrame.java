package client.view;

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
import javax.swing.JSeparator;
import javax.swing.LayoutStyle;

/**
 *
 * @author Илья Котов
 * https://vk.com/ilya_kotov99
 */

public class AboutFrame extends JDialog {
	private static final long serialVersionUID = 676025796012997264L;

	public AboutFrame(JFrame parent) {
        this.setLocationRelativeTo(parent);
		this.setResizable(false);
        this.setTitle("About");
        this.setIconImage(new ImageIcon(getClass().getResource("/images/about.png")).getImage());
        
        initComponents();

		jLabel3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(
							new URI("https://vk.com/ilya_kotov99"));
				} catch (URISyntaxException ex) {
					Logger.getLogger(AboutFrame.class.getName()).log(
							Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(AboutFrame.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
			public void mouseEntered(MouseEvent e) {
				jLabel3.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});

		jLabel4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(
							new URI("https://vk.com/almaz_kg"));
				} catch (URISyntaxException ex) {
					Logger.getLogger(AboutFrame.class.getName()).log(
							Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(AboutFrame.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
			public void mouseEntered(MouseEvent e) {
				jLabel4.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
        
        jLabel6.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(
							new URI("https://github.com/StudentDevStudio"));
				} catch (URISyntaxException ex) {
					Logger.getLogger(AboutFrame.class.getName()).log(
							Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(AboutFrame.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}

			public void mouseEntered(MouseEvent e) {
				jLabel6.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
        });
    }

    private void initComponents() {
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jSeparator1 = new JSeparator();
        jLabel6 = new JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setText("Development Chat");

        jLabel2.setText("Чат-клиент");

        jLabel3.setForeground(new java.awt.Color(102, 102, 255));
        jLabel3.setText("Илья Котов");

        jLabel4.setForeground(new java.awt.Color(102, 102, 255));
        jLabel4.setText("Almaz Мурзабеков");

        jLabel6.setForeground(new java.awt.Color(102, 102, 255));
        jLabel6.setText("StudentDevStudio, 2014");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel2))
                            .addComponent(jLabel1)
                            .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel6))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addComponent(jLabel4))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        pack();
    }

    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel6;
    private JSeparator jSeparator1;
}