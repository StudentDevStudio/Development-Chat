package client.userstree;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class UsersTree extends JTree {
    private static final long serialVersionUID = 3174242270208177085L;
    private UsersTreeModel model;
    
    public UsersTree(UsersTreeModel model){
        super(model);
        this.model = model;
        setRootVisible(false);
        this.setCellRenderer(new UsersTreeRenderer());
    }
    
    public void addOnlineUser(String userName){
    	 model.addOnlineUser(userName);
         expandAll();
    }
    public void deleteOnlineUser(String userName){
       model.deleteOnlineUser(userName);
       expandAll();
    }
    public void addOfflineUser(String userName){
    	model.addOfflineUser(userName);
        expandAll();
    }
    public void deleteOfflineUser(String userName){
    	model.deleteOfflineUser(userName);
        expandAll();
    }
    
    private void expandAll(){
    	TreePath offline = new TreePath(model.getOffline().getPath());
        expandPath(offline);
        
        TreePath online = new TreePath(model.getOnline().getPath());
        expandPath(online);
        
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
