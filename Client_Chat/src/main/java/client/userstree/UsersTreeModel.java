package client.userstree;

import java.util.Enumeration;

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
    
	public void deleteOnlineUser(String userName) {
		@SuppressWarnings("unchecked")
		Enumeration<DefaultMutableTreeNode> enumerat = online.children();
		while (enumerat.hasMoreElements()) {
			DefaultMutableTreeNode child = enumerat.nextElement();

			if (child.getUserObject().toString().equals(userName)) {
				online.remove(child);
				this.reload(online);
				return;
			}
		}
	}
    public void deleteOfflineUser(String userName){
        @SuppressWarnings("unchecked")
        Enumeration<DefaultMutableTreeNode> enumerat = offline.children();
        while(enumerat.hasMoreElements()){
            DefaultMutableTreeNode child = enumerat.nextElement();
           
            if(child.getUserObject().toString().equals(userName)){
            	offline.remove(child);
                this.reload(offline);
                return;
            }
        }
    }
	public void addOnlineUser(String userName){
		 online.add(new DefaultMutableTreeNode(userName));
	     this.reload(online);
	}
    public void addOfflineUser(String userName){
        offline.add(new DefaultMutableTreeNode(userName));
        this.reload(offline);
    }


    public DefaultMutableTreeNode getOnline() {
  		return online;
  	}
    public DefaultMutableTreeNode getOffline() {
  		return offline;
  	}
}
