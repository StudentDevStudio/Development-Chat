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
    
    public void deleteOnlineUser(String userName){
    	 Object o =  this.getChild(this.getRoot(), 0);
         DefaultMutableTreeNode node = (DefaultMutableTreeNode)o;
         
         System.out.println(node.getUserObject());
         
         
         @SuppressWarnings("unchecked")
         Enumeration<DefaultMutableTreeNode> enumerat = node.children();
         while(enumerat.hasMoreElements()){
             DefaultMutableTreeNode child = enumerat.nextElement();
             System.out.println(child.getUserObject().toString());
             
             if(child.getUserObject().toString().equals(userName)){
                 node.remove(child);
                 this.reload(node);
                 return;
             }
         }
    }
    public void deleteOfflineUser(String userName){
        Object o =  this.getChild(this.getRoot(), 1);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)o;
        
        
        @SuppressWarnings("unchecked")
        Enumeration<DefaultMutableTreeNode> enumerat = node.children();
        while(enumerat.hasMoreElements()){
            DefaultMutableTreeNode child = enumerat.nextElement();
            System.out.println(child.getUserObject().toString());
            
            if(child.getUserObject().toString().equals(userName)){
                node.remove(child);
                this.reload(node);
                return;
            }
        }
    }
	public void addOnlineUser(String userName){
		 Object o = this.getChild(this.getRoot(), 0);
	     DefaultMutableTreeNode node = (DefaultMutableTreeNode)o;
	     
	     node.add(new DefaultMutableTreeNode(userName));
	}
    public void addOfflineUser(String userName){
        Object o =  this.getChild(this.getRoot(), 1);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)o;
        
        node.add(new DefaultMutableTreeNode(userName));
    }
}
