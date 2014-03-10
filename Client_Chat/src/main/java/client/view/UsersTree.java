package client.view;

import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class UsersTree extends JTree {
    private static final long serialVersionUID = 3174242270208177085L;

    public UsersTree(DefaultTreeModel model){
        super(model);
    }
    
    public void addOnlineUser(String userName){
        Object o =  this.getModel().getChild(this.getModel().getRoot(), 0);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)o;
        
        node.add(new DefaultMutableTreeNode(userName));
        revalidate();
    }
    public void deleteOnlineUser(String userName){
        Object o =  this.getModel().getChild(this.getModel().getRoot(), 0);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)o;
        
        @SuppressWarnings("unchecked")
        Enumeration<DefaultMutableTreeNode> enumerat = node.children();
        while(enumerat.hasMoreElements()){
            DefaultMutableTreeNode child = enumerat.nextElement();
            System.out.println(child.getUserObject().toString());
            
            if(child.getUserObject().toString().equals(userName)){
                node.remove(child);
                this.revalidate();
                return;
            }
        }
    }
    public void addOfflineUser(String userName){
        Object o =  this.getModel().getChild(this.getModel().getRoot(), 1);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)o;
        
        node.add(new DefaultMutableTreeNode(userName));
        revalidate();
    }
    public void deleteOfflineUser(String userName){
        Object o =  this.getModel().getChild(this.getModel().getRoot(), 1);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)o;
        
        
        @SuppressWarnings("unchecked")
        Enumeration<DefaultMutableTreeNode> enumerat = node.children();
        while(enumerat.hasMoreElements()){
            DefaultMutableTreeNode child = enumerat.nextElement();
            System.out.println(child.getUserObject().toString());
            
            if(child.getUserObject().toString().equals(userName)){
                node.remove(child);
                this.revalidate();
                return;
            }
        }
    }
}
