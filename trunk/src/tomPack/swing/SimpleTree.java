package tomPack.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * @version 2009/11/05
 * @author Tom Brito
 */
public class SimpleTree extends JTree {

    private static final long serialVersionUID = 6953476658174073941L;

    public SimpleTree() {
	this((TreeNode)null);
    }

    public SimpleTree(TreeModel model) {
	this();
	setModel(model);
    }

    public SimpleTree(TreeNode root) {
	super(root);
	setRootVisible(false);
	setShowsRootHandles(true);
	initListeners();
    }
    
    private void initListeners() {
	addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		// the left button is automatic, but the right no
		if (SwingUtilities.isRightMouseButton(e)) {
		    int row = getClosestRowForLocation(e.getX(), e.getY());
		    setSelectionRow(row);
		}
		super.mouseClicked(e);
	    }
	});
    }

    //
    // Nodes
    //

    /**
     * Delegate and make a TreeNode cast from
     * {@link JTree#getLastSelectedPathComponent()}.
     */
    public TreeNode getSelectedNode() {
	return (TreeNode) getLastSelectedPathComponent();
    }

    /**
     * From {@link DefaultTreeModel}.
     * <p>
     * Builds the parents of node up to and including the root node, where the
     * original node is the last element in the returned array. The length of
     * the returned array gives the node's depth in the tree.
     * 
     * @param aNode
     *            the TreeNode to get the path for
     * @param depth
     *            an int giving the number of steps already taken towards the
     *            root (on recursive calls), used to size the returned array
     * @return an array of TreeNodes giving the path from the root to the
     *         specified node
     */
    protected TreeNode[] getPathToRoot(TreeNode aNode, int depth) {

	TreeNode[] retNodes;

	// This method recurses, traversing towards the root in order
	// size the array. On the way back, it fills in the nodes,
	// starting from the root and working back to the original node.

	/*
	 * Check for null, in case someone passed in a null node, or they passed
	 * in an element that isn't rooted at root.
	 */
	if (aNode == null) {
	    if (depth == 0)
		return null;
	    else
		retNodes = new TreeNode[depth];
	} else {
	    depth++;
	    if (aNode == getModel().getRoot())
		retNodes = new TreeNode[depth];
	    else
		retNodes = getPathToRoot(aNode.getParent(), depth);
	    retNodes[retNodes.length - depth] = aNode;
	}

	return retNodes;
    }

    /**
     * Go to the leaf node, expand its parent, and set the leaf node as the
     * selected node.
     * 
     * @param node
     */
    public void focusNode(TreeNode node) {

	// Need get the parent to expand
	TreePath parentPath = new TreePath(getPathToRoot(node.getParent(), 0));
	expandPath(parentPath); // JTree method

	// Select the leaf on the tree
	TreePath path = new TreePath(getPathToRoot(node, 0));
	setSelectionPath(path);
    }

    public void expandNodeParent(TreeNode node) {
	expandNode(node.getParent());
    }

    public void expandNode(TreeNode node) {
	TreePath path = new TreePath(getPathToRoot(node, 0));
	expandPath(path); // JTree method
    }

    //
    // Other methods
    //

}
