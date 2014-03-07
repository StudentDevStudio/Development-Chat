package client.view;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Илья Котов
 */


/**
 *  UPD:
 *  Пока не прикручен к главному интерфейсу
 *
 * @author Almaz
 * https://vk.com/almaz_kg
 */
public class JTreeManipulator {

    public void addActiveUser(JTree tree, String userName) {   

        Object src = tree.getLastSelectedPathComponent(); //Найти выделенный элемент в JTree
        
        
        
        DefaultTreeModel model = (DefaultTreeModel)tree.getModel(); //Получение модели JTree
        
        if (src != null) {
            DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)src;
            if(treeNode.isRoot()) {
                DefaultMutableTreeNode tmp = new DefaultMutableTreeNode(userName);
                model.insertNodeInto(tmp, treeNode, treeNode.getChildCount());
            }
        }
    }
    
    public void deleteUser(JTree tree) {
        DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
        Object src = tree.getLastSelectedPathComponent();
        if(src!=null) {
            DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)src;
            if(!treeNode.isRoot()) {
                if(treeNode.getChildCount()==0)
                    model.removeNodeFromParent(treeNode);
            }
        }
    }
    
}
