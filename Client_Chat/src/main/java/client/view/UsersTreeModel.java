package client.view;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class UsersTreeModel extends DefaultTreeModel {
    private static final long serialVersionUID = 7356295658371697134L;
    private DefaultMutableTreeNode online;
    private DefaultMutableTreeNode offline;
    
    public UsersTreeModel(TreeNode root) {
        super(new DefaultMutableTreeNode("Users"));
        online = new DefaultMutableTreeNode("Online");
        offline = new DefaultMutableTreeNode("Offline");
        
        
        insertNodeInto(online, (MutableTreeNode) getRoot(), 0);
        insertNodeInto(offline, (MutableTreeNode) getRoot(), 1);
    }
}
