package client.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

/**
 * Этот класс реализует логику отображения и выбора смайлов. Причем, этот класс
 * должен предоставлять возможность "легкой" загрузки смайлов из хранилища
 * 
 * @author Almaz
 */
public class SmileChooser extends JDialog {
	private static final long serialVersionUID = -6996201191623977406L;
	private ChatView parent;
	private List<JButton> buttons;
	
	private boolean isActive;
	
	public SmileChooser(JFrame parent, boolean modal) {
		super(parent, modal);
		this.parent = (ChatView)parent;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		initComponents();
	}

	@Override
	public void setVisible(boolean b) {
		this.isActive = b;
		super.setVisible(b);
	}

	private void createElements(){
		jScrollPane1 = new JScrollPane();
		jPanel1 = new JPanel();
		jButton1 = new JButton();
		jButton2 = new JButton();
		jButton3 = new JButton();
		jButton4 = new JButton();
		jButton5 = new JButton();
		jButton6 = new JButton();
		jButton7 = new JButton();
		jButton8 = new JButton();
		jButton9 = new JButton();
		jButton12 = new JButton();
		jButton13 = new JButton();
		jButton14 = new JButton();
		jButton15 = new JButton();
		jButton16 = new JButton();
		jButton17 = new JButton();
		jButton18 = new JButton();
		jButton20 = new JButton();
		jButton21 = new JButton();
		jButton22 = new JButton();
		jButton23 = new JButton();
		jButton24 = new JButton();
		jButton19 = new JButton();
		jButton11 = new JButton();
		jButton10 = new JButton();
	}
	private void initComponents() {
		createElements();
		initLayout();
		jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.buttons = new ArrayList<JButton>();
		
		
		jButton1.setIcon(new ImageIcon(getClass().getResource("/images/icon_smile.gif")));
		jButton2.setIcon(new ImageIcon(getClass().getResource("/images/icon_wink.gif")));
		jButton3.setIcon(new ImageIcon(getClass().getResource("/images/icon_wave.gif")));
		jButton4.setIcon(new ImageIcon(getClass().getResource("/images/icon_biggrin.gif")));
		jButton5.setIcon(new ImageIcon(getClass().getResource("/images/icon_neutral.gif")));
		jButton6.setIcon(new ImageIcon(getClass().getResource("/images/icon_cheesygrin.gif")));
		jButton7.setIcon(new ImageIcon(getClass().getResource("/images/icon_clap.gif")));
		jButton8.setIcon(new ImageIcon(getClass().getResource("/images/icon_thumbup.gif")));
		jButton9.setIcon(new ImageIcon(getClass().getResource("/images/icon_lol.gif")));
		jButton10.setIcon(new ImageIcon(getClass().getResource("/images/icon_rolleyes.gif")));
		jButton11.setIcon(new ImageIcon(getClass().getResource("/images/icon_sad.gif")));
		jButton12.setIcon(new ImageIcon(getClass().getResource("/images/icon_surprised.gif")));
		jButton13.setIcon(new ImageIcon(getClass().getResource("/images/icon_think.gif")));
		jButton14.setIcon(new ImageIcon(getClass().getResource("/images/icon_wtf.gif")));
		jButton15.setIcon(new ImageIcon(getClass().getResource("/images/icon_confused.gif")));
		jButton16.setIcon(new ImageIcon(getClass().getResource("/images/icon_cool.gif")));
		jButton17.setIcon(new ImageIcon(getClass().getResource("/images/icon_crazy.gif")));
		jButton18.setIcon(new ImageIcon(getClass().getResource("/images/icon_cry.gif")));
		jButton19.setIcon(new ImageIcon(getClass().getResource("/images/icon_shh.gif")));
		jButton20.setIcon(new ImageIcon(getClass().getResource("/images/icon_eek.gif")));
		jButton21.setIcon(new ImageIcon(getClass().getResource("/images/icon_problem.gif")));
		jButton22.setIcon(new ImageIcon(getClass().getResource("/images/icon_question.gif")));
		jButton23.setIcon(new ImageIcon(getClass().getResource("/images/icon_razz.gif")));
		jButton24.setIcon(new ImageIcon(getClass().getResource("/images/icon_thumbdown.gif")));
		
		
		this.buttons.add(jButton1);
		this.buttons.add(jButton2);
		this.buttons.add(jButton3);
		this.buttons.add(jButton4);
		this.buttons.add(jButton5);
		this.buttons.add(jButton6);
		this.buttons.add(jButton7);
		this.buttons.add(jButton8);
		this.buttons.add(jButton9);
		this.buttons.add(jButton10);
		this.buttons.add(jButton11);
		this.buttons.add(jButton12);
		this.buttons.add(jButton13);
		this.buttons.add(jButton14);
		this.buttons.add(jButton15);
		this.buttons.add(jButton16);
		this.buttons.add(jButton17);
		this.buttons.add(jButton18);
		this.buttons.add(jButton19);
		this.buttons.add(jButton20);
		this.buttons.add(jButton21);
		this.buttons.add(jButton22);
		this.buttons.add(jButton23);
		this.buttons.add(jButton24);
		

		initButtons();
		pack();
	}

