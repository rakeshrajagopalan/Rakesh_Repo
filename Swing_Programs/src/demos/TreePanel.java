package demos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;

public class TreePanel extends JPanel {
	public TreePanel(TreeNode root, TreeCellRenderer renderer) {
		// use a layout that will stretch tree to panel size
		setLayout(new BorderLayout());

		// Create tree
		JTree tree = new JTree(root);

		// Set line style
		tree.putClientProperty("JTree.lineStyle", "Angled");

		// Change the cell renderer
		tree.setCellRenderer(renderer);

		// Put tree in a scrollable pane
		JScrollPane sp = new JScrollPane(tree);

		add(sp, BorderLayout.CENTER);
	}
}