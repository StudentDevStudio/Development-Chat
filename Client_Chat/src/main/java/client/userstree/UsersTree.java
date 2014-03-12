package client.userstree;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class UsersTree extends JTree {
    private static final long serialVersionUID = 3174242270208177085L;

    public UsersTree(UsersTreeModel model){
        super(model);
        setRootVisible(false);
        UsersTreeRenderer renderer = new UsersTreeRenderer();
        this.setCellRenderer(renderer);
    }
    
    public void addOnlineUser(String userName){
    	 UsersTreeModel model = (UsersTreeModel) this.getModel();
         
         model.addOnlineUser(userName);
         expandRow(1);
    }
    public void deleteOnlineUser(String userName){
       UsersTreeModel model = (UsersTreeModel) this.getModel();
       
       model.deleteOnlineUser(userName);
       expandRow(1);
    }
    public void addOfflineUser(String userName){
    	UsersTreeModel model = (UsersTreeModel) this.getModel();
        
        model.addOfflineUser(userName);
        expandRow(2);
    }
    public void deleteOfflineUser(String userName){
    	UsersTreeModel model = (UsersTreeModel) this.getModel();
        
        model.deleteOfflineUser(userName);
        expandRow(2);
    }

	public void clear() {
		UsersTreeModel model = (UsersTreeModel) this.getModel();
        
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		
		DefaultMutableTreeNode online =  (DefaultMutableTreeNode) root.getChildAt(0);
		DefaultMutableTreeNode offline =  (DefaultMutableTreeNode) root.getChildAt(1);
		
		online.removeAllChildren();
        offline.removeAllChildren();
        
        model.reload(root);
	}
}