	@Override
	public void dispose() {
		this.isActive = false;
		super.dispose();
	}
	public boolean isActive() {
		return isActive;
	}
	

	private JButton jButton1;
	private JButton jButton9;
	private JButton jButton12;
	private JButton jButton13;
	private JButton jButton14;
	private JButton jButton15;
	private JButton jButton16;
	private JButton jButton17;
	private JButton jButton18;
	private JButton jButton2;
	private JButton jButton20;
	private JButton jButton21;
	private JButton jButton22;
	private JButton jButton23;
	private JButton jButton24;
	private JButton jButton19;
	private JButton jButton11;
	private JButton jButton10;
	private JButton jButton3;
	private JButton jButton4;
	private JButton jButton5;
	private JButton jButton6;
	private JButton jButton7;
	private JButton jButton8;
	private JPanel jPanel1;
	private JScrollPane jScrollPane1;
	

	private void setImageToParentField(Icon icon) {
		this.parent.getMainTextPane().insertIcon(icon);
	}

	private void initButtons(){
		for (final JButton button : this.buttons) {
			button.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent event) {
					if (event.getClickCount() == 2) {
						setImageToParentField(button.getIcon());
					}
				}

			});
		}
	}
	/**
	 * This is an auto-generated code!
	 */
	private void initLayout(){
		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jButton1,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton2,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton3,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton4,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton5,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton6,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton7,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton8,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jButton9,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton12,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton13,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton14,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton15,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton16,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton17,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton18,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jButton20,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton21,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton22,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton23,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton24,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton19,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton11,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton10,
																				GroupLayout.PREFERRED_SIZE,
																				26,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(62, Short.MAX_VALUE)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(jButton1)
														.addComponent(jButton2)
														.addComponent(jButton3)
														.addComponent(jButton4)
														.addComponent(jButton5)
														.addComponent(jButton6)
														.addComponent(jButton7)
														.addComponent(jButton8))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(jButton9)
														.addComponent(jButton12)
														.addComponent(jButton13)
														.addComponent(jButton14)
														.addComponent(jButton15)
														.addComponent(jButton16)
														.addComponent(jButton17)
														.addComponent(jButton18))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(jButton20)
														.addComponent(jButton21)
														.addComponent(jButton22)
														.addComponent(jButton23)
														.addComponent(jButton24)
														.addComponent(jButton19)
														.addComponent(jButton11)
														.addComponent(jButton10))
										.addContainerGap(24, Short.MAX_VALUE)));

		jScrollPane1.setViewportView(jPanel1);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(jScrollPane1,
				GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(jScrollPane1,
				GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE));


	}

}
