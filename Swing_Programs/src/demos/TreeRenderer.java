package demos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;

public class TreeRenderer extends JFrame {
	// The initial width and height of the frame
	static int WIDTH = 300;
	static int HEIGHT = 200;

	private static class CellRenderer implements TreeCellRenderer {

		private JLabel renderer;

		CellRenderer() {
			renderer = new JLabel();
			renderer.setOpaque(true);
		}

		public Component getTreeCellRendererComponent(JTree tree, Object value,
				boolean selected, boolean expanded, boolean leaf, int row,
				boolean hasFocus) {

			// Change background color based on selected state
			Color background = (selected ? Color.lightGray : Color.white);
			renderer.setBackground(background);

			if (leaf) {
				renderer.setText(value.toString());
			} else {
				renderer.setText("ROOT: " + value.toString());
			}
			return renderer;
		}
	}

	public TreeRenderer(String lab) {
		super(lab);

		// Create the tree nodes
		DefaultMutableTreeNode component = new DefaultMutableTreeNode(
				"Component");
		DefaultMutableTreeNode container = new DefaultMutableTreeNode(
				"Container");
		DefaultMutableTreeNode box = new DefaultMutableTreeNode("Box");
		DefaultMutableTreeNode jComponent = new DefaultMutableTreeNode(
				"JComponent");
		DefaultMutableTreeNode abstractButton = new DefaultMutableTreeNode(
				"AbstractButton");
		DefaultMutableTreeNode jButton = new DefaultMutableTreeNode("JButton");
		DefaultMutableTreeNode jMenuItem = new DefaultMutableTreeNode(
				"JMenuItem");
		DefaultMutableTreeNode jToggle = new DefaultMutableTreeNode(
				"JToggleButton");
		DefaultMutableTreeNode jLabel = new DefaultMutableTreeNode("JLabel");
		DefaultMutableTreeNode etc = new DefaultMutableTreeNode("...");

		// Group the nodes
		component.add(container);
		container.add(box);
		container.add(jComponent);
		jComponent.add(abstractButton);
		abstractButton.add(jButton);
		abstractButton.add(jMenuItem);
		abstractButton.add(jToggle);
		jComponent.add(jLabel);
		jComponent.add(etc);

		CellRenderer cell = new CellRenderer();
		TreePanel tp = new TreePanel(component, cell);

		Container content = getContentPane();
		content.add(tp, BorderLayout.CENTER);
	}

	public static void main(String args[]) {
		TreeRenderer frame = new TreeRenderer("Tree Example");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
	}
}
