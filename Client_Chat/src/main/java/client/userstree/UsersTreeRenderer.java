package client.userstree;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

public class UsersTreeRenderer implements TreeCellRenderer {
	private static final String ONLINE = "online";
	private static final String OFFLINE = "offline";

	private ImageIcon online = new ImageIcon(getClass().getResource(
			"/images/icons/bullet_blue.png"));
	private ImageIcon offline = new ImageIcon(getClass().getResource(
			"/images/icons/bullet_black.png"));
	private ImageIcon offline_users = new ImageIcon(getClass().getResource(
           "/images/icons/offline-icon.png"));
	private ImageIcon online_users = new ImageIcon(getClass().getResource(
            "/images/icons/online-icon.png"));

	
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		JLabel label = new JLabel(node.getUserObject().toString());
		
		if(ONLINE.equals(node.toString().toLowerCase())){
		    label.setIcon(online_users);
		    return label;
		}
        if (OFFLINE.equals(node.toString().toLowerCase())) {
            label.setIcon(offline_users);
            return label;
        }

		if (node.getParent() != null) {
			if (ONLINE.equals(node.getParent().toString().toLowerCase())) {
				label.setIcon(online);
				return label;
			}
			if (OFFLINE.equals(node.getParent().toString().toLowerCase())) {
				label.setIcon(offline);
				return label;
			}
		}
		return label; 
	}
}
