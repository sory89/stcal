package com.stcal.fen;

import com.stcal.control.Datas;
import com.stcal.don.DCouple;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: val
 * Date: 13/03/14
 * Time: 22:53
 * To change this template use File | Settings | File Templates.
 */
class DynamicTree extends JPanel {
    protected DefaultMutableTreeNode rootNode;
    protected DefaultTreeModel treeModel;
    protected JTree tree;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();

    public DynamicTree() {
        super(new GridLayout(1, 0));

        rootNode = new DefaultMutableTreeNode("Liste des stages");
        treeModel = new DefaultTreeModel(rootNode);

        tree = new JTree(treeModel);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setEditable(false);
        tree.setRootVisible(true);
        tree.setShowsRootHandles(true);
        tree.setExpandsSelectedPaths(true);
        JScrollPane scrollPane = new JScrollPane(tree);
        add(scrollPane);
    }

    /** Remove all nodes except the root node. */
    public void clear() {
        rootNode.removeAllChildren();
        treeModel.reload();
    }

    DefaultMutableTreeNode getRootNode() {
        return rootNode;
    }

    /** Remove the currently selected node. */
    public void removeCurrentNode() {
        TreePath currentSelection = tree.getSelectionPath();
        if (currentSelection != null) {
            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection
                    .getLastPathComponent());
            MutableTreeNode parent = (MutableTreeNode) (currentNode.getParent());
            if (parent != null) {
                treeModel.removeNodeFromParent(currentNode);
            }
        }

        // Either there was no selection, or the root was selected.
        toolkit.beep();
    }

    /** Add child to the currently selected node. */
    public DefaultMutableTreeNode addObject(Object child) {
        DefaultMutableTreeNode parentNode = null;
        TreePath parentPath = tree.getSelectionPath();

        if (parentPath == null) {
            parentNode = rootNode;
        } else {
            parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
        }

        return addObject(parentNode, child, true);
    }

    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
                                            Object child) {
        return addObject(parent, child, false);
    }

    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
                                            Object child, boolean shouldBeVisible) {
        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

        if (parent == null) {
            parent = rootNode;
        }

        // It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
        treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

        // Make sure the user can see the lovely new node.
        if (shouldBeVisible) {
            tree.scrollPathToVisible(new TreePath(childNode.getPath()));
        }
        return childNode;
    }

    class MyTreeModelListener implements TreeModelListener {
        public void treeNodesChanged(TreeModelEvent e) {
            DefaultMutableTreeNode node;
            node = (DefaultMutableTreeNode) (e.getTreePath().getLastPathComponent());

      /*
       * If the event lists children, then the changed node is the child of the
       * node we've already gotten. Otherwise, the changed node and the
       * specified node are the same.
       */

            int index = e.getChildIndices()[0];
            node = (DefaultMutableTreeNode) (node.getChildAt(index));

            System.out.println("The user has finished editing the node.");
            System.out.println("New value: " + node.getUserObject());
        }

        public void treeNodesInserted(TreeModelEvent e) {
        }

        public void treeNodesRemoved(TreeModelEvent e) {
        }

        public void treeStructureChanged(TreeModelEvent e) {
        }
    }

    /* Deroule l'arbre */
    public void expandAll() {
        int row = 0;
        while (row < tree.getRowCount()) {
            tree.expandRow(row);
            row++;
        }
    }

    /* Gere la suppression des stage */
    public void removeDCoupleFromJtree(DefaultListModel<DCouple> listCpl)
    {
        if(((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).isLeaf()){
            DCouple cpl = (DCouple)((DefaultMutableTreeNode)tree.getLastSelectedPathComponent()).getUserObject();
            System.out.println("Suppression du stage en cours ...");
            if(listCpl.contains(cpl)){
                listCpl.removeElement(cpl);
                System.out.println("Stage supprimé.");
                Datas.etu.addElement(cpl.getEtu());
                if(((MutableTreeNode) tree.getLastSelectedPathComponent()).getParent().getChildCount()==1){
                    treeModel.removeNodeFromParent((MutableTreeNode)((MutableTreeNode) tree.getLastSelectedPathComponent()).getParent());
                }
                else{
                    treeModel.removeNodeFromParent((MutableTreeNode)tree.getLastSelectedPathComponent());
                }
                treeModel.reload();
                tree.setSelectionRow(0);
            }
            else{
                System.out.println("Suppression annulée : Stage non contenu dans les données.");
            }
        }
        else{
            System.out.println("Impossible de supprimer.");
        }
    }

    public void DraggedDCoupleFromJtree(DefaultListModel<DCouple> listCpl)
    {
        if(((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).isLeaf()){
            DCouple cpl = (DCouple)((DefaultMutableTreeNode)tree.getLastSelectedPathComponent()).getUserObject();
            System.out.println("Suppression du stage en cours ...");
            if(listCpl.contains(cpl)){
                listCpl.removeElement(cpl);
                System.out.println("Stage supprimé.");

                if(((MutableTreeNode) tree.getLastSelectedPathComponent()).getParent().getChildCount()==1){
                    treeModel.removeNodeFromParent((MutableTreeNode)((MutableTreeNode) tree.getLastSelectedPathComponent()).getParent());
                }
                else{
                    treeModel.removeNodeFromParent((MutableTreeNode)tree.getLastSelectedPathComponent());

                }
                treeModel.reload();
                tree.setSelectionRow(0);

            }
            else{
                System.out.println("Suppression annulée : Stage non contenu dans les données.");
            }
        }
        else{
            System.out.println("Impossible de supprimer.");
        }
    }


    public JTree getTree() {
        return tree;
    }
}
